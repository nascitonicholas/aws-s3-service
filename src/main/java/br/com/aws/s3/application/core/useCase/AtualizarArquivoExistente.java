package br.com.aws.s3.application.core.useCase;

import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.core.domain.PropertiesConfig;
import br.com.aws.s3.application.ports.in.AtualizarArquivoExistentePortIn;
import br.com.aws.s3.application.ports.out.AmazonS3PortOut;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class AtualizarArquivoExistente implements AtualizarArquivoExistentePortIn {

    private final String ATRIBUICAO = "=";
    private final String FINAL_LINHA = ";";
    private final AmazonS3PortOut amazonS3PortOut;

    public AtualizarArquivoExistente(AmazonS3PortOut amazonS3PortOut) {
        this.amazonS3PortOut = amazonS3PortOut;
    }

    @Override
    public void atualizarArquivoExistente(PropertiesConfig properties, List<Properties> file) {
        properties.getList().parallelStream().forEach(item ->
                file.forEach(f -> {
                        if(f.getPropertieName().equalsIgnoreCase(item.getPropertieName())) {
                            f.setValue(item.getValue());
                        }
                })
        );
        String arquivoAtualizado = gerarArquivo(file);
        amazonS3PortOut.uploadNovoArquivo(arquivoAtualizado.getBytes());
    }

    private String gerarArquivo(List<Properties> file) {
        AtomicReference<String> newFile = null;
        file.forEach(f-> newFile.set(newFile.get() + f.getPropertieName() + ATRIBUICAO + f.getValue() + FINAL_LINHA));
        return newFile.get();
    }
}

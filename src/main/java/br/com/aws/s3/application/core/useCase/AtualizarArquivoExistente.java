package br.com.aws.s3.application.core.useCase;

import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.core.domain.PropertiesConfig;
import br.com.aws.s3.application.ports.in.AtualizarArquivoExistentePortIn;
import br.com.aws.s3.application.ports.out.AmazonS3PortOut;

import java.util.List;
import java.util.Optional;

public class AtualizarArquivoExistente implements AtualizarArquivoExistentePortIn {

    private final AmazonS3PortOut amazonS3PortOut;

    public AtualizarArquivoExistente(AmazonS3PortOut amazonS3PortOut) {
        this.amazonS3PortOut = amazonS3PortOut;
    }

    @Override
    public void atualizarArquivoExistente(PropertiesConfig propertiesRequest, List<Properties> fileProperties) {
        propertiesRequest.getList().parallelStream().forEach(item -> {
                Optional<Properties> p = fileProperties.stream().filter(f -> item.getPropertieName().equalsIgnoreCase(f.getPropertieName())).findFirst();
                if(p.isPresent()) {
                    fileProperties.stream()
                            .filter(f -> item.getPropertieName().equalsIgnoreCase(f.getPropertieName()))
                            .forEach(f -> f.setValue(item.getValue()));
                } else {
                    fileProperties.add(new Properties(item.getPropertieName(), item.getValue()));
                }
        });
        amazonS3PortOut.uploadNovoArquivo(propertiesRequest.getBucketName(), propertiesRequest.getFileName(), fileProperties);
    }
}

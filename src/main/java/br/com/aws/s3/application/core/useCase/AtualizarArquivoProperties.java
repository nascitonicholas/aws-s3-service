package br.com.aws.s3.application.core.useCase;

import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.core.domain.PropertiesConfig;
import br.com.aws.s3.application.ports.in.AtualizarArquivoExistentePortIn;
import br.com.aws.s3.application.ports.in.AtualizarArquivoPortIn;
import br.com.aws.s3.application.ports.in.BuscarArquivoPortIn;
import br.com.aws.s3.application.ports.in.CriarNovoArquivoPortIn;

import java.util.List;
import java.util.Objects;

public class AtualizarArquivoProperties implements AtualizarArquivoPortIn {

    private final BuscarArquivoPortIn buscarArquivoPortIn;
    private final CriarNovoArquivoPortIn criarNovoArquivoPortIn;
    private final AtualizarArquivoExistentePortIn atualizarArquivoExistentePortIn;

    public AtualizarArquivoProperties(BuscarArquivoPortIn buscarArquivoPortIn,
                                      CriarNovoArquivoPortIn criarNovoArquivoPortIn,
                                      AtualizarArquivoExistentePortIn atualizarArquivoExistentePortIn) {
        this.buscarArquivoPortIn = buscarArquivoPortIn;
        this.criarNovoArquivoPortIn = criarNovoArquivoPortIn;
        this.atualizarArquivoExistentePortIn = atualizarArquivoExistentePortIn;
    }

    @Override
    public Void atualizarArquivoProperties(PropertiesConfig propertiesRequest) {
        List<Properties> file = buscarArquivoPortIn.buscaArquivoPorNome(propertiesRequest.getBucketName(), propertiesRequest.getFileName());
        if(Objects.nonNull(file) && !file.isEmpty()) {
            atualizarArquivoExistentePortIn.atualizarArquivoExistente(propertiesRequest, file);
            return null;
        }
        criarNovoArquivoPortIn.criaNovoArquivo(propertiesRequest);
        return null;
    }

}

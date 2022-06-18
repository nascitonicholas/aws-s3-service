package br.com.aws.s3.application.ports.in;

import br.com.aws.s3.application.core.domain.PropertiesConfig;

public interface CriarNovoArquivoPortIn {
    void criaNovoArquivo(PropertiesConfig properties);
}

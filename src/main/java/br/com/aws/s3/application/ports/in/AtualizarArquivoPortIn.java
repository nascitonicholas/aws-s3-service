package br.com.aws.s3.application.ports.in;

import br.com.aws.s3.application.core.domain.PropertiesConfig;

public interface AtualizarArquivoPortIn {

    Void atualizarArquivoProperties(PropertiesConfig properties);

}

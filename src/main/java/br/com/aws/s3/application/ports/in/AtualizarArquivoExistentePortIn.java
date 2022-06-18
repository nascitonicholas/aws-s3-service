package br.com.aws.s3.application.ports.in;

import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.core.domain.PropertiesConfig;

import java.util.List;

public interface AtualizarArquivoExistentePortIn {
    void atualizarArquivoExistente(PropertiesConfig properties, List<Properties> file);
}

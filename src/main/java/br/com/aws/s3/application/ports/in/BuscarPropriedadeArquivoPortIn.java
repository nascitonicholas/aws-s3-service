package br.com.aws.s3.application.ports.in;

import br.com.aws.s3.application.core.domain.Properties;

public interface BuscarPropriedadeArquivoPortIn {
    Properties buscarParametroArquivo(String bucketName, String fileName, String propertieName);
}

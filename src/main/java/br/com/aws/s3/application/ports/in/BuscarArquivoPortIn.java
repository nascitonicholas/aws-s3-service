package br.com.aws.s3.application.ports.in;

import br.com.aws.s3.application.core.domain.Properties;

import java.util.List;

public interface BuscarArquivoPortIn {
    List<Properties> buscaArquivoPorNome(String bucketName, String fileName);
}

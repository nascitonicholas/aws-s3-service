package br.com.aws.s3.application.ports.out;

import br.com.aws.s3.application.core.domain.Properties;

import java.util.List;

public interface AmazonS3PortOut {
    List<Properties> buscaArquivoPorNome(String bucketName, String fileName);
    void uploadNovoArquivo(byte[] bytes);
}

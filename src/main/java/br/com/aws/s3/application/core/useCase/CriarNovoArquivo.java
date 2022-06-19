package br.com.aws.s3.application.core.useCase;

import br.com.aws.s3.application.core.domain.PropertiesConfig;
import br.com.aws.s3.application.ports.in.CriarNovoArquivoPortIn;
import br.com.aws.s3.application.ports.out.AmazonS3PortOut;

public class CriarNovoArquivo implements CriarNovoArquivoPortIn {

    private final AmazonS3PortOut amazonS3PortOut;

    public CriarNovoArquivo(AmazonS3PortOut amazonS3PortOut) {
        this.amazonS3PortOut = amazonS3PortOut;
    }

    @Override
    public void criaNovoArquivo(PropertiesConfig properties) {
        amazonS3PortOut.uploadNovoArquivo(properties.getBucketName(), properties.getFileName(), properties.getList());
    }
}

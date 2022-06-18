package br.com.aws.s3.application.core.useCase;

import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.ports.in.BuscarArquivoPortIn;
import br.com.aws.s3.application.ports.out.AmazonS3PortOut;

import java.util.List;

public class BuscarArquivo implements BuscarArquivoPortIn {

    private final AmazonS3PortOut amazonS3PortOut;

    public BuscarArquivo(AmazonS3PortOut amazonS3PortOut) {
        this.amazonS3PortOut = amazonS3PortOut;
    }

    @Override
    public List<Properties> buscaArquivoPorNome(String bucketName, String fileName) {
        return amazonS3PortOut.buscaArquivoPorNome(bucketName, fileName);
    }

}

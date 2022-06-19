package br.com.aws.s3.application.core.useCase;

import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.ports.in.BuscarArquivoPortIn;
import br.com.aws.s3.application.ports.in.BuscarPropriedadeArquivoPortIn;

import java.util.List;
import java.util.Optional;

public class BuscarPropriedadeArquivo implements BuscarPropriedadeArquivoPortIn {

    private final BuscarArquivoPortIn buscarArquivoPortIn;

    public BuscarPropriedadeArquivo(BuscarArquivoPortIn buscarArquivoPortIn) {
        this.buscarArquivoPortIn = buscarArquivoPortIn;
    }

    @Override
    public Properties buscarParametroArquivo(String bucketName, String fileName, String propertieName) {
        List<Properties> propertiesList = buscarArquivoPortIn.buscaArquivoPorNome(bucketName, fileName);
        Optional<Properties> propertie = propertiesList.stream().filter(p -> propertieName.equalsIgnoreCase(p.getPropertieName())).findFirst();
        if(propertie.isPresent()) return propertie.get();
        return new Properties();
    }
}

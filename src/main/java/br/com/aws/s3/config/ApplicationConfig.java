package br.com.aws.s3.config;

import br.com.aws.s3.application.core.useCase.*;
import br.com.aws.s3.application.ports.in.AtualizarArquivoExistentePortIn;
import br.com.aws.s3.application.ports.in.BuscarArquivoPortIn;
import br.com.aws.s3.application.ports.in.CriarNovoArquivoPortIn;
import br.com.aws.s3.application.ports.out.AmazonS3PortOut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public AtualizarArquivoProperties atualizarArquivoProperties(BuscarArquivoPortIn buscarArquivoPortIn,
                                                                 CriarNovoArquivoPortIn criarNovoArquivoPortIn,
                                                                 AtualizarArquivoExistentePortIn atualizarArquivoExistentePortIn) {
        return new AtualizarArquivoProperties(buscarArquivoPortIn, criarNovoArquivoPortIn, atualizarArquivoExistentePortIn);
    }

    @Bean
    public BuscarArquivo buscaArquivoPorNome(AmazonS3PortOut amazonS3PortOut) {
        return new BuscarArquivo(amazonS3PortOut);
    }

    @Bean
    public CriarNovoArquivo criaNovoArquivo(AmazonS3PortOut amazonS3PortOut) {
        return new CriarNovoArquivo(amazonS3PortOut);
    }

    @Bean
    public AtualizarArquivoExistente atualizarArquivoExistente(AmazonS3PortOut amazonS3PortOut) {
        return new AtualizarArquivoExistente(amazonS3PortOut);
    }

    @Bean
    public BuscarPropriedadeArquivo buscarParametroArquivo(BuscarArquivoPortIn buscarArquivoPortIn) {
        return new BuscarPropriedadeArquivo(buscarArquivoPortIn);
    }

}

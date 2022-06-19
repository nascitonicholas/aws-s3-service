package br.com.aws.s3.application.adapters.outbound;

import br.com.aws.s3.application.adapters.outbound.domain.S3Uri;
import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.ports.out.AmazonS3PortOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.client.config.ClientOverrideConfiguration;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.net.URI;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@Component
public class AmazonS3 implements AmazonS3PortOut {

    private final String ATRIBUICAO = "=";
    private final String FINAL_LINHA = ";";
    private final String VAZIO = "";
    private static S3Client connection;

    @Override
    public List<Properties> buscaArquivoPorNome(String bucketName, String fileName) {
        try{
            URI uri = URI.create("s3://" + bucketName + criaNomeArquivoComExtensao(fileName));
            S3Uri s3Uri = S3Uri.from(uri);
            ResponseBytes<GetObjectResponse> response = connection().getObjectAsBytes(GetObjectRequest.builder()
                    .bucket(s3Uri.getBucket())
                    .key(s3Uri.getKey())
                    .build());
            String objectResponse = new String(response.asByteArray());
            log.info("Response object: {}", objectResponse);
            return tratarRetorno(objectResponse);
        } catch (NoSuchKeyException e) {
            return new ArrayList<>();
        }
    }

    private List<Properties> tratarRetorno(String objectResponse) {
        List<Properties> propertiesList = new ArrayList<>();
        String[] properties = objectResponse.split(";");
        Arrays.stream(properties).forEach(p -> {
            ArrayList<String> propertie = new ArrayList<>(Arrays.asList(p.split("=")));
            propertiesList.add(new Properties(propertie.get(0), propertie.get(1)));
        });
        return propertiesList;
    }

    @Override
    public void uploadNovoArquivo(String bucketName, String fileName, List<Properties> file) {
        try {
            URI uri = URI.create("S3://" + bucketName + criaNomeArquivoComExtensao(fileName));
            S3Uri s3Uri = S3Uri.from(uri);
            connection().putObject(PutObjectRequest.builder()
                    .bucket(s3Uri.getBucket())
                    .key(s3Uri.getKey())
                    .acl(ObjectCannedACL.PRIVATE)
                    .build(), RequestBody.fromBytes(gerarArquivo(file).getBytes()));
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException("", e);
        }
    }

    private synchronized S3Client connect() {
        try {
            return S3Client.builder()
                    .overrideConfiguration(ClientOverrideConfiguration.builder()
                            .apiCallTimeout(Duration.ofMillis(3000))
                            .apiCallAttemptTimeout(Duration.ofMillis(3000))
                            .build())
                    .region(Region.SA_EAST_1)
                    .endpointOverride(new URI("http://host.docker.internal:4566"))
                    .credentialsProvider(StaticCredentialsProvider.create(AwsBasicCredentials.create("xxx", "xxx")))
                    .build();
        } catch (Exception ex) {
            throw new RuntimeException("", ex);
        }
    }

    private S3Client connection() {
        if(isConnected()) {
            return connection;
        }
        return connect();
    }

    private boolean isConnected() { return connection != null; }

    private String criaNomeArquivoComExtensao(String fileName) { return "/" + fileName + ".properties"; }

    private String gerarArquivo(List<Properties> file) {
        AtomicReference<String> newFile = new AtomicReference<>();
        file.forEach(f-> {
            String updatedPropertie = f.getPropertieName() + ATRIBUICAO + f.getValue() + FINAL_LINHA;
            newFile.set(Objects.nonNull(newFile.get())? newFile.get() + updatedPropertie: VAZIO + updatedPropertie);
        });
        return newFile.get();
    }
}

package br.com.aws.s3.application.adapters.outbound.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.URI;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class S3Uri {

    private final String bucket;
    private final String key;

    public static S3Uri from(URI uri) {
        if(Objects.nonNull(uri) && isS3Uri(uri) && !uri.getHost().isBlank() && !uri.getPath().isBlank()) {
            return new S3Uri(uri.getHost(), removePathPrefix(uri.getPath()));
        } else {
            throw new RuntimeException(uri.toString());
        }
    }

    private static String removePathPrefix(String path) { return path.replaceFirst("/", "");}

    private static boolean isS3Uri(URI uri) { return "s3".equalsIgnoreCase(uri.getScheme());}
}

package br.com.aws.s3.application.adapters.inbound.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("lista-parametros")
    private List<Properties> list = new ArrayList<>();

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Properties {
        @JsonProperty("propertie-name")
        private String propertieName;
        @JsonProperty("propertie-value")
        private String value;
    }

}

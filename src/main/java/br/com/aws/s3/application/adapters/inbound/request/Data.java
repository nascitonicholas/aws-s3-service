package br.com.aws.s3.application.adapters.inbound.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    @JsonProperty("lista-parametros")
    private List<Properties> list;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class Properties {
        @JsonProperty("propertie-name")
        private String propertie;
        @JsonProperty("propertie-value")
        private String value;
    }

}

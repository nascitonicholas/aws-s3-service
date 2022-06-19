package br.com.aws.s3.application.adapters.inbound.mapper;

import br.com.aws.s3.application.adapters.inbound.request.Data;
import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.core.domain.PropertiesConfig;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface DataMapper {
    //@Mapping(target = "list", source = "data.list")
    PropertiesConfig dataMapToPropertiesConfig(String bucketName, String fileName, Data data);
    Properties dataPropertiesMapToProperties(Data.Properties properties);
}

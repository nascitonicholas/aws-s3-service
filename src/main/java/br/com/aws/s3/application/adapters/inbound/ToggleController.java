package br.com.aws.s3.application.adapters.inbound;

import br.com.aws.s3.application.adapters.inbound.mapper.DataMapper;
import br.com.aws.s3.application.adapters.inbound.request.Data;
import br.com.aws.s3.application.core.domain.Properties;
import br.com.aws.s3.application.core.useCase.BuscarPropriedadeArquivo;
import br.com.aws.s3.application.ports.in.AtualizarArquivoPortIn;
import br.com.aws.s3.application.ports.in.BuscarArquivoPortIn;
import lombok.AllArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/toggle-properties")
public class ToggleController {

    private final AtualizarArquivoPortIn atualizarArquivoPortIn;
    private final BuscarArquivoPortIn buscarArquivo;
    private final BuscarPropriedadeArquivo buscarPropriedadeArquivo;
    private final DataMapper mapper = Mappers.getMapper(DataMapper.class);

    @PutMapping("/{bucketName}/{fileName}")
    public ResponseEntity<?> putPropertiesFileS3(@PathVariable(value = "bucketName") String bucketName,
                                                  @PathVariable(value = "fileName") String fileName,
                                                  @RequestBody Data request) {
        atualizarArquivoPortIn.atualizarArquivoProperties(mapper.dataMapToPropertiesConfig(bucketName, fileName, request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{bucketName}/{fileName}/{propertieName}")
    public ResponseEntity<?> getPropertieS3(@PathVariable(value = "bucketName") String bucketName,
                                                  @PathVariable(value = "fileName") String fileName,
                                                  @PathVariable(value = "propertieName") String propertieName) {
        Properties properties = buscarPropriedadeArquivo.buscarParametroArquivo(bucketName, fileName, propertieName);
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/{bucketName}/{fileName}")
    public ResponseEntity<?> getFilePropertiesS3(@PathVariable(value = "bucketName") String bucketName,
                                                  @PathVariable(value = "fileName") String fileName) {
        List<Properties> properties = buscarArquivo.buscaArquivoPorNome(bucketName, fileName);
        return ResponseEntity.ok(properties);
    }

}

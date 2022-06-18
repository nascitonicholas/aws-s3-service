package br.com.aws.s3.application.adapters.inbound;

import br.com.aws.s3.application.adapters.inbound.request.Data;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController("/toggle-properties")
public class ToggleController {

    @PutMapping("/{bucketName}/{fileName}")
    public ResponseEntity<?> postPropertiesFileS3(@PathVariable("bucketName") String bucketName,
                                                  @PathVariable("fileName") String fileName,
                                                  @RequestBody Data request) {

        return null;
    }

    @GetMapping("/{bucketName}/{fileName}/{propertieName}")
    public ResponseEntity<?> postPropertiesFileS3(@PathVariable("bucketName") String bucketName,
                                                  @PathVariable("fileName") String fileName,
                                                  @PathVariable("propertieName") String propertieName) {
        return null;
    }

    @GetMapping("/{bucketName}/{fileName}")
    public ResponseEntity<?> postPropertiesFileS3(@PathVariable("bucketName") String bucketName,
                                                  @PathVariable("fileName") String fileName) {
        return null;
    }

}

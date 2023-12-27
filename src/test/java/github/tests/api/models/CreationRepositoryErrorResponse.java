package github.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreationRepositoryErrorResponse {

    private String message;
    private List<CreationRepositoryError> errors;
    private String documentationUrl;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CreationRepositoryError {

        private String resource;
        private String code;
        private String field;
        private String message;
    }
}
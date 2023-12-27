package github.tests.api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatchRepositoryRequest {

    private String name;
    private String description;
    @JsonProperty("private")
    private Boolean isPrivate;
}
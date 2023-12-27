package github.tests.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CreationRepositoryRequest {

    private String name;
    private String description;
    @JsonProperty("homepage")
    private String homePage;
    @JsonProperty("auto_init")
    private Boolean autoInit;
}
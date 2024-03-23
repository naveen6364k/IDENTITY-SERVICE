package com.innoclique.identity.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FetchUser {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String emailAddress;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String securityGroup;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String locationName;
}

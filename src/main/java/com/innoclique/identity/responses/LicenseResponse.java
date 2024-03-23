package com.innoclique.identity.responses;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.innoclique.identity.DTO.LicenseValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LicenseResponse {
    private StatusResponse statusResponse;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<LicenseValidation> userDetailsList;

}

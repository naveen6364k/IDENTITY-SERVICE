package com.innoclique.identity.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.innoclique.identity.DTO.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class EditLocationDetailsResponse {
    private StatusResponse statusResponse;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Location location;
}

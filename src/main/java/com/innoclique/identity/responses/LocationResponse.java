package com.innoclique.identity.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.innoclique.identity.DTO.Location;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class LocationResponse {
    private StatusResponse statusResponse;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer totalRecords;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Location> locationDetails;
}

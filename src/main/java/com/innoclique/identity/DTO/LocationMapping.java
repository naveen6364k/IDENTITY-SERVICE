package com.innoclique.identity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LocationMapping {
    private DefaultLocation defaultLocation;
    private List<AllowedLocation> allowedLocations;
}

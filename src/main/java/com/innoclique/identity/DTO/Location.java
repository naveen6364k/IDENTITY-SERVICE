package com.innoclique.identity.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Location {
    private Integer locationId;
    private String code;
    private Integer providerId;
    private String address;
    private String locationName;
    private String city;
    private String state;
    private String zipCode;
    private int isActive;


}


package com.innoclique.identity.DTO;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class License {

    private int providerId;
    private Date visitDate;
    private String locationName;
    private int patientId;
    private String licenseKey;
}

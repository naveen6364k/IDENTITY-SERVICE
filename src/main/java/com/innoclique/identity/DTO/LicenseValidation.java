package com.innoclique.identity.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class LicenseValidation {
    @Id
    private int patientId;
    private int patientLocationId;
    private String firstName;
    private String lastName;
    private int locationId;
    private int userId;
    private String email;
    private String phone;

    public LicenseValidation() {

    }
}

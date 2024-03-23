package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "PatientLocationMapping")
@Getter
@Setter
public class PatientLocationMappingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLID")
    private int plId;

    @Column(name = "PatientID")
    private Integer patientId;

    @Column(name = "LocationID")
    private Integer locationId;

    @Column(name = "ProviderID")
    private Integer providerId;

    @Column(name = "DefaultFlag")
    private boolean defaultFlag;

    @Column(name = "LicenseKeyEntity")
    private String licenseKey;

    @Column(name = "VisitDate")
    private Date visitDate;


}
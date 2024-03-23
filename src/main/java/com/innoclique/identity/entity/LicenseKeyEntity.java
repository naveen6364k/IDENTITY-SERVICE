package com.innoclique.identity.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "licensekeys")
@Getter
@Setter
public class LicenseKeyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KeyID")
    private int keyID;

    @Column(name = "KeyValue", nullable = false)
    private String keyValue;

    @Column(name = "ProductName", nullable = false)
    private String productName;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "ExpiryDate")
    private Date expiryDate;

    @Column(name = "IsValid")
    private boolean isValid;

    @Column(name="patientId")
    private int patientId;

    @Column(name="providerId")
    private int providerId;
}
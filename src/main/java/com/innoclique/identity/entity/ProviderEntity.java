package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name = "Provider")
@Getter
@Setter
public class ProviderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProviderID")
    private int providerId;

    @Column(name = "ProviderCode")
    private String providerCode;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "ClientID")
    private int clientId;

    @Column(name = "Specialty")
    private String specialty;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Email")
    private String email;

    @Column(name = "IsActive")
    private boolean isActive = true;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;


 //   @OneToMany(mappedBy = "provider")
//    private List<ProviderLocationMappingEntity> providerLocationMappingEntities;

}

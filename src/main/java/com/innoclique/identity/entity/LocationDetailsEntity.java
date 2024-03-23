package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Location")
@Getter
@Setter
public class LocationDetailsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LocationID")
    private int locationId;

    @Column(name = "ClientID")
    private Integer clientId;

    @Column(name = "ProviderID")
    private Integer providerId;

    @Column(name = "Code")
    private String code;

    @Column(name = "LocationName")
    private String locationName;

    @Column(name = "Address")
    private String address;

    @Column(name = "City")
    private String city;

    @Column(name = "State")
    private String state;

    @Column(name = "ZipCode")
    private String zipCode;

    @Column(name = "Phone")
    private String phone;

    @Column(name = "Email")
    private String email;

    @Column(name = "IsActive")
    private int isActive;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @OneToMany(mappedBy = "location")
    private List<UserLocationEntity> userLocationEntities;

    //@OneToMany(mappedBy = "location")
    //private List<ProviderLocationMappingEntity> providerLocationMappingEntities;

}
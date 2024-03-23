package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserLocation")
@Getter
@Setter
public class UserLocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    private UserEntity user;

    @Column(name = "ClientID")
    private Integer clientId;

    @ManyToOne
    @JoinColumn(name = "LocationID", referencedColumnName = "LocationID")
    private LocationDetailsEntity location;

    @Column(name = "DefaultFlag")
    private int defaultFlag;

    @Column(name = "IsActive")
    private int isActive;
}

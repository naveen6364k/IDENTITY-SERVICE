package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "StateMaster")
public class StateMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StateID")
    private Integer stateID;

    @Column(name = "StateName")
    private String stateName;

    @Column(name = "StateCode")
    private String stateCode;

    @Column(name = "IsActive")
    private int isActive;


}
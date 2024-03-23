package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "MasterSource")
@Getter
@Setter
public class AlertConfigEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MasterSourceID")
    private int masterSourceID;

    @Column(name = "Type")
    private String type;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "Description1")
    private String description1;

    @Column(name = "DescriptionValue")
    private int descriptionValue;

    @Column(name = "ParentID")
    private Integer parentID;

    @Column(name = "ChildExist")
    private Integer childExist;

    @Column(name = "OrderID")
    private int orderID;

    @Column(name = "Level")
    private Integer level;

    @Column(name = "IsActive")
    private boolean isActive;

    @Column(name = "CreatedAt")
    private Timestamp createdAt;
}
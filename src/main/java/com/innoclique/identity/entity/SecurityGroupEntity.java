package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SecurityGroup")
@Getter
@Setter
public class SecurityGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SecurityGroupID")
    private int securityGroupId;

    @Column(name = "GroupName")
    private String groupName;

    @Column(name = "Description")
    private String description;

    @Column(name = "IsActive")
    private Integer isActive;
}

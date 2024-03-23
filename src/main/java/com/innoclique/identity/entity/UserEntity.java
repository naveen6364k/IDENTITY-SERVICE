package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private int userId;

    @Column(name = "UserCode")
    private String userCode;

    @Column(name = "UserTypeID")
    private Integer userTypeId;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "SecurityGroupID")
    private Integer securityGroupId;

    @Column(name = "Password1")
    private String password1;

    @Column(name = "Password2")
    private String password2;

    @Column(name = "Alerts")
    private Integer alerts;

    @Column(name = "EmailAddress")
    private String emailAddress;

    @Column(name = "LastActivity")
    private Date lastActivity;

    @Column(name = "LastLogin")
    private Date lastLogin;

    @Column(name = "CreatedAt")
    private Date createdAt;

    @Column(name = "UpdatedAt")
    private Date updatedAt;

    @Column(name = "CreatedBy")
    private Integer createdBy;

    @OneToMany(mappedBy = "user")
    private List<UserLocationEntity> userLocationEntities;

}

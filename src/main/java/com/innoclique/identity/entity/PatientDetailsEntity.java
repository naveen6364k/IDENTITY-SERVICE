package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "PatientDetails")
@Getter
@Setter
public class PatientDetailsEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "PatientID")
        private int patientId;

        @Column(name = "PatientAccountNo")
        private Integer patientAccountNo;

        @Column(name = "FirstName", nullable = false)
        private String firstName;

        @Column(name = "LastName", nullable = false)
        private String lastName;

        @Column(name = "DateOfBirth")
        private Date dateOfBirth;

        @Column(name = "Gender")
        private String gender;

        @Column(name = "Address")
        private String address;

        @Column(name = "City")
        private String city;

        @Column(name = "State", length = 2)
        private String state;

        @Column(name = "ZipCode", length = 10)
        private String zipCode;

        @Column(name = "Phone")
        private String phone;

        @Column(name = "Email")
        private String email;

        @Column(name = "UserID")
        private Integer userId;

        @Column(name = "IsActive")
        private boolean isActive;

        @Column(name = "CreatedAt")
        private Timestamp createdAt;

        @Column(name = "UpdatedAt")
        private Timestamp updatedAt;


    }
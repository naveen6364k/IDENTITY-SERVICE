package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "PatientMoodMoniter")
@Getter
@Setter
public class PatientMoodMoniterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PMoniterID")
    private Integer pMoniterID;

    @Column(name = "PLID")
    private int plid;

    @Column(name = "MoodStatusID")
    private int moodStatusID;

    @Column(name = "CreatedAt")
    private Date createdAt;
}

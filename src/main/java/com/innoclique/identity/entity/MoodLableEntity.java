package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;

@Entity
@Getter
@Setter
@Table(name = "MoodLable")
public class MoodLableEntity {
    @Id
    @Column(name = "MoodLableID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moodLableID;

    @Column(name = "Wish")
    private String wish;

    @Column(name = "Time")
    private Time time;
}

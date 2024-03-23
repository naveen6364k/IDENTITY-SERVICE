package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 *  @author Naveen Kumar Chintala
 */

@Data
@Entity
@Table(name = "MoodStatus")
public class MoodstatusEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="MoodStatusID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer moodStatusId;

    @Column(name = "Mood")
    private String mood;

    @Column(name = "IsActive")
    private Integer isActive;

    @Column(name="MoodScore")
    private Integer moodScore;



}

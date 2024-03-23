package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "UserAlerts")
@Getter
@Setter
public class UserAlertsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UAlertID")
    private Long userAlertId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "LocationID")
    private Long locationId;

    @Column(name = "AlertType")
    private String alertType;

    @Column(name = "AlertTime")
    private LocalDateTime alertTime;

    @Column(name = "DaysofWeek")
    private String daysOfWeek;

    @Column(name = "IsEnabled")
    private Integer isEnabled;

    @Column(name = "Description")
    private String description;

    @Column(name = "CreatedAt")
    private LocalDateTime createdAt;
}

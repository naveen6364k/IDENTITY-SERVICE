package com.innoclique.identity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserAlerts {
    @Id
    @Column(name = "UAlertID")
    private Long userAlertId;
    @Column(name = "AlertType")
    private String alertType;
}

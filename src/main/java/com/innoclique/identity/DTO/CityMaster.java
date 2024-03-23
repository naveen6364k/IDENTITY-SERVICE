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
public class CityMaster {
    @Id
    @Column(name = "CityID")
    private  Integer cityId;
    @JsonProperty("City's")
    private String cityName;
    private int stateId;
}

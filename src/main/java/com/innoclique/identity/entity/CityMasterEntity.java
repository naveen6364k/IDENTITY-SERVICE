package com.innoclique.identity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CityMaster")
public class CityMasterEntity {
    @Id
    @Column(name = "CityID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer cityId;
    @Column(name = "CityName")
    private String cityName;
    @Column(name="StateID")
    private int stateId;

//    @ManyToOne
//    @JoinColumn(name = "state_master_state_id")
//    private StateMasterEntity stateMaster;

}

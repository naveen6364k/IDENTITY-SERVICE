package com.innoclique.identity.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Provider {
    @Id
    Integer providerId;
    String providerName;
}

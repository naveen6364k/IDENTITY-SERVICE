package com.innoclique.identity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AllowedLocation {
    private int locId;
    private String locationName;
    private int isDefault;
}

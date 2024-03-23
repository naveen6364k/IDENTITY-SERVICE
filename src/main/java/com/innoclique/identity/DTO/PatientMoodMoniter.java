package com.innoclique.identity.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientMoodMoniter {
    private int moodStatusID;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int plid;

}

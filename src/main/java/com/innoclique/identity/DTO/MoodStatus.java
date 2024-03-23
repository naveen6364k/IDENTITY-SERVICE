package com.innoclique.identity.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MoodStatus {

    private Integer moodStatusId;
    private String mood;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String time;
}

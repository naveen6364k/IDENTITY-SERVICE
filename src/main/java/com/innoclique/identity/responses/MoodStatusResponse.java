package com.innoclique.identity.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.innoclique.identity.DTO.MoodStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@Setter
public class MoodStatusResponse {
    private  StatusResponse statusResponse;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private  String wish;
    @JsonInclude (JsonInclude.Include.NON_NULL)
    private List<MoodStatus> moodStatus;
    public MoodStatusResponse(StatusResponse statusResponse,List<MoodStatus> moodStatus){
        this.statusResponse=statusResponse;
        this.moodStatus=moodStatus;
    }



}

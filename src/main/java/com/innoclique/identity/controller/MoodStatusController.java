package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.MoodStatus;
import com.innoclique.identity.responses.MoodStatusResponse;
import com.innoclique.identity.service.MoodStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/mood")
public class MoodStatusController {



    @Autowired
    MoodStatusService moodStatusService;
    @CrossOrigin
    @PostMapping("/getWishMoods")
    public MoodStatusResponse getWishMoods(@RequestBody MoodStatus moodStatus) {
        return moodStatusService.getWishMoods(moodStatus);
    }
    @CrossOrigin
    @GetMapping("/getMoodStatus")
    public MoodStatusResponse getAllMoodStatus(){
        return moodStatusService.getAllMoodStatus();
    }

}

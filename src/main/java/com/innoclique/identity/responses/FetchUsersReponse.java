package com.innoclique.identity.responses;

import com.innoclique.identity.DTO.FetchUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class FetchUsersReponse {

    private StatusResponse statusResponse;
    private List<FetchUser> users;
}

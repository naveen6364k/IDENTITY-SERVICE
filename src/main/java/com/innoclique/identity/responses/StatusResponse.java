package com.innoclique.identity.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatusResponse {
    private int statusCode;
    private String message;

    public StatusResponse() {

    }
}

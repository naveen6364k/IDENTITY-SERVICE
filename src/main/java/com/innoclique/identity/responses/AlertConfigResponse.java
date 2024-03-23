package com.innoclique.identity.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class AlertConfigResponse {
    private StatusResponse statusResponse;
    private Map<String, Map<String,Integer>> alertConfigurations;
}

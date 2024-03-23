package com.innoclique.identity.responses;

import com.innoclique.identity.DTO.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@AllArgsConstructor
@Getter
public class ProviderResponse {
    private StatusResponse statusResponse;
    private List<Provider> providerList;
}

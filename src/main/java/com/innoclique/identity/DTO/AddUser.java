package com.innoclique.identity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AddUser {
    private List<User> userDetails;
    private LocationMapping locationMapping;
}

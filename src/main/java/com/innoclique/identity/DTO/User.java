package com.innoclique.identity.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private int userId;
    private String code;
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private String confirmPassword;
    private Integer securityGroup;
    private Integer userTypeId;
    private int receiveAlerts;
    private boolean enabled;
    private boolean emailVerified;

}

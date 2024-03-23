package com.innoclique.identity.controller;

import com.innoclique.identity.DTO.User;
import com.innoclique.identity.service.UserManagementService;
import lombok.extern.log4j.Log4j2;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author Naveen Kumar Chintala
 */

@RestController
@RequestMapping("/api/v1/user")
@Log4j2
public class UserManagementController {

	
	@Autowired
	UserManagementService userManagementService;
	


    @PostMapping("/userInfo")
    public List<UserRepresentation> userInfo(@RequestBody User user) {


		return userManagementService.userInfo(user);
    }
    
    
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody User userDetails) {


		log.info("create user is invoked : {}", userDetails);

		var authentication = SecurityContextHolder.getContext().getAuthentication();

		log.info("authorities are : {}", authentication.getAuthorities());
		boolean userCreated = userManagementService.createUser(userDetails);
    	
    	String message  = userCreated ? "User created" : "User not created";
    	
    	if(userCreated)
    		return new ResponseEntity<String>(message, HttpStatus.CREATED);
    	else
        	return new ResponseEntity<String>(message, HttpStatus.OK);
        
    }


	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {


		log.info("Authentication invoked for user  : {}", user.getUserName());


		return userManagementService.validateUser(user);

	}

	@PostMapping("/refreshToken")
	public String refreshToken(@RequestBody String refreshToken) {


		log.info("refreshToken invoked : {}", refreshToken);


		return userManagementService.refreshToken(refreshToken);

	}



}

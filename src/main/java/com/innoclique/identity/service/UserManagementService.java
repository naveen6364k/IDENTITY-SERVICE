package com.innoclique.identity.service;

import com.innoclique.identity.DTO.User;
import com.innoclique.identity.constants.AppConstants;
import jakarta.ws.rs.core.Response;
import lombok.extern.log4j.Log4j2;
import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.token.TokenManager;
import org.keycloak.representations.AccessTokenResponse;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Naveen Kumar Chintala
 */

@Service
@Log4j2
public class UserManagementService {
	
	@Autowired
	Keycloak keycloak;

	@Value("${keycloak.auth-server-url}")
	private String authServerUrl;

	@Value("${keycloak.realm}")
	private String realm;

	@Value("${keycloak.resource}")
	private String resource;

	@Value("${keycloak.credentials.secret}")
	private String credentialsSecret;

	public  ResponseEntity<AccessTokenResponse> validateUser(User user) {

	  Keycloak keycloakUser =	KeycloakBuilder.builder()
			  .serverUrl(authServerUrl)
			  .realm(realm)
			  .username(user.getUserName())
			  .password(user.getPassword())
			  .clientId(resource)
			  .clientSecret(credentialsSecret)
			  .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).
					  disableTrustManager().build())
			  .build();




		try {
			AccessTokenResponse accessTokenResponse = keycloakUser.tokenManager().getAccessToken();


			return new ResponseEntity<>(accessTokenResponse, HttpStatus.OK);
		}catch (Exception e){
			log.error("Exception occurred in validateUser for user : {} : {}", user.getUserName(), e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}

	private static Map<String, Object> getStringObjectMap() {
		Map<String, Object> clientCredentials = new HashMap<>();
		clientCredentials.put("secret", "eyJhbGciOiJIUzUxMiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJkMTI4ZTNkNS1mYTU3LTQzODgtOWJhMS0zNWI2ZDgzYzY2OGEifQ.eyJleHAiOjE3NDI1NzQ1NDAsImlhdCI6MTcxMTAzODU0MCwianRpIjoiMTE5Y2IyMDAtZjI3ZC00MTliLTgzMGMtM2M1YzkyMzEyNWNkIiwiaXNzIjoiaHR0cHM6Ly8yMC4xOTcuNS45Nzo4NDQzL3JlYWxtcy9DYWxtX1NjaWVudCIsImF1ZCI6Imh0dHBzOi8vMjAuMTk3LjUuOTc6ODQ0My9yZWFsbXMvQ2FsbV9TY2llbnQiLCJ0eXAiOiJJbml0aWFsQWNjZXNzVG9rZW4ifQ.WX-v7a8ifC0lD7S8tAs_U3_oZgxD1iFyqY5iI2jqbCyVy6Doy3ud4xuwUD0o8dD_ChwzviqDD05EBxCHHNvDTg");
		return clientCredentials;
	}

	public RealmResource getRealm() {
		return keycloak.realm(AppConstants.REALM_NAME);
    }

	public List<UserRepresentation> userInfo(User user) {

		try {
			return getRealm()
					.users()
					.searchByUsername(user.getEmailAddress(), true);
		}catch (Exception e){
			log.error("Exception occurred in getRealm method : {}", e.getMessage());
		}
      return new ArrayList<>();
	}

	public boolean createUser(User userDetails) {
		
		UserRepresentation userRepresentation = new UserRepresentation();

		userRepresentation.setUsername(userDetails.getFirstName());
		userRepresentation.setFirstName(userDetails.getFirstName());
		userRepresentation.setLastName(userDetails.getLastName());
		userRepresentation.setEmail(userDetails.getEmailAddress());
		userRepresentation.setEnabled(userDetails.isEnabled());
		userRepresentation.setEmailVerified(userDetails.isEmailVerified());

		Response response = getRealm().users().create(userRepresentation);

		log.info("User creation status : {} for user Name : {}", response.getStatus(), userDetails.getEmailAddress());

		if(response.getStatus() == 201) {
		// Set password flow
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		String userId = CreatedResponseUtil.getCreatedId(response);
		passwordCred.setTemporary(false);
		passwordCred.setType("password");
		passwordCred.setValue(userDetails.getPassword());
		UserResource userResource = getRealm().users().get(userId);
		userResource.resetPassword(passwordCred);

        }else {
			log.info("User is not created for user name : {} and status : {}", userDetails.getEmailAddress(), response.getStatus());
        }
        return true;
    }


	public String refreshToken(String refreshToken) {
		Keycloak keycloakUser =	KeycloakBuilder.builder()
				.serverUrl(authServerUrl)
				.realm(realm)
				.clientId(resource)
				.clientSecret(refreshToken)
				.resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).
						disableTrustManager().build())
				.build();



		  String newAccessToken = keycloak.tokenManager().refreshToken().getToken();

		return  newAccessToken;

	}
}

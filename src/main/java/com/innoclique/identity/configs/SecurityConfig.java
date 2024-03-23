package com.innoclique.identity.configs;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


/**
 * @author Naveen Kumar Chintala
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Log4j2
public class SecurityConfig {

	@Value("${app.security.enabled}")
	private boolean securityEnabled;

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			// -- Swagger UI v3 (OpenAPI)
			"/v3/api-docs/**",
			"/swagger-ui/**",
			"/api/v1/login",
			//  "/patientDetails/**",
			//	 "/patientDetails/**"
			// other public endpoints of your API may be appended to this array
	};


	private final JwtAuthenticationConverter jwtAuthConverter = new JwtAuthenticationConverter();


	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		log.info("Is security Enabled : {}", securityEnabled);

		if(securityEnabled) {
			http.csrf(AbstractHttpConfigurer::disable).
					authorizeHttpRequests(auth ->  auth
							.requestMatchers(AUTH_WHITELIST).
							permitAll().
							anyRequest().
							authenticated());

			http.oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
					.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter)));

			http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(STATELESS));
		}else{
			http.csrf(AbstractHttpConfigurer::disable).
					authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
		}


		return http.build();
	}
}
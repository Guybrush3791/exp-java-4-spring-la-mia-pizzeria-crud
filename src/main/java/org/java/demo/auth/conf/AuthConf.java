package org.java.demo.auth.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConf {

	@Bean
	PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    
		return 
			http.csrf(c -> c.disable())
//				.cors(c -> c.disable())
				.authorizeHttpRequests(a -> a
					.requestMatchers("/api/v1/**").permitAll()
					.requestMatchers(
							"/*/create",
							"/*/new",
							"/*/update/**",
							"/*/delete/**",
							
							"/*/*/*/create"
					).hasAuthority("ADMIN")
			        .requestMatchers("/**").hasAnyAuthority("USER", "ADMIN")
//			        .requestMatchers("/**").permitAll()
			).formLogin(f -> f.permitAll()
			).logout(l -> l.logoutSuccessUrl("/")
			).build();
	}
}

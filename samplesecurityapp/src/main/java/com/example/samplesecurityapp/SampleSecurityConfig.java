package com.example.samplesecurityapp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//mysql
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@Configuration
@EnableMethodSecurity
public class SampleSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests(authorize -> {
			authorize.anyRequest().permitAll();
		});
		http.formLogin(form -> {
			form.defaultSuccessUrl("/secret").loginPage("/login");
		});
		
		return http.build();
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		String username = "user";
//		String password = "pass";
//		
//		UserDetails user = User.withUsername(username)
//				.password(
//						PasswordEncoderFactories
//						.createDelegatingPasswordEncoder()
//						.encode(password))
//				.roles("USER")
//				.build();
//		
//		return new InMemoryUserDetailsManager(user);
//		
//	}
	
	@Bean
	public UserDetailsManager userDetailsManager() {
	    JdbcUserDetailsManager users = new JdbcUserDetailsManager(this.dataSource);
	    
	    // ユーザー登録
//	    users.createUser(makeUser("taro", "yamada", "USER"));
//	    users.createUser(makeUser("hanako", "flower", "USER"));
//	    users.createUser(makeUser("sachiko", "happy", "USER"));
//	    users.createUser(makeUser("admin", "kanri", "ADMIN"));
	    
	    return users;
	}

	private UserDetails makeUser(String user, String pass, String role) {
	    return User.withUsername(user)
	            .password(
	                    PasswordEncoderFactories
	                    .createDelegatingPasswordEncoder()
	                    .encode(pass))
	            .roles(role)
	            .build();
	}

}

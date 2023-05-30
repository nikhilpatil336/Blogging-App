package com.youtubeproject.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebSecurity
//@EnableSwagger2
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private String[] urls = {
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
	{
		/*UserDetails admin = User.withUsername("Nikhil")
				.password(passwordEncoder.encode("abcd"))
				//.password("abcd")
				.roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(admin);*/
		
		return new CustomUserDetailService();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
	{
		return httpSecurity
				.csrf().disable()
				.authorizeHttpRequests()
				//.anyRequest().permitAll()
				.requestMatchers("/api/categories/**").permitAll()
				.requestMatchers("/v3/api-docs").permitAll()
				.requestMatchers(urls).permitAll()
				.requestMatchers(HttpMethod.GET).permitAll()
//				.and()
//				.authorizeHttpRequests()
				.requestMatchers("/api/users/**").authenticated()
				.and()
				.httpBasic()
				.and()
				.build();
	}
}

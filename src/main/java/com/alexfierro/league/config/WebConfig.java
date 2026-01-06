/**
 * 
 */
package com.alexfierro.league.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author alpha
 *
 */
@Configuration
public class WebConfig {
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedOrigins("http://localhost:4200") //Update using the correct URL if the server is different of the localhost server
					.allowedMethods("GET", "POST", "DELETE", "OPTIONS", "PUT")
					.allowedHeaders("*")
					.allowCredentials(true);
			}
		};
	}
}

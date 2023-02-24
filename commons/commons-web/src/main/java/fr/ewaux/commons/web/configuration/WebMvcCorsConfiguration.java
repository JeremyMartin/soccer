package fr.ewaux.commons.web.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebMvcCorsConfiguration {

	private static final List<String> ALLOWED_HEADERS = Arrays.asList(HttpHeaders.AUTHORIZATION,
		HttpHeaders.ACCEPT_LANGUAGE,
		HttpHeaders.CONTENT_DISPOSITION,
		HttpHeaders.CONTENT_TYPE,
		HttpHeaders.TRANSFER_ENCODING);
	private static final List<String> EXPOSED_HEADERS = Arrays.asList(HttpHeaders.AUTHORIZATION,
		HttpHeaders.ACCEPT_LANGUAGE,
		HttpHeaders.CONTENT_DISPOSITION,
		HttpHeaders.CONTENT_TYPE,
		HttpHeaders.TRANSFER_ENCODING);
	private static final long MAX_AGE = 3600;

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(Boolean.FALSE);
		corsConfiguration.setAllowedHeaders(ALLOWED_HEADERS);
		corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
		corsConfiguration.setExposedHeaders(EXPOSED_HEADERS);
		corsConfiguration.setMaxAge(MAX_AGE);
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}

}

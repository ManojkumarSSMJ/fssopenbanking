package com.fss.openbanking.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import okhttp3.OkHttpClient;

@Configuration
	public class HttpClientConfig {

	    @Bean
	    public OkHttpClient httpClient() {
	        return new OkHttpClient();
	    }
}

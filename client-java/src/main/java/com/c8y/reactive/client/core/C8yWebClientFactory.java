package com.c8y.reactive.client.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class C8yWebClientFactory {
	
	private String baseUrl;

	private String userName;

	private String password;

	private WebClient.Builder webClientBuilder;

	public C8yWebClientFactory(@Value("${C8Y.baseURL}") String baseUrl, @Value("${C8Y.service.user}") String userName,
			@Value("${C8Y.service.password}") String password, WebClient.Builder webClientBuilder) {
		this.webClientBuilder = webClientBuilder;
		this.baseUrl = baseUrl;
		this.userName = userName;
		this.password = password;
	}
	
	public WebClient buildC8yWebClient() {
		return webClientBuilder.baseUrl(baseUrl).defaultHeaders(header -> header.setBasicAuth(userName, password))
				.build();
	}
}
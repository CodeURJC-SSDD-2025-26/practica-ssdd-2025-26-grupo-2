package com.ssdd.backend.service;

import com.ssdd.backend.dto.EmailRequestDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UtilityEmailClient {

    private final RestClient restClient;

    public UtilityEmailClient(@Value("${utility-service.url}") String utilityServiceUrl) {
        this.restClient = RestClient.builder()
                .baseUrl(utilityServiceUrl)
                .build();
    }

    public void sendEmail(EmailRequestDTO request) {
        restClient.post()
                .uri("/api/emails")
                .body(request)
                .retrieve()
                .toBodilessEntity();
    }
}

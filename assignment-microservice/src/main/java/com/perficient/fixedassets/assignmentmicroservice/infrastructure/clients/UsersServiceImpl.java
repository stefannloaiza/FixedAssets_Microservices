package com.perficient.fixedassets.assignmentmicroservice.infrastructure.clients;

import com.perficient.fixedassets.assignmentmicroservice.application.services.UsersService;
import com.perficient.fixedassets.assignmentmicroservice.domain.models.dto.UserDTO;
import com.perficient.fixedassets.assignmentmicroservice.domain.singleton.RestClientSingleton;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

@Service
@Slf4j
public class UsersServiceImpl implements UsersService {
    private static final String USER_URL;
    private static final RestClient restClient;

    static {
        USER_URL = "http://localhost:8080/api/v1/users";
        restClient = RestClientSingleton.getInstance();
    }

    @Override
    public UserDTO getUserByUserId(Long userId) {
        log.info("Getting user by userId: {}", userId);
        return restClient.get()
                .uri("%s/%d".formatted(USER_URL, userId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    log.error("Error: status {} getting user by userId: {}", response.getStatusCode(), userId);
                    throw new RestClientException("Error: status " + response.getStatusCode() + " getting user by userId: " + userId);
                })
                .body(UserDTO.class);
    }
}

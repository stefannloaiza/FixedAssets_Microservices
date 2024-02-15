package com.perficient.fixedassets.assignmentmicroservice.domain.singleton;

import org.springframework.web.client.RestClient;

import java.util.Objects;

public class RestClientSingleton {
    private static RestClient restClient;

    private RestClientSingleton() {
    }

    public static RestClient getInstance() {
        if (Objects.isNull(restClient)) restClient = RestClient.create();
        return restClient;
    }
}

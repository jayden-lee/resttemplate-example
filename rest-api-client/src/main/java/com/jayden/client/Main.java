package com.jayden.client;

import com.jayden.client.dto.User;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public class Main {

    private static final String BASE_URL = "http://localhost:8080";

    public static void main(String[] args) {
        RestTemplate restTemplate = restTemplate();

        System.out.println("## Get One User");
        User user = restTemplate.getForObject(BASE_URL + "/users/1", User.class);
        System.out.printf("User: " + user);
    }

    private static RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
        return restTemplate;
    }
}

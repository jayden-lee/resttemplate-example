package com.jayden.client;

import com.jayden.client.dto.User;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.UUID;

public class Main {

    private static final String BASE_URL = "http://localhost:8080";

    private static RestTemplate restTemplate;

    public static void main(String[] args) {
        callGetOneUser();
        printLine();
        callGetAllUsers();
    }

    private static void callGetOneUser() {
        System.out.println("########## Get One User ##########");
        ResponseEntity<User> responseEntity = restTemplate().exchange(BASE_URL + "/users/{id}", HttpMethod.GET, null, User.class, 1);
        System.out.println(responseEntity.getBody());
        System.out.println("##################################");
    }

    private static void callGetAllUsers() {
        System.out.println("########## Get All User ##########");
        ResponseEntity<User[]> user = restTemplate().getForEntity(BASE_URL + "/users", User[].class);
        User[] users = user.getBody();
        System.out.println("User Count: " + users.length);
        System.out.println("##################################");
    }

    private static HttpEntity<String> headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("API-KEY", UUID.randomUUID().toString());
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    private static RestTemplate restTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
            restTemplate.setMessageConverters(Collections.singletonList(new MappingJackson2HttpMessageConverter()));
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().set("REST-API-KEY", UUID.randomUUID().toString());
                request.getHeaders().setContentType(MediaType.APPLICATION_JSON);
                return execution.execute(request, body);
            });
        }

        return restTemplate;
    }

    private static void printLine() {
        System.out.println();
    }
}

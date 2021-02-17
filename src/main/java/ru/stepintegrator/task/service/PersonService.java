package ru.stepintegrator.task.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.stepintegrator.task.to.PersonTo;

import java.util.Arrays;
import java.util.Objects;

@Service
public class PersonService {

    public static final String PERSONS_REST_URL = "https://jsonplaceholder.typicode.com/users";
    private final RestTemplate restTemplate;

    public PersonService(RestTemplate restTemplate) {
        this.restTemplate= restTemplate;
    }

    public PersonTo getByParam(String paramName, Object paramValue) {
        final String url =  UriComponentsBuilder.fromUriString(PERSONS_REST_URL)
                .queryParam(paramName, paramValue)
                .build()
                .toString();

        return Arrays.stream(Objects.requireNonNull(restTemplate.getForObject(url, PersonTo[].class)))
                .findFirst()
                .orElse(null);
    }

}

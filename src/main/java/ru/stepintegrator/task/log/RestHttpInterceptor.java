package ru.stepintegrator.task.log;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import ru.stepintegrator.task.service.ExternalLogService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RestHttpInterceptor implements ClientHttpRequestInterceptor {

    private final ExternalLogService logService;

    public RestHttpInterceptor(ExternalLogService logService) {
        this.logService = logService;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
        ClientHttpResponse httpResponse = clientHttpRequestExecution.execute(httpRequest, body);

        String responseBody = new String(httpResponse.getBody().readAllBytes(), StandardCharsets.UTF_8);
        logService.addLogItem(httpRequest.getURI().toString(), responseBody);

        return httpResponse;
    }
}

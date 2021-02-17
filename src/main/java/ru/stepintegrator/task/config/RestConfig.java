package ru.stepintegrator.task.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import ru.stepintegrator.task.log.RestHttpInterceptor;
import ru.stepintegrator.task.service.ExternalLogService;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestConfig {

    @Bean
    public CloseableHttpClient httpClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60)
                .setConnectionRequestTimeout(60)
                .setSocketTimeout(60)
                .build();
        return HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
    }

    @Bean
    public RestTemplate restTemplate(CloseableHttpClient httpClient, RestHttpInterceptor restHttpInterceptor) {
        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        RestTemplate restTemplate = new RestTemplate(requestFactory);
        List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(restHttpInterceptor);
        restTemplate.setInterceptors(interceptors);
        restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
        return restTemplate;
    }

    @Bean
    public RestHttpInterceptor httpInterceptor(ExternalLogService logService) {
        return new RestHttpInterceptor(logService);
    }

}

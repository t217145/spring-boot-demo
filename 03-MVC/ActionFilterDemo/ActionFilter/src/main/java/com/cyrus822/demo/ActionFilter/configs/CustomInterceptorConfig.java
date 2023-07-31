package com.cyrus822.demo.ActionFilter.configs;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import com.cyrus822.demo.ActionFilter.interceptor.CustomInterceptor;
import com.cyrus822.demo.ActionFilter.interceptor.CustomRestInterceptor;

@Configuration
public class CustomInterceptorConfig implements WebMvcConfigurer {

    @Bean(name = "qhRestTemplate")
    public RestTemplate qhRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();

        List<ClientHttpRequestInterceptor> interceptors
          = restTemplate.getInterceptors();
        if (CollectionUtils.isEmpty(interceptors)) {
            interceptors = new ArrayList<>();
        }
        interceptors.add(new CustomRestInterceptor());
        restTemplate.setInterceptors(interceptors);

        return restTemplate;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(new CustomInterceptor());
    }
}
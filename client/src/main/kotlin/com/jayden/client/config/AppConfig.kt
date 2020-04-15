package com.jayden.client.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import java.nio.charset.Charset
import java.time.Duration

@Configuration
class AppConfig {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder()
            .requestFactory {
                BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory())
            }
            .setConnectTimeout(Duration.ofSeconds(5))
            .setReadTimeout(Duration.ofSeconds(20))
            .additionalMessageConverters(StringHttpMessageConverter(Charset.forName("UTF-8")))
            .additionalInterceptors(LoggingRequestInterceptor())
            .build()
    }
}
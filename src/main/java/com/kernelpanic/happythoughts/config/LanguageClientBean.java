package com.kernelpanic.happythoughts.config;

import com.google.cloud.language.v1.LanguageServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class LanguageClientBean {
    @Bean
    public LanguageServiceClient languageServiceClient() throws IOException {
        return LanguageServiceClient.create();
    }
}

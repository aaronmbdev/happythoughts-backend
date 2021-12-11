package com.kernelpanic.happythoughts.ports.phrases;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
public class QuoteDownloader {
    private final WebClient webClient;
    private final static String SOURCE = "https://zenquotes.io/api/quotes";

    protected QuoteDownloader(WebClient webClient) {
        this.webClient = webClient;
    }

    protected Mono<String> downloadQuotes() {
        log.info("Descargando frases bonitas");
        return webClient.get()
                .uri(SOURCE)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }

}

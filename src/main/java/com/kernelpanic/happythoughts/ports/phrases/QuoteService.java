package com.kernelpanic.happythoughts.ports.phrases;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kernelpanic.happythoughts.utils.NumberUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class QuoteService {

    protected static List<Quote> quotes;
    protected final ObjectMapper mapper;

    public QuoteService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    public void initialize() {
        log.info("Iniciando Servicio de frases bonitas");
        QuoteDownloader downloader = new QuoteDownloader(WebClient.create());
        String rawQuotes = downloader.downloadQuotes().block();
        try {
            parseQuotes(rawQuotes);
        } catch (Exception e) {
            log.error("Hubo un error convirtiendo rawQuotes a un array de quotes.");
            e.printStackTrace();
        }
    }

    public static Optional<Quote> getRandomQuote() {
        int size = quotes.size();
        int index = NumberUtils.getRandomInt(0,size);
        if(quotes.size() > 0) {
            log.debug("Retornando quote nº{}",index);
            return Optional.of(quotes.get(index));
        } else {
            log.debug("Se ha pedido un random quote pero no había ninguno en la lista");
            return Optional.empty();
        }
    }

    private void parseQuotes(final String rawQuotes) throws JsonProcessingException {
        log.info("Procesando frases bonitas");
        quotes = Arrays.asList(mapper.readValue(rawQuotes, Quote[].class));
        log.info("Se han descargado {} frases bonitas",quotes.size());
    }
}

package com.kernelpanic.happythoughts.ports.language;

import com.google.cloud.language.v1.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProcesorService {
    private final LanguageServiceClient client;

    public ProcesorService(LanguageServiceClient client) {
        this.client = client;
    }

    public Optional<TextAnalysis> analyzeText(final String text) {
        log.info("Analizando texto: {}",text);
        Document doc = Document.newBuilder().setContent(text).setType(Document.Type.PLAIN_TEXT).build();
        Optional<Sentiment> sentiment = getSentiment(doc);
        if(sentiment.isPresent()) {
            List<Entity> lista = getEntities(doc);
            TextAnalysis resultado = new TextAnalysis(sentiment.get(),lista);
            return Optional.of(resultado);
        }
        return Optional.empty();
    }

    private Optional<Sentiment> getSentiment(final Document doc) {
        log.info("Analizando sentimiento del texto");
        AnalyzeSentimentResponse SentResponse = client.analyzeSentiment(doc);
        if(SentResponse.hasDocumentSentiment()) {
            log.info("Hemos detectado sentimiento, puntuación: {}",SentResponse.getDocumentSentiment().getScore());
            return Optional.of(SentResponse.getDocumentSentiment());
        } else {
            log.info("No hemos detectado sentimiento en el texto");
            return Optional.empty();
        }
    }

    private List<Entity> getEntities(final Document doc) {
        log.info("Analizando entidades del texto");
        AnalyzeEntitiesResponse EntResponse = client.analyzeEntities(doc);
        log.info("Encontradas {} entidades",EntResponse.getEntitiesList().size());
        return new LinkedList<>(EntResponse.getEntitiesList());
    }

}

package com.kernelpanic.happythoughts.business.comoestas;

import com.google.cloud.language.v1.Entity;
import com.kernelpanic.happythoughts.business.happythougths.HTDocument;
import com.kernelpanic.happythoughts.business.happythougths.HTService;
import com.kernelpanic.happythoughts.ports.language.ProcesorService;
import com.kernelpanic.happythoughts.ports.language.TextAnalysis;
import com.kernelpanic.happythoughts.ports.phrases.Quote;
import com.kernelpanic.happythoughts.ports.phrases.QuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class ComoEstasService {
    private final ProcesorService langService;
    private final HTService htService;

    public ComoEstasService(ProcesorService langService, HTService htService) {
        this.langService = langService;
        this.htService = htService;
    }

    public TextAnalysis analizeText(final String text) {
        return langService.analyzeText(text).orElse(null);
    }

    public String savePositiveMood(final String text, final TextAnalysis analisis) {
        HTDocument doc = new HTDocument(text);
        doc.setEntities(generateEntityList(analisis.getEntityList()));
        return htService.createHTindex(doc);
    }

    private String[] generateEntityList(final List<Entity> lista) {
        List<String> result = new LinkedList<>();
        for(Entity entity: lista) {
            result.add(entity.getName());
        }
        return result.toArray(new String[0]);
    }

    public String[] getEntities(final String saved) {
        return htService.getEntities(saved);
    }

    public String getEntitiesforNegative(final TextAnalysis analisis) {
        List<Entity> list = analisis.getEntityList();
        list.sort((entity, t1) -> Float.compare(entity.getSalience(), t1.getSalience()));
        return list.get(0).getName();
    }

    public NegativeResponse getPositivePhrase(final String veredict, final String key) {
        List<HTDocument> matches = htService.buscarFraseBonita(key);
        String frase;
        if(matches.isEmpty()) {
            frase = QuoteService.getRandomQuote().map(Quote::getQuote).orElse("No se ha encontrado una frase random");
            return new NegativeResponse(veredict, frase, "");
        } else {
            frase = matches.get(0).getHappyPhrase();
            return new NegativeResponse(veredict, frase, matches.get(0).getMood());
        }
    }


}

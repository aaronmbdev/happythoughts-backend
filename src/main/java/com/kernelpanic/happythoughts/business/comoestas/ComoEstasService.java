package com.kernelpanic.happythoughts.business.comoestas;

import com.google.cloud.language.v1.Entity;
import com.kernelpanic.happythoughts.business.happythougths.HTDocument;
import com.kernelpanic.happythoughts.business.happythougths.HTService;
import com.kernelpanic.happythoughts.ports.language.ProcesorService;
import com.kernelpanic.happythoughts.ports.language.TextAnalysis;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
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
}

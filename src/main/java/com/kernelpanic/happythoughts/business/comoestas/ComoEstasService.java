package com.kernelpanic.happythoughts.business.comoestas;

import com.kernelpanic.happythoughts.ports.language.ProcesorService;
import com.kernelpanic.happythoughts.ports.language.TextAnalysis;
import org.springframework.stereotype.Service;

@Service
public class ComoEstasService {
    private final ProcesorService langService;


    public ComoEstasService(ProcesorService langService) {
        this.langService = langService;
    }

    public TextAnalysis analizeText(final String text) {
        return langService.analyzeText(text).orElse(null);
    }
}

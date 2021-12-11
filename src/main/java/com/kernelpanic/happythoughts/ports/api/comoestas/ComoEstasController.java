package com.kernelpanic.happythoughts.ports.api.comoestas;

import com.kernelpanic.happythoughts.business.comoestas.*;
import com.kernelpanic.happythoughts.ports.language.TextAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("como-estas")
@Slf4j
public class ComoEstasController {

    private final ComoEstasService service;

    public ComoEstasController(ComoEstasService service) {
        this.service = service;
    }

    @PostMapping(value = "/analiza",produces = "application/json")
    @CrossOrigin(origins = "http://localhost:3000")
    public AnalisisResponse getAnalysis(@RequestBody String text) {
        TextAnalysis analisis = service.analizeText(text);
        String veredict = analisis.getVeredict();
        if(veredict.equals("positive")) {
            String saved = service.savePositiveMood(text,analisis);
            log.info("Se guardó mood positivo con id: {}",saved);
            return new PositiveResponse(veredict, saved);
        } else if(veredict.equals("negative")) {
            return new NegativeResponse(veredict);
        } else {
            return new ErrorResponse(veredict,"No se pudo determinar el sentimiento del texto");
        }
    }
}

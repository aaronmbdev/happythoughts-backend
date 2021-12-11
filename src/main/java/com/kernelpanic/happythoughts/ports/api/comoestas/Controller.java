package com.kernelpanic.happythoughts.ports.api.comoestas;

import com.kernelpanic.happythoughts.business.comoestas.ComoEstasService;
import com.kernelpanic.happythoughts.ports.language.TextAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("como-estas")
@Slf4j
public class Controller {

    private final ComoEstasService service;

    public Controller(ComoEstasService service) {
        this.service = service;
    }

    @PostMapping(value = "/analiza",produces = "application/json")
    public AnalisisResponse getAnalysis(@RequestBody String text) {
        TextAnalysis analisis = service.analizeText(text);
        String veredict = analisis.getVeredict();
        if(veredict.equals("positive")) {
            return new PositiveResponse(veredict);
        } else if(veredict.equals("negative")) {
            return new NegativeResponse(veredict);
        } else {
            return new ErrorResponse(veredict,"No se pudo determinar el sentimiento del texto");
        }
    }
}

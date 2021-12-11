package com.kernelpanic.happythoughts.ports.api.motivate;

import com.kernelpanic.happythoughts.business.motivate.MotivateResponseError;
import com.kernelpanic.happythoughts.business.motivate.MotivateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("motivate")
@Slf4j
public class MotivateController {
    private final MotivateService service;

    public MotivateController(MotivateService service) {
        this.service = service;
    }

    @PostMapping(value = "/motiva/{id}",produces = "application/json")
    @CrossOrigin(origins = "http://localhost:3000")
    public void guardaMotivacion(@PathVariable String id, @RequestBody String text) throws MotivateResponseError {
        log.info("Actualizando frase de documento con id '{}'",id);
       if(! service.putPhrase(id,text)) throw new MotivateResponseError("No se ha encontrado el documento con id '"+id+"'");
    }
}

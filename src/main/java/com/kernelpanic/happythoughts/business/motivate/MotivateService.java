package com.kernelpanic.happythoughts.business.motivate;

import com.kernelpanic.happythoughts.business.happythougths.HTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MotivateService {
    private final HTService service;

    public MotivateService(HTService repository) {
        this.service = repository;
    }

    public boolean putPhrase(final String id, final String phrase) {
        if(service.updatePhrase(id,phrase)) {
            log.info("Se ha guardado la información");
            return true;
        }
        log.error("No se ha actualizado la información");
        return false;
    }
}

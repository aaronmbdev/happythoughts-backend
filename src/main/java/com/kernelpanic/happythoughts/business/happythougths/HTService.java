package com.kernelpanic.happythoughts.business.happythougths;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Slf4j
public class HTService {
    private final HTRepository repository;
    private final ElasticsearchOperations elasticOps;
    private final static String INDEX = "happythougths";

    @PostConstruct
    public void buildIndex() {
        elasticOps.indexOps(HTDocument.class).refresh();
    }

    public HTService(HTRepository repository, ElasticsearchOperations elasticOps) {
        this.repository = repository;
        this.elasticOps = elasticOps;
    }

    public String createHTindex(final HTDocument document) {
        log.info("Guardando documento: {}",document);
        HTDocument saved = repository.save(document);
        log.info("Nuevo documento: {}",document);
        return saved.getId();
    }

    public boolean updatePhrase(final String id, final String phrase) {
        return repository.findById(id).map(x -> {
            log.info("Encontrado documento con id {}",id);
            x.setHappyPhrase(phrase);
            repository.save(x);
            log.info("Actualizada información del documento: {}",x);
            return true;
        }).orElse(false);
    }

}

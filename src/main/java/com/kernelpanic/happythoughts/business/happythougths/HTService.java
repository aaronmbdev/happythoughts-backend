package com.kernelpanic.happythoughts.business.happythougths;

import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.WildcardQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
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

    public String[] getEntities(final String id) {
        return repository.findById(id).map(HTDocument::getEntities).orElse(null);
    }

    public List<HTDocument> buscarFraseBonita(final String key) {
        log.info("Buscando coincidencias para la keyword: {}", key);
        WildcardQueryBuilder queryBuilder = QueryBuilders
                .wildcardQuery("name",key+"*");
        Query searchQ = new NativeSearchQueryBuilder()
                .withFilter(queryBuilder)
                .build();
        SearchHits<HTDocument> hits = elasticOps.search(searchQ, HTDocument.class,
        IndexCoordinates.of(INDEX));
        List<HTDocument> productMatches = new ArrayList<>();
        hits.forEach(searchHit-> productMatches.add(searchHit.getContent()));
        log.info("Coincidencias encontradas: {}",productMatches);
        return productMatches;
    }

}

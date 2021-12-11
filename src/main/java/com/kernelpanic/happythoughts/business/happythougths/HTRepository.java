package com.kernelpanic.happythoughts.business.happythougths;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface HTRepository extends ElasticsearchRepository<HTDocument, String> {
    HTDocument getById(final String id);
}

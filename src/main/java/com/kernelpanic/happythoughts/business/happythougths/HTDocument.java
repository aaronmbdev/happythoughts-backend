package com.kernelpanic.happythoughts.business.happythougths;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Arrays;

@Document(indexName = "happythougths")
@Getter
@Setter
public class HTDocument {
    @Id
    private String id;

    @Field(type = FieldType.Text, name="mood")
    private String mood;

    @Field(type = FieldType.Text, name = "phrase")
    private String happyPhrase;

    @Field(type = FieldType.Keyword, name = "entities")
    private String[] entities;

    public HTDocument(final String mood) {
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "HappyThought {" +
                "id = '" + id + '\'' +
                ", mood = '" + mood + '\'' +
                ", frase = '" + happyPhrase + '\'' +
                ", entities = '" + Arrays.toString(entities) + '\'' +
                "}";
    }
}

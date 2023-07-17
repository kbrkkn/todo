package com.hepsiemlak.todo.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;


@Data
@RequiredArgsConstructor
@Document
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    private String id;

    @Field
    private String title;

    @Field
    private TodoStatus status;

    @Field
    private String creationTime;

    @Field
    private String updatedTime;

    @Field
    private String category;

    @Field
    private Integer priority;


}

package com.hepsiemlak.todo.repository;

import com.couchbase.client.java.json.JsonObject;
import com.hepsiemlak.todo.model.Todo;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.data.couchbase.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends CouchbaseRepository<Todo, String> {

    @Query("#{#n1ql.selectEntity} WHERE " +
            "#{#n1ql.filter} " +
            "AND status = $1.status")
    List<Todo> getFilteredTodoList(@Param("todoDTO") JsonObject jsonObject);


}

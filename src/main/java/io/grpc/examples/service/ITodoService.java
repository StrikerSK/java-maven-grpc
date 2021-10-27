package io.grpc.examples.service;


import io.grpc.examples.entity.TodoEntity;

import java.util.List;

public interface ITodoService {

    String createTodo(String name, String description);
    TodoEntity getTodo(String id);
    List<String> getIds();
    List<TodoEntity> getTodos();

}

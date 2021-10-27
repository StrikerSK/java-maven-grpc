package io.grpc.examples.service;


import java.util.List;

public interface ITodoService {

    String createTodo(String name, String description);
    String getTodo(String id);
    List<String> getIds();

}

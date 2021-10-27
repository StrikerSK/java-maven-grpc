package io.grpc.examples.service;

import io.grpc.examples.client.TodoServiceClient;
import io.grpc.examples.entity.TodoEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoGrpcService implements ITodoService {

    private final TodoServiceClient client;

    @Override
    public String createTodo(String name, String description) {
        return client.CreateTodo(name, description);
    }

    @Override
    public TodoEntity getTodo(String id) {
        return client.GetTodo(id);
    }

    @Override
    public List<String> getIds() {
        return client.GetTodos().stream().map(e -> e.getId()).collect(Collectors.toList());
    }

    @Override
    public List<TodoEntity> getTodos() {
        return client.GetTodos();
    }

}

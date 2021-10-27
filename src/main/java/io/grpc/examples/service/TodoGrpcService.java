package io.grpc.examples.service;

import io.grpc.examples.client.TodoServiceClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TodoGrpcService implements ITodoService {

    private final TodoServiceClient client;

    @Override
    public String createTodo(String name, String description) {
        return client.CreateTodo(name, description);
    }

}

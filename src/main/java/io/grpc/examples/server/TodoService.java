package io.grpc.examples.server;

import com.google.protobuf.Empty;
import io.grpc.examples.todo.IdRequest;
import io.grpc.examples.todo.NewTodo;
import io.grpc.examples.todo.PersistedTodo;
import io.grpc.examples.todo.TodoServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class TodoService extends TodoServiceGrpc.TodoServiceImplBase {

    private List<PersistedTodo> todos = new ArrayList<>();

    @Override
    public void createTodo(NewTodo request, StreamObserver<IdRequest> responseObserver) {
        String todoID = UUID.randomUUID().toString();
        PersistedTodo newTodo = PersistedTodo.newBuilder()
                .setId(todoID)
                .setName(request.getName())
                .setDescription(request.getDescription())
                .setDone(false)
                .build();

        todos.add(newTodo);
        log.info("Todo created: {}", todoID);
        responseObserver.onNext(IdRequest.newBuilder().setId(todoID).build());
        responseObserver.onCompleted();
    }

    @Override
    public void getTodo(IdRequest request, StreamObserver<PersistedTodo> responseObserver) {
        super.getTodo(request, responseObserver);
    }

    @Override
    public void getTodos(Empty request, StreamObserver<PersistedTodo> responseObserver) {
        super.getTodos(request, responseObserver);
    }
}

package io.grpc.examples.client;

import com.google.protobuf.Empty;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.entity.TodoEntity;
import io.grpc.examples.server.ChatServiceServer;
import io.grpc.examples.todo.IdRequest;
import io.grpc.examples.todo.NewTodo;
import io.grpc.examples.todo.PersistedTodo;
import io.grpc.examples.todo.TodoServiceGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple client that requests a greeting from the {@link ChatServiceServer}.
 */
@Slf4j
@Component
public class TodoServiceClient {

  private final TodoServiceGrpc.TodoServiceBlockingStub stub;
  private final ManagedChannel channel;

  /** Construct client for accessing HelloWorld server using the existing channel. */
  public TodoServiceClient() {
      String target = "localhost:9000";
      channel = ManagedChannelBuilder.forTarget(target)
              .usePlaintext()
              .build();

      stub = TodoServiceGrpc.newBlockingStub(channel);
  }

  public String CreateTodo(String name, String description) {
    try {
      NewTodo requestMessage = NewTodo.newBuilder()
              .setName(name)
              .setDescription(description)
              .setDone(false)
              .build();
      IdRequest responseMessage = stub.createTodo(requestMessage);
      return responseMessage.getId();
    } catch (StatusRuntimeException e) {
      log.warn("RPC failed: {}", e.getStatus());
      throw new RuntimeException("Something went wrong");
    }
  }
  public TodoEntity GetTodo(String id) {
    try {
      return new TodoEntity(stub.getTodo(IdRequest.newBuilder().setId(id).build()));
    } catch (StatusRuntimeException e) {
      log.warn("RPC failed: {}", e.getStatus());
      throw new RuntimeException("Something went wrong");
    }
  }

  public List<TodoEntity> GetTodos() {
    try {
      List<TodoEntity> storedTodos = new ArrayList<>();
      Iterator<PersistedTodo> response = stub.getTodos(Empty.newBuilder().build());

      while (response.hasNext()) {
        storedTodos.add(new TodoEntity(response.next()));
      }

      return storedTodos;
    } catch (StatusRuntimeException e) {
      log.warn("RPC failed: {}", e.getStatus());
      throw new RuntimeException("Something went wrong");
    }
  }
}

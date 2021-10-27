package io.grpc.examples.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.examples.chat.ChatServiceGrpc;
import io.grpc.examples.chat.UserMessage;
import io.grpc.examples.server.ChatServiceServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * A simple client that requests a greeting from the {@link ChatServiceServer}.
 */
@Slf4j
@Component
public class ChatServiceClient {

  private final ChatServiceGrpc.ChatServiceBlockingStub stub;
  private final ManagedChannel channel;

  /** Construct client for accessing HelloWorld server using the existing channel. */
  public ChatServiceClient() {
      String target = "localhost:9000";
      channel = ManagedChannelBuilder.forTarget(target)
              .usePlaintext()
              .build();

      stub = ChatServiceGrpc.newBlockingStub(channel);
  }

  public String sendRequest(String name) {
    try {
      UserMessage requestMessage = UserMessage.newBuilder().setBody(name).build();
      UserMessage responseMessage = stub.sayHello(requestMessage);
      return responseMessage.getBody();
    } catch (StatusRuntimeException e) {
      log.warn("RPC failed: {}", e.getStatus());
      throw new RuntimeException("Something went wrong");
    }
  }
}

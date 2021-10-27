package io.grpc.examples.server;

import io.grpc.examples.chat.ChatServiceGrpc;
import io.grpc.examples.chat.UserMessage;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {

    @Override
    public void sayHello(UserMessage request, StreamObserver<UserMessage> responseObserver) {
        UserMessage reply = UserMessage.newBuilder().setBody("Hello " + request.getBody()).build();
        log.info("Request message: {}", request.getBody());
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

}

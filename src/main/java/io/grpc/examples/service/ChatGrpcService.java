package io.grpc.examples.service;

import io.grpc.examples.client.ChatServiceClient;
import org.springframework.stereotype.Service;

@Service
public class ChatGrpcService {

    private final ChatServiceClient client;

    public ChatGrpcService() {
        client = new ChatServiceClient();
    }

    public String sendMessage(String name) {
        return client.sendRequest(name);
    }

}

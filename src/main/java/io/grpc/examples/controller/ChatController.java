package io.grpc.examples.controller;

import io.grpc.examples.service.ChatGrpcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RequestMapping("/")
@RestController
public class ChatController {

    private final ChatGrpcService service;

    @GetMapping("/message/{username}")
    @ResponseBody
    public String sendMessage(@PathVariable String username) {
        return service.sendMessage(username);
    }

}

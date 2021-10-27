package io.grpc.examples.controller;

import io.grpc.examples.service.ITodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/")
@RestController
@AllArgsConstructor
public class TodoController {

    private final ITodoService grpcService;

    @GetMapping("/todo")
    @ResponseBody
    public String createTodo() {
        return grpcService.createTodo("First todo", "Description of first todo");
    }

}

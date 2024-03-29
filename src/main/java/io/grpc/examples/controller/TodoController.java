package io.grpc.examples.controller;

import io.grpc.examples.entity.TodoEntity;
import io.grpc.examples.service.ITodoService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/todo/{id}")
    @ResponseBody
    public TodoEntity getTodo(@PathVariable String id) {
        return grpcService.getTodo(id);
    }

    @GetMapping("/todos")
    @ResponseBody
    public List<TodoEntity> getTodos() {
        return grpcService.getTodos();
    }

}

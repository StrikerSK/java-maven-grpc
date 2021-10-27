package io.grpc.examples.entity;

import io.grpc.examples.todo.PersistedTodo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TodoEntity {

    private String id;
    private String name;
    private String description;
    private Boolean done;

    public TodoEntity(PersistedTodo todo) {
        this.id = todo.getId();
        this.name = todo.getName();
        this.description = todo.getDescription();
        this.done = todo.getDone();
    }

}

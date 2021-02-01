package com.custom.server.todoist;

import com.custom.generated.todoist.TodoServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.custom.generated.todoist.TodoServiceOuterClass.APIResponse;
import static com.custom.generated.todoist.TodoServiceOuterClass.Empty;
import static com.custom.generated.todoist.TodoServiceOuterClass.Todo;
import static com.custom.generated.todoist.TodoServiceOuterClass.TodoList;

public class TodoService extends TodoServiceGrpc.TodoServiceImplBase {

	private final List<Todo> customTodos = new ArrayList<>();

	@Override
	public void add(Todo request, StreamObserver<APIResponse> responseObserver) {
		APIResponse.Builder response = APIResponse.newBuilder();
		try {
			customTodos.add(request);
			response
					.setResponseCode(200)
					.setResponseMessage("Success");
		} catch (Exception e) {
			response
					.setResponseCode(400)
					.setResponseMessage("Wrong argument");
		}

		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

	@Override
	public void list(Empty request, StreamObserver<TodoList> responseObserver) {
		TodoList.Builder response = TodoList.newBuilder();
		try {
			response
					.addAllTodos(customTodos);
		} catch (Exception e) {
			response
					.addAllTodos(Collections.emptyList());
		}

		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
	}

}

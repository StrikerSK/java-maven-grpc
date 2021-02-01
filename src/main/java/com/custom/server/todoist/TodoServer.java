package com.custom.server.todoist;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class TodoServer {

	public static void main(String[] args) throws IOException, InterruptedException {
		Server server = ServerBuilder.forPort(9090).addService(new TodoService()).build();
		server.start();

		System.out.println("Server started at " + server.getPort());
		server.awaitTermination();
	}

}

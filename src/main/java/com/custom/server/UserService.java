package com.custom.server;

import com.custom.protobuf.User;
import io.grpc.stub.StreamObserver;

import java.util.Arrays;

import static com.custom.protobuf.userGrpc.userImplBase;

public class UserService extends userImplBase {

	@Override
	public void login(User.LoginRequest request, StreamObserver<User.APIResponse> responseObserver) {
		System.out.println("Inside login");

		String username = request.getUsername();
		String password = request.getPassword();

		User.APIResponse.Builder response = User.APIResponse.newBuilder();
		if (username.equals(password)) {
			response
					.setResponseCode(200)
					.setResponseMessage("Success")
					.addAllNumbers(Arrays.asList(123,234,345));
		} else {
			response
					.setResponseCode(401)
					.addNumbers(456)
					.setResponseMessage("Unauthorized");
		}

		responseObserver.onNext(response.build());
		responseObserver.onCompleted();
		System.out.printf("Received username: %s and password: %s%n", username, password);
	}

	@Override
	public void logout(User.Empty request, StreamObserver<User.APIResponse> responseObserver) {
		System.out.println("Inside logout");

	}
}

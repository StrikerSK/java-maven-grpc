package io.grpc.examples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "io.grpc.examples.*")
public class JavaGrpcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaGrpcApplication.class, args);
	}

}

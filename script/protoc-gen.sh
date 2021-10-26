#!/usr/bin/bash

SRC_PATH="./src/main"

JAVA_PATH="${SRC_PATH}/java"

PROTO_PATH="${SRC_PATH}/resources"

cd ..

protoc \
  --plugin=protoc-gen-grpc-java \
  --java_out=${JAVA_PATH} \
  --grpc-java_out=${JAVA_PATH} \
  --proto_path=$PROTO_PATH \
  Chat.proto
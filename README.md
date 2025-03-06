brew install automake
brew install libtool
brew install protobuf


protoc --java_out=./common/src/main/java --proto_path=./common/src/main/resources/proto  ./common/src/main/resources/proto/*.proto

protoc --grpc-java_out=./common/src/main/java --proto_path=./common/src/main/resources/proto  ./common/src/main/resources/proto/*.proto

protoc --plugin=protoc-gen-grpc-java=/Users/wangyifei/Downloads/protoc-gen-grpc-java-1.64.0-osx-aarch_64.exe --grpc-java_out=./common/src/main/java --proto_path=./common/src/main/resources/proto  ./common/src/main/resources/proto/*.proto

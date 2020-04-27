FROM maven:3.6.3-openjdk-14-slim AS build
RUN mkdir -p /home/app
WORKDIR /home/app
COPY pom.xml .
RUN apt-get update && apt-get install -y unzip wget
RUN wget https://github.com/google/protobuf/releases/download/v3.11.4/protoc-3.11.4-linux-x86_64.zip
RUN unzip protoc-3.11.4-linux-x86_64.zip -d protoc3
RUN mv protoc3/bin/* /usr/local/bin/
RUN mkdir -p /root/.m2
COPY toolchains.xml /root/.m2
RUN mvn verify clean --fail-never
COPY src ./src
RUN mvn clean package

FROM openjdk:14.0.1-slim
RUN mkdir -p /home/app
WORKDIR /home/app
COPY --from=build /home/app/target/realchat-1.0-jar-with-dependencies.jar realchat-1.0-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar","realchat-1.0-jar-with-dependencies.jar"]
CMD ["--help"]

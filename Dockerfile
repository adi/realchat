FROM maven:3.6.3-openjdk-14-slim AS build
RUN mkdir -p /home/app
WORKDIR /home/app
COPY pom.xml .
RUN mvn verify clean --fail-never
COPY src ./src
RUN mvn clean package

FROM openjdk:14.0.1-slim
RUN mkdir -p /home/app
WORKDIR /home/app
COPY --from=build /home/app/target/realchat-1.0-jar-with-dependencies.jar realchat-1.0-jar-with-dependencies.jar
ENTRYPOINT ["java","-jar","realchat-1.0-jar-with-dependencies.jar"]
CMD ["--help"]

FROM gradle:8.7-jdk21 AS build
COPY . /home/gradle/project
WORKDIR /home/gradle/project

RUN gradle build --no-daemon

FROM openjdk:21-jdk AS runtime
VOLUME /tmp
COPY --from=build /home/gradle/project/build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

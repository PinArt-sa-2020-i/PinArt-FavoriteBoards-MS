# For Java 11, try this
FROM adoptopenjdk/openjdk11:alpine-jre

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG BUILD_DIR=build
ENTRYPOINT ["java","-jar","app/lib/gs-spring-boot-docker-0.1.0.jar"]
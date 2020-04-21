FROM adoptopenjdk/openjdk11:ubi
VOLUME /tmp
EXPOSE 8080
ADD target/userboards-1.jar userboards-1.jar
ENTRYPOINT ["java","-jar","userboards-1.jar"]
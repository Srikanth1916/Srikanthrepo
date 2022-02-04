FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.11_9 as builder
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract  

FROM adoptopenjdk/openjdk11:x86_64-alpine-jre-11.0.11_9
COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./
ENTRYPOINT ["java","${JAVA_OPTIONS}", "org.springframework.boot.loader.JarLauncher"]
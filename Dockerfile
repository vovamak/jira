FROM openjdk:17-buster
ARG JAR_FILE=target/jira-1.0.jar
COPY ./resources /resources
COPY ${JAR_FILE} /jira-1.0.jar

ENV SPRING_DATASOURCE_URL=jdbc:postgresql://postgres-db:5432/jira
ENV SPRING_DATASOURCE_USERNAME=jira
ENV SPRING_DATASOURCE_PASSWORD=JiraRush

ENTRYPOINT ["java", "-jar", "/jira-1.0.jar"]
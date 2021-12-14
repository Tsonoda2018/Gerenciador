FROM openjdk:11

ARG PROFILE
ARG ADDITIONAL_OPTS

ENV PROFILE=${PROFILE}
ENV ADDITIONAL_OPTS=${ADDITIONAL_OPTS}

WORKDIR /app

COPY /target/gerenciador-financeiro-0.0.1-SNAPSHOT.jar gerenciador-financeiro-mysql.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java ${ADDITIONAL_OPTS} -jar gerenciador-financeiro-mysql.jar --spring.profiles.active=${PROFILE}

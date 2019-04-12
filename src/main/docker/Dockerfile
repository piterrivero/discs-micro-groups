FROM openjdk

VOLUME /tmp
ADD maven/groups-0.0.1-SNAPSHOT.jar groups.jar
RUN sh -c 'touch /groups.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/groups.jar"]

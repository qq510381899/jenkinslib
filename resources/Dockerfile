FROM java:8

VOLUME /tmp

COPY target/spring-cloud-eureka.jar /tmp/

EXPOSE 8761

ENTRYPOINT ["java","-jar","/tmp/spring-cloud-eureka.jar"]

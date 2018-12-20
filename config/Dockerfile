FROM java:8

ADD target/*.jar app.jar

EXPOSE 6062

ENTRYPOINT ["java","-jar","/app.jar"]
FROM mysql:5.6

ADD target/*.jar app.jar

EXPOSE 3306

ENTRYPOINT ["java","-jar","/app.jar"]
FROM openjdk:18
EXPOSE 8081
ADD target/AmraChat_Usr_Clnt_API-0.0.1-SNAPSHOT AmraChat_Usr_Clnt_API-0.0.1-SNAPSHOT
ENTRYPOINT ["java","-jar","/AmraChat_Usr_Clnt_API-0.0.1-SNAPSHOT"]

# Start with a base image containing Javaruntime
FROM openjdk:21-jdk-slim

# Information around who maintains the image
LABEL org.opencontainers.image.authors="eazybytes.com"

# Add the application's jar to the image
COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

# Execute the application
ENTRYPOINT ["java", "-jar", "account-0.0.1-SNAPSHOT.jar"]
# Use an official Maven image to build the Spring boot app
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set the Working directory
WORKDIR /app

#Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

#Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use an official OpenJDK image to run the application
FROM eclipse-temurin:21-jdk-jammy

#Set the working directory
WORKDIR /app

#Copy the built JAR file from the build stage
COPY --from=build /app/target/FundTheStory-0.0.1-SNAPSHOT.jar .

#Expose port 8086
EXPOSE 8086

#Specify the command to run the application
ENTRYPOINT ["java","-jar","/app/FundTheStory-0.0.1-SNAPSHOT.jar"]


# Step 1: Use an official JDK base image
FROM openjdk:17-jdk-slim

# Step 2: Set working directory inside container
WORKDIR /app

# Step 3: Copy jar file into container
COPY target/Employee-prom-0.0.1-SNAPSHOT.jar app.jar

# Step 4: Expose the application port
EXPOSE 8085

# Step 5: Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]

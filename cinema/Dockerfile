# ---- Etapa 1: compilar ----
FROM maven:3.9.6-eclipse-temurin-17 AS builder

WORKDIR /app

# Copiar primero solo el pom.xml para aprovechar caché de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar el código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests -B

# ---- Etapa 2: imagen final ligera ----
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar el JAR generado en la etapa anterior
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
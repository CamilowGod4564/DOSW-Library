# Etapa 1: Construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar pom.xml y descargar dependencias (cacheado en capas)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (JRE es suficiente, más liviano que JDK)
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Instalar curl para healthcheck
RUN apk add --no-cache curl

# Copiar el JAR generado
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto
EXPOSE 8080

# Usar exec para que las señales del sistema (SIGTERM) lleguen al proceso Java
ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -jar app.jar"]

### ======== STAGE 1: BUILD ========
FROM maven:3-amazoncorretto-17-alpine AS build

# Directorio de trabajo
WORKDIR /app

# Copiar pom.xml
COPY pom.xml .

# Descargar dependencias antes del source (cache inteligente)
RUN mvn dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Construir el jar
RUN mvn clean package -DskipTests


### ======== STAGE 2: RUNTIME ========
FROM amazoncorretto:17-alpine AS deploy

# Directorio de trabajo
WORKDIR /app

# Copiar el jar generado
COPY --from=build /app/target/*.jar app.jar

# Exponer puerto del microservicio de emails
EXPOSE 8060

# Variables de entorno opcionales para mail (pueden venir de compose)
ENV SPRING_PROFILES_ACTIVE=prod

# Levantar microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]
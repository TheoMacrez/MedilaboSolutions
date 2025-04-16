# Étape 1 : image de base avec Java 21
FROM openjdk:21-jdk-slim

# Étape 2 : définir le répertoire de travail dans l’image
WORKDIR /app

# Étape 3 : copier le .jar depuis ton projet local vers l’image
COPY target/medilabo-diabetes-assessment-0.0.1-SNAPSHOT.jar app.jar

# Étape 4 : exposer le port sur lequel l'application va écouter
EXPOSE 8084

# Étape 4 : définir la commande à exécuter au lancement du conteneur
ENTRYPOINT ["java", "-jar", "app.jar"]
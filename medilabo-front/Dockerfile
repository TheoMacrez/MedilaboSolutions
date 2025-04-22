# Étape 1 : image de base avec Java 21
FROM openjdk:21-jdk-slim

# Étape 1.5 : installer curl
RUN apt-get update && apt-get install -y curl

# Étape 2 : définir le répertoire de travail dans l’image
WORKDIR /app

# Étape 3 : copier le .jar et le script d'entrypoint dans l’image
COPY target/medilabo-front-0.0.1-SNAPSHOT.jar app.jar
COPY entrypoint.sh entrypoint.sh

# Étape 4 : rendre le script exécutable
RUN chmod +x entrypoint.sh

# Étape 4 : exposer le port sur lequel l'application va écouter
EXPOSE 8082

# Étape 4 : définir la commande à exécuter au lancement du conteneur
ENTRYPOINT ["./entrypoint.sh"]
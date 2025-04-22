#!/bin/sh

# Petite sécurité
set -e

echo "🔎 Waiting for Keycloak to be ready at http://keycloak:8080/realms/medilabo/.well-known/openid-configuration ..."

# On boucle tant que Keycloak ne répond pas correctement
until curl -s --fail http://keycloak:8080/realms/medilabo/.well-known/openid-configuration > /dev/null; do
  echo "⏳ Keycloak not ready yet. Waiting 5 seconds..."
  sleep 5
done

echo "✅ Keycloak is ready! Starting the application..."

# Lance l'appli Java
exec java -jar app.jar

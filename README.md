
# MedilaboSolutions

MedilaboSolutions est une suite d'applications médicales permettant de centraliser les données des patients, d'analyser les risques de diabète, et de faciliter la communication entre les professionnels de santé.
Le projet repose sur une architecture microservices pour assurer modularité, scalabilité et maintenance aisée.


## Architecture

Le projet est constitué de cinq microservices principaux :

* [**medilabo-patient**](https://github.com/TheoMacrez/MedilaboSolutions/tree/main/medilabo-patient) : Service de gestion des patients, basé sur une base de données MySQL.

* [**medilabo-notes**](https://github.com/TheoMacrez/MedilaboSolutions/tree/main/medilabo-notes) : Service de gestion des notes médicales, basé sur MongoDB.

* [**medilabo-diabetes-assessment**](https://github.com/TheoMacrez/MedilaboSolutions/tree/main/medilabo-diabetes-assessment) : Service d'analyse de risques de diabète, consommant les API patient et note.

* [**medilabo-gateway**](https://github.com/TheoMacrez/MedilaboSolutions/tree/main/medilabo-gateway) : Gateway unifiant les accès aux services via Spring Cloud Gateway.

* [**medilabo-front**](https://github.com/TheoMacrez/MedilaboSolutions/tree/main/medilabo-front) : Front-end en Spring Boot utilisant Thymeleaf et OAuth2 avec Keycloak pour l'authentification.

Chaque microservice est déployé indépendamment et communique via des API REST.
## Bonnes pratiques GreenCode

Afin de rendre le projet plus respectueux de l'environnement et durable, voici un ensemble d'actions concrètes recommandées :

### 1. Optimisation des performances
* Réduire les calculs inutiles : Simplifier les algorithmes dans les services comme AssessmentService, éviter les opérations redondantes (par exemple, ne calculer l'âge du patient qu'une seule fois).

* Utiliser le cache : Mettre en place un système de cache (comme Caffeine ou Redis) pour les données rarement modifiées (dossiers patients).

### 2. Réduction de la consommation mémoire
* Utilisation efficace des objets : Éviter la création de collections ou objets intermédiaires inutiles.

* Structures de données adaptées : Utiliser les structures les plus légères et efficaces selon le contexte (ex. HashSet pour des recherches rapides au lieu de List).

### 3. Architecture et infrastructure
* Microservices légers : Réduire la taille des réponses API et minimiser le nombre d'appels interservices.

* Choix d'infrastructure verte : Si hébergement cloud, privilégier des fournisseurs engagés pour la durabilité (ex : AWS Green Energy, Google Cloud Sustainability).

### 4. Algorithmes plus écologiques
* Optimisation des requêtes : S'assurer que toutes les requêtes MongoDB ou MySQL soient bien indexées et optimisées pour éviter les traitements lourds.

* Réduction de la complexité algorithmique : Favoriser des solutions en temps linéaire (O(n)) ou quasi-linéaire.

### 5. Tests et automatisation
* Tests de performance : Ajouter des tests de charge dans le CI/CD pour surveiller les dérives de consommation.

* Évaluation énergétique : Utiliser des outils d'estimation de consommation énergétique (ex : Cloud Carbon Footprint pour les environnements cloud).

### 6. Monitoring et audits
* Surveillance de la consommation : Implémenter du monitoring (ex : Prometheus + Grafana) pour observer la consommation des ressources.

* Audits réguliers : Planifier des audits pour détecter des inefficacités ou gaspillages dans les services.

### 7. Formation et sensibilisation
* Sessions Green Code : Organiser des formations internes sur les bonnes pratiques écologiques en développement logiciel.

* Documentation : Ajouter des guides internes sur l'impact environnemental des choix d'architecture et de code.

## Conclusion

En adoptant progressivement ces bonnes pratiques, MedilaboSolutions pourra non seulement améliorer ses performances, mais aussi réduire son empreinte carbone numérique.
Le Green Code n'est pas seulement un geste pour l'environnement : il conduit également à une architecture plus rapide, plus légère et plus fiable.
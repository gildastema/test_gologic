[![Java CI with Maven](https://github.com/gildastema/test_gologic/actions/workflows/maven.yml/badge.svg)](https://github.com/gildastema/test_gologic/actions/workflows/maven.yml)

# Projet Test Gologic

    Le projet comporte 2 branches
    -   La branche "base" : qui contient la premiere version avec les tests mais sans le support address
    - La branche principal "main" contient les tests et le support de la gestion des addresses

# JAVA version Minimal 11

    Dans les sources du projet j ai beaucoup utilise "var" qui a ete introduit sous java 11


# Lancer le projet 
    ## Avec Maven installer globalement
````bash
    mvn clean package
````

    ## Sans maven installer de facon global
Aller dans le projet 
````bash
    ./mvnw clean  package 
````

````bash
    java -jar target/test-0.0.1-SNAPSHOT.jar

````

# Ajout du Support de la CI avec Git Action

# Organisation des tests

    - Le dossier Units/ contient les tests unitaires
    - Le dossier Features/ Lui contient les tests d'integrations

Le port par defaut est le 8080

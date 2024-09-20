# Blog System API

Das Blog System bietet eine RESTful API, die es ermöglicht, Blogposts zu verwalten. Dieses Backend wurde entwickelt, um grundlegende CRUD-Operationen (Create, Read, Update, Delete) auf Blogposts durchzuführen.

## Technologien

- **Quarkus**: Modernes Java-Framework, das eine schnelle Boot-Zeit und eine effiziente Ausführung in produktionsähnlichen Umgebungen ermöglicht.
- **Jakarta RESTful Web Services (JAX-RS)**: Ermöglicht die Erstellung von RESTful Web Services in Java.
- **JPA (Jakarta Persistence API)**: Verwendet für die ORM (Object-Relational Mapping) zum effizienten Zugriff und zur Verwaltung der Datenbankoperationen.

## Setup und Installation

Um dieses Projekt lokal zu starten, folgen Sie diesen Schritten:

1. **Voraussetzungen**: Stellen Sie sicher, dass JDK 11 oder höher und Maven installiert sind.
2. **Repository klonen**:
   ```bash
   git clone [URL zu Ihrem Repository]
   cd [Projektverzeichnis]
3. **Quarkus Starten**: Quarkus im Dev-Modus starten:
    ```bash
    mvn quarkus:dev

## API-Endpunkte

Die API unterstützt folgende Endpunkte:

### Blogposts abrufen

- **GET /blogs**: Listet alle Blogposts.
- **GET /blogs/{id}**: Ruft einen spezifischen Blogpost anhand seiner ID ab.

### Blogpost erstellen

- **POST /blogs**:
  Erstellt einen neuen Blogpost.
  - **Body**:
    ```json
    {
      "title": "Titel des Blogposts",
      "author": "Autor des Blogposts",
      "content": "Inhalt des Blogposts"
    }
    ```

### Blogpost aktualisieren

- **PUT /blogs/{id}**:
  Aktualisiert einen bestehenden Blogpost.
  - **Body**:
    ```json
    {
      "title": "Neuer Titel",
      "author": "Neuer Autor",
      "content": "Neuer Inhalt"
    }
    ```

### Blogpost löschen

- **DELETE /blogs/{id}**:
  Löscht einen Blogpost anhand seiner ID.

## Fehlerbehandlung

Die API gibt standardisierte Fehlercodes und Nachrichten zurück, falls Probleme auftreten. Zum Beispiel:

- **404 Not Found**: Wenn ein Blogpost mit der angegebenen ID nicht gefunden werden kann.
- **400 Bad Request**: Wenn die Anforderung fehlerhafte Daten enthält.

## Docker

Dieses Projekt ist eine Quarkus-basierte Blog-Applikation mit Keycloak-Authentifizierung und Docker-Containerisierung.

## Voraussetzungen

- **Docker** und **Docker-Compose**
- **Maven**
- **MySQL**

## Keycloak Konfiguration

1. **Starte Keycloak:**
   ```bash
   docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin bitnami/keycloak:latest
   ```

2. **Erstelle einen Realm:**
    - Name: `blog-realm`

3. **Erstelle einen Client:**
    - Client ID: `blog-client`
    - Client-Protokoll: `openid-connect`
    - Zugangstyp: `confidential`
    - Root-URL: `http://localhost:8081`

4. **Füge Rollen hinzu:**
    - `user`, `admin`

5. **Erstelle einen Admin-Benutzer:**
    - Benutzername: `admin`
    - Passwort: `admin1234`
    - Rolle: `admin`

## Quarkus Konfiguration

Erstelle eine Datei `application.properties` im Ordner `src/main/resources` mit folgendem Inhalt:

```properties
# Datenbank-Konfiguration
quarkus.datasource.db-kind=mysql
quarkus.datasource.username=myuser
quarkus.datasource.password=mypassword
quarkus.datasource.jdbc.url=jdbc:mysql://mysql:3306/mydb

# CORS Konfiguration
quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:8081,http://localhost:53377
quarkus.http.cors.methods=GET,POST,PUT,DELETE,OPTIONS
quarkus.http.cors.headers=accept,authorization,content-type,x-requested-with
quarkus.http.cors.access-control-allow-credentials=true

# Hibernate ORM Konfiguration
quarkus.hibernate-orm.database.generation=update
quarkus.hibernate-orm.log.sql=true

# OpenAPI Konfiguration
mp.openapi.extensions.enabled=true
quarkus.smallrye-openapi.path=/openapi
quarkus.smallrye-openapi.info.title=Blog Backend API
quarkus.smallrye-openapi.info.version=1.0.0
quarkus.smallrye-openapi.info.description=API für das Blog Backend
quarkus.swagger-ui.always-include=true
quarkus.swagger-ui.path=/swagger-ui

# Keycloak Konfiguration
quarkus.oidc.auth-server-url=http://localhost:8080/realms/blog-realm
quarkus.oidc.client-id=blog-client
quarkus.oidc.credentials.secret=iIulodrT0jaIYHNkTzZOoM7sokDwcWyx
quarkus.oidc.application-type=web-app
quarkus.http.auth.permission.roles.paths=/*
quarkus.http.auth.permission.roles.policy=authenticated

# Keycloak Admin-Client Konfiguration
quarkus.keycloak.admin-client.server-url=http://localhost:8080
quarkus.keycloak.admin-client.realm=blog-realm
quarkus.keycloak.admin-client.client-id=admin-cli
quarkus.keycloak.admin-client.username=admin
quarkus.keycloak.admin-client.password=admin1234

# Quarkus Server Konfiguration
quarkus.http.port=8081
```

## Docker-Containerisierung

### Dockerfile

Das `Dockerfile.jvm` befindet sich im Ordner `src/main/docker`. Der Inhalt des `Dockerfile.jvm` sieht wie folgt aus:

```dockerfile
# src/main/docker/Dockerfile.jvm

# 1. Stufe: Build Stage
FROM quay.io/quarkus/ubi-quarkus-mandrel-builder-image:22.3-java17 AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests -Dquarkus.package.type=fast-jar

# 2. Stufe: Run Stage
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/quarkus-app /app/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/quarkus-run.jar"]
```

### Docker-Compose Datei

Erstelle eine `docker-compose.yml` Datei im Stammverzeichnis des Projekts mit folgendem Inhalt:

```yaml
version: '3.8'

services:
  keycloak:
    image: bitnami/keycloak:latest
    environment:
      - KEYCLOAK_ADMIN=admin
      - KEYCLOAK_ADMIN_PASSWORD=admin
    ports:
      - 8080:8080

  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: rootpassword
      MYSQL_DATABASE: mydb
      MYSQL_USER: myuser
      MYSQL_PASSWORD: mypassword
    ports:
      - 3306:3306

  blog-system:
    build:
      context: .
      dockerfile: src/main/docker/Dockerfile.jvm  # Pfad zum Dockerfile.jvm
    ports:
      - 8081:8080
    environment:
      QUARKUS_DATASOURCE_JDBC_URL: jdbc:mysql://mysql:3306/mydb
      QUARKUS_DATASOURCE_USERNAME: myuser
      QUARKUS_DATASOURCE_PASSWORD: mypassword
      QUARKUS_OIDC_AUTH_SERVER_URL: http://keycloak:8080/realms/blog-realm
      QUARKUS_OIDC_CLIENT_ID: blog-client
      QUARKUS_OIDC_CREDENTIALS_SECRET: iIulodrT0jaIYHNkTzZOoM7sokDwcWyx
    depends_on:
      - keycloak
      - mysql
```

### Anwendung starten

1. Erstelle das Docker Image und starte die Container:
    ```bash
    docker-compose up --build
    ```

2. Die Anwendung ist jetzt unter `http://localhost:8081` verfügbar und nutzt Keycloak zur Authentifizierung.

## Anwendung im Entwicklungsmodus starten

1. Starte MySQL in einem separaten Docker Container:
   ```bash
   docker run --name mysql -e MYSQL_ROOT_PASSWORD=rootpassword -e MYSQL_DATABASE=mydb -e MYSQL_USER=myuser -e MYSQL_PASSWORD=mypassword -p 3306:3306 -d mysql:8.0
   ```

2. Starte Keycloak:
   ```bash
   docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin bitnami/keycloak:latest
   ```

3. Starte die Quarkus Anwendung:
   ```bash
   ./mvnw quarkus:dev "-Dquarkus.package.type=fast-jar" -DskipTests
   ```

4. Die Anwendung ist jetzt unter `http://localhost:8081` erreichbar.

Ein öffentliches Docker-Image für das Blog-System ist im GitHub-Paket-Registry verfügbar.

### Docker-Image abrufen

Um das Image herunterzuladen, verwenden Sie den folgenden Befehl:

```bash
docker pull ghcr.io/<dein-github-username>/blogSystem:latest
```

## Ausführen des Docker-Containers
Um den Container auszuführen, verwenden Sie:

```bash
docker run -d \
  -e DB_HOST=<db_host> \
  -e DB_USER=<db_user> \
  -e DB_PASSWORD=<db_password> \
  -e AUTH_SERVER_URL=<auth_server_url> \
  -p 8080:8080 \
  ghcr.io/<dein-github-username>/blogSystem:latest
```

## Beispiel für Authentifizierung und Zugriff
Sie können sich authentifizieren und das System mit den folgenden Befehlen testen:

- Token abrufen (ersetzen Sie <benutzername> und <passwort> durch Ihre Anmeldedaten):

```bash
curl -X POST <auth_server_url>/token -d "username=<benutzername>&password=<passwort>"
```
- Zugriff auf die API: Verwenden Sie das Token, um auf geschützte Ressourcen zuzugreifen:

```bash
curl -H "Authorization: Bearer <dein_access_token>" http://localhost:8080/api/endpoint
```

## Erforderlich Untersysteme
Stellen Sie sicher, dass die folgenden Untersysteme ausgeführt werden:

- Datenbank: Stellen Sie sicher, dass Ihre Datenbank läuft und zugänglich ist.
- Authentifizierungsserver: Stellen Sie sicher, dass Ihr Authentifizierungsserver aktiv ist.

## Beitrag und Support

Contributions sind willkommen. Falls Sie einen Fehler finden oder eine Erweiterung vorschlagen möchten, öffnen Sie bitte ein Issue oder einen Pull Request in diesem Repository.


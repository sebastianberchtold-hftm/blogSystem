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

## Beitrag und Support

Contributions sind willkommen. Falls Sie einen Fehler finden oder eine Erweiterung vorschlagen möchten, öffnen Sie bitte ein Issue oder einen Pull Request in diesem Repository.


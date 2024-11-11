Shop Datenbankmodellierung Projekt
Dieses Projekt ist eine Spring Boot-Anwendung zur Verwaltung eines Online-Shops. Es stellt verschiedene REST-APIs
bereit, um mit Entitäten wie Adresse, Artikel, Bestellung, Kunde und Warenkorb zu interagieren. Die Anwendung bietet
CRUD-Operationen und ermöglicht das Hinzufügen und Entfernen von Artikeln in Bestellungen und Warenkörben.

Projektstruktur
Controller: Verantwortlich für die Bereitstellung der API-Endpunkte und die Verarbeitung der HTTP-Anfragen.
DTOs (Data Transfer Objects): Übertragen Daten zwischen der API und dem Service. Es gibt RequestDTO- und
ResponseDTO-Klassen für jede Entität.
Services: Geschäftsschicht zur Handhabung der Anwendungslogik und Kommunikation mit der Datenbank.
Mapper: Konvertiert zwischen Entitäten und DTOs.
Entities: Datenbank-Entitäten, die die Tabellen in der Datenbank repräsentieren.
API Endpunkte
Adresse
GET /api/v1/adressen: Abrufen aller Adressen.
GET /api/v1/adressen/{id}: Abrufen einer Adresse nach ID.
POST /api/v1/adressen: Erstellen einer neuen Adresse.
DELETE /api/v1/adressen/{id}: Löschen einer Adresse nach ID.
PUT /api/v1/adressen/{adresseId}/kunden/{kundeId}: Kunde zu einer Adresse hinzufügen.
DELETE /api/v1/adressen/{adresseId}/kunden/{kundeId}: Kunde von einer Adresse entfernen.
Artikel
GET /api/v1/artikel: Abrufen aller Artikel.
GET /api/v1/artikel/{id}: Abrufen eines Artikels nach ID.
POST /api/v1/artikel: Erstellen eines neuen Artikels.
DELETE /api/v1/artikel/{id}: Löschen eines Artikels nach ID.
Bestellung
GET /api/v1/bestellungen: Abrufen aller Bestellungen.
GET /api/v1/bestellungen/{id}: Abrufen einer Bestellung nach ID.
DELETE /api/v1/bestellungen/{id}: Löschen einer Bestellung nach ID.
POST /api/v1/bestellungen/addArticleToOrder: Artikel zu einer Kundenbestellung hinzufügen.
Kunde
GET /api/v1/kunden: Abrufen aller Kunden.
GET /api/v1/kunden/{id}: Abrufen eines Kunden nach ID.
POST /api/v1/kunden: Erstellen eines neuen Kunden.
PUT /api/v1/kunden/{id}: Aktualisieren eines bestehenden Kunden.
DELETE /api/v1/kunden/{id}: Löschen eines Kunden nach ID.
Warenkorb
GET /api/v1/warenkoerbe: Abrufen aller Warenkörbe.
GET /api/v1/warenkoerbe/{id}: Abrufen eines Warenkorbs nach ID.
DELETE /api/v1/warenkoerbe/{id}: Löschen eines Warenkorbs nach ID.
POST /api/v1/warenkoerbe/addArticle: Artikel zu einem Kundenwarenkorb hinzufügen.

Hinweise
DTOs: Verwenden Sie DTOs zur Validierung und Transformation der Eingabedaten.
Mapper: Die Mapper-Klassen übernehmen die Konvertierung zwischen Entitäten und DTOs.
Fehlerbehandlung: Die Anwendung gibt sinnvolle HTTP-Statuscodes zurück, um den Status jeder Anfrage anzuzeigen (z. B.
200 OK, 404 Not Found).
Lizenz
Dieses Projekt steht unter der MIT-Lizenz.
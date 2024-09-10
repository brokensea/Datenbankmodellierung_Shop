Kunde                      Adresse                        Bestellung                  Artikel
-----------------          -----------------              -----------------           -----------------
| kunde_id (PK)  |n      1| adresse_id (PK)   |           | bestellung_id (PK) |      | artikel_id (PK) |
| name           |--------| strasse           |           | kunde_id (FK)      |      | name             |
| email          |        | hausnummer        |           | datum              |      | beschreibung     |
| telefonnummer  |        | stadt             |           | status             |      | preis            |
| adresse_id (FK)|        | postleitzahl      |           | gesamtpreis        |      | lagerbestand     |
| registrierungsdatum|    | land              |           -----------------           | kategorie        |
-----------------          -----------------                                    -----------------

Warenkorb                                               Bestellung_Artikel
-----------------                                       -----------------
| warenkorb_id (PK)|                                    | bestellung_id (FK)|
| kunde_id (FK)    |                                    | artikel_id (FK)   |
-----------------                                    | anzahl           |
                                                      | einzelpreis       |
Warenkorb_Artikel                                      -----------------
-----------------
| warenkorb_id (FK)|
| artikel_id (FK)  |
| anzahl          |
-----------------




Kunde                  Bestellung                  Artikel
-----------------      -----------------           -----------------
| kunde_id (PK)  |1   n| bestellung_id (PK) |      | artikel_id (PK) |
| name           |---->| kunde_id (FK)      |n   n| name             |
| email          |     | datum              |      | beschreibung     |
| telefonnummer  |     | status             |      | preis            |
| adresse        |     | gesamtpreis        |      | lagerbestand     |
| registrierungsdatum|  -----------------           | kategorie        |
-----------------                                    -----------------
          1 |                             
            |n                                        n
Warenkorb   |                                     Bestellung_Artikel
-----------------                                 -----------------
| warenkorb_id (PK)|                                | bestellung_id (FK)|
| kunde_id (FK)    |                                | artikel_id (FK)   |
-----------------                                 | anzahl           |
                                                 | einzelpreis       |
Warenkorb_Artikel                                   -----------------
-----------------
| warenkorb_id (FK)|
| artikel_id (FK)  |
| anzahl          |
-----------------



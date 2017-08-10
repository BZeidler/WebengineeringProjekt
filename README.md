# WebengineeringProjekt
Projekt für VL Webengineering, Sommersemester 2017

## (Bisherige) Vorab Planung
Hier steht tatsächlich eher eine Textfassung der Anforderungen an das Projekt. Dient mehr als "Checkliste" für mich.

### Benötigt im Frontend
- Art StartSeite
  - SignUp
    - Eingabe e-Mail && PW && Nutzer-Text
    - Danach: Auto Login || Startseite 
  - Login
    - Anzeige persönliche(?) Seite, aktiviereung/Verwendbarkeit von fratching Funktionen
- Persönliche Seite
  - Find Match
      - Anzeige von neuem, unbekannten Text
      - ggf priorisierung von "halben" Matches 
        - voller Match: Nutzer A mag Nutzer B Text && vice versa
        - halber Match: Nutzer A mag Nutzer B Text
      - Like / Dislike
        - Dislike speichern
        - Like 
          - Falls: Bisher Nutzer B mag Nutzer A, nun Nutzer A mag Nutzer B -> Match
          - sonst: halber Match, ggf bei/für Gegenseite vermerken (für priorisierung)   
        
  - List Matches
    - Anzeige aller vorhandenen (vollen) Matches
      - Nutzername + TimeStamp + letzte chat Nachricht
      - "Chatroom" betreten   
  - Chatting
    - Anzeige Name(Chat Partner)
    - Anzeige TimeStamp + Chatnachrichten
    - Eingabewidget + Button
  
### Benötigt im Backend (DB)
Alle Daten die in irgendeiner Form in der DB gespeichert werden müssen, sortiert nach Funktionen.
-  ~~SignUp: neuer Nutzer: Anzeige Name(?), Mail, PW, Text~~
- ~~Find Match~~
  - ~~like Texte/Nutzer (soll nicht nocheinmal gezeigt werden)~~
  - ~~dislike Texte/Nutzer (soll nicht nocheinmal gezeigt werden)~~
  - ~~likedBy (priorisierung dieser Texte)~~(bereits enthalten in like, keine Änderung)
- ~~List Matches~~
  - ~~Vorhandene Matches, Paar von 2 Nutzern~~
- ~~Chatting~~
  - ~~Chatverlauf: Autor, TimeStamp, Text~~
  
### Benötigt im Backend (Java)
- ~~Funktion SignUp~~
  - ~~neuen Nutzer in DB einfügen (Eintrag in DB)~~
  - ~~ggf. Login~~ (Token wird returned) 
- ~~Funktion Login (theor. Fertig aus VL ?)~~
  - ~~???~~
- ~~Funktion findMatch~~
  - ~~Rückgabe Nutzer/Text (laden aus DB)~~
  - ~~Falls "likedBy" !leer (laden aus DB)~~
    - ~~ersten Eintrag wählen~~
    - ~~Sonst anderen Nutzer/Text, wobei~~ 
      - ~~Nutzer nicht in Like && Nutzer nicht in Dislike~~
    - ~~Sonderfall: es gibt keine neuen Texte (Alle liked, disliked)~~
    	- ~~???~~ 
- ~~Funktion listMatch~~
  - ~~Rückgabe aller Matches (laden aus DB)~~
  - ~~Ausserdem letzter Timestamp + Chatnachricht (laden aus DB, Chat)~~
- ~~Funkltion Chat~~
  - ~~Anzeige/Rückgabe Verlauf (laden aus DB)~~
  - ~~neue Nachricht senden (Eintrag in DB)~~
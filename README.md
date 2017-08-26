# WebengineeringProjekt
Projekt für VL Webengineering, Sommersemester 2017
 
Vorab soll erwähnt werden dass zwar alles geforderte implementiert sein sollte (solange ich nichts übersehen/vergessen habe).
ABER:
Es läuft nicht auf Heroku. Ich weiss aktuell nicht wieso oder warum, aber wenn ich versuche die Webseite aufzurufen kommt meine heißgeliebte "Whitelabel Error Page", mehr dazu später. Die Heroku logs geben keinen Fehler aus (also auch keinen crashed state), also sollte theoretisch doch alles funktionieren und die Dev Konsole in Chrome meldet nur 404 Not Found. Ich habe nun einen guten halben Tag im Dunkeln gestochert und habe noch immer keinen Ansatz, also belasse ich es ein wenig entnervt dabei.

Dann sei noch erwähnt dass ich das ganze in einem GitHub Repository habe, [hier](https://github.com/BZeidler/WebengineeringProjekt) zu finden, da gibt es auch alle Statistiken zu Comitts, Autor etc.

Zur Entwicklung selbst: Weil ich tatsächlich nicht andauernd zwischen backend und frontend hin und her springen wollte, habe ich zunächst das backend geschrieben und danach das frontend. Der Hauptgrund hierfür war schlicht dass ich nicht sonderlich viel Vertrauen in meine JavaScript kenntnisse gesetzt habe (nach wie vor nicht) und ich erst den "einfachen" Teil Hinter mich bringen wollte. Anfangs war ich tatsächlich auch halbwegs stolz darauf, dass ich tatsächlich recht viel Funktionalität in Form von SQL Befehlen umsetzen konnte (insbesondere das grobe Filtern von neuen Match Vorschlägen). Jetzt gegen Ende sind mir aber einige Fehler darin aufgefallen und ich habe es durch Java Quelltext ersetzt. Mit anderen Worten: Der Teil auf über den ich mich gefreut habe existiert nicht mehr. Erwähnen sollte ich hierbei eventuell noch die Entscheidung keine Kommentare vor die Funktionen geschrieben zu haben. Das ist tatsächlich bewusst, und basiert auf einer rein persönlichen Abneigung gegen diese Form der Kommentierung (IntelliSense nennt sich das glaube ich). Ich sehe zwar ein, dass ein anderer Entwickler Vorteile daraus ziehen kann wenn der Quelltext sonst nicht einsehbar ist, aber das ist hier einfach nicht der Fall. Ich habe das selbst geschrieben und ich finde dass diese Kommentare den Quelltext unnötig strecken und letztenendes weniger lesbar machen. Das setzt natürlich eine halbwegs vernünftige Namensgebung voraus, was ich anstrebe.    

Zum Frontend gibt es nicht wirklich etwas zu sagen. Das Geschulte Auge wird sehen dass der Großteil tatsächlich das Vorlesungsprojekt ist. Nur modifiziert, dass es tut was es eben soll. JavaScipt ist mir, als C++ Entwickler, nach wie vor ein wenig suspekt. 

Wirklich nennenswerte "Entwicklungsentschidungen" gibt es in meinen Augen tatsächlich auch nicht. Ich habe mir zu Beginn überlegt was ich brauche und das dann so implementiert wie ich es sinnvoll finde, jeweils eine Funktionalität nach der anderen. Das spiegelt sich denke ich auch in den Commits wieder (nichts -> Viele in kurzem Zeitfenster -> nichts). Das ist wieder eine persönliche Vorliebe: Ich möchte Dinge tatsächlich Fertig haben(in der Gesamtheit), bevor ich sie comitte. Die Einzige tatsächlichce Entscheidung in diesem Sinne war das Zusammenführen von drei Tabellen zu einer einzigen: Aus den Tabellen Like, Dislike und Match wurde eine Tabelle, die den Status zwischen zwei Nutzern darstellt. Tatsächlich wollte ich einfach nicht für jeden neuen Vorschlag auf drei unterschiedliche Tabellen zugreifen müssen nur um alle Nutzer zu finden die ich NICHT vorschlagen darf (ich suche ja nach neuen Vorschlägen), Ausserdem sollten alle diese Tabellen mehr oder weniger die selbe Information speichern: "Es gibt eine Beziehung zwischen diesen beiden Nutzern". Diese Beziehung kann ein Like, Dislike oder ein Match sein.

Am Ende gibt es dann noch das Kapitel "(Bisherige) Vorab Planung", dass tatsächlich nur als Checkliste von mir genutzt wurde um einen Überblick über die umzusetzenden Features zu haben. Ich habe es stehen gelassen weil ich keinen Nachteil darin sehe.

## Whitelabel Error Page
Wie in den commit-Nachrichten ersichtlich ist habe ich mitten während der Entwicklung einen Betriebssystem-Wechsel durchgeführt. Das gesamte Backend wurde unter Windows mit Eclipse Neon 3 geschrieben. Dann sollte das Frontend geschrieben werden wobei mir diese "Whitelabel Error Page" begegnet ist. Ich muss zugeben dass ich noch immer nicht wirklich verstehe was das Problem ist. Es sah allerdings so aus als wolle Spring eine Datei auf meinem PC (mit Pfad C:\. etc. ) als URL auflösen (ohne file:///), was fehlgeschlagen ist. Nach einiger Zeit der Ratlosigkeit habe ich dann eine Virtuelle Maschine mit Ubuntu aufgesetzt und darin weiter entwickelt. Das lief auch ohne Probleme. Insgesamt hat mich das ganze ca anderthalb Tage (teilweise wegen der durchaus langsamen Internetverbindung die sich durch ganze 1.5GB Ubuntu ISO gequält hat) gekostet, was nicht wirklich angenehm war. Letztenendes konnte mit der VM das Projekt aber abgeschlossen werden, mit ausnahme von Heroku.    

## "Installation"
Das Projekt baut mit Maven, Ziele wie compile oder install funktionieren und sind getestet (unter Windows 10 und Ubuntu 16 ).
Kommandozeilen Befehle sollten also funktionieren wie gewohnt.

Da ich selbst allerdings Eclipse zur Entwicklung verwendet habe folgt noch eine exemplarische Anleitung für Eclipse neon 3.

- [Download](http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/neon3), entpacken und ausführen

- Willkommensseite schließen

- Window -> Perspective -> Open Perspective -> other -> git
- Clone a Git repository
- in URI: https://github.com/BZeidler/WebengineeringProjekt eintragen -> next -> next
- ggf Directory anpassen
- Finish
- In Registerkarte "Git Repositories" rechtsklick auf Working Tree (muss ggf erst aufgeklappt werden) -> Import Projects 
- ggf Ordner Anpassen
- Finish
- Ansicht wechseln zu Java, entweder
  - Window -> Perspective -> Open Perspective -> other -> Java, oder
  - oben rechts in der Ecke 
- Rechtsklick auf das Projekt -> Run As -> Maven Build... -> entsprechendes goal eintragen
- src/main/java/bernhardZeidler/projekt aufklappen,
- rechtsklick auf Main.java -> Run As -> Java Application

## (Bisherige) Vorab Planung
Hier steht tatsächlich eher eine Textfassung der Anforderungen an das Projekt. Dient mehr als "Checkliste" für mich.

### Benötigt im Frontend
- Art StartSeite
  - SignUp
    - Eingabe e-Mail && PW && Nutzer-Text
    - Danach: Auto Login(passiert im backend) || Startseite 
  - ~~Login~~
    - ~~Anzeige persönliche(?) Seite, aktiviereung/Verwendbarkeit von fratching Funktionen
- Persönliche Seite
  - ~~Find Match~~
      - ~~Anzeige von neuem, unbekannten Text~~
      - ~~ggf priorisierung von "halben" Matches(passiert im backend)~~
        - ~~voller Match: Nutzer A mag Nutzer B Text && vice versa~~
        - ~~halber Match: Nutzer A mag Nutzer B Text~~
      - ~~Like / Dislike~~
        - ~~Dislike speichern~~
        - ~~Like(ebenfalls im backend)~~
          - ~~Falls: Bisher Nutzer B mag Nutzer A, nun Nutzer A mag Nutzer B -> Match~~
          - ~~sonst: halber Match, ggf bei/für Gegenseite vermerken (für priorisierung)~~   
        
  - ~~List Matches~~
    - ~~Anzeige aller vorhandenen (vollen) Matches~~
      - ~~Nutzername + TimeStamp + letzte chat Nachricht~~
      - ~~"Chatroom" betreten(anclicken)~~   
  - ~~Chatting~~
    - ~~Anzeige Name(Chat Partner)~~
    - ~~Anzeige TimeStamp + Chatnachrichten~~
    - ~~Eingabewidget + Button~~
  
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
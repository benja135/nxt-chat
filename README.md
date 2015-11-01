http://benja135.xyz/blog/lejos-communication-bluetooth-entre-nxt

Communication Bluetooth entre plusieurs robots NXT mindstorms avec un PC maitre.

##### Fonctionnement de « NXTs Chat »

La classe principale est « Master ». Celle-ci lance autant de threads que d'adresse de robot que vous lui avez donné à sa construction. Elle donne à chaque thread une adresse, une « boite aux lettres » personnel, et un « facteur » connu de tous les threads (celui-ci connaît toutes les adresses / boites aux lettres). Un thread communique avec un unique et différent NXT. Si sa boite au lettre contient quelque chose, alors il récupère la « lettre » et l'envoie à son NXT. Un sous-thread de réception écoute en permanence le NXT associé. A la réception d'un message, il demande au facteur d'aller poster la lettre où il faut.

Structure des messages transmis (type ''StringUTF'') :

- du NXT au PC : - ''destinataire-message'' avec destinataire l'adresse BT du destinataire codé sur 12 char, et '-' un caractère.
Ou ''message'' message pour tous les NXT connectés (sauf lui même).
- du PC au NXT : ''expéditeur-message'' avec expéditeur l'adresse BT de l’expéditeur codé sur 12 char.

Coté NXT, une classe « Client » qui attend que le PC se connecte, et où vous pouvez envoyer des messages avec les destinataires voulus. Elle contient un thread de réception qui écoute en permanence le PC et qui affiche les messages entrant.

Pour les tests, l'appuie sur un des boutons envoie un message (les adresses des destinataires sont à régler pour vos robots).

Les Client doivent être lancés avant le Master.

##### Détails techniques à savoir

- Un NXT supporte au maximum 1 connexion entrante et 3 sortantes (dû au matériel).
Ceci implique que pour faire communiquer plus de 2 NXT, il faut passer par un PC qui jouera le rôle d'un hub pour relier tous les robots. (ou on peut aussi imaginer une communication en chaîne avec tous les robots… NXT1 → NXT2 → NXT3 … → NXT1)
- Les méthodes de lecture sur les streams entrants sont bloquantes (dû au matériel aussi).
Ceci implique qu'on est obligé de mettre en place un programme multi-threads, pour pouvoir gérer la lecture en continue sans bloquer le reste du programme.
- La méthode DataInputStream.available() de NXJ Lejos est trompeuse. Elle retourne le nombre d'octet pouvant être lu sans blocage. Soit toujours 0 !

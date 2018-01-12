Projet de Programmation Avancée

--- Sujet ---

Le but de ce projet est de construire un jeu de combat virtuel de type « RobotWar ».
Dans une arène de combat en 2D, vue de dessus, des robots s'affrontent, gérés par une IA relativement basique. Le comportement ainsi que le graphisme des robots est décidé par des plugins. Un robot est actif tant que sa vie n'a pas atteint 0 et le gagnant est le dernier robot actif. À chaque robot est associé une quantité d'énergie et chaque action consomme une partie de celle-ci. L'énergie remonte régulièrement tant qu'elle n'a pas atteint la valeur maximale.

--- Test du projet ---

Une fois le projet récupéré, il vous suffit de placer un terminal ayant pour position la racine du projet. Par la suite vous suffira d’exécuter cette commande :

<i>java -jar moteur/launch.jar</i>

--- Conception ---

3 modules :
  - moteur : contient le mécanisme de jeu
  - annotation : contient les annoationx relatives au chargement des plugins
  - plugins : les plugins du jeu
        ° attaque
        ° deplacement
        ° graphisme
 

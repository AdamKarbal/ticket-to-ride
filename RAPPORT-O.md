# Rapport Projet Semestre 5

## I – Point d’avancement

### Résumé des fonctionnalités réalisées sur tout le jeu

- Wagons (différentes couleurs)
- Carte Wagons (différentes couleurs/locomotive)
- 55 destinations courtes
- 34 destinations longues
- 60 Meeples passager
- Une représentation du plateau de jeu
- Mise en place aléatoire de tous les Meeples dans la Map selon les règles du jeu
- Les Meeples sont récupérables selon les règles du jeu quand on pose une route
- On peut poser des wagons sur des routes si on a assez de cartes wagons
- Au premier tour, le joueur peut refuser des cartes destinations
- Au premier tour, le joueur peut piocher 4 cartes destinations
- Lors d’un tour, le joueur peut piocher (pioche visible/invisible/carte destination)
- Lors d’un tour, le joueur peut poser un train
- Si le joueur a pioché, il peut rejouer
- Si la pioche est vide, la défausse est utilisée
- Si un joueur a moins de deux wagons, le jeu se finit
- Le joueur qui a complété le plus de cartes destinations bénéficie du titre de Globetrotter
- Les joueurs qui ont le plus de Meeples d’une couleur, ou les deuxièmes, gagnent des points de victoire (20PV pour le premier, 10PV pour le deuxième)

### Résumé des fonctionnalités non réalisées

- Lorsqu’il n’y a plus de défausse ni de pioche, le jeu s’arrête comme si l’un des joueurs n'avait plus que deux wagons.

### Résumé des logs

- Implémentation d’un logger pour les classes : `GestionEtatJeu`, `Jeu`, `Affichage` et `Main`.
- Formatage des logs via `LogFormatter` pour réduire la quantité d’informations affichées.
- Différents niveaux de logs (`INFO`, `OFF`) selon la commande utilisée (`--2thousands`, `--demo`).

### Résumé des statistiques CSV

- Ajout d’une fonctionnalité CSV pour obtenir les statistiques d’une partie : 
  - Numéro de la partie, nombre de tours, nom du robot, positionnement, nombre de points, nombre de cartes destination complétées, nombre de routes, nombre de meeples de chaque couleur.
- Utilisation de `csv.updateStat(jeu)` pour mettre à jour le fichier CSV.
- Proposition d’optimisation : stocker temporairement les données dans un buffer avant d’écrire dans le fichier CSV.

### Résumé du bot

- Le bot utilise deux stratégies :
  - Stratégie pour les cartes destinations : sélectionne jusqu’à 2 cartes basées sur la présence de villes communes et la distance la plus courte.
  - Stratégie d’optimisation des trajets via l’algorithme de Dijkstra.
- Analyse des performances sur 1000 parties : 
  - Victoire dans 20% des cas, moins efficace que les autres robots.
  - Stratégie trop centrée sur les destinations courtes, limitant les points.
  - Ne pose pas assez de routes contrairement au robot aléatoire car le robot se contente de compléter ses cartes Destinations.

## II – Architecture et qualité

### Architecture du projet et choix

#### 1) Robot générique

- **Attributs** : main contenant les cartes wagons et destinations, liste de meeples pris et trajets effectués.
- **Interactions avec le reste du jeu** : sélectionne une action, mais son exécution est gérée par un gestionnaire.
- **Pourquoi cette implémentation ?** : le robot n’accède pas directement aux éléments du jeu, assurant le respect des règles et une bonne division de la responsabilité.

#### 2) `MoteurDeJeu`

- **Attributs** : pioche courante, pioche visible, pioche des destinations longues et courtes, défausse.
- **Interactions avec le reste du jeu** : exécute les actions du robot et met à jour les éléments du jeu.
- **Pourquoi cette implémentation ?** : assure la synchronisation des éléments du jeu.

#### 3) `GestionTrajets`

- **Attributs** : carte du jeu, dictionnaire des trajets disponibles.
- **Interaction** : calcule les trajets optimaux et met à jour les trajets disponibles.
- **Pourquoi cette implémentation ?** : assure la cohérence entre le robot et les éléments du jeu.

#### 4) `GameMap`

- **Attributs** : graphe non-orienté pondéré représentant les villes et leurs connexions, implémenté sous la forme d'un HashMap qui mappe toutes les villes avec leurs voisins et leur distance, et une ArrayList des trajets.
- **Interactions avec le reste du jeu** : regroupe tous les trajets et meeples.
- **Pourquoi cette implémentation ?** : facilite l’utilisation de l’algorithme de Dijkstra, central à la stratégie de deux robots.

#### 5) Autres robots

- Héritent de la classe `Robot`.
- Factorisation des comportements communs (piocher, poser un train) et surcharge de méthodes spécifiques comme l'algorithme de choix d'action.

### Documentation et exécution

- Chaque méthode possède une [`JavaDoc` explicative](javadoc/index.html). Il faut télécharger le fichier et l'ouvrir avec son navigateur.
- Instructions d’exécution disponibles dans le `Readme`.

### État de la base de code

#### Points positifs

L’utilisation du moteur de jeu est bien pensée, en effet c'est une discussion entre le robot et le moteur de jeu. Le moteur de jeu dicte les règles au robot, et lui demande l’action qu’il veut faire. Si l’action est possible alors il l’effectue sinon il lui demande de faire autre chose. C’est le moteur de jeu qui fait les actions pour éviter que le robot ait accès à des éléments auxquels il ne devrait pas avoir accès. 

#### Points à améliorer

1. **Utilisation de HashMap pour `WagonsByCouleur`** : 
	L’implémentation des robots serait aussi à revoir. En effet, Robot est définie comme une classe mais n’est jamais instanciée. On           utilise toujours les robots avec un type d’intelligence en particulier, ce qui nous a poussé, en tant que développeurs, à créer différentes “versions” du jeu. Implémenter les différents types de robots de manière uniforme nous aurait évité la création de trois versions différentes du jeu.  Cela aurait pu être fait en définissant Robot comme une classe abstraite avec des méthodes communes et des signatures imposées. Ce défaut d’implémentation s’est avéré handicapant au moment d’implémenter les commandes 2thousand et démo. Améliorer cet aspect du projet à ce moment-là aurait été risqué faute de temps (trop de méthodes à réécrire, d’appels de fonctions à changer, de test à réécrire...), d’où notre choix de créer différentes versions du jeu implémentant les différents robots. 

2. **Implémentation des robots** : 
	L’implémentation des robots serait aussi à revoir. En effet, Robot est définie comme une classe mais n’est jamais instanciée. On utilise toujours les robots avec un type d’intelligence en particulier, ce qui nous a poussé, en tant que développeurs, à créer différentes “versions” du jeu. Implémenter les différents types de robots de manière uniforme nous aurait évité la création de trois versions différentes du jeu.  Cela aurait pu être fait en définissant Robot comme une classe abstraite avec des méthodes communes et des signatures imposées. Ce défaut d’implémentation s’est avéré handicapant au moment d’implémenter les commandes 2thousand et démo. Améliorer cet aspect du projet à ce moment-là aurait été risqué faute de temps (trop de méthodes à réécrire, d’appels de fonctions à changer, de test à réécrire...), d’où notre choix de créer différentes versions du jeu implémentant les différents robots. 

### Répartition

100/100/100/100.

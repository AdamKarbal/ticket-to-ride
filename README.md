# ttr-germany-24-25-fise-24-25-ttr-o-se

## Prérequis
Avant de lancer le jeu, assurez-vous d'avoir :
- **Java** installé sur votre machine.
- **Maven** installé et configuré.

## Commandes de lancement
Le jeu propose plusieurs modes d'exécution que vous pouvez spécifier avec des arguments lors de l'exécution avec Maven.

### 1. Mode Simulation (`--2thousands`)
Ce mode lance 1000 parties entre les deux meilleurs robots, puis 1000 autres parties contre le meilleur robot. Il permet d'afficher les statistiques de victoire.

Commande :
```sh
mvn exec:java -Dexec.args="--2thousands"
```

### 2. Mode Démo (`--demo`)
Ce mode lance une partie normalement, entre tous les robots présent dans le jeu.

Commande :
```sh
mvn exec:java -Dexec.args="--demo"
```

### 3. Enregistrement des statistiques (`--csv`)
L'option `--csv` peut être ajoutée à n'importe quel mode pour enregistrer les statistiques de chaque partie dans un fichier CSV.

Exemple avec le mode simulation :
```sh
mvn exec:java -Dexec.args="--2thousands --csv"
```
Exemple avec le mode démo :
```sh
mvn exec:java -Dexec.args="--demo --csv"
```

## Fichier CSV
Si l'option `--csv` est activée, les statistiques seront enregistrées dans le fichier `stats/gamestats.csv`.

## Remarque
- Assurez-vous que le fichier CSV est accessible en écriture.
- L'utilisation du mode `--2thousands` peut prendre du temps en fonction des performances de votre machine.

Bon jeu ! 🎲


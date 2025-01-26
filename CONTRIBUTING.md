
\---

Comment contribuer ?

\## Créer une issue 



\## Branches et fonctions

\-

- `feature/nom-de-la-feature`:
- C'est ici que tout le développement actif se passe.
- Une fonctionnalité à la fois.
- Une fois que tout est testé et fonctionnel, vous pouvez merge dans `main`.

\- 

- `main`:
- Cette est branche est utilisée pour regrouper les différentes features.

\-

- `pre-production`:
- Cette branche est utilisée pour tester des fonctionnalités presque stables et voir comment les différentes fonctionnalités 
interagissent entre elles.
- Lorsque vous avez terminé une fonctionnalité ou une série de corrections dans `main`.

\-

- `production` :
- Cette branche contient uniquement des versions stables et validées.
- Une fois que tout est validé dans `pre-production`, vous pouvez merge dans `production`.
- IL FAUT PULL UP (pour qu'on review avant)
- Chaque itération stable est marquée avec un tag (par exemple, `WEEK1`, `WEEK2`, etc.) correspondant aux milestones complétés.

\---

\## Étapes pour push des changements

1. \Créer une nouvelle branche depuis `main` :\

git checkout main

git pull origin main

git checkout -b feature/nom-de-la-feature

2. \Merge dans `main` :\

Créer une pull request sur le client GitHub.

Gérer les conflits minutieusement.




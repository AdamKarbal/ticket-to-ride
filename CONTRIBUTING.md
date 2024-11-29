
\---

\## Branches

- \*\*`main`\*\* :
- C'est ici que tout le développement actif se passe.

\-

- \*\*`pre-production`\*\* :
- Cette branche est utilisée pour tester des fonctionnalités presque stables.
- Lorsque vous avez terminé une fonctionnalité ou une série de corrections dans `main`.

- \*\*`production`\*\* :
- Cette branche contient uniquement des versions stables et validées.
- Une fois que tout est validé dans `pre-production`, vous pouvez merger dans `production`.
- IL FAUT PULL UP (pour qu'on review avant)
- Chaque itération stable est marquée avec un tag (par exemple, `WEEK1`, `WEEK2`, etc.) correspondant aux milestones complétés.

\---

\## Étapes pour push des changements

1. \*\*Créer une nouvelle branche depuis `main` :\*\*

\```bash

git checkout main

git pull origin main

git checkout -b feature/nom-de-la-feature

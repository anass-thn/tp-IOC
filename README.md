# Activité Pratique N°1 : Injection de Dépendances & Création d'un Mini-Framework IoC

Ce projet a pour but de comprendre et de mettre en œuvre le concept d'inversion de contrôle (IoC) et l'injection de dépendances à travers différentes approches (statique, dynamique, Spring XML, Spring Annotations), puis de concevoir notre propre mini-framework IoC.

## Structure du Projet

- **Couche DAO (Data Access Object)** : Définit l'accès aux données.
  - `dao.IDao` (Interface) : Contient la méthode `getValue()`.
  - `dao.DaoImpl` (Classe) : Implémentation simulant une récupération de données (retourne 23.4).
- **Couche Métier** : Contient la logique applicative.
  - `metier.IMetier` (Interface) : Contient la méthode `calcul()`.
  - `metier.MetierImpl` (Classe) : Implémentation de la logique métier. Elle possède un attribut de type `IDao` (couplage faible) et un setter `setDao(IDao)` pour injecter la dépendance.
- **Couche Présentation** : Point d'entrée de l'application.
  - `pres.Pres1` (Classe) : Instanciation statique (avec `new`) et injection de dépendances manuelle (via le setter).
  - `pres.Pres2` (Classe) : Instanciation dynamique (avec chargement dynamique des classes et réflexivité) à partir du fichier `config.txt`.
  - `pres.PresAvecSpringXml` (Classe) : Chargement du contexte Spring à partir d'un fichier XML et récupération du bean métier.
- **Fichiers de Configuration** :
  - `config.txt` : Contient les noms complets des classes concrètes à instancier (`dao.DaoImpl` et `metier.MetierImpl`).
  - `src/main/resources/applicationContext.xml` : Configuration XML pour déclarer les beans `dao` et `metier` et définir l'injection par setter.





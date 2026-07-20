# Activité Pratique N°1 : Injection de Dépendances & Création d'un Mini-Framework IoC

Ce projet a pour but de comprendre et de mettre en œuvre le concept d'inversion de contrôle (IoC) et l'injection de dépendances à travers différentes approches (statique, dynamique, Spring XML, Spring Annotations), puis de concevoir notre propre mini-framework IoC.

## Structure du Projet

- **Couche DAO (Data Access Object)** : Définit l'accès aux données.
  - `dao.IDao` (Interface) : Contient la méthode `getValue()`.
- **Couche Métier** : Contient la logique applicative.
  - `metier.IMetier` (Interface) : Contient la méthode `calcul()`.

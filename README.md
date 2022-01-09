# ( Librairie nécessaire : JavaFX )

```
java --module-path "**lien vers le SDK JavaFX**\lib" --add-modules=javafx.controls,javafx.swing,javafx.fxml -jar git\Modelisation3D.PLY\livrables\livrable_2\projetmode2-livrable2.jar
```

# Membres de l'équipe

 - Alexis Bonal
 - Basien Lemarié
 - Romann Cornet
 - Clément Lasselin

# Livrable 1

## Fonctionnalités implémentées

- [x] Afficher la liste détaillée des modèles
- [x] Il est possible de choisir le modèle à visualiser dans la liste
- [x] Le modèle est chargé et visualisé
- [X] Message d'erreur en cas d'erreur de format dans le fichier
- [x] La visualisation est correcte
- [x] On visualise simultanément trois vues du modèle (de face, de haut, de côté)
- [x] Rotation
- [x] Translation
- [x] Homotétie

## Autres éléments demandés

- [X] Tests pour les classes de calcul mathématique
- [X] Captures d'écran pour le rendu
- [X] Vidéo de présentation du rendu
- [X] Respect du format de rendu (cf Moodle)

## Distribution du travail (qui a fait quoi)

- Alexis : Class Maths (Matrix et Benchmark) du package Maths ainsi que les tests de la class Matrix, Début de MVC, Controller, modélisation des faces, clean Code
- Romann : Début de MVC, fonctionnalités de l'interface(boutons, translation, zoom), esthétique de l'interface, lecture de fichier
- Bastien : Interface avancé (Bibliothèque de ply, génération de captures, esthétique de l'interface, explorateur de fichiers)
- Clément : Première version de l'interface, tests du FileReader et des autres class de maths, clean code

## Difficultés rencontrées

- Problèmes avec les librairies JavaFx et Maven au début du projet
- Le MVC n'a pas été implémenté dès le début, nous avons donc du revoir notre conception du projet au milieu de sa production
- Il était parfois difficile de travailler à 4 en même temps sur le projet car nous étions tous sur une même classe
- Parfois difficile de clean Code sans influencer sur d'autres classes et créer des conflits

## Points positifs

- La répartition des tâches
- Nous sommes satisfait de l'esthétique de notre interface

# Livrable 2

## Fonctionnalités implémentées


- [x] Affichage faces seulement / segments seulement
- [x] Affichage avancé de la bibliothèque de modèles
- [ ] Recherche dans la bibliothèque de modèles
- [X] Éditer les informations sur un modèle
- [x] Modèle centré
- [X] Éclairage
- [ ] Lissage
- [ ] Ombre portée
- [ ] Vue en tranches
- [x] Controleur horloge
- [x] Autres : 
    - [x] Changement des couleurs de faces, d'arrêtes et de fonds 
    - [x] Capture d'écran
    - [x] Raccourcis clavier
    - [x] Boutons de reset 

## Autres exigences

- [X] Tests unitaires
- [X] Diagramme de classes UML
- [X] Javadoc
- [X] Captures d'écran
- [X] Vidéo de présentation
- [X] Respect du format de rendu

## Distribution du travail (qui a fait quoi)

- Alexis : MVC, éclairage, couleurs, modèle centré
- Romann : PMD, Rotation auto, Bibliothèque de PLY
- Bastien : Interface (FXML, gestion des boutons/slider... et des fonctions liées)
- Clément : MVC, Javadoc/Commentaire, PMD, clean code

## Difficultés rencontrées

- Canvas : il n'est pas géré comme un élément classique JavaFX (pas beaucoup d'options disponibles : ex resizeable), ne s'adaptent pas aux anchors pain
- Difficultés pour implémenter le MVC
- Loi de Demeter dans les règles PMD
- Pas réussi à faire ombre portée et vue en tranches

## Amélioration par rapport au Livrable 1

- Presque plus de problèmes avec git et les conflits sur le projet (nous avons bien progressé sur git depuis le début de S3)


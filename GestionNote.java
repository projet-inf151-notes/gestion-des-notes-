# 1. Contexte et objectif du projet

Ce projet consiste à concevoir et développer une **application console Java de gestion des notes des étudiants**, destinée à un usage pédagogique (initiation à la programmation et au génie logiciel).

L’objectif principal est de permettre :

* la saisie des informations des étudiants,
* l’enregistrement de leurs notes,
* le calcul de moyennes simples,
* l’affichage structuré des résultats.

**Contrainte majeure** : l’implémentation devra utiliser **exclusivement les notions fondamentales de la programmation** :

* entrées/sorties console,
* variables et constantes,
* structures de contrôle (conditions, boucles),
* fonctions (méthodes simples),
* tableaux.

Aucune utilisation de :

* bases de données,
* fichiers,
* classes avancées, collections (ArrayList, Map…),
* frameworks ou bibliothèques externes.

---

# 2. Analyse des besoins (Génie logiciel – Phase d’analyse)

## 2.1 Acteurs

* **Utilisateur** : enseignant ou étudiant utilisant le programme via la console.

## 2.2 Besoins fonctionnels

L’application doit permettre :

1. Ajouter un étudiant (nom, prénom, matricule)
2. Saisir les notes d’un étudiant (par matière)
3. Calculer la moyenne d’un étudiant
4. Afficher les informations et la moyenne d’un étudiant
5. Afficher la liste de tous les étudiants
6. Quitter le programme proprement

## 2.3 Besoins non fonctionnels

* Application en **mode console**
* Langage : **Java**
* Simplicité et lisibilité du code
* Robustesse minimale (contrôle des choix du menu)

---

# 3. Spécification fonctionnelle

## 3.1 Description générale

L’application fonctionne sous forme de **menu textuel interactif** affiché en boucle jusqu’à ce que l’utilisateur choisisse de quitter.

## 3.2 Menu principal

```
1. Ajouter un étudiant
2. Saisir les notes d’un étudiant
3. Calculer la moyenne d’un étudiant
4. Afficher un étudiant
5. Afficher tous les étudiants
0. Quitter
```

---

# 4. Conception (Génie logiciel – Phase de conception)

## 4.1 Architecture générale

* Programme monolithique
* Une seule classe : `GestionNotes`
* Données stockées dans des **tableaux statiques**

## 4.2 Structures de données

Constantes :

* `MAX_ETUDIANTS`
* `MAX_MATIERES`

Tableaux :

* `String[] noms`
* `String[] prenoms`
* `String[] matricules`
* `double[][] notes`
* `int nbEtudiants`

## 4.3 Fonctions (méthodes)

* `afficherMenu()`
* `ajouterEtudiant()`
* `saisirNotes()`
* `calculerMoyenne(int index)`
* `afficherEtudiant()`
* `afficherTous()`

---

# 5. Algorithmes (Phase de conception détaillée)

## 5.1 Algorithme principal

1. Initialiser les tableaux
2. Tant que l’utilisateur ne quitte pas :

   * afficher le menu
   * lire le choix
   * exécuter la fonction correspondante

## 5.2 Calcul de moyenne

* Somme des notes
* Division par le nombre de matières

---

# 6. Implémentation (Code source Java)

```java
import java.util.Scanner;

public class GestionNotes {

    static final int MAX_ETUDIANTS = 30;
    static final int MAX_MATIERES = 5;

    static String[] noms = new String[MAX_ETUDIANTS];
    static String[] prenoms = new String[MAX_ETUDIANTS];
    static String[] matricules = new String[MAX_ETUDIANTS];
    static double[][] notes = new double[MAX_ETUDIANTS][MAX_MATIERES];
    static int nbEtudiants = 0;

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int choix;

        do {
            afficherMenu();
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    ajouterEtudiant();
                    break;
                case 2:
                    saisirNotes();
                    break;
                case 3:
                    afficherMoyenne();
                    break;
                case 4:
                    afficherEtudiant();
                    break;
                case 5:
                    afficherTous();
                    break;
                case 0:
                    System.out.println("Fin du programme.");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 0);
    }

    static void afficherMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1. Ajouter un étudiant");
        System.out.println("2. Saisir les notes");
        System.out.println("3. Calculer la moyenne");
        System.out.println("4. Afficher un étudiant");
        System.out.println("5. Afficher tous les étudiants");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }

    static void ajouterEtudiant() {
        if (nbEtudiants >= MAX_ETUDIANTS) {
            System.out.println("Capacité maximale atteinte.");
            return;
        }

        System.out.print("Nom : ");
        noms[nbEtudiants] = scanner.nextLine();

        System.out.print("Prénom : ");
        prenoms[nbEtudiants] = scanner.nextLine();

        System.out.print("Matricule : ");
        matricules[nbEtudiants] = scanner.nextLine();

        nbEtudiants++;
        System.out.println("Étudiant ajouté avec succès.");
    }

    static void saisirNotes() {
        System.out.print("Indice de l'étudiant (0 à " + (nbEtudiants - 1) + ") : ");
        int index = scanner.nextInt();

        if (index < 0 || index >= nbEtudiants) {
            System.out.println("Indice invalide.");
            return;
        }

        for (int i = 0; i < MAX_MATIERES; i++) {
            System.out.print("Note matière " + (i + 1) + " : ");
            notes[index][i] = scanner.nextDouble();
        }
    }

    static double calculerMoyenne(int index) {
        double somme = 0;
        for (int i = 0; i < MAX_MATIERES; i++) {
            somme += notes[index][i];
        }
        return somme / MAX_MATIERES;
    }

    static void afficherMoyenne() {
        System.out.print("Indice de l'étudiant : ");
        int index = scanner.nextInt();
        System.out.println("Moyenne : " + calculerMoyenne(index));
    }

    static void afficherEtudiant() {
        System.out.print("Indice de l'étudiant : ");
        int index = scanner.nextInt();

        System.out.println("Nom : " + noms[index]);
        System.out.println("Prénom : " + prenoms[index]);
        System.out.println("Matricule : " + matricules[index]);
        System.out.println("Moyenne : " + calculerMoyenne(index));
    }

    static void afficherTous() {
        for (int i = 0; i < nbEtudiants; i++) {
            System.out.println(i + " - " + noms[i] + " " + prenoms[i] + " | Moyenne : " + calculerMoyenne(i));
        }
    }
}
```

---

# 7. Tests et validation

* Test ajout étudiant
* Test saisie des notes
* Test calcul de moyenne
* Test choix invalides

---

# 8. Évolutions possibles

* Gestion par identifiant au lieu d’indice
* Sauvegarde dans un fichier
* Interface graphique
* Gestion des coefficients

---

**Ce projet respecte strictement les principes fondamentaux de la programmation et constitue une base solide pour un étudiant débutant en Java et en génie logiciel.**

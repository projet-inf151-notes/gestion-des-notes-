import java.util.Scanner;

public class GestionNote {

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



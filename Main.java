import java.util.*;

/**
 * Classe représentant un étudiant avec :
 * matricule, nom, filière, niveau et un ensemble de notes.
 */
class Etudiant {

    // Identifiant unique de l'étudiant
    String matricule;

    // Nom complet de l'étudiant
    String nom;

    // Filière de l'étudiant (ex. Informatique)
    String filiere;

    // Niveau (ex. L1, L2, M1)
    String niveau;

    // Map de notes : clé = UE (matière), valeur = note sur 20
    Map<String, Double> notes = new HashMap<>();

    /**
     * Constructeur de l'étudiant
     */
    Etudiant(String matricule, String nom, String filiere, String niveau) {
        this.matricule = matricule;
        this.nom = nom;
        this.filiere = filiere;
        this.niveau = niveau;
    }

    /**
     * Permet d'ajouter ou de modifier une note pour une UE donnée
     */
    void ajouterNote(String ue, double note) {
        notes.put(ue, note); // Si l'UE existe déjà, la note sera mise à jour
    }

    /**
     * Calcule la moyenne de l'étudiant à partir des notes enregistrées
     */
    double calculerMoyenne() {
        if (notes.isEmpty()) return 0; // Évite une division par zéro
        double somme = 0;

        // Parcourt toutes les notes et les additionne
        for (double n : notes.values()) {
            somme += n;
        }

        return somme / notes.size(); // Moyenne = somme des notes / nombre d'UE
    }

    /**
     * Détermine automatiquement la mention de l’étudiant selon sa moyenne
     */
    String mention() {
        double m = calculerMoyenne();

        if (m < 10) return "Échec";
        if (m < 12) return "Passable";
        if (m < 14) return "Assez Bien";
        if (m < 16) return "Bien";
        return "Très Bien";
    }
}



/**
 * Classe principale contenant le menu et toutes les opérations du programme.
 */
public class Main {

    // Scanner pour lire le texte entrée par l'utilisateur dans la console
    static Scanner sc = new Scanner(System.in);

    // Liste des filières disponibles
    static List<String> filieres = new ArrayList<>();

    // Map liant une filière à une liste de niveaux (ex : Info → [L1, L2, L3])
    static Map<String, List<String>> niveaux = new HashMap<>();

    // Liste de tous les étudiants
    static List<Etudiant> etudiants = new ArrayList<>();


    public static void main(String[] args) {

        int choix;

        // Boucle infinie jusqu'à ce que l'utilisateur choisisse "0 : Quitter"
        do {
            menu();                  // Affichage du menu
            choix = sc.nextInt();    // Lecture du choix
            sc.nextLine();           // Nettoie le buffer

            // Exécution des commandes selon le choix
            switch (choix) {
                case 1: ajouterFiliere(); break;
                case 2: ajouterNiveau(); break;
                case 3: ajouterEtudiant(); break;
                case 4: ajouterNote(); break;
                case 5: afficherEtudiants(); break;
                case 6: afficherNotes(); break;
                case 0: System.out.println("Au revoir !"); break;
                default: System.out.println("Choix invalide !");
            }

        } while (choix != 0); // Fin de programme si choix = 0
    }

    /**
     * Affiche le menu principal à l'écran
     */
    static void menu() {
        System.out.println("\n===== GESTION DES NOTES =====");
        System.out.println("1. Ajouter filière");
        System.out.println("2. Ajouter niveau");
        System.out.println("3. Ajouter étudiant");
        System.out.println("4. Ajouter note étudiant");
        System.out.println("5. Afficher liste des étudiants");
        System.out.println("6. Afficher notes étudiant");
        System.out.println("0. Quitter");
        System.out.print("Votre choix : ");
    }



    // ---------------------------------------------------------
    // ███   GESTION DES FILIÈRES ET NIVEAUX
    // ---------------------------------------------------------

    /**
     * Ajoute une nouvelle filière dans la liste
     */
    static void ajouterFiliere() {
        System.out.print("Nom filière : ");
        String f = sc.nextLine();

        filieres.add(f);                // On ajoute la filière
        niveaux.put(f, new ArrayList<>()); // On prépare sa liste de niveaux

        System.out.println("Filière ajoutée.");
    }

    /**
     * Ajoute un niveau à une filière existante
     */
    static void ajouterNiveau() {
        System.out.print("Nom filière : ");
        String f = sc.nextLine();

        if (!filieres.contains(f)) {
            System.out.println("Filière inexistante.");
            return;
        }

        System.out.print("Nom niveau : ");
        String n = sc.nextLine();

        niveaux.get(f).add(n); // Ajout du niveau dans la bonne filière

        System.out.println("Niveau ajouté.");
    }


    // ---------------------------------------------------------
    // ███   GESTION DES ÉTUDIANTS
    // ---------------------------------------------------------

    /**
     * Ajoute un nouvel étudiant dans la liste
     */
    static void ajouterEtudiant() {
        System.out.print("Nom étudiant : ");
        String nom = sc.nextLine();

        System.out.print("Matricule : ");
        String m = sc.nextLine();

        System.out.print("Filière : ");
        String f = sc.nextLine();

        System.out.print("Niveau : ");
        String nv = sc.nextLine();

        // Création de l'objet Etudiant
        etudiants.add(new Etudiant(m, nom, f, nv));

        System.out.println("Étudiant ajouté.");
    }


    // ---------------------------------------------------------
    // ███   GESTION DES NOTES
    // ---------------------------------------------------------

    /**
     * Ajoute une note pour un étudiant donné
     */
    static void ajouterNote() {
        System.out.print("Matricule étudiant : ");
        String m = sc.nextLine();

        Etudiant e = chercherEtudiant(m);

        if (e == null) {
            System.out.println("Étudiant introuvable.");
            return;
        }

        System.out.print("Nom UE : ");
        String ue = sc.nextLine();

        System.out.print("Note : ");
        double note = sc.nextDouble();
        sc.nextLine(); // Nettoyage du buffer

        e.ajouterNote(ue, note);

        System.out.println("Note ajoutée.");
    }


    // ---------------------------------------------------------
    // ███   AFFICHAGES
    // ---------------------------------------------------------

    /**
     * Affiche la liste complète des étudiants
     */
    static void afficherEtudiants() {
        for (Etudiant e : etudiants) {
            System.out.println(
                e.matricule + " | " + e.nom + " | " + e.filiere + " | " + e.niveau
            );
        }
    }

    /**
     * Affiche toutes les notes d'un étudiant (avec moyenne + mention)
     */
    static void afficherNotes() {
        System.out.print("Matricule étudiant : ");
        String m = sc.nextLine();

        Etudiant e = chercherEtudiant(m);

        if (e == null) {
            System.out.println("Étudiant introuvable.");
            return;
        }

        System.out.println("Notes de " + e.nom + " :");

        // Affichage de toutes les notes
        for (var entry : e.notes.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }

        // Affichage de la moyenne et de la mention
        System.out.println("Moyenne : " + e.calculerMoyenne());
        System.out.println("Mention : " + e.mention());
    }


    // ---------------------------------------------------------
    // ███   FONCTIONS UTILITAIRES
    // ---------------------------------------------------------

    /**
     * Recherche un étudiant par son matricule
     */
    static Etudiant chercherEtudiant(String m) {
        for (Etudiant e : etudiants) {
            if (e.matricule.equals(m)) {
                return e; // Étudiant trouvé
            }
        }
        return null; // Aucun étudiant trouvé
    }
}

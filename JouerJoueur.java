import java.util.Scanner;

public class JouerJoueur {
    public static void jouerPartie(String[][] plateau) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel camp commence ? (N pour Noir, B pour Blanc) : ");
        String campActuel = scanner.nextLine().toUpperCase();
        if (!campActuel.equals("N") && !campActuel.equals("B")) {
            System.out.println("Entrée invalide. Par défaut, le camp Noir commence.");
            campActuel = "N";
        }

        boolean jeuEnCours = true;

        while (jeuEnCours) {
            // Vérification des conditions de victoire avant chaque tour
            if (!GameRules.roiPresent(plateau, "N")) {
                System.out.println("Le camp Blanc a gagné, le roi Noir a été capturé !");
                break;
            }
            if (!GameRules.roiPresent(plateau, "B")) {
                System.out.println("Le camp Noir a gagné, le roi Blanc a été capturé !");
                break;
            }

            System.out.println("\nC'est au tour du camp " + (campActuel.equals("N") ? "Noir" : "Blanc") + ".");
            afficher.afficherPlateau(plateau);
            System.out.println("Entrez les coordonnées de la pièce à déplacer (ligne colonne) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            // Vérifier si la case contient une pièce du camp actuel
            String piece = plateau[ligne][colonne];
            if (piece == null || piece.equals(".")) {
                System.out.println("Aucune pièce à cet emplacement. Réessayez.");
                continue;
            }
            if (!piece.endsWith(campActuel)) {
                System.out.println("Pas de pièce de votre camp à cet emplacement. Réessayez.");
                continue;
            }

            // Demander les coordonnées de destination jusqu'à ce que le déplacement soit valide
            boolean deplacementValide = false;
            while (!deplacementValide) {
                System.out.println("Entrez les coordonnées de destination (ligne colonne) : ");
                int nvLigne = scanner.nextInt();
                int nvColonne = scanner.nextInt();
                scanner.nextLine();

                // Déplacer la pièce en fonction de son type
                if (piece.charAt(0) == 'T') {
                    deplacementValide = Tour.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                } else if (piece.charAt(0) == 'S') {
                    deplacementValide = Soldat.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                } else if (piece.charAt(0) == 'R') {
                    deplacementValide = RoiPions.deplacement(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                } else if (piece.charAt(0) == 'F') {
                    deplacementValide = Fou.deplacerFou(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                } else if (piece.charAt(0) == 'C') {
                    deplacementValide = Cavalier.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                } else if (piece.charAt(0) == 'E') {
                    deplacementValide = Elephant.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                } else if (piece.charAt(0) == 'B') {
                    deplacementValide = Canon.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
                }

                if (!deplacementValide) {
                    System.out.println("Déplacement invalide. Veuillez réessayer.");
                }
            }

            campActuel = campActuel.equals("N") ? "B" : "N";
        }

        scanner.close();
    }
}

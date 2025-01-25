import java.util.Scanner;

public class JoueurVsBot {
    public static void BotFacile(String[][] plateau) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Quel camp commence ? (N pour Noir, B pour Blanc) : ");
        String campActuel = scanner.nextLine().toUpperCase();
        if (!campActuel.equals("N") && !campActuel.equals("B")) {
            System.out.println("Entrée invalide. Par défaut, le camp Noir commence.");
            campActuel = "N";
        }

        boolean jeuEnCours = true;

        while (jeuEnCours) {
            System.out.println("\nC'est au tour du camp " + (campActuel.equals("N") ? "Noir" : "Blanc") + ".");
            afficher.afficherPlateau(plateau);

            if (campActuel.equals("N")) {
                System.out.println("Entrez les coordonnées de la pièce à déplacer (ligne colonne) : ");
                int ligne = scanner.nextInt();
                int colonne = scanner.nextInt();
                scanner.nextLine();

                String piece = plateau[ligne][colonne];
                if (piece.equals(".")) {
                    System.out.println("Aucune pièce à cet emplacement. Réessayez.");
                    continue;
                }
                if (!piece.endsWith(campActuel)) {
                    System.out.println("Pas de pièce de votre camp à cet emplacement. Réessayez.");
                    continue;
                }

                // Tour du joueur
                boolean deplacementValide = false;
                while (!deplacementValide) {
                    System.out.println("Entrez les coordonnées de destination (ligne colonne) : ");
                    int nvLigne = scanner.nextInt();
                    int nvColonne = scanner.nextInt();
                    scanner.nextLine();

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
                }

                if (!GameRules.roiPresent(plateau, "B")) {
                    System.out.println("Le camp Noir a gagné, le roi Blanc a été capturé !");
                    break;
                }

            } else {
                // Tour du bot
                if (campActuel.equals("B")) {
                    Bot.jouerCoupAleatoire(plateau, "B");
                }

                if (!GameRules.roiPresent(plateau, "N")) {
                    System.out.println("Le camp Blanc a gagné, le roi Noir a été capturé !");
                    break;
                }
            }
            campActuel = campActuel.equals("N") ? "B" : "N";
        }
    }
}
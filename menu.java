import javax.naming.BinaryRefAddr;
import java.util.Scanner;
import java.util.Random;

public class menu {

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        String J1;
        String J2;

        System.out.println("===================================");
        System.out.println("||        Jeu du Xiangqi         ||");
        System.out.println("||===============================||");
        System.out.println("||    1 : Règle du Xiangqi       ||");
        System.out.println("||    2 : Jouer contre un Ami    ||");
        System.out.println("||    3 : Jouer contre IA        ||");
        System.out.println("===================================");
        System.out.println("||         0 : Quitter           ||");
        System.out.println("===================================");

        choix = scanner.nextInt();
        scanner.nextLine();
        String[][] plateau = Départ.plateauDépart();

        switch (choix) {
            case 1:
                afficherRegles();
                menu();
                break;

            case 2:
                System.out.println("Entrez votre pseudo de 12 caractères: ");
                J1 = scanner.nextLine();
                while (J1.length() > 12) {
                    System.out.println("Le pseudo doit être de 12 caractères maximum. Essayez encore: ");
                    J1 = scanner.nextLine();
                }

                System.out.println("Entrez le deuxième pseudo de 12 caractères maximum : ");
                J2 = scanner.nextLine();
                while (J2.length() > 12) {
                    System.out.println("Le pseudo doit être de 12 caractères maximum. Essayez encore: ");
                    J2 = scanner.nextLine();
                }

                System.out.println("Bienvenue " + J1 + " et " + J2 + " !");
                JouerJoueur.jouerPartie(plateau);
                System.out.println("Quel camp commence ? (N pour Noir, B pour Blanc) : ");
                break;

            case 3:
                // Demander le pseudo du joueur et la difficulté de l'IA
                System.out.println("Entrez votre pseudo de 12 caractères maximum: ");
                J1 = scanner.nextLine();
                while (J1.length() > 12) {
                    System.out.println("Le pseudo doit être de 12 caractères maximum. Essayez encore: ");
                    J1 = scanner.nextLine();
                }

                // Choix de la difficulté
                int difficulte = choisirDifficulte(scanner);
                System.out.println("Vous avez choisi la difficulté: " + (difficulte == 1 ? "Facile" : (difficulte == 2 ? "Moyenne" : "Difficile")));
                switch (difficulte) {
                    case 1:
                        JoueurVsBot.BotFacile(plateau);
                        break;
                }
                break;

            case 0:
                System.out.println("Merci d'avoir joué !");
                System.exit(0);
                break;

            default:
                System.out.println("Choix invalide, veuillez réessayer.");
                break;
        }
    }
    public static void afficherRegles() {
        System.out.println("==========================================");
        System.out.println("||               Les Règles             ||");
        System.out.println("==========================================\n");

        System.out.println("1. Plateau de jeu");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Le plateau comporte une grille de 9 colonnes et 10 rangées, séparées par une rivière au milieu.");
        System.out.println("Les pièces sont placées sur les intersections des lignes, appelées points.");
        System.out.println("Chaque joueur commence avec ses pièces disposées sur deux rangées proches de lui.\n");

        System.out.println("2. Objectif du jeu");
        System.out.println("----------------------------------------------------------------------------------------------------------");
        System.out.println("Le but est de capturer le Général (ou Roi) de l’adversaire, similaire au \"mat\" aux échecs occidentaux.\n");

        System.out.println("3. Les pièces et leurs mouvements");
        System.out.println("----------------------------------------------------------------------------------------------------------");

        System.out.println("Général (Roi):");
        System.out.println("  - Reste dans le palais (une zone de 3×3 cases au centre, sur chaque côté du plateau).");
        System.out.println("  - Se déplace d’un point à la fois, horizontalement ou verticalement.");
        System.out.println("  - Ne peut pas se retrouver face à l’autre Général sans aucune pièce entre eux.\n");

        System.out.println("Conseillers (ou Gardiens):");
        System.out.println("  - Restent aussi dans le palais.");
        System.out.println("  - Se déplacent en diagonale d’un point.\n");

        System.out.println("Éléphants:");
        System.out.println("  - Se déplacent en diagonale de deux points.");
        System.out.println("  - Ne peuvent pas traverser la rivière.");
        System.out.println("  - Ne peuvent pas sauter par-dessus une pièce.\n");

        System.out.println("Cavaliers:");
        System.out.println("  - Se déplacent comme les cavaliers des échecs occidentaux (en \"L\"), mais leur mouvement est bloqué si un point adjacent est occupé.\n");

        System.out.println("Chariots (ou Tours):");
        System.out.println("  - Se déplacent horizontalement ou verticalement de n’importe quel nombre de points, tant qu’il n’y a pas d’obstacle.\n");

        System.out.println("Canons:");
        System.out.println("  - Se déplacent comme les Chariots, mais pour capturer une pièce, ils doivent sauter par-dessus une seule pièce (appelée obstacle).\n");

        System.out.println("Soldats (ou Pions):");
        System.out.println("  - Se déplacent d’un point en avant.");
        System.out.println("  - Après avoir traversé la rivière, ils peuvent aussi se déplacer latéralement d’un point.");
        System.out.println("  - Ne reculent jamais.\n");
        System.out.println("==========================================");
        System.out.println("||   Amusez-vous en jouant au Xiangqi!   ||");
        System.out.println("==========================================");
    }

    public static int choisirDifficulte(Scanner scanner) {
        int choix;
        do {
            System.out.println("Quelles difficultés ?");
            System.out.println("=================================");
            System.out.println("||    1 : Facile               ||");
            System.out.println("=================================");
            System.out.println("||    2 : Moyen                ||");
            System.out.println("=================================");
            System.out.println("||    3 : Difficile            ||");
            System.out.println("=================================");
            choix = scanner.nextInt();
            scanner.nextLine();

            if (choix < 1 || choix > 3) {
                System.out.println("Saisie incorrecte, veuillez choisir entre 1 et 3.");
            }
        } while (choix < 1 || choix > 3);

        return choix;
    }
}


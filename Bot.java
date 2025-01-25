import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Bot {

    public static void jouerCoupAleatoire(String[][] plateau, String campBot) {
        Random random = new Random();
        List<int[]> piecesDisponibles = new ArrayList<>();

        // Récupérer toutes les pièces disponibles pour le bot
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                String piece = plateau[i][j];
                if (piece != null && piece.endsWith(campBot)) {
                    piecesDisponibles.add(new int[]{i, j}); // Ajouter la position de la pièce
                }
            }
        }

        if (piecesDisponibles.isEmpty()) {
            System.out.println("Aucune pièce disponible pour le bot.");
            return;
        }

        // Sélectionner une pièce aléatoire
        int[] pieceSelectionnee = piecesDisponibles.get(random.nextInt(piecesDisponibles.size()));
        int ligne = pieceSelectionnee[0];
        int colonne = pieceSelectionnee[1];
        String piece = plateau[ligne][colonne];

        // Générer un coup aléatoire pour la pièce sélectionnée
        int[] coupAleatoire = genererCoupAleatoire(plateau, ligne, colonne, piece, campBot);
        if (coupAleatoire != null) {
            int nvLigne = coupAleatoire[0];
            int nvColonne = coupAleatoire[1];

            plateau[nvLigne][nvColonne] = piece;
            plateau[ligne][colonne] = ".";
            System.out.println("Le bot a joué: " + piece + " de (" + ligne + "," + colonne + ") à (" + nvLigne + "," + nvColonne + ")");
            afficherPlateau(plateau);
        }
    }

    public static int[] genererCoupAleatoire(String[][] plateau, int ligne, int colonne, String piece, String campBot) {
        switch (piece.charAt(0)) {
            case 'T': return genererCoupAleatoireTour(plateau, ligne, colonne, campBot);
            case 'S': return genererCoupAleatoireSoldat(plateau, ligne, colonne, campBot);
            case 'R': return genererCoupAleatoireRoi(plateau, ligne, colonne, campBot);
            case 'F': return genererCoupAleatoireFou(plateau, ligne, colonne, campBot);
            case 'C': return genererCoupAleatoireCavalier(plateau, ligne, colonne, campBot);
            case 'E': return genererCoupAleatoireElephant(plateau, ligne, colonne, campBot);
            case 'B': return genererCoupAleatoireCanon(plateau, ligne, colonne, campBot);
            default: return null;
        }
    }

    public static int[] genererCoupAleatoireTour(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        ajouterCoupsLignesColonnes(plateau, ligne, colonne, coupsPossibles, campBot);
        return choisirCoupAleatoire(coupsPossibles);
    }

    public static int[] genererCoupAleatoireSoldat(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        int direction = campBot.equals("N") ? 1 : -1;
        ajouterCoup(plateau, ligne + direction, colonne, coupsPossibles, campBot); // Avancer
        if ((campBot.equals("N") && ligne >= 5) || (campBot.equals("B") && ligne <= 4)) {
            ajouterCoup(plateau, ligne, colonne - 1, coupsPossibles, campBot);
            ajouterCoup(plateau, ligne, colonne + 1, coupsPossibles, campBot);
        }
        return choisirCoupAleatoire(coupsPossibles);
    }

    public static int[] genererCoupAleatoireRoi(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] dir : directions) {
            int nvLigne = ligne + dir[0];
            int nvColonne = colonne + dir[1];
            if (dansPalais(nvLigne, nvColonne, campBot)) {
                ajouterCoup(plateau, nvLigne, nvColonne, coupsPossibles, campBot);
            }
        }
        return choisirCoupAleatoire(coupsPossibles);
    }

    public static int[] genererCoupAleatoireFou(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        int[][] directions = {{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int[] dir : directions) {
            int nvLigne = ligne + dir[0];
            int nvColonne = colonne + dir[1];
            if (dansPalais(nvLigne, nvColonne, campBot)) {
                ajouterCoup(plateau, nvLigne, nvColonne, coupsPossibles, campBot);
            }
        }
        return choisirCoupAleatoire(coupsPossibles);
    }

    public static int[] genererCoupAleatoireCavalier(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        int[][] mouvements = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}};
        for (int[] move : mouvements) {
            int nvLigne = ligne + move[0];
            int nvColonne = colonne + move[1];
            if (estDansLimites(nvLigne, nvColonne) && plateau[ligne + move[0] / 2][colonne + move[1] / 2].equals(".")) {
                ajouterCoup(plateau, nvLigne, nvColonne, coupsPossibles, campBot);
            }
        }
        return choisirCoupAleatoire(coupsPossibles);
    }

    public static int[] genererCoupAleatoireElephant(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        int[][] mouvements = {{-2, -2}, {-2, 2}, {2, -2}, {2, 2}};
        int limiteRiviere = campBot.equals("N") ? 5 : 4;
        for (int[] move : mouvements) {
            int nvLigne = ligne + move[0];
            int nvColonne = colonne + move[1];
            if (estDansLimites(nvLigne, nvColonne) && ((campBot.equals("N") && nvLigne < limiteRiviere) || (campBot.equals("B") && nvLigne > limiteRiviere))) {
                ajouterCoup(plateau, nvLigne, nvColonne, coupsPossibles, campBot);
            }
        }
        return choisirCoupAleatoire(coupsPossibles);
    }

    public static int[] genererCoupAleatoireCanon(String[][] plateau, int ligne, int colonne, String campBot) {
        List<int[]> coupsPossibles = new ArrayList<>();
        int[][] directions = {
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };

        for (int[] direction : directions) {
            int x = ligne;
            int y = colonne;
            boolean pieceIntermediaireTrouvee = false;

            while (true) {
                x += direction[0];
                y += direction[1];

                if (!estDansLimites(x, y)) break;

                String caseCourante = plateau[x][y];

                if (caseCourante != null && !caseCourante.equals(".")) {
                    if (pieceIntermediaireTrouvee) {
                        break;
                    } else {
                        // Marquer qu'une pièce intermédiaire a été trouvée
                        pieceIntermediaireTrouvee = true;
                    }
                } else {
                    if (!pieceIntermediaireTrouvee) {
                        coupsPossibles.add(new int[]{x, y});
                    } else {
                        // Capture possible si une pièce intermédiaire a été trouvée
                        if (plateau[x][y] != null && !plateau[x][y].endsWith(campBot)) {
                            coupsPossibles.add(new int[]{x, y});
                        }
                        break;
                    }
                }
            }
        }
        return choisirCoupAleatoire(coupsPossibles);
    }

    // Fonctions utilitaires
    public static boolean dansPalais(int ligne, int colonne, String camp) {
        return (camp.equals("N") && ligne >= 0 && ligne <= 2 && colonne >= 3 && colonne <= 5) ||
                (camp.equals("B") && ligne >= 7 && ligne <= 9 && colonne >= 3 && colonne <= 5);
    }

    public static boolean estDansLimites(int ligne, int colonne) {
        return ligne >= 0 && ligne < 10 && colonne >= 0 && colonne < 9;
    }

    public static void ajouterCoup(String[][] plateau, int ligne, int colonne, List<int[]> coups, String campBot) {
        if (estDansLimites(ligne, colonne) && (plateau[ligne][colonne].equals(".") || !plateau[ligne][colonne].endsWith(campBot))) {
            coups.add(new int[]{ligne, colonne});
        }
    }

    public static void ajouterCoupsLignesColonnes(String[][] plateau, int ligne, int colonne, List<int[]> coups, String campBot) {
        for (int i = ligne - 1; i >= 0; i--) {
            if (ajouterCoupEtVerifierObstacle(plateau, i, colonne, coups, campBot)) break;
        }
        for (int i = ligne + 1; i < plateau.length; i++) {
            if (ajouterCoupEtVerifierObstacle(plateau, i, colonne, coups, campBot)) break;
        }
        for (int j = colonne - 1; j >= 0; j--) {
            if (ajouterCoupEtVerifierObstacle(plateau, ligne, j, coups, campBot)) break;
        }
        for (int j = colonne + 1; j < plateau[0].length; j++) {
            if (ajouterCoupEtVerifierObstacle(plateau, ligne, j, coups, campBot)) break;
        }
    }

    public static boolean ajouterCoupEtVerifierObstacle(String[][] plateau, int ligne, int colonne, List<int[]> coups, String campBot) {
        if (plateau[ligne][colonne].equals(".")) {
            coups.add(new int[]{ligne, colonne});
            return false;
        } else if (!plateau[ligne][colonne].endsWith(campBot)) {
            coups.add(new int[]{ligne, colonne});
            return true;
        }
        return true;
    }

    public static int[] choisirCoupAleatoire(List<int[]> coups) {
        if (coups.isEmpty()) return null;
        return coups.get(new Random().nextInt(coups.size()));
    }

    public static void afficherPlateau(String[][] plateau) {
        for (String[] ligne : plateau) {
            for (String casePlateau : ligne) {
                System.out.print(casePlateau + " ");
            }
            System.out.println();
        }
    }
}
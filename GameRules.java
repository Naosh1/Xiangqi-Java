public class GameRules {

    public static boolean estEnEchec(String[][] plateau, String camp) {
        int roiLigne = -1, roiColonne = -1;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                String piece = plateau[i][j];
                if (piece != null && piece.charAt(0) == 'R' && piece.endsWith(camp)) {
                    roiLigne = i;
                    roiColonne = j;
                    break;
                }
            }
            if (roiLigne != -1) break;
        }

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                String piece = plateau[i][j];
                if (piece != null && !piece.endsWith(camp)) {
                    // Si la pièce appartient à l'adversaire, vérifier si elle peut atteindre le roi
                    if (PiecePeutAtteindre(plateau, i, j, roiLigne, roiColonne, piece , camp)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean PiecePeutAtteindre(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String piece, String campActuel) {
        if (piece.charAt(0) == 'T') {
            Tour.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else if (piece.charAt(0) == 'S') {
            Soldat.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else if (piece.charAt(0) == 'R') {
            RoiPions.deplacement(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else if (piece.charAt(0) == 'F') {
            Fou.deplacerFou(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else if (piece.charAt(0) == 'C') {
            Cavalier.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else if (piece.charAt(0) == 'E') {
            Elephant.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else if (piece.charAt(0) == 'B') {
            Canon.deplacer(plateau, ligne, colonne, nvLigne, nvColonne, campActuel);
        } else {
            System.out.println("Type de pièce inconnu. Réessayez.");
        }
        return false;
    }

    public static boolean roiPresent(String[][] plateau, String camp) {
        String roi = "R" + camp;
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                if (plateau[i][j] != null && plateau[i][j].equals(roi)) {
                    return true;
                }
            }
        }
        return false;
    }


}

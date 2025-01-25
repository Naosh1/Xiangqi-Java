public class Fou {

    public static boolean deplacerFou(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String couleur) {
        String pieceDepart = plateau[ligne][colonne];
        if (pieceDepart == null || !pieceDepart.startsWith("F") || !pieceDepart.endsWith(couleur)) {
            System.out.println("Erreur : Pas de fou de votre camp à cette position.");
            return false;
        }

        // Vérifier que le déplacement est d'une case en diagonale
        int diagLigne = Math.abs(nvLigne - ligne);
        int diagColonne = Math.abs(nvColonne - colonne);
        if (diagLigne != 1 || diagColonne != 1) {
            System.out.println("Déplacement invalide : Le fou doit se déplacer d'une case en diagonale.");
            return false;
        }

        // Vérifier que le fou reste dans le palais
        if (couleur.equals("N")) {
            if (nvLigne < 0 || nvLigne > 2 || nvColonne < 3 || nvColonne > 5) {
                System.out.println("Déplacement invalide : Le fou doit rester dans le palais du camp Noir.");
                return false;
            }
        } else if (couleur.equals("B")) { // Camp Blanc
            if (nvLigne < 7 || nvLigne > 9 || nvColonne < 3 || nvColonne > 5) {
                System.out.println("Déplacement invalide : Le fou doit rester dans le palais du camp Blanc.");
                return false;
            }
        }

        String pieceArrivee = plateau[nvLigne][nvColonne];
        if (!pieceArrivee.equals(".") && pieceArrivee.endsWith(couleur)) {
            System.out.println("Déplacement invalide : La case d'arrivée est occupée par une pièce alliée.");
            return false;
        }

        plateau[nvLigne][nvColonne] = plateau[ligne][colonne];
        plateau[ligne][colonne] = ".";
        System.out.println("Le fou a été déplacé avec succès.");
        return true;
    }
}


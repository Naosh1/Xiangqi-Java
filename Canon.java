public class Canon {

    public static boolean deplacer(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String camp) {
        String piece = plateau[ligne][colonne];

        if (nvLigne < 0 || nvLigne >= plateau.length || nvColonne < 0 || nvColonne >= plateau[0].length) {
            System.out.println("Erreur : Déplacement hors des limites du plateau.");
            return false;
        }

        if (piece == null || !piece.startsWith("B" + camp)) {
            System.out.println("Erreur : La pièce sélectionnée n'est pas un canon de votre camp.");
            return false;
        }

        if (ligne != nvLigne && colonne != nvColonne) {
            System.out.println("Erreur : Le canon doit se déplacer verticalement ou horizontalement.");
            return false;
        }

        boolean pieceIntermediaireTrouvee = false;

        // Vérification du chemin pour un déplacement horizontal
        if (ligne == nvLigne) {
            int start = Math.min(colonne, nvColonne) + 1;
            int end = Math.max(colonne, nvColonne);

            for (int i = start; i < end; i++) {
                if (plateau[ligne][i] != null && !plateau[ligne][i].equals(".")) {
                    if (pieceIntermediaireTrouvee) {
                        System.out.println("Erreur : Le chemin est bloqué par plusieurs pièces.");
                        return false;
                    }
                    pieceIntermediaireTrouvee = true;
                }
            }
        }
        // Vérification du chemin pour un déplacement vertical
        else if (colonne == nvColonne) {
            int start = Math.min(ligne, nvLigne) + 1;
            int end = Math.max(ligne, nvLigne);

            for (int i = start; i < end; i++) {
                if (plateau[i][colonne] != null && !plateau[i][colonne].equals(".")) {
                    if (pieceIntermediaireTrouvee) {
                        System.out.println("Erreur : Le chemin est bloqué par plusieurs pièces.");
                        return false;
                    }
                    pieceIntermediaireTrouvee = true;
                }
            }
        }

        // Vérification des règles de capture et de saut
        String pieceDestination = plateau[nvLigne][nvColonne];
        if (pieceIntermediaireTrouvee) {
            if (pieceDestination != null && !pieceDestination.startsWith(camp)) {
                plateau[nvLigne][nvColonne] = piece;
                plateau[ligne][colonne] = ".";
                System.out.println("Succès : Le canon a capturé une pièce ennemie.");
                return true;
            } else {
                System.out.println("Erreur : Le canon doit capturer une pièce après un saut.");
                return false;
            }
        } else {
            if (pieceDestination == null) {
                plateau[nvLigne][nvColonne] = piece;
                plateau[ligne][colonne] = ".";
                System.out.println("Succès : Le canon a été déplacé sur une case vide.");
                return true;
            } else {
                System.out.println("Erreur : Le canon ne peut pas capturer sans sauter.");
                return false;
            }
        }
    }
}

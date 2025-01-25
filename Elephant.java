public class Elephant {

    // Méthode pour déplacer l'éléphant
    public static boolean deplacer(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String camp) {
        String piece = plateau[ligne][colonne];
        if (nvLigne < 0 || nvLigne >= plateau.length || nvColonne < 0 || nvColonne >= plateau[0].length) {
            System.out.println("Déplacement hors des limites !");
            return false;
        }

        if (piece == null || !piece.startsWith("E" + camp)) {
            System.out.println("La pièce sélectionnée n'est pas un éléphant de votre camp !");
            return false;
        }

        // Vérifier si l'éléphant essaie de traverser la rivière
        if (camp.equals("N") && nvLigne >= 5) {
            System.out.println("L'éléphant noir ne peut pas traverser la rivière !");
            return false;
        }
        if (camp.equals("B") && nvLigne <= 4) {
            System.out.println("L'éléphant blanc ne peut pas traverser la rivière !");
            return false;
        }

        int verifLigne = Math.abs(nvLigne - ligne);
        int verifColonne = Math.abs(nvColonne - colonne);
        if (verifLigne != 2 || verifColonne != 2) {
            System.out.println("Déplacement invalide : L'éléphant doit se déplacer de 2 cases en diagonale.");
            return false;
        }

        String pieceArrivee = plateau[nvLigne][nvColonne];
        if (pieceArrivee != null && pieceArrivee.startsWith(camp)) {
            System.out.println("Déplacement invalide : La case d'arrivée est occupée par une pièce alliée.");
            return false;
        }

        // Vérification que la case intermédiaire est libre (l'éléphant ne peut pas sauter par-dessus d'autres pièces)
        int ligneIntermediaire = (ligne + nvLigne) / 2;
        int colonneIntermediaire = (colonne + nvColonne) / 2;

        if (plateau[ligneIntermediaire][colonneIntermediaire] != null && !plateau[ligneIntermediaire][colonneIntermediaire].equals(".")) {
            System.out.println("Déplacement invalide : L'éléphant ne peut pas sauter par-dessus d'autres pièces.");
            return false;
        }

        plateau[nvLigne][nvColonne] = piece;
        plateau[ligne][colonne] = ".";
        System.out.println("L'éléphant a été déplacé avec succès.");
        return true;
    }
}
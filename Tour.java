public class Tour {

    public static boolean deplacer(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String campActuel) {
        if (ligne != nvLigne && colonne != nvColonne) {
            System.out.println("La tour ne peut se déplacer que sur une ligne ou une colonne.");
            return false;
        }

        if (ligne == nvLigne) {
            int minCol = Math.min(colonne, nvColonne);
            int maxCol = Math.max(colonne, nvColonne);
            for (int i = minCol + 1; i < maxCol; i++) {
                String caseIntermediaire = plateau[ligne][i];
                if (caseIntermediaire != null && !caseIntermediaire.equals(".") && !caseIntermediaire.endsWith(campActuel)) {
                    System.out.println("Il y a un obstacle sur le chemin horizontal. Déplacement annulé.");
                    return false;
                }
            }
        } else if (colonne == nvColonne) {
            int minLigne = Math.min(ligne, nvLigne);
            int maxLigne = Math.max(ligne, nvLigne);
            for (int i = minLigne + 1; i < maxLigne; i++) {
                String caseIntermediaire = plateau[i][colonne];
                if (caseIntermediaire != null && !caseIntermediaire.equals(".") && !caseIntermediaire.endsWith(campActuel)) {
                    System.out.println("Il y a un obstacle sur le chemin vertical. Déplacement annulé.");
                    return false;
                }
            }
        }

        String pieceDestination = plateau[nvLigne][nvColonne];
        if (pieceDestination != null && !pieceDestination.equals(".") && pieceDestination.endsWith(campActuel)) {
            System.out.println("Vous ne pouvez pas déplacer la tour sur une case occupée par une pièce de votre camp.");
            return false;
        }

        plateau[nvLigne][nvColonne] = plateau[ligne][colonne];
        plateau[ligne][colonne] = ".";
        System.out.println("La tour a été déplacée avec succès.");
        return true;
    }
}

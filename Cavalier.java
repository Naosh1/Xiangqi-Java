public class Cavalier {

    public static boolean estDeplacementValide(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String campActuel) {
        int diffLigne = Math.abs(nvLigne - ligne);
        int diffColonne = Math.abs(nvColonne - colonne);

        // Vérifier que le déplacement est en "L"
        if (!((diffLigne == 2 && diffColonne == 1) || (diffLigne == 1 && diffColonne == 2))) {
            return false;
        }

        // Vérifier si le cavalier est bloqué
        if (diffLigne == 2) {
            int bloqueLigne = (nvLigne > ligne) ? ligne + 1 : ligne - 1;
            if (plateau[bloqueLigne][colonne] != null && !plateau[bloqueLigne][colonne].equals(".")) {
                return false;
            }
        } else if (diffColonne == 2) {
            int bloqueColonne = (nvColonne > colonne) ? colonne + 1 : colonne - 1;
            if (plateau[ligne][bloqueColonne] != null && !plateau[ligne][bloqueColonne].equals(".")) {
                return false;
            }
        }

        // Vérifier que la case de destination est valide
        String pieceDestination = plateau[nvLigne][nvColonne];
        if (pieceDestination != null && !pieceDestination.equals(".") && pieceDestination.endsWith(campActuel)) {
            return false;
        }

        return true;
    }

    public static boolean deplacer(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String campActuel) {
        if (!estDeplacementValide(plateau, ligne, colonne, nvLigne, nvColonne, campActuel)) {
            System.out.println("Déplacement invalide.");
            return false;
        }

        plateau[nvLigne][nvColonne] = plateau[ligne][colonne];
        plateau[ligne][colonne] = ".";
        System.out.println("Le cavalier a été déplacé avec succès.");
        return true;
    }
}
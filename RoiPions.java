public class RoiPions {

    public static boolean deplacement(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String campActuel) {
        if (nvLigne < 0 || nvLigne >= plateau.length || nvColonne < 0 || nvColonne >= plateau[0].length) {
            System.out.println("Déplacement hors des limites !");
            return false;
        }

        String piece = plateau[ligne][colonne];
        if (piece == null || piece.equals(".")) {
            System.out.println("Aucune pièce à déplacer !");
            return false;
        }

        if (!plateau[nvLigne][nvColonne].equals(".") && plateau[nvLigne][nvColonne].endsWith(campActuel)) {
            System.out.println("Vous ne pouvez pas capturer une pièce de votre propre camp !");
            return false;
        }

        if (piece.startsWith("R")) {
            int diffLigne = Math.abs(nvLigne - ligne);
            int diffColonne = Math.abs(nvColonne - colonne);

            if ((diffLigne + diffColonne) != 1) {
                System.out.println("Le roi ne peut se déplacer que d'une case dans les directions cardinales.");
                return false;
            }

            if (!estDansPalais(nvLigne, nvColonne, campActuel)) {
                System.out.println("Le roi ne peut pas quitter le palais.");
                return false;
            }

            plateau[nvLigne][nvColonne] = piece;
            plateau[ligne][colonne] = ".";
            System.out.println("Roi déplacé avec succès !");
            return true;
        }

        System.out.println("Ce type de pièce ne peut pas être déplacé par cette méthode.");
        return false;
    }

    private static boolean estDansPalais(int ligne, int colonne, String campActuel) {
        if (campActuel.equals("N")) {
            return (ligne >= 0 && ligne <= 2 && colonne >= 3 && colonne <= 5);
        } else if (campActuel.equals("B")) {
            return (ligne >= 7 && ligne <= 9 && colonne >= 3 && colonne <= 5);
        }
        return false;
    }
}
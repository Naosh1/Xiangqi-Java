public class Case {
    public static boolean caseLibre(String[][] tab, int ligne, int colonne) {
        System.out.println("piece : '" + tab[ligne][colonne] + "'");
        if (!tab[ligne][colonne].equals("     ")) return false;
        else return true;
    }
    public static boolean cheminLibre(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne) {
        if (ligne == nvLigne) {
            int min = Math.min(colonne, nvColonne);
            int max = Math.max(colonne, nvColonne);
            for (int j = min + 1; j < max; j++) {
                if (plateau[ligne][j] != null) {
                    return false;
                }
            }
        } else if (colonne == nvColonne) {
            int min = Math.min(ligne, nvLigne);
            int max = Math.max(ligne, nvLigne);
            for (int i = min + 1; i < max; i++) {
                if (plateau[i][colonne] != null) {
                    return false;
                }
            }
        }

        return true;
    }
}
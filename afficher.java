public class afficher {

    // Méthode pour afficher le plateau
    public static void afficherPlateau(String[][] plateau) {
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                // Si la case est vide (null), afficher "."
                if (plateau[i][j] == null) {
                    System.out.print(". ");
                } else {
                    System.out.print(plateau[i][j] + " ");
                }
            }
            System.out.println(); // Nouvelle ligne après chaque rangée
        }
    }
    
}

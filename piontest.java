public class piontest {
    public class Tour {

        public static boolean mouvementValide(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne) {
            if (ligne != nvLigne && colonne != nvColonne) {
                return false;
            }

            if (!cheminLibre(plateau, ligne, colonne, nvLigne, nvColonne)) {
                return false;
            }

            return true;
        }

        private static boolean cheminLibre(String[][] plateau, int ligneDepart, int colonneDepart, int ligneArrivee, int colonneArrivee) {
            if (ligneDepart == ligneArrivee) {
                int min = Math.min(colonneDepart, colonneArrivee);
                int max = Math.max(colonneDepart, colonneArrivee);
                for (int j = min + 1; j < max; j++) {
                    if (plateau[ligneDepart][j] != null) {
                        return false;
                    }
                }
            } else if (colonneDepart == colonneArrivee) {
                int min = Math.min(ligneDepart, ligneArrivee);
                int max = Math.max(ligneDepart, ligneArrivee);
                for (int i = min + 1; i < max; i++) {
                    if (plateau[i][colonneDepart] != null) {
                        return false;
                    }
                }
            }

            return true;
        }

        public static void deplacerTour(String[][] plateau, int ligneDepart, int colonneDepart, int ligneArrivee, int colonneArrivee) {
            if (mouvementValide(plateau, ligneDepart, colonneDepart, ligneArrivee, colonneArrivee)) {
                plateau[ligneArrivee][colonneArrivee] = plateau[ligneDepart][colonneDepart];
                plateau[ligneDepart][colonneDepart] = null;
                System.out.println("Déplacement effectué !");
            } else {
                System.out.println("Déplacement invalide !");
            }
        }
    }
    public class cavalier {

        public static boolean mouvementValide(String[][] plateau, int ligneDepart, int colonneDepart, int ligneArrivee, int colonneArrivee) {
            if (ligneDepart != ligneArrivee && colonneDepart != colonneArrivee) {
                return false;
            }


            if (!cheminLibre(plateau, ligneDepart, colonneDepart, ligneArrivee, colonneArrivee)) {
                return false;
            }

            return true;
        }

        private static boolean cheminLibre(String[][] plateau, int ligneDepart, int colonneDepart, int ligneArrivee, int colonneArrivee) {
            if (ligneDepart == ligneArrivee) { // Mouvement horizontal
                int min = Math.min(colonneDepart, colonneArrivee);
                int max = Math.max(colonneDepart, colonneArrivee);
                for (int j = min + 1; j < max; j++) {
                    if (plateau[ligneDepart][j] != null) {
                        return false;
                    }
                }
            } else if (colonneDepart == colonneArrivee) {
                int min = Math.min(ligneDepart, ligneArrivee);
                int max = Math.max(ligneDepart, ligneArrivee);
                for (int i = min + 1; i < max; i++) {
                    if (plateau[i][colonneDepart] != null) {
                        return false;
                    }
                }
            }

            return true;
        }

        public static void deplacerTour(String[][] plateau, int ligneDepart, int colonneDepart, int ligneArrivee, int colonneArrivee) {
            if (mouvementValide(plateau, ligneDepart, colonneDepart, ligneArrivee, colonneArrivee)) {
                plateau[ligneArrivee][colonneArrivee] = plateau[ligneDepart][colonneDepart];
                plateau[ligneDepart][colonneDepart] = null;
                System.out.println("Déplacement effectué !");
            } else {
                System.out.println("Déplacement invalide !");
            }
        }
    }
}

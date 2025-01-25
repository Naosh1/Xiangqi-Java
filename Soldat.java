public class Soldat {

    public static boolean deplacer(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String camp) {
        String piece = plateau[ligne][colonne];

        if (nvLigne < 0 || nvLigne >= plateau.length || nvColonne < 0 || nvColonne >= plateau[0].length) {
            System.out.println("Déplacement hors des limites !");
            return false;
        }

        if (piece == null || !piece.startsWith("S") || !piece.endsWith(camp)) {
            System.out.println("La pièce sélectionnée n'est pas un soldat de votre camp !");
            return false;
        }

        if (camp.equals("N")) {
            return deplacerSoldatNoir(plateau, ligne, colonne, nvLigne, nvColonne, piece);
        }
        else if (camp.equals("B")) {
            return deplacerSoldatBlanc(plateau, ligne, colonne, nvLigne, nvColonne, piece);
        } else {
            System.out.println("Camp inconnu !");
            return false;
        }
    }

    private static boolean deplacerSoldatNoir(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String piece) {
        if (ligne < 5) {
            if (nvLigne == ligne + 1 && nvColonne == colonne) {
                if (plateau[nvLigne][nvColonne].equals(".") || !plateau[nvLigne][nvColonne].endsWith("N")) {
                    plateau[nvLigne][nvColonne] = piece;
                    plateau[ligne][colonne] = ".";
                    System.out.println("Soldat noir déplacé !");
                    return true;
                } else {
                    System.out.println("La case de destination est occupée par une pièce alliée !");
                    return false;
                }
            } else {
                System.out.println("Déplacement invalide pour un soldat noir avant la rivière (il ne peut qu'avancer d'une case en vertical).");
                return false;
            }
        } else {
            if ((nvLigne == ligne + 1 && nvColonne == colonne) ||
                    (nvLigne == ligne - 1 && nvColonne == colonne) ||
                    (nvLigne == ligne && Math.abs(nvColonne - colonne) == 1)) {
                if (plateau[nvLigne][nvColonne].equals(".") || !plateau[nvLigne][nvColonne].endsWith("N")) {
                    plateau[nvLigne][nvColonne] = piece;
                    plateau[ligne][colonne] = ".";
                    System.out.println("Soldat noir déplacé !");
                    return true;
                } else {
                    System.out.println("La case de destination est occupée par une pièce alliée !");
                    return false;
                }
            } else {
                System.out.println("Déplacement invalide pour un soldat noir après la rivière.");
                return false;
            }
        }
    }

    private static boolean deplacerSoldatBlanc(String[][] plateau, int ligne, int colonne, int nvLigne, int nvColonne, String piece) {
        if (ligne > 4) {
            if (nvLigne == ligne - 1 && nvColonne == colonne) {
                if (plateau[nvLigne][nvColonne].equals(".") || !plateau[nvLigne][nvColonne].endsWith("B")) {
                    plateau[nvLigne][nvColonne] = piece;
                    plateau[ligne][colonne] = ".";
                    System.out.println("Soldat blanc déplacé !");
                    return true;
                } else {
                    System.out.println("La case de destination est occupée par une pièce alliée !");
                    return false;
                }
            } else {
                System.out.println("Déplacement invalide pour un soldat blanc avant la rivière (il ne peut qu'avancer d'une case en vertical).");
                return false;
            }
        } else {
            if ((nvLigne == ligne + 1 && nvColonne == colonne) ||
                    (nvLigne == ligne - 1 && nvColonne == colonne) ||
                    (nvLigne == ligne && Math.abs(nvColonne - colonne) == 1)) {
                if (plateau[nvLigne][nvColonne].equals(".") || !plateau[nvLigne][nvColonne].endsWith("B")) {
                    plateau[nvLigne][nvColonne] = piece;
                    plateau[ligne][colonne] = ".";
                    System.out.println("Soldat blanc déplacé !");
                    return true;
                } else {
                    System.out.println("La case de destination est occupée par une pièce alliée !");
                    return false;
                }
            } else {
                System.out.println("Déplacement invalide pour un soldat blanc après la rivière.");
                return false;
            }
        }
    }
}

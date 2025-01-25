public class Départ {
    public static String[][] plateauDépart() {
        String[][] plateau = new String[10][9];

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                plateau[i][j] = ".";
            }
        }
        // NOIR
        plateau[0][0] = "TN";
        plateau[0][8] = "TN";
        plateau[0][7] = "CN";
        plateau[0][1] = "CN";
        plateau[0][2] = "EN";
        plateau[0][6] = "EN";
        plateau[0][5] = "FN";
        plateau[0][3] = "FN";
        plateau[0][4] = "RN";
        plateau[2][1] = "BN";
        plateau[2][7] = "BN";
        plateau[3][0] = "SN";
        plateau[3][2] = "SN";
        plateau[3][4] = "SN";
        plateau[3][6] = "SN";
        plateau[3][8] = "SN";

        //Blanc
        plateau[9][0] = "TB";
        plateau[9][8] = "TB";
        plateau[9][7] = "CB";
        plateau[9][1] = "CB";
        plateau[9][2] = "EB";
        plateau[9][6] = "EB";
        plateau[9][5] = "FB";
        plateau[9][3] = "FB";
        plateau[9][4] = "RB";
        plateau[7][1] = "BB";
        plateau[7][7] = "BB";
        plateau[6][0] = "SB";
        plateau[6][2] = "SB";
        plateau[6][4] = "SB";
        plateau[6][6] = "SB";
        plateau[6][8] = "SB";

        return plateau;
    }
}

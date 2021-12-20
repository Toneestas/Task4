public class Help {
    public static void getHelp(String[] gameMoves){
        int helpTableLength = gameMoves.length+1;
        String[][] helpTable = new String[helpTableLength][helpTableLength];
        for(int i = 1; i<helpTableLength;i++) {
            helpTable[0][i] = gameMoves[i - 1];
            helpTable[i][0] = gameMoves[i - 1];
        }

        for(int i = 1; i<helpTableLength;i++) {
            for (int j = 1; j < helpTableLength; j++) {
                helpTable[i][j] = Win.isWin(Integer.toString(i), j-1, gameMoves);
            }
        }

        for(int i = 0; i<helpTableLength;i++) {
            for (int j = 0; j < helpTableLength; j++) {
                System.out.printf("%15s",helpTable[i][j]);
            }
            System.out.println();
        }
    }
}

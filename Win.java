public class Win {
    public static String isWin(String userMove, int computerMove, String[] gameMoves) throws NumberFormatException{
        int a = Integer.parseInt(userMove);
        if(a-1 == computerMove){
            return "Draw";
        }
        for (int i = a; i < a + gameMoves.length / 2; i++) {
            if(gameMoves[computerMove].equals(gameMoves[i % gameMoves.length]))
                return "Win";
        }
        return "Lost";
    }
}

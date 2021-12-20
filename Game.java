import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] gameMoves) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
       while(true) {
           if (!inputValidation(gameMoves))
               return;

           int computerMove = new Random().nextInt(gameMoves.length);
           byte[] cryptographicKey = Key.getCryptographicKey();
           byte[] hmac = Hmac.getHmac(cryptographicKey, gameMoves[computerMove]);

           System.out.println("HMAC: " + bytesToHex(hmac));

           showMenu(gameMoves);

           System.out.print("Enter your move: ");

           String userMove = getUserMove();

           if(userMove.equals("0"))
               return;
           else if (userMove.equals("?")) {
               Help.getHelp(gameMoves);
               continue;
           }
           else
               try {
                   System.out.println("Your move: " + gameMoves[Integer.parseInt(userMove) - 1]);
                   System.out.println("Computer move: " + gameMoves[computerMove]);
                   String result = Win.isWin(userMove, computerMove, gameMoves);
                   System.out.println(result);
               }catch (Exception e){
                   System.out.println("Invalid value entered!");
                   continue;
               }

           System.out.println("HMAC key: " + bytesToHex(cryptographicKey));
       }
    }

    public static String getUserMove(){
        Scanner s = new Scanner(System.in);
        return s.nextLine();
    }

    public static void showMenu(String[] gameMoves){
        System.out.println("Available moves:");
        for(int i = 0; i<gameMoves.length; i++)
            System.out.println(i+1 + " - " + gameMoves[i]);
        System.out.println("0 - exit");
        System.out.println("? - help");
    }

    public static String bytesToHex(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
             sb.append(String.format("%02x", b));
        return sb.toString();
    }

    private static boolean inputValidation(String[] gameMoves){
        String inputInformation = "The number of parameters must be odd and more than three.\nIt is also not allowed to enter the same parameters.\nInput could be: Rock Scissors Paper";
        for (int i = 0; i < gameMoves.length; i++) {
            for (int j = i+1; j < gameMoves.length; j++) {
                if(gameMoves[i].equals(gameMoves[j])) {
                    System.out.println("Duplicate parameters have been introduced!\n" + inputInformation);
                    return false;
                }
            }
        }
        if (gameMoves.length == 0) {
            System.out.println("No parameters have been entered!\n" + inputInformation);
            return false;
        }
        else if (gameMoves.length < 3) {
            System.out.println("Fewer than three parameters entered!\n" + inputInformation);
            return false;
        }
        else if (gameMoves.length % 2 == 0){
            System.out.println("An even number of parameters have been entered!\n" + inputInformation);
            return false;
        }
        return true;
    }
}

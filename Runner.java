import java.util.Scanner;
public class Runner {
  public static void main(String[] args) {
    /*
        _:_
       '-.-'
      __.'.__
     |_______|
      \=====/
       )___(
      /_____\
       |   |
       |   |
       |   |
       |   |
       |   |
      /_____\
     (=======)
     }======={
    (_________)
    */
    Scanner scanner = new Scanner(System.in);
    System.out.println("This is the Trial Program for the Chessbot System.\nCurrently it is only able to run a Random Game.\n It will create the next board state every 10 seconds.\nIf you have not yet run the console command 'chcp 65001' please do so now.\nThis allows the console to display the Unicode Chess Pieces.");
    System.out.println("Begin Random Game? (Y/N)");
    boolean scanning = true;
    while(scanning){
      String input = scanner.nextLine();
      if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
        
        ChessBot.randomGame();
        // scanner.close();
        scanning = false;
      }else{
        System.out.print("Are you Sure?");
      }
    }
  }
}
import java.util.Scanner;
// import java.util.ArrayList;
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
    System.out.println("This is the Trial Program for the Chessbot System.\nCurrently it is set to run a PLAYER Game.\nPlease only use Algebraic Chess Notation.\nAn error will throw if ANYTHING ELSE is passed through the scanner.");
    // System.out.println("THIS IS CURRENTLY IN A DEBUG STATE.");
    boolean scanning = true;
    System.out.println("Use Unicode instead of Letters?");
    boolean unicode = false;
    String input = scanner.nextLine();
    if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
      unicode = true;
    }
    System.out.println("Begin PLAYER Game? (Y/N)");
    scanning = true;
    // ArrayList<Long> gameResults = new ArrayList<Long>();
    while(scanning){
      input = scanner.nextLine();
      if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
        // COMMENTED LINES ARE FOR DEBUG PURPOSES
        // for(int i = 0; i < 100; i++){ //Iterating through 100 seeds in order to bugtest
          /*gameResults.add(*/ChessBot.playerGame(unicode)/*)*/;
        //   System.out.println("Press ENTER to continue, type 'ESCAPE' to print all completed games.");
        //   input = scanner.nextLine();
        //   if(input.indexOf("ESC") != -1){
        //     i = i + 100;
        //   }
        // } 
        // for(Long i : gameResults){
        //   System.out.println(i);
        // }
        scanner.close();
        scanning = false;
      }else{
        System.out.print("Are you Sure?");
      }
    }
  }
}
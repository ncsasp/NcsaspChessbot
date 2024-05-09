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
    System.out.println("This is the Trial Program for the Chessbot System.\nCurrently it is only able to run a Random Game.\nPress ENTER in order to move to the next move.");
    // System.out.println("THIS IS CURRENTLY IN A DEBUG STATE.");
    boolean scanning = true;
    System.out.println("Autopromote to Queen? (Y/N)");
    boolean autoPromote = false;
    String input = scanner.nextLine();
    if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
      autoPromote = true;
    }
    System.out.println("Use Unicode instead of Letters?");
    boolean unicode = false;
    input = scanner.nextLine();
    if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
      unicode = true;
    }
    System.out.println("Begin Random Game? (Y/N)");
    scanning = true;
    // ArrayList<Long> gameResults = new ArrayList<Long>();
    while(scanning){
      input = scanner.nextLine();
      if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
        // COMMENTED LINES ARE FOR DEBUG PURPOSES
        // for(int i = 0; i < 100; i++){ //Iterating through 100 seeds in order to bugtest
          /*gameResults.add(*/ChessBot.randomGame(unicode,autoPromote)/*)*/;
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
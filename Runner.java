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
    System.out.println("This is the Trial Program for the Chessbot System.\nCurrently it is only able to run a Random Game.\nIt will run a game, then pause at the end for 5 seconds.");
    System.out.println("THIS IS CURRENTLY IN A DEBUG STATE.");
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
    while(scanning){
      input = scanner.nextLine();
      if(input.indexOf("y") != -1 || input.indexOf("Y") != -1){
        for(int i = 0; i < 100; i++){ //Iterating through 100 seeds in order to bugtest
          ChessBot.randomGame(unicode,autoPromote);
          // System.out.println("Continue?");
          // input = scanner.nextLine();
          // if(input.indexOf("eed") != -1){
          //   System.out.println(seed);
          //   i = 1000;
          // }
          try {
            Thread.sleep(5000);
          } catch (InterruptedException e) {
    
          }
        } 
        scanner.close();
        scanning = false;
      }else{
        System.out.print("Are you Sure?");
      }
    }
  }
}
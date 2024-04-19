import java.util.Random;
//COMMENTED LINES ARE FOR DEBUG PURPOSES
// import java.util.ArrayList;
import java.util.Scanner;
public class ChessBot{
  private static Scanner input = new Scanner(System.in);
  @SuppressWarnings("unused")
  private boolean unicode;
  @SuppressWarnings("unused")
  private boolean autoPromote;
  // private static Scanner seedChecker = new Scanner("Seeds.txt");
  // private static ArrayList<Long> seeds = new ArrayList<Long>();
  // public static void debugPrep(){
  //   while(seedChecker.hasNextLong()){
  //     seeds.add(seedChecker.nextLong());
  //   }
  // }
  public static Long randomGame(boolean unicode, boolean autoPromote){
    // boolean checking = true;
    // boolean found = false;
    long randNum = 0;
    // while(checking){
      randNum = (long)(Math.random() * 1000);
    //   for(long i : seeds){
    //     if(i == randNum){
    //       found = true;
    //     }
    //   }
    //   if(!found){
    //     checking = false;
    //     seeds.add((long)randNum);
    //   }
    // }
    return randomGame(randNum, unicode, autoPromote);
  }
  public static Long randomGame(long randNum, boolean unicode, boolean autoPromote){
    int drawCounter = 0;
    Board permaBoard = new Board();
    Random randomNumberGenerator = new Random(randNum);
    System.out.println("Your game's seed is: " + randNum + ".");
    permaBoard.printBoard(unicode);
    try {
      Thread.sleep(0);
    } catch (InterruptedException e) {

    }
    // ArrayList<String> tried = new ArrayList<String>();
    boolean[][][][] tried = new boolean[8][8][8][8];
    int count = 0;
    while(permaBoard.checkStatus()){
      int a = randomNumberGenerator.nextInt(8);
      int b = randomNumberGenerator.nextInt(8);
      int c = randomNumberGenerator.nextInt(8);
      int d = randomNumberGenerator.nextInt(8);
      drawCounter++;
      boolean inList = false;
      if(tried[a][b][c][d]){
        inList = true;
      }else{
        tried[a][b][c][d] = true;
        count++;
      }
      if(!inList){
        if(permaBoard.checkValid(a,b,c,d,false,autoPromote)){
          permaBoard.movePiece(a,b,c,d,false,autoPromote);
          tried = new boolean[8][8][8][8];
          count = 0;
          permaBoard.printBoard(unicode);
          System.out.println(randNum);
          // try {
          //   Thread.sleep(0);
          // } catch (InterruptedException e) {
    
          // }
          input.nextLine();

        }
      }else{
        if(count >= 4096){
          permaBoard.setCheckmate(true);
        }
      }
    }
    if(permaBoard.checkForCheck()){
      System.out.println("Checkmate!");
    }else{
      System.out.println("Stalemate!");
    }
    System.out.println("Total Attempted Random Moves: " + drawCounter + ".");
    return randNum;
  }
  
  
  
}

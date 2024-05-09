import java.util.Random;
//COMMENTED LINES ARE FOR DEBUG PURPOSES
// import java.util.ArrayList;
// import java.util.Scanner;
public class ChessBot{
  // private static Scanner input = new Scanner(System.in);
  @SuppressWarnings("unused")
  private static boolean unicode;
  @SuppressWarnings("unused")
  public static boolean autoPromote;
  static int drawCounter = 0;
  static Random randomNumberGenerator = new Random();
  //DEBUG STUFF
  // private static Scanner seedChecker = new Scanner("Seeds.txt");
  // private static ArrayList<Long> seeds = new ArrayList<Long>();
  // public static void debugPrep(){
  //   while(seedChecker.hasNextLong()){
  //     seeds.add(seedChecker.nextLong());
  //   }
  // }
  public static Long randomGame(boolean unicode, boolean autoPromotey){
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
    return randomGame(randNum, unicode, autoPromotey);
  }
  public static Long randomGame(long randNum, boolean unicode, boolean autoPromotey){
    autoPromote = autoPromotey;
    Board permaBoard = new Board();
    randomNumberGenerator.setSeed(randNum);
    System.out.println("Your game's seed is: " + randNum + ".");
    permaBoard.printBoard(unicode);
    try {
      Thread.sleep(0);
    } catch (InterruptedException e) {

    }
    makeRandomMoves(permaBoard);
    System.out.println(randNum);
    // try {
    //   Thread.sleep(0);
    // } catch (InterruptedException e) {
  
    // }
    // input.nextLine();
    if(permaBoard.checkStalemate()){
      System.out.println("Stalemate!");
    }else if(permaBoard.checkForCheck()){
      System.out.println("Checkmate!");
      if(permaBoard.getTurn() == -1){
        System.out.println("White Wins!");
      }else if(permaBoard.getTurn() == 1){
        System.out.println("Black Wins!");
      }
    }else{
      System.out.println("Stalemate!");
    }
    System.out.println("Total Attempted Random Moves: " + drawCounter + ".");
    return randNum;
  }
  private static void makeRandomMoves(Board board){
    boolean[][][][] tried = new boolean[8][8][8][8];
    int count = 0;
    while(board.checkStatus()){
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
        if(board.checkValid(a,b,c,d,false)){
          System.out.println("--------------------------------\n");
          int[] coordinates = {a,b,c,d};
          System.out.println(MoveInterpreter.convertToString(coordinates,board));
          board.movePiece(a,b,c,d,false);
          tried = new boolean[8][8][8][8];
          count = 0;
          board.printBoard(unicode);
          

        }
      }else{
        if(count >= 4096){
          board.setCheckmate(true);
          return;
        }
      }
    }
  }
}
  

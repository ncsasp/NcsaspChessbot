import java.util.Random;
// import java.util.ArrayList;
public class ChessBot{
  private boolean unicode;
  private boolean autoPromote;
  public static long randomGame(boolean unicode, boolean autoPromote){
    int randNum = (int)(Math.random() * 1000);
    return randomGame(randNum, unicode, autoPromote);

  }
  public static long randomGame(long randNum, boolean unicode, boolean autoPromote){
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
          try {
            Thread.sleep(0);
          } catch (InterruptedException e) {
    
          }

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

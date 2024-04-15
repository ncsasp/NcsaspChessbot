import java.util.Random;
import java.util.ArrayList;
public class ChessBot{
  public static void randomGame(){
    int randNum = (int)(Math.random() * 1000);
    randomGame(randNum);

  }
  public static void randomGame(long randNum){
    Board permaBoard = new Board();
    Random randomNumberGenerator = new Random(randNum);
    System.out.println("Your game's seed is: " + randNum + ".");
    permaBoard.printBoard();
    try {
      Thread.sleep(0);
    } catch (InterruptedException e) {

    }
    ArrayList<String> tried = new ArrayList<String>();
    while(permaBoard.checkStatus() != 2){
      int a = randomNumberGenerator.nextInt(8);
      int b = randomNumberGenerator.nextInt(8);
      int c = randomNumberGenerator.nextInt(8);
      int d = randomNumberGenerator.nextInt(8);
      String test = a+" "+b+" "+c+" "+d;
      boolean inList = false;
      for(int i = 0; i < tried.size(); i++){
        if(tried.get(i).equals(test)){
          inList = true;
        }
      }
      if(!inList){
        tried.add(a+" "+b+" "+c+" "+d);
        if(permaBoard.checkValid(a,b,c,d,false)){
          permaBoard.movePiece(a,b,c,d,false);
          tried = new ArrayList<String>();
          permaBoard.printBoard();
          try {
            Thread.sleep(0);
          } catch (InterruptedException e) {
    
          }

        }
      }else{
        if(tried.size() >= 4096){
          permaBoard.setCheckmate(true);
        }
      }
    }
    if(permaBoard.checkForCheck()){
      System.out.println("Checkmate!");
    }else{
      System.out.println("Stalemate!");
    }
  }
  
  
  
}

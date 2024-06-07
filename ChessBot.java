import java.util.Random;
//COMMENTED LINES ARE FOR DEBUG PURPOSES
import java.util.ArrayList;
import java.util.Scanner;
public class ChessBot{
  private static Scanner input = new Scanner(System.in);
  private static boolean unicode;
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
  private static boolean checkForMate(Board board){
    for(int a = 0; a < 8; a++){
      for(int b = 0; b < 8; b++){
        for(int c = 0; c < 8; c++){
          for(int d = 0; d < 8; d++){
            if(board.checkValid(a,b,c,d,false)){
              return true;
            }
          }
        }
      }
    }
    board.setCheckmate(true);
    return false;
  }
  public static void Smartgame(boolean unicode,int movesAhead){
    autoPromote = true;
    Board permaBoard = new Board();
    permaBoard.printBoard(unicode);
    while(permaBoard.checkStatus() == 0){
      System.out.println("");
      System.out.print("Chessbot is thinking");
      findBestMove((movesAhead * 2)-1, permaBoard, true, true);
      permaBoard.printBoard(unicode);
      if(checkForMate(permaBoard)){
        System.out.println("");
        System.out.print("Chessbot is thinking");
        findBestMove((movesAhead * 2)-1, permaBoard, false, true);
        permaBoard.printBoard(unicode);
        checkForMate(permaBoard);
      }
    }
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
  }
  public static void playerGame(boolean unicode){
    autoPromote = false;
    Board permaBoard = new Board();
    permaBoard.printBoard(unicode);
    while(checkForMate(permaBoard)){
      int[] move = MoveInterpreter.interpret(input.nextLine(),permaBoard);
      if(permaBoard.checkValidCastling(move[0],move[1],move[2],move[3],false)){
        permaBoard.movePiece(move,true);
      }else{
        permaBoard.movePiece(move,false);
      }
      permaBoard.printBoard(unicode);
    }
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
  }
  public static long playerRandomGame(boolean unicode){
    long randNum = 0;
    randNum = (long)(Math.random() * 10000);
    return playerRandomGame(randNum, unicode);
  }
  public static void playerSmartGame(boolean unicode, int movesAhead){
    autoPromote = true;
    Board permaBoard = new Board();
    permaBoard.printBoard(unicode);
    if(randomNumberGenerator.nextInt(2) == 1){
      while(permaBoard.checkStatus() == 0){
      autoPromote = false;
      boolean moving = true;
      while(moving){
        int[] move = MoveInterpreter.interpret(input.nextLine(),permaBoard);
      
        if(permaBoard.checkValidCastling(move[0],move[1],move[2],move[3],false)){
          moving = !permaBoard.movePiece(move,true);
        }else{
          moving = !permaBoard.movePiece(move,false);
        }
      }
      permaBoard.printBoard(unicode);
      checkForMate(permaBoard);
      autoPromote = true;
      System.out.println("");
      System.out.print("Chessbot is thinking");
      findBestMove((movesAhead * 2)-1, permaBoard, false,true);
      permaBoard.printBoard(unicode);
    }
    } else{
      while(permaBoard.checkStatus() == 0){
        System.out.println("");
        System.out.print("Chessbot is thinking");
        findBestMove((movesAhead * 2)-1,permaBoard,true,true);
        permaBoard.printBoard(unicode);
        if(permaBoard.checkStatus() == 0){
          autoPromote = false;
          boolean moving = true;
          while(moving){
            int[] move = MoveInterpreter.interpret(input.nextLine(),permaBoard);
            if(permaBoard.checkValidCastling(move[0],move[1],move[2],move[3],false)){
              moving = !permaBoard.movePiece(move,true);
            }else{
              moving = !permaBoard.movePiece(move,false);
            }
          }
          permaBoard.printBoard(unicode);
          checkForMate(permaBoard);
          autoPromote = true;
        }
      }
    }
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
  }
  public static double findBestMove(int movesAhead, Board board, boolean color,boolean original){
    if(movesAhead == 0){
      return board.getEval();
    }else{
      double best;
      if(color){
        best = -9999999;
      }else{
        best = 9999999;
      }
      ArrayList<int[]> bestMoves = new ArrayList<int[]>();
      for(int a = 0; a < 8; a++){
        if(original){
          System.out.print(".");
        }
        for(int b = 0; b < 8; b++){
          for(int c = 0; c < 8; c++){
            for(int d = 0; d < 8; d++){
              
              if(board.checkValid(a,b,c,d,false)){
                Move temp = new Move(a, b, c, d, board.getBoard(), board.getwMoves(),board.getbMoves(), autoPromote);
                double tempEval = findBestMove(movesAhead-1,temp.getBoardObj(),!color,false);
                if(color){
                  if(tempEval > best){
                    bestMoves = new ArrayList<int[]>();
                    best = tempEval;
                    bestMoves.add(new int[]{a,b,c,d,});
                  }else if(tempEval == best){
                    bestMoves.add(new int[]{a,b,c,d});
                  }else{
                    // System.out.println("That's a shitty move");
                  }
                }else{
                  if(tempEval < best){
                    bestMoves = new ArrayList<int[]>();
                    best = tempEval;
                    bestMoves.add(new int[]{a,b,c,d});
                  }else if(tempEval == best){
                    bestMoves.add(new int[]{a,b,c,d});
                  }else{
                    // System.out.println("That's a shitty move");
                  }
                }
              }
            }
          }
        }
      }
      if(bestMoves.size() != 0){
        int[] temp = bestMoves.get((int)(Math.random() * bestMoves.size()));
        if(original){
          if(board.checkValidCastling(temp[0],temp[1],temp[2],temp[3],false)){
            board.movePiece(temp[0],temp[1],temp[0],temp[3],true,true);
            if(temp[3] == temp[1]-2){
              board.storeMoveToHistory("O-O", board.getTurn());
            }else if(temp[3] == temp[1]+2){
              board.storeMoveToHistory("O-O-O", board.getTurn());
            }
          }else{
            board.movePiece(temp[0],temp[1],temp[2],temp[3],false,false);
          }
          
        }else{
          if(board.checkValidCastling(temp[0],temp[1],temp[2],temp[3],true)){
            board.movePiece(temp[0],temp[1],temp[0],temp[3],true,true);
          }else{
            board.movePiece(temp[0],temp[1],temp[2],temp[3],true,false);
          }
        }
        return board.getEval();
      }else{
        return board.getEval();
      }
    }
  }
  
  public static long playerRandomGame(long randNum, boolean unicode){
    autoPromote = true;
    Board permaBoard = new Board();
    randomNumberGenerator.setSeed(randNum);
    System.out.println("Your game's seed is: " + randNum + ".");
    permaBoard.printBoard(unicode);
    if(randomNumberGenerator.nextInt(2) == 1){
      while(permaBoard.checkStatus() == 0){
      autoPromote = false;
      boolean moving = true;
      while(moving){
        int[] move = MoveInterpreter.interpret(input.nextLine(),permaBoard);
        if(permaBoard.checkValidCastling(move[0],move[1],move[2],move[3],false)){
          moving = !permaBoard.movePiece(move,true);
        }else{
          moving = !permaBoard.movePiece(move,false);
        }
      }
      permaBoard.printBoard(unicode);
      checkForMate(permaBoard);
      autoPromote = true;
      if(permaBoard.checkStatus() == 0){
        boolean testValue = false;
        while(!testValue){
        boolean[][][][] tried = new boolean[8][8][8][8];
        int count = 0;
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
          if(permaBoard.checkValid(a,b,c,d,false)){
            System.out.println("--------------------------------\n");
            int[] coordinates = {a,b,c,d};
            if(permaBoard.checkValidCastling(a,b,c,d,false)){
              permaBoard.movePiece(a,b,c,d,false,true);
            }else{
              permaBoard.movePiece(a,b,c,d,false,false);
            }
            testValue = true;
            System.out.println(MoveInterpreter.convertToString(coordinates,permaBoard));
            tried = new boolean[8][8][8][8];
            count = 0;
            permaBoard.printBoard(unicode);
            
  
          }
        }else{
          if(count >= 4096){
            permaBoard.setCheckmate(true);
            testValue = true;
          }
        }
      }
      }
    }
    } else{
      while(permaBoard.checkStatus() == 0){
        boolean testValue = false;
        while(!testValue){
        boolean[][][][] tried = new boolean[8][8][8][8];
        int count = 0;
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
          if(permaBoard.checkValid(a,b,c,d,false)){
            System.out.println("--------------------------------\n");
            int[] coordinates = {a,b,c,d};
            if(permaBoard.checkValidCastling(a,b,c,d,false)){
              permaBoard.movePiece(a,b,c,d,false,true);
            }else{
              permaBoard.movePiece(a,b,c,d,false,false);
            }
            testValue = true;
            System.out.println(MoveInterpreter.convertToString(coordinates,permaBoard));
            tried = new boolean[8][8][8][8];
            count = 0;
            permaBoard.printBoard(unicode);
            
  
          }
        }else{
          if(count >= 4096){
            permaBoard.setCheckmate(true);
            testValue = true;
          }
        }
      }
        if(permaBoard.checkStatus() == 0){
          autoPromote = false;
          boolean moving = true;
          while(moving){
            int[] move = MoveInterpreter.interpret(input.nextLine(),permaBoard);
            if(permaBoard.checkValidCastling(move[0],move[1],move[2],move[3],false)){
              moving = !permaBoard.movePiece(move,true);
            }else{
              moving = !permaBoard.movePiece(move,false);
            }
          }
          permaBoard.printBoard(unicode);
          checkForMate(permaBoard);
          autoPromote = true;
        }
      }
    }
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
  public static long randomGame(boolean unicode, boolean autoPromotey){
    // boolean checking = true;
    // boolean found = false;
    long randNum = 0;
    // while(checking){
      randNum = (long)(Math.random() * 10000);
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
    while(board.checkStatus()==0){
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
          if(board.checkValidCastling(a,b,c,d,false)){
            board.movePiece(a,b,c,d,false,true);//TODO: Finish Castling
          }else{
          board.movePiece(a,b,c,d,false,false);
          }
          System.out.println(MoveInterpreter.convertToString(coordinates,board));
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
  

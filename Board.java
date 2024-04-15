import java.util.Scanner;

import java.util.ArrayList;
public class Board {
  // Default Chessboard positions on a standard chess board
  private Piece[][] board = new Piece[][] {
      { new Rook(-1, 7, 0), new Knight(-1, 7, 1), new Bishop(-1, 7, 2), new Queen(-1, 7, 3), new King(-1, 7, 4),
          new Bishop(-1, 7, 5), new Knight(-1, 7, 6), new Rook(-1, 7, 7) },
      { new Pawn(-1, 6, 0), new Pawn(-1, 6, 1), new Pawn(-1, 6, 2), new Pawn(-1, 6, 3), new Pawn(-1, 6, 4),
          new Pawn(-1, 6, 5), new Pawn(-1, 6, 6), new Pawn(-1, 6, 7) },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { new Pawn(1, 1, 0), new Pawn(1, 1, 1), new Pawn(1, 1, 2), new Pawn(1, 1, 3), new Pawn(1, 1, 4),
          new Pawn(1, 1, 5), new Pawn(1, 1, 6), new Pawn(1, 1, 7) },
      { new Rook(1, 0, 0), new Knight(1, 0, 1), new Bishop(1, 0, 2), new Queen(1, 0, 3), new King(1, 0, 4),
          new Bishop(1, 0, 5), new Knight(1, 0, 6), new Rook(1, 0, 7) } };

  private int bMoves = 0;
  private int wMoves = 0;
  private boolean inCheck = false;
  private boolean inCheckmate = false;
  public int getTurn(){
    if(wMoves > bMoves){
      return -1;
    }else if(wMoves == bMoves){
      return 1;
    }else{
      System.out.println("AAAAAAAAAAAAA IT DID THE THING AGAIN " + wMoves + " " + bMoves);
      return 0;
    }
  }

  public Board(Piece[][] board, int wMoves, int bMoves) {
    this.board = board;
    this.wMoves = wMoves;
    this.bMoves = bMoves;
  }

  public Board() {
  }

  public Board(Piece[][] board) {
    this.board = board;
  }

  public int getMoves() {
    return wMoves - bMoves;
  }

  public int checkStatus() {
    // 0 = nothing
    // 1 = check
    // 2 = mate
    if (inCheckmate) {
      return 2;
    } else if (inCheck) {
      return 1;
    } else {
      if(checkStalemate()){
        return 3;
      }
      return 0;
    }
  }
  public void setCheckmate(boolean value){
    inCheckmate = value;
  }
  public boolean getInCheck(){
    return inCheck;
  }
  public int getEval() {
    int sum = 0;
    for (int outer = 0; outer < 8; outer++) {
      for (int inner = 0; inner < 8; inner++) {
        if (board[outer][inner] != null) {
          sum += board[outer][inner].getValue();
        }
      }
    }
    return sum;
  }

  public Piece[][] getBoard() {
    return board;
  }

  public void printBoard() {
    /*
     * How the hell do i print this?
     * --- --- --- --- --- --- --- ---
     * |BR |BN |BB |BQ |BK |BB |BN |BR |
     * --- --- --- --- --- --- --- ---
     * |BP |BP |BP |BP |BP |BP |BP |BP |
     * --- --- --- --- --- --- --- ---
     * | | | | | | | | |
     * --- --- --- --- --- --- --- ---
     * | | | | | | | | |
     * --- --- --- --- --- --- --- ---
     * | | | | | | | | |
     * --- --- --- --- --- --- --- ---
     * | | | | | | | | |
     * --- --- --- --- --- --- --- ---
     * |WP |WP |WP |WP |WP |WP |WP |WP |
     * --- --- --- --- --- --- --- ---
     * |WR |WN |WB |WQ |WK |WB |WN |WR |
     * --- --- --- --- --- --- --- ---
     * 
     * Update: I did it!
     */
    for (int outer = 0; outer < 8; outer++) {
      System.out.println("\n --- --- --- --- --- --- --- ---");
      System.out.print("|");
      for (int inner = 0; inner < 8; inner++) {
        if (board[outer][inner] != null) {
          if (board[outer][inner].getColor() == -1) {
            System.out.print("B");
          } else {
            System.out.print("W");
          }

          if (board[outer][inner].getType() == 1) {
            if (board[outer][inner].getColor() == -1) {
              System.out.print("\u265F");
            } else {
              System.out.print("\u2659");
            }
          } else if (board[outer][inner].getType() == 2) {
            if (board[outer][inner].getColor() == -1) {
              System.out.print("\u265D");
            } else {
              System.out.print("\u2657");
            }
          } else if (board[outer][inner].getType() == 3) {
            if (board[outer][inner].getColor() == -1) {
              System.out.print("\u265E");
            } else {
              System.out.print("\u2658");
            }
          } else if (board[outer][inner].getType() == 4) {
            if (board[outer][inner].getColor() == -1) {
              System.out.print("\u265C");
            } else {
              System.out.print("\u2656");
            }
          } else if (board[outer][inner].getType() == 5) {
            if (board[outer][inner].getColor() == -1) {
              System.out.print("\u265B");
            } else {
              System.out.print("\u2655");
            }
          } else if (board[outer][inner].getType() == 6) {
            if (board[outer][inner].getColor() == -1) {
              System.out.print("\u265A");
            } else {
              System.out.print("\u2654");
            }
          } else {
            System.out.print("something went wrong");
          }
          System.out.print(" |");
        } else {
          System.out.print("   |");
        }
      }
    }
    System.out.println("\n --- --- --- --- --- --- --- ---");
    boardAnalysis();
  }
  public boolean checkStalemate(){
    ArrayList<Piece> pieces = new ArrayList<Piece>();
    for(int i = 0; i<8;i++){
      for(int j = 0; j<8; j++){
        if(board[i][j] != null){
          pieces.add(board[i][j]);
        }
      }
    }
    if(pieces.size() == 2){ //Checks if there are only two pieces left on the board, in which case there are only kings left.
      return true;
    }else{
      for(int i = 0; i < pieces.size(); i++){
        if(pieces.get(i).getType() == 6){
          pieces.remove(i);
          i--;
        }
      }
      if(pieces.size() > 2){
        return false;
      }
      for(int i = 0; i < pieces.size(); i++){
        if(pieces.get(i).getType() == 1 || pieces.get(i).getType() == 4 || pieces.get(i).getType() == 5){
          return false;
        }else if(pieces.size() == 1){
          return true;
        }else if(pieces.get(i).getType() == 3){
          return false;
        }else{
          if(pieces.get(i).getSquareColor() == pieces.get(i+1).getSquareColor()){
            return true;
          }
        }
      }
    }
    return false;
  }

  public void boardAnalysis() {
    int eval = getEval();
    if (eval < 0) {
      System.out.println("Black is currently winning by " + Math.abs(eval) + " points of material.");
    } else if (eval > 0) {
      System.out.println("White is currently winning by " + eval + " points of material.");
    } else {
      System.out.println("Black and White have the same amount of Material.");
    }
    System.out.println("White has taken " + wMoves + " moves, and Black has taken " + bMoves + " moves.");
  }

  public void movePiece(int rank, int file, int newRank, int newFile, boolean checking) {
    Piece temp = null;
    if (newRank > 7 || newFile > 7 || newRank < 0 || newFile < 0) {
      System.out.println("Piece cannot move outside the chessboard!");
    } else if (rank == newRank && file == newFile) {
      System.out.println("This will not cause movement!");
    } else {
      if (board[7 - rank][file] == null) {
        System.out.println("There is not a piece there!");
      } else {
        temp = board[7 - rank][file];
        if (checkValid(rank, file, newRank, newFile, checking)) {
          board[7 - rank][file] = null;
          board[7 - newRank][newFile] = temp;
          board[7 - newRank][newFile].move(newRank, newFile);
          if (board[7 - newRank][newFile].getColor() == -1) {
            bMoves++;
          } else {
            wMoves++;
          }
          // PROMOTION CLAUSE
          if (!checking){
            if (board[7 - newRank][newFile].getType() == 1 && (newRank == 0 || newRank == 7)) {
              boolean promoting = true;
              @SuppressWarnings("resource")
              Scanner scanner = new Scanner(System.in);
              while (promoting) {
                System.out.println("What would you like to promote to?");
                String input = scanner.nextLine();
                int tempColor = board[7-newRank][newFile].getColor();
                if (input.indexOf("night") != -1) {
                  board[7 - newRank][newFile] = new Knight(tempColor,newRank,newFile);
                  promoting = false;
                  System.out.println("You promoted to a Knight!");
  
                } else if (input.indexOf("ishop") != -1) {
                  board[7 - newRank][newFile] = new Bishop(tempColor,newRank,newFile);
                  promoting = false;
                  System.out.println("You promoted to a Bishop!");
  
                } else if (input.indexOf("ook") != -1) {
                  board[7 - newRank][newFile] = new Rook(tempColor,newRank,newFile);
                  promoting = false;
                  System.out.println("You promoted to a Rook!");
  
                } else if (input.indexOf("ueen") != -1) {
                  board[7 - newRank][newFile] = new Queen(tempColor,newRank,newFile);
                  promoting = false;
                  System.out.println("You promoted to a Queen!");
  
                } else {
                  System.out.println("Unrecognized Piece. Valid options are: 'Queen', 'Knight', 'Bishop', and 'Rook'.");
                }
              }
            }
          }else{
            //TODO do the shit with the thing you know the one. do it. it's important.
          }
          

        } else {
          System.out.println("This move is Invalid!");
        }
      }
    }
  }

  public boolean checkValid(int rank, int file, int newRank, int newFile, boolean checking) {
    if (board[7 - rank][file] == null) {
      return false;
    }
    if (getTurn() != board[7 - rank][file].getColor()) {
      return false;
    } 

    
    
    switch (board[7 - rank][file].getType()) {
      case 1:
        Pawn p = (Pawn) board[7 - rank][file];
        if(!p.valid(board, newRank, newFile)){
          return false;
        }
        break;
      case 2:
        Bishop b = (Bishop) board[7 - rank][file];
        if(!b.valid(board, newRank, newFile)){
          return false;
        }
        break;
      case 3:
        Knight n = (Knight) board[7 - rank][file];
        if(!n.valid(board, newRank, newFile)){
          return false;
        }
        break;
      case 4:
        Rook r = (Rook) board[7 - rank][file];
        if(!r.valid(board, newRank, newFile)){
          return false;
        }
        break;
      case 5:
        Queen q = (Queen) board[7 - rank][file];
        if(!q.valid(board, newRank, newFile)){
          return false;
        }
        break;
      case 6:
        King k = (King) board[7 - rank][file];
        if(!k.valid(board, newRank, newFile)){
          return false;
        }
        break;
      default:
        System.out.println("Data returned as Piece, returning false.");
        return false;
    }

    // Finds the king and detects if the player is in check. During the switch
    // statement, it will actually do something with the information.
    if (!checking) {
      int kingRank = -1;
      int kingFile = -1;

      Move tempMove = new Move(rank, file, newRank, newFile, board,wMoves,bMoves);
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          if (tempMove.getBoard()[7 - i][j] != null) {
            if (tempMove.getBoard()[7 - i][j].getType() == 6 && tempMove.getBoard()[7-i][j].getColor() == getTurn()) {
              kingRank = i;
              kingFile = j;
              if(((King) tempMove.getBoard()[7 - kingRank][kingFile]).checkForCheck(board, kingRank, kingFile)){
                return false;
              }
            }
          }
        }
      }
      if(kingRank == -1 || kingFile == -1){
        System.out.println("Game is Over!");
        inCheckmate = true;
        return false;
      }
    }
    return true;
  }
  public boolean checkForCheck(){
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(board[i][j] != null){
          if(board[i][j].getType() == 6){
            return ((King) board[i][j]).checkForCheck(board, 7-i, j);
          }
        }
      }
    }
    return false;
    
  }
}


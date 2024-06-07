import java.util.Scanner;

import java.util.ArrayList;
public class Board {
  private ArrayList<int[]> takenPieces = new ArrayList<int[]>();
  // Default Chessboard positions on a standard chess board
  private Piece[][] board = new Piece[][] {
      { new Rook(-1, 7, 0), new Knight(-1, 7, 1), new Bishop(-1, 7, 2), new Queen(-1, 7, 3), new King(-1, 7, 4,false),
          new Bishop(-1, 7, 5), new Knight(-1, 7, 6), new Rook(-1, 7, 7) },
      { new Pawn(-1, 6, 0), new Pawn(-1, 6, 1), new Pawn(-1, 6, 2), new Pawn(-1, 6, 3), new Pawn(-1, 6, 4),
          new Pawn(-1, 6, 5), new Pawn(-1, 6, 6), new Pawn(-1, 6, 7) },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { null, null, null, null, null, null, null, null },
      { new Pawn(1, 1, 0), new Pawn(1, 1, 1), new Pawn(1, 1, 2), new Pawn(1, 1, 3), new Pawn(1, 1, 4),
          new Pawn(1, 1, 5), new Pawn(1, 1, 6), new Pawn(1, 1, 7) },
      { new Rook(1, 0, 0), new Knight(1, 0, 1), new Bishop(1, 0, 2), new Queen(1, 0, 3), new King(1, 0, 4,false),
          new Bishop(1, 0, 5), new Knight(1, 0, 6), new Rook(1, 0, 7) } };
  private int bMoves = 0;
  private int wMoves = 0;
  private boolean inCheck = false;
  private boolean inCheckmate = false;
  public int getwMoves(){
    return wMoves;
  }
  public int getbMoves(){
    return bMoves;
  }
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
  public ArrayList<String> WmoveHistory = new ArrayList<String>();
  public ArrayList<String> BmoveHistory = new ArrayList<String>();

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
    if(checkStalemate()){
      return 1;
    }else if(inCheckmate){
      return 2;
    }
    return 0;
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
    if(inCheck){
      if(getTurn() == -1){
        sum += 100;
      }else if(getTurn() == 1){
          sum -= 100;
      }
    }
    if(checkStatus() == 2){
      if(getTurn() == -1){
        return 100000;
      }else if(getTurn() == 1){
        return -100000;
      }
    }else if(checkStatus() == 1){
      if(getTurn() == -1){
        return -100000;
      }else if(getTurn() == 1){
        return 100000;
      }
    }
    return sum;
  }

  public Piece[][] getBoard() {
    return board;
  }

  public String getTaken(boolean unicode){
    String output = "TAKEN PIECES: ";
    for(int[] simplifiedPiece : takenPieces){
      if(simplifiedPiece[1] == -1){
        output += "W";
      }else if(simplifiedPiece[1] == 1){
        output += "B";
      }else{
        return "Taken Pieces Error: No color";
      }
      if(!unicode){
        switch(simplifiedPiece[0]){
          case 1: output+= "P";break;
          case 2: output+= "B";break;
          case 3: output+= "N";break;
          case 4: output+= "R";break;
          case 5: output+= "Q";break;
          case 6: output+= "K";break;
          default:return "Taken Pieces Error: Incorrect Piece Typing (not a value from 1 - 6)";
        }
      }else{
        switch(simplifiedPiece[0]){
          case 1:
            if(simplifiedPiece[1] == 1){
              output+="\u265F";
            } else {
              output+="\u2659";
            }
            break;
          case 2:
          if(simplifiedPiece[1] == 1){
            output+="\u265D";
          } else {
            output+="\u2657";
          }
          break;
          case 3:
          if(simplifiedPiece[1] == 1){
            output+="\u265E";
          } else {
            output+="\u2658";
          }
          break;
          case 4:
          if(simplifiedPiece[1] == 1){
            output+="\u265C";
          } else {
            output+="\u2656";
          }
          break;
          case 5:
          if(simplifiedPiece[1] == 1){
            output+="\u265B";
          } else {
            output+="\u2655";
          }
          break;
          case 6:
          if(simplifiedPiece[1] == 1){
            output+="\u265A";
          } else {
            output+="\u2654";
          }
          break;
          default:return "Taken Pieces Error: Incorrect Piece Typing (not a value from 1 - 6)";
        }
      }
      output+= ", ";
    }
    return output;
  }
  public void printBoard(boolean unicode) {
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
    System.out.println("\033[H\033[2J");
    System.out.print("                                             WHITE   BLACK ");
    for (int outer = 0; outer < 8; outer++) {
      System.out.println("\n   --- --- --- --- --- --- --- ---         |-------|-------|");
      System.out.print((8-outer) + " |");
      for (int inner = 0; inner < 8; inner++) {
        if (board[outer][inner] != null) {
          if (board[outer][inner].getColor() == -1) {
            System.out.print("B");
          } else {
            System.out.print("W");
          }

          if (board[outer][inner].getType() == 1) {
            if(unicode){
              if (board[outer][inner].getColor() == -1) {
                System.out.print("\u265F");
              } else {
                System.out.print("\u2659");
              }
            }else{
              System.out.print("P");
            }
          } else if (board[outer][inner].getType() == 2) {
            if(unicode){
              if (board[outer][inner].getColor() == -1) {
                System.out.print("\u265D");
              } else {
                System.out.print("\u2657");
              }
            }else{
              System.out.print("B");
            }
          } else if (board[outer][inner].getType() == 3) {
            if(unicode){
              if (board[outer][inner].getColor() == -1) {
                System.out.print("\u265E");
              } else {
                System.out.print("\u2658");
              }
            }else{
              System.out.print("N");
            }
          } else if (board[outer][inner].getType() == 4) {
            if(unicode){
              if (board[outer][inner].getColor() == -1) {
                System.out.print("\u265C");
              } else {
                System.out.print("\u2656");
              }
            }else{
              System.out.print("R");
            }
          } else if (board[outer][inner].getType() == 5) {
            if(unicode){
              if (board[outer][inner].getColor() == -1) {
                System.out.print("\u265B");
              } else {
                System.out.print("\u2655");
              }
            }else{
              System.out.print("Q");
            }
          } else if (board[outer][inner].getType() == 6) {
            if(unicode){
              if (board[outer][inner].getColor() == -1) {
                System.out.print("\u265A");
              } else {
                System.out.print("\u2654");
              }
            }else{
              System.out.print("K");
            }
          } else {
            System.out.print("something went wrong");
          }
          System.out.print(" |");
        } else {
          System.out.print("   |");
        }
      }
      //Printing the Move History Log
      System.out.print(" ");
      int Wtemp = (WmoveHistory.size()-1) - (outer);
      int Btemp = (BmoveHistory.size()-1) - (outer);
      int turn = wMoves-outer;
      if(turn < 0){
        turn = 0;
      }
      if(WmoveHistory.size() > outer && Wtemp >= 0){
        String mhStringWht = WmoveHistory.get(Wtemp);
        while(mhStringWht.length() < 7){
          mhStringWht+=" ";
        } 
        if(turn >= 100){
          System.out.print("    "+(turn)+"|" + mhStringWht + "|");
        }else if(turn >= 10){
          System.out.print("     "+(turn)+"|" + mhStringWht + "|");
        }else{
          System.out.print("      "+(turn)+"|" + mhStringWht + "|");
        }
        
      }else{
        if(turn >= 100){
          System.out.print("    "+(turn)+"|       |");
        }else if(turn >= 10){
          System.out.print("     "+(turn)+"|       |");
        }else{
          System.out.print("      "+(turn)+"|       |");
        }
        
      }
      if(BmoveHistory.size() > outer && Btemp >= 0){
        String mhStringBlk = BmoveHistory.get(Btemp);
        while(mhStringBlk.length() < 7){
          mhStringBlk+=" ";
        }
        System.out.print(mhStringBlk + "|");
      }else{
        System.out.print("       |");
      }
    }
    System.out.println("\n   --- --- --- --- --- --- --- ---         |-------|-------|");
    System.out.println("    a   b   c   d   e   f   g   h");
    boardAnalysis(unicode);
  }
  public void storeMoveToHistory(String move, int color){
    if(color == 1){
      WmoveHistory.add(move);
      BmoveHistory.add("");
    }else if(color == -1){
      BmoveHistory.set(BmoveHistory.size()-1, move);
    }
  }
  public void storeMoveToHistory(int[] move, int color){
    if(color == 1){
      WmoveHistory.add(MoveInterpreter.convertToString(move,this));
      BmoveHistory.add("");
    }else if(color == -1){
      BmoveHistory.set(BmoveHistory.size()-1,MoveInterpreter.convertToString(move,this));
    }
  }
  public int[] getMoveFromHistory(int index, int color){
    if(color == 1){
    return MoveInterpreter.interpret(WmoveHistory.get(index),this);
    }else if(color == -1){
    return MoveInterpreter.interpret(BmoveHistory.get(index),this);
    }else{
      return new int[]{-1,-1,-1,-1};
    }
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
    int bCount = 0;
    int nCount = 0;
    if(pieces.size() == 2){ //Checks if there are only two pieces left on the board, in which case there are only kings left.
      return true;
    }else if(pieces.size() > 4){ //If there are more than four pieces left, it cannot be stalemate
      //Stalemate by Repetition
      int repetitionCounter = 0;
      for(int i = 0; i < BmoveHistory.size(); i++){
        String tempString = BmoveHistory.get(i);
        for(int j = 0; j < BmoveHistory.size(); j++){
          if(tempString.equals(BmoveHistory.get(j))){
            repetitionCounter++;
            j++;
            if(repetitionCounter == 3){
              return true;
            }
          }else{
            repetitionCounter = 0;
          }
        }
      }
      repetitionCounter = 0;
      for(int i = 0; i < WmoveHistory.size(); i++){
        String tempString = WmoveHistory.get(i);
        for(int j = 0; j < WmoveHistory.size(); j++){
          if(tempString.equals(WmoveHistory.get(j))){
            repetitionCounter++;
            j++;
            if(repetitionCounter == 3){
              return true;
            }
          }else{
            repetitionCounter = 0;
          }
        }
      }
      return false;
    }else{
      for(int i = 0; i < pieces.size(); i++){ //Removes the Kings from the array, as they are not needed.
        if(pieces.get(i).getType() == 6){
          pieces.remove(i);
          i--;
        }else if(pieces.get(i).getType() == 1 || pieces.get(i).getType() == 4 || pieces.get(i).getType() == 5){ //If one of the pieces is a Pawn, Rook, or Queen, it cannot be stalemate.
          return false;
        }else if(pieces.get(i).getType() == 2){
          bCount++;
        }else if(pieces.get(i).getType() == 3){
          nCount++;
        }else{
          System.out.println("what");
        }
      }
      if(pieces.size() == 1){ //If there is only one piece, and it has gotten to this point, it is either a knight or a bishop, in which case there is insufficient material.
        return true;
      }
      if(bCount == nCount){ //If there are an equal number of bishops and knights, there is not insufficient material.
        return false;
      }
      if(nCount > 1){ //If there is more than one knight, there is not insufficient material.
        return false;
      }
      if(pieces.get(0).getColor() == pieces.get(1).getColor()){ //If both remaining bishops are *of* the same color, there is not stalemate
        return false;
      }else if(pieces.get(0).getSquareColor() == pieces.get(1).getSquareColor()){ //If both remaining bishops are *on* the same color, there is stalemate.
        return true;
      }
      
    }
    //Stalemate by Repetition
    int repetitionCounter = 0;
    for(int i = 0; i < BmoveHistory.size(); i++){
      String tempString = BmoveHistory.get(i);
      for(int j = 0; j < BmoveHistory.size(); j++){
        if(tempString.equals(BmoveHistory.get(j))){
          repetitionCounter++;
          j++;
          if(repetitionCounter == 3){
            return true;
          }
        }else{
          repetitionCounter = 0;
        }
      }
    }
    repetitionCounter = 0;
    for(int i = 0; i < WmoveHistory.size(); i++){
      String tempString = WmoveHistory.get(i);
      for(int j = 0; j < WmoveHistory.size(); j++){
        if(tempString.equals(WmoveHistory.get(j))){
          repetitionCounter++;
          j++;
          if(repetitionCounter == 3){
            return true;
          }
        }else{
          repetitionCounter = 0;
        }
      }
    }
    
    return false;
  }

  public void boardAnalysis(boolean unicode) {
    System.out.println(getTaken(unicode));
    double eval = getEval();
    if (eval < 0) {
      System.out.println("Black has advantage by " + Math.abs(eval)/10 + " points.");
    } else if (eval > 0) {
      System.out.println("White has advantage by " + eval/10 + " points.");
    } else {
      System.out.println("Neither side has advantage.");
    }
    // System.out.println("White has taken " + wMoves + " moves, and Black has taken " + bMoves + " moves.");
  }
  public boolean movePiece(int[] coordinates,boolean castling){
    for(int i : coordinates){
      if(i == -1){
        return false;
      }
    }
    return movePiece(coordinates[0],coordinates[1],coordinates[2],coordinates[3],false,castling);
  }

  public boolean movePiece(int rank, int file, int newRank, int newFile, boolean checking, boolean castling) {
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
          if(!checking){
            storeMoveToHistory(new int[]{rank,file,newRank,newFile},getTurn());
            if(board[7 - newRank][newFile] != null){
              if(board[7-newRank][newFile].getColor() == -1){
                takenPieces.add(new int[]{board[7 - newRank][newFile].getType(),board[7-newRank][newFile].getColor()});
              }else{
                takenPieces.add(0,new int[]{board[7 - newRank][newFile].getType(),board[7-newRank][newFile].getColor()});
              }
            }
          }
          board[7 - rank][file] = null;
          board[7 - newRank][newFile] = temp;
          board[7 - newRank][newFile].move(newRank, newFile);
          if(castling){
            if(newFile-2 == file){
              board[7-rank][5] = board[7-rank][7];
              board[7-rank][7] = null;
              board[7-newRank][newFile].move(rank,5);
            }else{
              board[7-rank][2] = board[7-rank][0];
              board[7-rank][0] = null;
              board[7-newRank][newFile].move(rank,2);
            }
          }
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
                String input;
                if(ChessBot.autoPromote){
                  input = "queen";
                }else{
                  System.out.println("What would you like to promote to?");
                  input = scanner.nextLine();
                }
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
          }

        } else {
          System.out.println("This move is Invalid!");
          return false;
        }
      }
    }
    return true;
  }
  public boolean checkValidCastling(int rank, int file, int newRank, int newFile, boolean checking){
    //If the first space is null
    if (board[7 - rank][file] == null) {
      return false;
    }
    //If it's not that piece's color
    if (getTurn() != board[7 - rank][file].getColor()) {
      return false;
    } 
    //If the piece on the first space is not a King
    if(board[7-rank][file].getType() != 6){
      return false;
    }
    King temp = (King)board[7-rank][file];
    //If the King has moved
    if(temp.getHasMoved()){
      return false;
    }
    //Short Castle
    if(newFile == file+2 && !(newFile+1 < 0)){
      if(board[7-rank][newFile+1] != null){
        if(board[7-rank][newFile+1].getType() == 4){
          if(!((Rook)board[7-rank][newFile+1]).getHasMoved()){
            for(int i = 1; i<3; i++){
              if(board[7-rank][file+i] != null){
                return false;
              }
            }
            return true;
          }
        }
      }
    //Long Castle
    }else if(newFile == file-2 && !(newFile-2 >= 8)){
      if(board[7-rank][newFile-2] != null){
        if(board[7-rank][newFile-2].getType() == 4){
          if(!((Rook)board[7-rank][newFile-2]).getHasMoved()){
            for(int i = 1; i<4; i++){
              if(board[7-rank][file-i] != null){
                return false;
              }
            }
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean checkValid(int rank, int file, int newRank, int newFile, boolean checking) {
    if (board[7 - rank][file] == null) {
      return false;
    }
    if (getTurn() != board[7 - rank][file].getColor()) {
      return false;
    } 
    if(checkValidCastling(rank, file, newRank, newFile, checking)){
      return true;
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

      Move tempMove = new Move(rank, file, newRank, newFile, board,wMoves,bMoves,ChessBot.autoPromote);
      for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {
          if (tempMove.getBoard()[7 - i][j] != null) {
            if (tempMove.getBoard()[7 - i][j].getType() == 6 && tempMove.getBoard()[7-i][j].getColor() == getTurn()) {
              kingRank = i;
              kingFile = j;
              if(((King) tempMove.getBoard()[7 - kingRank][kingFile]).checkForCheck(tempMove.getBoard(), kingRank, kingFile)){
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
            if(((King) board[i][j]).checkForCheck(board, 7-i, j)){
              return true;
            }
          }
        }
      }
    }
    return false;
    
  }
}


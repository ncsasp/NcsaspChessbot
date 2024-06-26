

public class King extends Piece{
  private boolean hasMoved = false;
  private double[][] WvalueChart = new double[][]{
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-2.0,-3.0,-3.0,-4.0,-4.0,-3.0,-3.0,-2.0},
    {-1.0,-2.0,-2.0,-2.0,-2.0,-2.0,-2.0,-1.0},
    { 2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0},
    { 2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0},
  };
  private double[][] BvalueChart = new double[][]{
    { 2.0, 3.0, 1.0, 0.0, 0.0, 1.0, 3.0, 2.0},
    { 2.0, 2.0, 0.0, 0.0, 0.0, 0.0, 2.0, 2.0},
    {-1.0,-2.0,-2.0,-2.0,-2.0,-2.0,-2.0,-1.0},
    {-2.0,-3.0,-3.0,-4.0,-4.0,-3.0,-3.0,-2.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
    {-3.0,-4.0,-4.0,-5.0,-5.0,-4.0,-4,0,-3.0},
  };
  public King(int color, int rank, int file,boolean hasMoved) {
      super(6, color, rank, file);
      if(color == 1){
        this.setValue(999 + WvalueChart[7-rank][file]);
      }else if(color == -1){
        this.setValue(-999 -BvalueChart[7-rank][file]);
      }else{
        System.out.println("No color.");
      }
      this.hasMoved = hasMoved;
  }
  public void move(int newRank, int newFile){
    super.move(newRank, newFile);
    if(getColor() == 1){
      this.setValue(999 + WvalueChart[7-newRank][newFile]);
    }else if(getColor() == -1){
      this.setValue(-999 -BvalueChart[7-newRank][newFile]);
    }else{
      System.out.println("No color.");
    }
    hasMoved = true;
  }
  public boolean getHasMoved(){
    return hasMoved;
  }
  public boolean valid(Piece[][] board, int newRank,int newFile){
      //The king can move one space in any direction. the easiest movement system i've done thus far.
    if((Math.abs(newRank-getRank()) == 1 || newRank-getRank() == 0) && (Math.abs(newFile-getFile()) == 1 || newFile-getFile() == 0)){
      if(board[7-newRank][newFile] != null){
        if(board[7-newRank][newFile].getColor() == getColor()){
          return false;
        }
      }
      return true;
    }
    return false;
  }
  public boolean checkForCheck(Piece[][] board,int rank,int file){
      /*Spaces to Check:
       for(int n = 1, n < 5, n++){
          rank + n, file + n : bishop queen
          rank - n, file + n : bishop queen if(n=1){pawn}
          rank + n, file - n : bishop queen
          rank - n, file - n : bishop queen if(n=1){pawn}
          rank, file + n : rook queen
          rank, file - n : rook queen
          rank + n, file : rook queen
          rank - n, file : rook queen
        }
        rank + 1, file + 2
        rank + 2, file + 1
        rank - 1, file + 2
        rank - 2, file + 1
        rank + 1, file - 2
        rank + 2, file - 1
        rank - 1, file - 2
        rank - 2, file + 1
      */
    //These variables represent whether the row/column/diagonal is blocked by a piece that cannot take the king
    boolean upRight = false;
    boolean upLeft = false;
    boolean downRight = false;
    boolean downLeft = false;
    boolean up = false;
    boolean right = false;
    boolean left = false;
    boolean down = false;
    for(int n = 1; n < 8; n++){
      //Down Right
      if(7-rank+n < 8 && file + n < 8){
        if(board[7 - rank + n][file + n] != null){
          if(!downRight){
            Piece temp = board[7-rank+n][file+n];
            if(temp.getType() == 2 || temp.getType() == 5 || (temp.getType() == 6 && n == 1) || (temp.getType() == 1 && n == 1 && temp.getColor() == 1 && getColor() == -1)){
              if(board[7-rank+n][file+n].getColor() != getColor()){
                return true;  
              }else{downRight = true;}
              
            }else{
              downRight = true;
            }
          }
        }
      }
      //Down Left
      if(7-rank+n < 8 && file - n >= 0){
        if(board[7 - rank + n][file - n] != null){
            if(!downLeft){
              Piece temp = board[7-rank+n][file-n];
              if(temp.getType() == 2 || temp.getType() == 5 || (temp.getType() == 6 && n == 1) || (temp.getType() == 1 && n == 1 && temp.getColor() == 1 && getColor() == -1)){
               if(board[7-rank+n][file-n].getColor() != getColor()){
                return true;  
                }else{downLeft = true;}
              }else{
                downLeft = true;
              }
            }
          }
        }
      //Up Right
      if(7-rank-n >= 0 && file+n < 8){
        if(board[7 - rank - n][file + n] != null){
          if(!upRight){
            Piece temp = board[7-rank-n][file+n];
            if(temp.getType() == 2 || temp.getType() == 5 || (temp.getType() == 1 && n == 1 && temp.getColor() == -1 && getColor() == 1) || (temp.getType() == 6 && n == 1)){
              if(board[7-rank-n][file+n].getColor() != getColor()){
                return true;  
              }else{upRight = true;}
            }else{
              upRight = true;
            }
          }
        }
      }
      //Up Left
      if(7-rank-n >= 0 && file - n >= 0){
        if(board[7 - rank - n][file - n] != null){
          if(!upLeft){
            Piece temp = board[7-rank-n][file-n];
            if(temp.getType() == 2 || temp.getType() == 5 || (temp.getType() == 1 && n == 1 && temp.getColor() == -1 && getColor() == 1) || (temp.getType() == 6 && n == 1)){
              if(board[7-rank-n][file-n].getColor() != getColor()){
                return true;  
              }else{upLeft = true;}
            }else{
              upLeft = true;
            }
          }
        }
      }
      //Right
      if(file+n < 8){
        if(board[7 - rank][file + n] != null){
          if(!right){
            int tempType = board[7-rank][file+n].getType();
            if(tempType == 4 || tempType == 5 || (tempType == 6 && n == 1)){
              if(board[7-rank][file+n].getColor() != getColor()){
                return true;  
              }else{right = true;}
            }else{
              right = true;
            }
          }
        }
      }
      //Left
      if(file - n >= 0){
        if(board[7 - rank][file - n] != null){
          if(!left){
            int tempType = board[7-rank][file-n].getType();
            if(tempType == 4 || tempType == 5 || (tempType == 6 && n == 1)){
              if(board[7-rank][file-n].getColor() != getColor()){
                return true;  
              }else{left = true;}
            }else{
              left = true;
            }
          }
        }
      }
      //Up
      if(7-rank+n < 8){
        if(board[7 - rank + n][file] != null){
          if(!up){
            int tempType = board[7-rank+n][file].getType();
            if(tempType == 4 || tempType == 5 || (tempType == 6 && n == 1)){
              if(board[7-rank+n][file].getColor() != getColor()){
                return true;  
              }else{up = true;}
            }else{
              up = true;
            }
          }
        }
      }
      //Down
      if(7-rank-n >= 0){
        if(board[7 - rank - n][file] != null){
          if(!down){
            int tempType = board[7-rank-n][file].getType();
            if(tempType == 4 || tempType == 5 || (tempType == 6 && n == 1)){
              if(board[7-rank-n][file].getColor() != getColor()){
                return true;  
              }else{down = true;}
            }else{
              down = true;
            }
          }
        }
      }
      
      //Ok so now that you've looked through all of those if() statments, you think we're done, right?
      //WRONG
      //Time to check for the knights baybeeeeee
      //You now have to look through the same number of if() statements because I can't think of a better way to do this
      //Actually i can, but it's too late. i've already made it like this.
      if(7-rank+1 < 8 && file + 2 < 8){
        if(board[7-rank+1][file+2] != null){
          Piece temp = board[7-rank+1][file+2];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank+2 < 8 && file+1 < 8){
        if(board[7-rank+2][file+1] != null){
          Piece temp = board[7-rank+2][file+1];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank-1 >= 0 && file+2 < 8){
        if(board[7-rank-1][file+2] != null){
          Piece temp = board[7-rank-1][file+2];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank-2 >= 0 && file+1 < 8){
        if(board[7-rank-2][file+1] != null){
          Piece temp = board[7-rank-2][file+1];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank+1 < 8 && file-2 >= 0){
        if(board[7-rank+1][file-2] != null){
          Piece temp = board[7-rank+1][file-2];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank+2 < 8 && file-1 >= 0){
        if(board[7-rank+2][file-1] != null){
          Piece temp = board[7-rank+2][file-1];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank-1 >= 0 && file-2 >= 0){
        if(board[7-rank-1][file-2] != null){
          Piece temp = board[7-rank-1][file-2];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
      if(7-rank-2 >= 0 && file-1 >= 0){
        if(board[7-rank-2][file-1] != null){
          Piece temp = board[7-rank-2][file-1];
          if(temp.getType() == 3 && temp.getColor() != getColor()){
            return true;
          }
        }
      }
    }
    return false;
  }
}

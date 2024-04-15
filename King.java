

public class King extends Piece{

  public King(int color, int rank, int file) {
      super(6, color, rank, file);
  }

  public boolean valid(Piece[][] board, int newRank,int newFile){
      //The king can move one space in any direction. the easiest movement system i've done thus far.
    if((Math.abs(newRank-getRank()) == 1 || newRank-getRank() == 0) && (Math.abs(newFile-getFile()) == 1 || newFile-getFile() == 0)){
      if(board[7-newRank][newFile] != null){
        if(board[7-newRank][newFile].getColor() == getColor()){
          return false;
        }
      }
      if(checkForCheck(board, newRank, newFile) == true){
        return false;
      }else{
        return true;
      }
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
              }
              
            }else{
              if(temp.getType() != 6){
                downRight = true;
              }
            
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
                }
              }else{
                if(temp.getType() != 6){
                  downLeft = true;
                }
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
              }
            }else{
              if(temp.getType() != 6){
                upRight = true;
              }
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
              }
            }else{
              if(temp.getType() != 6){
                upLeft = true;
              }
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
              }
            }else{
              if(tempType != 6){
                right = true;
              }
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
              }
            }else{
              if(tempType != 6){
                left = true;
              }
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
              }
            }else{
              if(tempType != 6){
                up = true;
              }
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
              }
            }else{
              if(tempType != 6){
                down = true;
              }
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

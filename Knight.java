

public class Knight extends Piece {

    public Knight(int color, int rank, int file) {
        super(3, color, rank, file);
    }
    public boolean valid(Piece[][] board, int newRank,int newFile){
        //the Knight moves 2 in one direction, and 1 in the other.
      //It does not have to worry about pieces in it's path.
      if((Math.abs(getRank()-newRank) == 2 && Math.abs(getFile()-newFile) == 1) || (Math.abs(getRank()-newRank) == 1 && Math.abs(getFile()-newFile) == 2)){
        if(board[7-newRank][newFile] != null){
          if(board[7-newRank][newFile].getColor() != getColor()){
            return true;
          }
        }else{
          return true;
        }
      }
      return false;
    }
}

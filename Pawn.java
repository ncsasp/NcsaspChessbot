

public class Pawn extends Piece{
    public Pawn(int color, int rank, int file) {
        super(1, color, rank, file);
    }
    
    public boolean valid(Piece[][] board,int newRank, int newFile){
        if(newFile - getFile() == 0){
            //If the pawn is on it's first square, it can move 2
            if(getRank() == 1 && newRank == 3 && getColor() == 1){
              if(board[5][getFile()] == null && board[4][getFile()] == null){
                return true;
              }
            }else if(getRank() == 6 && newRank == 4 && getColor() == -1){
              if(board[2][getFile()] == null && board[3][getFile()] == null){
                return true;
              }
            //Otherwise, the pawn only moves one space forwards.
            }else if(newRank-getRank() == 1 && getColor() == 1){
              if(board[7-newRank][getFile()] == null){ 
                return true;
              }
            }else if(newRank-getRank() == -1 && getColor() == -1){
              if(board[7-newRank][getFile()] == null){
                return true;
              }
            }
          //Unless capturing, in which case it captures forwards and diagonally.
          }else if(newRank-getRank() == 1 && Math.abs(newFile-getFile()) == 1 && board[7-newRank][newFile] != null && getColor() == 1){
            if(board[7-newRank][newFile].getColor() == -1){
              return true;
            }
          }else if(newRank-getRank() == -1 && Math.abs(newFile-getFile()) == 1 && board[7-newRank][newFile] != null && getColor() == -1){
            if(board[7-newRank][newFile].getColor() == 1){
              return true;
            }
          }
          return false;
    }
}

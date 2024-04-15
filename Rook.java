

public class Rook extends Piece {

    public Rook(int color, int rank, int file) {
        super(4, color, rank, file);
    }
    public boolean valid(Piece[][] board, int newRank,int newFile){
        //The rook moves any amount in one direction, and none in the other.
      int a = 0;
      int asign = 0;
      if((Math.abs(getRank() - newRank) > 0 && getFile() - newFile == 0)){
        a = newRank - getRank();
        if(a < 0){
          asign = -1;
        }else{
          asign = 1;
        }
        a = Math.abs(a);
        for(int i = 1; i < a; i++){
          if(board[7-(getRank() + (i*asign))][getFile()] != null){
            return false;
          }
        }
        if(board[7-newRank][getFile()] != null){
          if(board[7-newRank][getFile()].getColor() != getColor()){
            return true;
          }
        }else{
          return true;
        }
        
      }else if((Math.abs(getFile() - newFile) > 0 && getRank() - newRank == 0)){
        a = newFile - getFile();
        if(a < 0){
          asign = -1;
        }else{
          asign = 1;
        }
        a = Math.abs(a);
        for(int i = 1; i < a; i++){
          if(board[7-getRank()][getFile() + (i*asign)] != null){
            return false;
          }
        }
        if(board[7-getRank()][newFile] != null){
          if(board[7-getRank()][newFile].getColor() != getColor()){
            return true;
          }
        }else{
          return true;
        }
      
      }    
      return false;
    }
}

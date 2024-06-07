

public class Pawn extends Piece{
  private double[][] WvalueChart = new double[][]{
    { 80.0, 80.0, 80.0, 80.0, 80.0,90.0, 80.0, 80.0},
    { 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0},
    { 1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0},
    { 0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5},
    { 0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0},
    { 0.5,-0.5,-1.0, 0.0, 0.0,-1.0,-0.5, 0.5},
    { 0.5, 1.0, 1.0,-2.0,-2.0, 1.0, 1.0, 0.5},
    { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
  };
  private double[][] BvalueChart = new double[][]{
    { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
    { 0.5, 1.0, 1.0,-2.0,-2.0, 1.0, 1.0, 0.5},
    { 0.5,-0.5,-1.0, 0.0, 0.0,-1.0,-0.5, 0.5},
    { 0.0, 0.0, 0.0, 2.0, 2.0, 0.0, 0.0, 0.0},
    { 0.5, 0.5, 1.0, 2.5, 2.5, 1.0, 0.5, 0.5},
    { 1.0, 1.0, 2.0, 3.0, 3.0, 2.0, 1.0, 1.0},
    { 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0, 5.0},
    { 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0, 80.0},
  };
    public Pawn(int color, int rank, int file) {
        super(1, color, rank, file);
        if(color == 1){
          this.setValue(10 + WvalueChart[7-rank][file]);
        }else if(color == -1){
          this.setValue(-10 - BvalueChart[7-rank][file]);
        }else{
          System.out.println("No color.");
        }
    }
    public void move(int newRank, int newFile){
      super.move(newRank, newFile);
      if(getColor() == 1){
        this.setValue(10 + WvalueChart[7-newRank][newFile]);
      }else if(getColor() == -1){
        this.setValue(-10 -BvalueChart[7-newRank][newFile]);
      }else{
        System.out.println("No color.");
      }
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



public class Knight extends Piece {
  private double[][] WvalueChart = new double[][]{
    {-5.0,-4.0,-3.0,-3.0,-3.0,-3.0,-4.0,-5.0},
    {-4.0,-2.0, 0.0, 0.0, 0.0, 0.0,-2.0,-4.0},
    {-3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0,-3.0},
    {-3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5,-3.0},
    {-3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0,-3.0},
    {-3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5,-3.0},
    {-4.0,-2.0, 0.0, 0.5, 0.5, 0.0,-2.0,-4.0},
    {-5.0,-4.0,-3.0,-3.0,-3.0,-3.0,-4.0,-5.0},
  };
  private double[][] BvalueChart = new double[][]{
    {-5.0,-4.0,-3.0,-3.0,-3.0,-3.0,-4.0,-5.0},
    {-4.0,-2.0, 0.0, 0.5, 0.5, 0.0,-2.0,-4.0},
    {-3.0, 0.5, 1.0, 1.5, 1.5, 1.0, 0.5,-3.0},
    {-3.0, 0.0, 1.5, 2.0, 2.0, 1.5, 0.0,-3.0},
    {-3.0, 0.5, 1.5, 2.0, 2.0, 1.5, 0.5,-3.0},
    {-3.0, 0.0, 1.0, 1.5, 1.5, 1.0, 0.0,-3.0},
    {-4.0,-2.0, 0.0, 0.0, 0.0, 0.0,-2.0,-4.0},
    {-5.0,-4.0,-3.0,-3.0,-3.0,-3.0,-4.0,-5.0},
  };
    public Knight(int color, int rank, int file) {
        super(3, color, rank, file);
        if(color == 1){
          this.setValue(30 + WvalueChart[7-rank][file]);
        }else if(color == -1){
          this.setValue(-30 - BvalueChart[7-rank][file]);
        }else{
          System.out.println("No color.");
        }
    }
    public void move(int newRank, int newFile){
      super.move(newRank, newFile);
      if(getColor() == 1){
        this.setValue(30 + WvalueChart[7-newRank][newFile]);
      }else if(getColor() == -1){
        this.setValue(-30 -BvalueChart[7-newRank][newFile]);
      }else{
        System.out.println("No color.");
      }
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

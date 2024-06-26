

public class Bishop extends Piece {
  private double[][] WvalueChart = new double[][]{
    {-2.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-2.0},
    {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0,-1.0},
    {-1.0, 0.5, 0.5, 1.0, 1.0, 0.5, 0.5,-1.0},
    {-1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,-1.0},
    {-1.0, 1.0, 1.0, 1.0, 1.5, 1.0, 1.0,-1.0},
    {-1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5,-1.0},
    {-2.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-2.0},
  };
  private double[][] BvalueChart = new double[][]{
    {-2.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-2.0},
    {-1.0, 0.5, 0.0, 0.0, 0.0, 0.0, 0.5,-1.0},
    {-1.0, 1.0, 1.0, 1.0, 1.5, 1.0, 1.0,-1.0},
    {-1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 1.0, 1.0, 0.5, 0.0,-1.0},
    {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-1.0},
    {-2.0,-1.0,-1.0,-1.0,-1.0,-1.0,-1.0,-2.0},
  };
    public Bishop(int color, int rank, int file) {
        super(2, color, rank, file);
        if(color == 1){
          this.setValue(30 + WvalueChart[7-rank][file]);
        }else if(color == -1){
          this.setValue(-30 -BvalueChart[7-rank][file]);
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
        //Bishops move any direction in a diagonal.
      //Therefore, the absolute value of the differences in newRank and newFile must be equal.
      if(Math.abs(newRank-getRank()) == Math.abs(newFile-getFile())){
        //Checking for pieces blocking path (does not include the landing square as it captures by moving into a piece)
        int a = newRank - getRank();
        int b = newFile - getFile();
        int asign = 0;
        int bsign = 0;
        if(a < 0){
          asign = -1;
        }else{
          asign = 1;
        }
        if(b < 0){
          bsign = -1;
        }else{
          bsign = 1;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        //Checks for pieces in the path
        for(int i = 1; i < a; i++){
         // System.out.println(((rank + (i*asign))) +" "+ (file + (i*bsign)));
          if(board[7- (getRank() + (i*asign))][getFile() + (i*bsign)] != null){
            return false;
          }
        }
        //Checks for pieces on the end of the path (allows if capturing)
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

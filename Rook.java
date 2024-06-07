public class Rook extends Piece {
  private boolean hasMoved = false;
  private double[][] WvalueChart = new double[][]{
    { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
    { 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    { 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0},
  };
  private double[][] BvalueChart = new double[][]{
    { 0.0, 0.0, 0.0, 0.5, 0.5, 0.0, 0.0, 0.0},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    {-0.5, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-0.5},
    { 0.5, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5},
    { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0},
  };
    public Rook(int color, int rank, int file) {
        super(4, color, rank, file);
        if(color == 1){
          this.setValue(40 + WvalueChart[rank][file]);
        }else if(color == -1){
          this.setValue(-40 - BvalueChart[7-rank][file]);
        }else{
          System.out.println("No color.");
        }
    }
    public boolean getHasMoved(){
      return hasMoved;
    }
    public void move(int newRank, int newFile){
      super.move(newRank, newFile);
      if(getColor() == 1){
        this.setValue(40 + WvalueChart[7-newRank][newFile]);
      }else if(getColor() == -1){
        this.setValue(-40 -BvalueChart[7-newRank][newFile]);
      }else{
        System.out.println("No color.");
      }
      hasMoved = true;
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

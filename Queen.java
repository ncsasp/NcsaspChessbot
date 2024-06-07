

public class Queen extends Piece {
  private double[][] WvalueChart = new double[][]{
    {-2.0,-1.0,-1.0,-0.5,-0.5,-1.0,-1.0,-2.0},
    {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0,-1.0},
    {-0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0,-0.5},
    { 0.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0,-0.5},
    {-1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0,-1.0},
    {-2.0,-1.0,-1.0,-0.5,-0.5,-1.0,-1.0,-2.0},
  };
  private double[][] BvalueChart = new double[][]{
    {-2.0,-1.0,-1.0,-0.5,-0.5,-1.0,-1.0,-2.0},
    {-1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0,-1.0},
    {-0.5, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0,-0.5},
    { 0.0, 0.0, 0.5, 0.5, 0.5, 0.5, 0.0,-0.5},
    {-1.0, 0.5, 0.5, 0.5, 0.5, 0.5, 0.0,-1.0},
    {-1.0, 0.0, 0.5, 0.0, 0.0, 0.0, 0.0,-1.0},
    {-2.0,-1.0,-1.0,-0.5,-0.5,-1.0,-1.0,-2.0},
  };
    public Queen(int color, int rank, int file) {
        super(5, color, rank, file);
        if(color == 1){
          this.setValue(90 + WvalueChart[7-rank][file]);
        }else if(color == -1){
          this.setValue(-90 - BvalueChart[7-rank][file]);
        }else{
          System.out.println("No color.");
        }
    }
    public void move(int newRank, int newFile){
      super.move(newRank, newFile);
      if(getColor() == 1){
        this.setValue(90 + WvalueChart[7-newRank][newFile]);
      }else if(getColor() == -1){
        this.setValue(-90 -BvalueChart[7-newRank][newFile]);
      }else{
        System.out.println("No color.");
      }
    }
    public boolean valid(Piece[][] board, int newRank,int newFile){
        //So the queen moves like a combination of a bishop and a rook.
      //I'm going to start by copy/pasting the rook and bishop code into this and see if that makes it work properly.
      //This doesn't work. Time to troubleshoot.
      //Apparently when I copy/pasted I missed a bracket and spent some time trying to put it in the right spot to make it work.
      //I got it. We're good. I think it works. I'll eventually find out that some wacky thing happened and it'll make me sad.
      //whatever. I'll figure it out.
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
        if(Math.abs(newRank-getRank()) == Math.abs(newFile-getFile())){
        //Checking for pieces blocking path (does not include the landing square as it captures by moving into a piece)
        a = newRank - getRank();
        int b = newFile - getFile();
        asign = 0;
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
        for(int i = 1; i < a; i++){
          // System.out.println((7-(rank + (i*asign))) +" "+ (file + (i*bsign)));
          if(board[7- (getRank() + (i*asign))][getFile() + (i*bsign)] != null){
            return false;
          }
        }
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

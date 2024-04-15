
abstract class Piece{
  /*
  Piece List:
  1: pawn
  2: bishop
  3: knight
  4: rook
  5: queen
  6: king

  Piece values:
  1:1
  2:3
  3:3
  4:5
  5:9
  6:99
  All of black's pieces will have a negative value.
  */
  private int type;
  //1 = white, -1 = black
  private int color;
  private int rank;
  private int file;
  private int value;
  public Piece(int type, int color, int rank, int file ){
    this.type = type;
    if(color == -1 || color == 1){
      this.color = color;
    }else{
      System.out.println("Invalid Color");
      color = 1;
    }
    this.rank = rank;
    this.file = file;
    
    //sets the value of the piece based on the type of piece
    if(type == 1){
      value = color;
    }else if(type == 2 || type == 3){
      value = 3 * color;
    }else if(type == 4){
      value = 5 * color;
    }else if(type == 5){
      value = 9 * color;
    }else if(type == 6){
      value = 99 * color;
    }else{
      System.out.println("Invalid Piece Type");
    }
  }
  
  public void move(int newRank, int newFile){
   rank = newRank;
   file = newFile;
  }
  public boolean getSquareColor(){ //returns true if the piece is on a white square, and false if it is on a black square
    if(file % 2 == rank % 2){
      return false;
    }else{
      return true;
    }
  }
  
  public void promotion(int newType){
    if(type == 1 && (rank == 0 || rank == 7) && newType < 6 && newType > 1){
      type = newType; 
    }
    if(type == 2 || type == 3){
      value = 3 * color;
    }else if(type == 4){
      value = 5 * color;
    }else if(type == 5){
      value = 9 * color;
    }
    
  }
  public int getType(){
    return type;
  }
  public int getColor(){
    return color;
  }
  public int getValue(){
    return value;
  }
  public int getRank(){
    return rank;
  }
  public int getFile(){
    return file;
  }
  abstract boolean valid(Piece[][] Board, int newRank, int newFile);
}
public class Move {
  private Board board;
  private int[] move;

  public Move(int a, int b, int c, int d, Board board, boolean autoPromote){
    Piece[][] tempBoard = new Piece[8][8];
    move = new int[]{a,b,c,d};
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(board.getBoard()[i][j] != null){
          switch(board.getBoard()[i][j].getType()){
            case 1: tempBoard[i][j] = new Pawn(board.getBoard()[i][j].getColor(),7-i,j);break;
            case 2: tempBoard[i][j] = new Bishop(board.getBoard()[i][j].getColor(),7-i,j);break;
            case 3: tempBoard[i][j] = new Knight(board.getBoard()[i][j].getColor(),7-i,j);break;
            case 4: tempBoard[i][j] = new Rook(board.getBoard()[i][j].getColor(),7-i,j);break;
            case 5: tempBoard[i][j] = new Queen(board.getBoard()[i][j].getColor(),7-i,j);break;
            case 6: tempBoard[i][j] = new King(board.getBoard()[i][j].getColor(),7-i,j,((King)board.getBoard()[i][j]).getHasMoved());break;
            default:tempBoard[i][j] = null;break;
          }
        }
      }
    }
    this.board = new Board(tempBoard,board.getwMoves(),board.getbMoves());
    this.board.movePiece(a,b,c,d,true,false);
  }
  public Move(int a, int b, int c, int d, Piece[][] board, int wMoves, int bMoves, boolean autoPromote){
    Piece[][] tempBoard = new Piece[8][8];
    move = new int[]{a,b,c,d};
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(board[i][j] != null){
          switch(board[i][j].getType()){
            case 1: tempBoard[i][j] = new Pawn(board[i][j].getColor(),7-i,j);break;
            case 2: tempBoard[i][j] = new Bishop(board[i][j].getColor(),7-i,j);break;
            case 3: tempBoard[i][j] = new Knight(board[i][j].getColor(),7-i,j);break;
            case 4: tempBoard[i][j] = new Rook(board[i][j].getColor(),7-i,j);break;
            case 5: tempBoard[i][j] = new Queen(board[i][j].getColor(),7-i,j);break;
            case 6: tempBoard[i][j] = new King(board[i][j].getColor(),7-i,j,((King) board[i][j]).getHasMoved());break;
            default:tempBoard[i][j] = null;break;
          }
        }
      }
    }
    this.board = new Board(tempBoard,wMoves,bMoves);
    if(this.board.checkValidCastling(a,b,c,d,true)){
      this.board.movePiece(a,b,c,d,true,true);
    }else{
      this.board.movePiece(a,b,c,d,true,false);
    }
  }
  public boolean equals(Move toCheck){
    for(int i = 0; i<8; i++){
      for(int j = 0; j<8; j++){
        if(!toCheck.getBoard()[i][j].equals(board.getBoard()[i][j])){
          return false;
        }
      }
    }
    return true;
  }
  public int[] getMove(){
    return move;
  }
  public Board getBoardObj(){
    return board;
  }
  public Piece[][] getBoard() {
    return board.getBoard();
  }
}

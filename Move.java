public class Move {
  private Board board;

  public Move(int a, int b, int c, int d, Board board,boolean autoPromote) {
    this.board = board;
    board.movePiece(a, b, c, d, true);
  }

  public Move(int a, int b, int c, int d, Piece[][] board, int wMoves, int bMoves, boolean autoPromote){
    Piece[][] tempBoard = new Piece[8][8];
    for(int i = 0; i < 8; i++){
      for(int j = 0; j < 8; j++){
        if(board[i][j] != null){
          switch(board[i][j].getType()){
            case 1: tempBoard[i][j] = new Pawn(board[i][j].getColor(),7-i,j);break;
            case 2: tempBoard[i][j] = new Bishop(board[i][j].getColor(),7-i,j);break;
            case 3: tempBoard[i][j] = new Knight(board[i][j].getColor(),7-i,j);break;
            case 4: tempBoard[i][j] = new Rook(board[i][j].getColor(),7-i,j);break;
            case 5: tempBoard[i][j] = new Queen(board[i][j].getColor(),7-i,j);break;
            case 6: tempBoard[i][j] = new King(board[i][j].getColor(),7-i,j);break;
            default:tempBoard[i][j] = null;break;
          }
        }
      }
    }
    this.board = new Board(tempBoard,wMoves,bMoves);
    this.board.movePiece(a,b,c,d,true);
  }

  public Piece[][] getBoard() {
    return board.getBoard();
  }
}

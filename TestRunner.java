import java.util.Scanner;
public class TestRunner{
    public static void main(String[] args) {
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        while(board.checkStatus()){
            board.movePiece(MoveInterpreter.interpret(input.nextLine(),board));
            board.printBoard(false);
        }
        input.close();
        
    }
}
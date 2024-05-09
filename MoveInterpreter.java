public class MoveInterpreter {
    //Please note, that this will not check if a move is valid.
    //It is purely designed to convert a string into a set of coordinates, or a set of coordinates into a String.
    public static int[] interpret(String input, Board board){
        int[] coordinates = new int[4];
        





        return coordinates;
    }
    
    public static String convertToString(int[] coordinates, Board board){
        if(coordinates.length != 4){
            System.out.println("Incorrect Coordinate Formatting.");
            return "You fucked up";
        }
        for(int x : coordinates){
            if(x<0 || x>7){
                System.out.println("Coordinates given are outside bounds of the board.");
                return "You fucked up";
            }
        }
        String output = "";
        if(board.getBoard()[7-coordinates[0]][coordinates[1]] != null){
            String[] aThroughH = {"a","b","c","d","e","f","g","h"};
            String fromCol = aThroughH[coordinates[1]];
            String toCol = aThroughH[coordinates[3]];
            int type = board.getBoard()[7-coordinates[0]][coordinates[1]].getType();
            switch(type){
                case 1: break;
                case 2: output+="B"; break;
                case 3: output+="N"; break;
                case 4: output+="R"; break;
                case 5: output+="Q"; break;
                case 6: output+="K"; break;
                default: System.out.println("Piece not valid type");return "You fucked up";
            }
            if(board.getBoard()[7-coordinates[2]][coordinates[3]] != null){
                if(type == 1){
                    output+=fromCol;
                }
                output+="x";
            }
            output+=(toCol + (1 + coordinates[2]));



        }
        return output;
    }
}

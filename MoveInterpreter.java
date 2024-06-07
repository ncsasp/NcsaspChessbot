public class MoveInterpreter {
    //Please note, that this will not check if a move is valid.
    //It is purely designed to convert a string into a set of coordinates, or a set of coordinates into a String.
    public static int[] interpret(String input, Board board){
        int[] coordinates = {-1,-1,-1,-1};
        Piece[][] tempBoard = board.getBoard();
        String fromA = "-1";
        String fromB = "-1";
        String piece = "";
        Boolean takes;
        String toA;
        String toB;
        String[] invalidChars = new String[] {"9","A","C","D","E","F","G","H","I","J","L","M","P","S","T","U","V","W","X","Y","Z","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","y","z",",",".","/","<",">","?","\"","'","{","}","[","]","|","\\","_","=","`","~","!","@","$","$","%","^","&","&","*","(",")"};
        for(String character : invalidChars){
            if(input.indexOf(character) != -1){
                System.out.println("Invalid Character Found: '" + character + "'.");
                return coordinates;
            }
        }
        if(input.equals("0-0")){
            System.out.println("You chose to Castle Short (Only works as white)");
            //TODO: Finish Castling Input Selection
            return new int[]{0,4,0,6};
        }
        String[] validChars = new String[]{"1","2","3","4","5","6","7","8"};
        int charCount = 0;
        for(String character : validChars){
            if(input.indexOf(character) == -1){
                charCount++;
            }
        }
        if(charCount == 8){
            System.out.println("You didn't include a number from 1-8. That's how i found that this ain't algebraic chess notation.");
            return coordinates;
        }
        if(charCount < 6){
            System.out.println("You put too many numbers from 1-8. That's how i found that this ain't algebraic chess notation.");
            return coordinates;
        }
        
        charCount = 0;
        validChars = new String[] {"a","b","c","d","e","f","g","h"};
        for(String character : validChars){
            if(input.indexOf(character) != -1){
                charCount ++;
            }
        }
        if(charCount > 2){
            System.out.println("Too many lowercase letters bucko.");
            return coordinates;
        }
        switch(input.length()){
            case 2:
                piece = "P";
                takes = false;
                toA = input.substring(0,1);
                toB = input.substring(1);
                break;
            case 3:
                if(input.indexOf("+") != -1 || input.indexOf("#") != -1){
                    
                    
                    piece = "P";
                    takes = false;
                    toA = input.substring(0,1);
                    toB = input.substring(1,2);
                }else{
                    
                    
                    piece = input.substring(0,1);
                    takes = false;
                    toA = input.substring(1,2);
                    toB = input.substring(2,3);
                }
                break;
            case 4:
                if(input.indexOf("+") != -1 || input.indexOf("#") != -1){
                    
                    
                    piece = input.substring(0,1);
                    takes = false;
                    toA = input.substring(1,2);
                    toB = input.substring(2,3);
                }else if(input.indexOf("x") != -1){
                    switch(input.substring(0,1)){
                        case "a","b","c","d","e","f","g","h": 
                            switch(input.substring(0,1)){
                                case "a":fromA = "1";break;
                                case "b":fromA = "2";break;
                                case "c":fromA = "3";break;
                                case "d":fromA = "4";break;
                                case "e":fromA = "5";break;
                                case "f":fromA = "6";break;
                                case "g":fromA = "7";break;
                                case "h":fromA = "8";break;
                            }
                            
                            piece = "P";
                            break;
                        case "K","Q","B","N","R":
                            
                            
                            piece = input.substring(0,1);
                            break;
                    }
                    takes = true;
                    toA = input.substring(2,3);
                    toB = input.substring(3,4);
                }else{
                    fromA = input.substring(0,1);
                    
                    piece = input.substring(1,2);
                    takes = false;
                    toA = input.substring(2,3);
                    toB = input.substring(3,4);
                }
                break;
            case 5:
                if(input.indexOf("+") != -1 || input.indexOf("#") != -1){
                    if(input.indexOf("x") != -1){
                        switch(input.substring(0,1)){
                            case "a","b","c","d","e","f","g","h": 
                                fromA = input.substring(0,1);
                                
                                piece = "P";
                                break;
                            case "K","Q","B","N","R":
                                
                                
                                piece = input.substring(0,1);
                                break;
                            default: System.out.println("Look. That's an error with the notation.");return coordinates;
                        }
                        takes = true;
                        toA = input.substring(2,3);
                        toB = input.substring(3,4);
                    }else{
                        fromA = input.substring(0,1);
                        
                        piece = input.substring(1,2);
                        takes = false;
                        toA = input.substring(2,3);
                        toB = input.substring(3,4);
                    }
                }else if(input.indexOf("x") != -1){
                    fromA = input.substring(0,1);
                    
                    piece = input.substring(1,2);
                    takes = true;
                    toA = input.substring(3,4);
                    toB = input.substring(4,5);
                }else{
                    fromA = input.substring(0,1);
                    fromB = input.substring(1,2);
                    piece = input.substring(2,3);
                    takes = false;
                    toA = input.substring(3,4);
                    toB = input.substring(4,5);
                }
                break;
            case 6:
                fromA = input.substring(0,1);
                if(input.indexOf("x") != -1 && (input.indexOf("+") != -1 || input.indexOf("#") != -1)){
                    fromA = input.substring(0,1);
                    
                    piece = input.substring(1,2);
                    takes = true;
                    toA = input.substring(3,4);
                    toB = input.substring(4,5);
                }else{
                    fromB = input.substring(1,2);
                    piece = input.substring(2,3);
                    if(input.indexOf("x") != -1){
                        takes = true;
                        toA = input.substring(4,5);
                        toB = input.substring(5,6);
                    }else{
                        takes = false;
                        toA = input.substring(3,4);
                        toB = input.substring(4,5);
                    }
                }
                break;
            case 7:
                fromA = input.substring(0,1);
                fromB = input.substring(1,2);
                piece = input.substring(2,3);
                takes = true;
                toA = input.substring(4,5);
                toB = input.substring(5,6);
                break;
            default: System.out.println("Invalid Notation"); return coordinates;
        }
        
        coordinates[2] = Integer.parseInt(toB) - 1;
        switch(toA){
            case "a": coordinates[3] = 0; break;
            case "b": coordinates[3] = 1; break;
            case "c": coordinates[3] = 2; break;
            case "d": coordinates[3] = 3; break;
            case "e": coordinates[3] = 4; break;
            case "f": coordinates[3] = 5; break;
            case "g": coordinates[3] = 6; break;
            case "h": coordinates[3] = 7; break;
            default: System.out.println("Wowza. That's not algebraic chess notation buster.");
        }
        int typeInt;
        switch(piece){
            case "P": typeInt = 1; break;
            case "B": typeInt = 2; break;
            case "N": typeInt = 3; break;
            case "R": typeInt = 4; break;
            case "Q": typeInt = 5; break;
            case "K": typeInt = 6; break;
            default: System.out.println("What kind of a piece do you think that is?"); return coordinates;
        }
        if(Integer.parseInt(fromA) != -1){
            switch(fromA){
                case "a": coordinates[1] = 0; break;
                case "b": coordinates[1] = 1; break;
                case "c": coordinates[1] = 2; break;
                case "d": coordinates[1] = 3; break;
                case "e": coordinates[1] = 4; break;
                case "f": coordinates[1] = 5; break;
                case "g": coordinates[1] = 6; break;
                case "h": coordinates[1] = 7; break;
                default: coordinates[0] = Integer.parseInt(fromA);
            }
        }
        if(Integer.parseInt(fromB) == -1){
            int turn = board.getTurn();
            switch(typeInt){
                case 1: 
                    if(takes){
                        int count = 0;
                        if(coordinates[2] > 0){
                            if(coordinates[3] > 0){
                                if(tempBoard[7-(coordinates[2]-1)][coordinates[3]-1] != null){
                                    if(tempBoard[7-(coordinates[2]-1)][coordinates[3]-1].getColor() == turn && tempBoard[7-(coordinates[2]-1)][coordinates[3]-1].getType() == 1){
                                        Piece tempPiece = tempBoard[7-(coordinates[2]-1)][coordinates[3]-1];
                                        if(fromA != "-1"){
                                            if((tempPiece.getFile() == coordinates[3] + 1 || tempPiece.getFile() == coordinates[3] - 1) && tempPiece.getRank() == coordinates[2] - 1){
                                                coordinates[0] = tempPiece.getRank();
                                                coordinates[1] = tempPiece.getFile();
                                            }else{
                                                count++;
                                            }
                                        }else{
                                            coordinates[0] = tempPiece.getRank();
                                            coordinates[1] = tempPiece.getFile();
                                        }
                                    }else{
                                        count++;
                                    }
                                }else{
                                    count++;
                                }
                            }else{
                                count++;
                            }
                            if(coordinates[3] < 7){
                                if(tempBoard[7-(coordinates[2]-1)][coordinates[3]+1] != null){
                                    if(tempBoard[7-(coordinates[2]-1)][coordinates[3]+1].getColor() == turn && tempBoard[7-(coordinates[2]-1)][coordinates[3]+1].getType() == 1){
                                        Piece tempPiece = tempBoard[7-(coordinates[2]-1)][coordinates[3]+1];
                                        if(fromA != "-1"){
                                            if((tempPiece.getFile() == coordinates[3] + 1 || tempPiece.getFile() == coordinates[3] - 1) && tempPiece.getRank() == coordinates[2] - 1){
                                                coordinates[0] = tempPiece.getRank();
                                                coordinates[1] = tempPiece.getFile();
                                            }else{
                                                count++;
                                            }
                                        }else{
                                            coordinates[0] = tempPiece.getRank();
                                            coordinates[1] = tempPiece.getFile();
                                        }
                                    }else{
                                        count++;
                                    }
                                }else{
                                    count++;
                                }
                            }
                        }else{
                            count+=2;
                        }
                        if(coordinates[2] < 7){
                            if(coordinates[3] > 0){
                                if(tempBoard[7-(coordinates[2]+1)][coordinates[3]-1] != null){
                                    if(tempBoard[7-(coordinates[2]+1)][coordinates[3]-1].getColor() == turn && tempBoard[7-(coordinates[2]+1)][coordinates[3]-1].getType() == 1){
                                        Piece tempPiece = tempBoard[7-(coordinates[2]+1)][coordinates[3]-1];
                                        if(fromA != "-1"){
                                            if((tempPiece.getFile() == coordinates[3] + 1 || tempPiece.getFile() == coordinates[3] - 1) && tempPiece.getRank() == coordinates[2] + 1){
                                                coordinates[0] = tempPiece.getRank();
                                                coordinates[1] = tempPiece.getFile();
                                            }else{
                                                count++;
                                            }
                                        }else{
                                            coordinates[0] = tempPiece.getRank();
                                            coordinates[1] = tempPiece.getFile();
                                        }
                                    }else{
                                        count++;
                                    }
                                }else{
                                    count++;
                                }
                            }else{
                                count++;
                            }
                            if(coordinates[3] < 7){
                                if(tempBoard[7-(coordinates[2]+1)][coordinates[3]+1] != null){
                                    if(tempBoard[7-(coordinates[2]+1)][coordinates[3]+1].getColor() == turn && tempBoard[7-(coordinates[2]+1)][coordinates[3]+1].getType() == 1){
                                        Piece tempPiece = tempBoard[7-(coordinates[2]+1)][coordinates[3]+1];
                                        if(fromA != "-1"){
                                            if((tempPiece.getFile() == coordinates[3] + 1 || tempPiece.getFile() == coordinates[3] - 1) && tempPiece.getRank() == coordinates[2] + 1){
                                                coordinates[0] = tempPiece.getRank();
                                                coordinates[1] = tempPiece.getFile();
                                            }else{
                                                count++;
                                            }
                                        }else{
                                            coordinates[0] = tempPiece.getRank();
                                            coordinates[1] = tempPiece.getFile();
                                        }
                                    }else{
                                        count++;
                                    }
                                }else{
                                    count++;
                                }
                            }
                        }else{
                            count+=2;
                        }
                        if(count == 4){
                            System.out.println("No Pawn Found (longer)");
                            return coordinates;
                        }
                    }else{
                        int count = 0;
                        if(coordinates[2] == 3 || coordinates[2] == 4){
                            if(tempBoard[7-(coordinates[2]-2)][coordinates[3]] != null){
                                Piece tempPiece = tempBoard[7-(coordinates[2]-2)][coordinates[3]];
                                if(tempPiece.getColor() == turn && tempPiece.getType() == 1){
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{count++;}
                            }else{count++;}
                            if(tempBoard[7-(coordinates[2]+2)][coordinates[3]] != null){
                                Piece tempPiece = tempBoard[7-(coordinates[2]+2)][coordinates[3]];
                                if(tempPiece.getColor() == turn && tempPiece.getType() == 1){
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{count++;}
                            }else{count++;}
                        }
                        if(coordinates[2] != 7){
                            if(tempBoard[7-(coordinates[2]-1)][coordinates[3]] != null){
                                if(tempBoard[7-(coordinates[2]-1)][coordinates[3]].getColor() == turn && tempBoard[7-(coordinates[2]-1)][coordinates[3]].getType() == 1){
                                    Piece tempPiece = tempBoard[7-(coordinates[2]-1)][coordinates[3]];
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile(); 
                                }else{
                                    count++;
                                }
                            }else{
                                count++;
                            }
                        }else{count++;}
                        if(coordinates[2] != 0){
                            if(tempBoard[7-(coordinates[2]+1)][coordinates[3]] != null){
                                if(tempBoard[7-(coordinates[2]+1)][coordinates[3]].getColor() == turn && tempBoard[7-(coordinates[2]+1)][coordinates[3]].getType() == 1){
                                    Piece tempPiece = tempBoard[7-(coordinates[2]+1)][coordinates[3]];
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{
                                    count++;
                                }
                            }else{
                                count++;
                            }
                        }else{
                            count++;
                        }
                        if(count == 4){
                            System.out.println("No Pawn Found (shorter)");
                            return coordinates;
                        }
                    }
                break;
                case 2:
                    for(int i = 1; coordinates[2] - i >= 0 && coordinates[3] - i >= 0; i++){
                        if(tempBoard[7-(coordinates[2]-i)][coordinates[3]-i] != null){
                            if(tempBoard[7-(coordinates[2]-i)][coordinates[3]-i].getType() == 2 && tempBoard[7-(coordinates[2]-i)][coordinates[3]-i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]-i)][coordinates[3]-i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; coordinates[2] - i >= 0 && i + coordinates[3] <= 7; i++){
                        if(tempBoard[7-(coordinates[2]-i)][coordinates[3]+i] != null){
                            if(tempBoard[7-(coordinates[2]-i)][coordinates[3]+i].getType() == 2 && tempBoard[7-(coordinates[2]-i)][coordinates[3]+i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]-i)][coordinates[3]+i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; i + coordinates[2] <= 7 && coordinates[3] - i >= 0; i++){
                        if(tempBoard[7-(coordinates[2]+i)][coordinates[3]-i] != null){
                            if(tempBoard[7-(coordinates[2]+i)][coordinates[3]-i].getType() == 2 && tempBoard[7-(coordinates[2]+i)][coordinates[3]-i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]+i)][coordinates[3]-i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; i + coordinates[2] <= 7 && i + coordinates[3] <= 7; i++){
                        if(tempBoard[7-(coordinates[2]+i)][coordinates[3]+i] != null){
                            if(tempBoard[7-(coordinates[2]+i)][coordinates[3]+i].getType() == 2 && tempBoard[7-(coordinates[2]+i)][coordinates[3]+i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]+i)][coordinates[3]+i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                break;
                case 3:
                    if(!(7-(coordinates[2]+2) < 0 || coordinates[3]+1 > 7)){
                    if(tempBoard[7-(coordinates[2]+2)][coordinates[3]+1] != null){
                        if(tempBoard[7-(coordinates[2]+2)][coordinates[3]+1].getType() == 3 && tempBoard[7-(coordinates[2]+2)][coordinates[3]+1].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]+2)][coordinates[3]+1];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]+2) < 0 || coordinates[3]-1 < 0)){
                    if(tempBoard[7-(coordinates[2]+2)][coordinates[3]-1] != null){
                        if(tempBoard[7-(coordinates[2]+2)][coordinates[3]-1].getType() == 3 && tempBoard[7-(coordinates[2]+2)][coordinates[3]-1].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]+2)][coordinates[3]-1];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]-2) > 7 || coordinates[3]+1 > 7)){
                    if(tempBoard[7-(coordinates[2]-2)][coordinates[3]+1] != null){
                        if(tempBoard[7-(coordinates[2]-2)][coordinates[3]+1].getType() == 3 && tempBoard[7-(coordinates[2]-2)][coordinates[3]+1].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]-2)][coordinates[3]+1];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]-2) > 7 || coordinates[3]-1 < 0)){
                    if(tempBoard[7-(coordinates[2]-2)][coordinates[3]-1] != null){
                        if(tempBoard[7-(coordinates[2]-2)][coordinates[3]-1].getType() == 3 && tempBoard[7-(coordinates[2]-2)][coordinates[3]-1].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]-2)][coordinates[3]-1];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]+1) < 0 || coordinates[3]+2 > 7)){
                    if(tempBoard[7-(coordinates[2]+1)][coordinates[3]+2] != null){
                        if(tempBoard[7-(coordinates[2]+1)][coordinates[3]+2].getType() == 3 && tempBoard[7-(coordinates[2]+1)][coordinates[3]+2].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]+1)][coordinates[3]+2];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]+1) < 0 || coordinates[3]-2 < 0)){
                    if(tempBoard[7-(coordinates[2]+1)][coordinates[3]-2] != null){
                        if(tempBoard[7-(coordinates[2]+1)][coordinates[3]-2].getType() == 3 && tempBoard[7-(coordinates[2]+1)][coordinates[3]-2].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]+1)][coordinates[3]-2];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]-1) > 7 || coordinates[3]+2 > 7)){
                    if(tempBoard[7-(coordinates[2]-1)][coordinates[3]+2] != null){
                        if(tempBoard[7-(coordinates[2]-1)][coordinates[3]+2].getType() == 3 && tempBoard[7-(coordinates[2]-1)][coordinates[3]+2].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]-1)][coordinates[3]+2];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                    if(!(7-(coordinates[2]-1) > 7 || coordinates[3]-2 < 0)){
                    if(tempBoard[7-(coordinates[2]-1)][coordinates[3]-2] != null){
                        if(tempBoard[7-(coordinates[2]-1)][coordinates[3]-2].getType() == 3 && tempBoard[7-(coordinates[2]-1)][coordinates[3]-2].getColor() == turn){
                            Piece temp = tempBoard[7-(coordinates[2]-1)][coordinates[3]-2];
                            if(fromA != "-1"){
                                if(!(temp.getRank() != coordinates[0] && temp.getFile() != coordinates[1])){
                                    coordinates[0] = temp.getRank();
                                    coordinates[1] = temp.getFile();
                                }
                            }else{
                                coordinates[0] = temp.getRank();
                                coordinates[1] = temp.getFile();
                            }
                        }
                    }
                    }
                break;
                case 4:
                for(int i = 1; coordinates[2] - i >= 0; i++){
                    if(tempBoard[7-(coordinates[2]-i)][coordinates[3]] != null){
                        if(tempBoard[7-(coordinates[2]-i)][coordinates[3]].getType() == 4 && tempBoard[7-(coordinates[2]-i)][coordinates[3]].getColor() == turn){
                            Piece tempPiece = tempBoard[7-(coordinates[2]-i)][coordinates[3]];
                            if(fromA != "-1"){
                                if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{
                                    i = 99;
                                }
                            }else{
                                coordinates[0] = tempPiece.getRank();
                                coordinates[1] = tempPiece.getFile();
                            }
                        }else{
                            i = 99;
                        }
                    }
                }
                for(int i = 1; i + coordinates[2] <= 7; i++){
                    if(tempBoard[7-(coordinates[2]+i)][coordinates[3]] != null){
                        if(tempBoard[7-(coordinates[2]+i)][coordinates[3]].getType() == 4 && tempBoard[7-(coordinates[2]+i)][coordinates[3]].getColor() == turn){
                            Piece tempPiece = tempBoard[7-(coordinates[2]+i)][coordinates[3]];
                            if(fromA != "-1"){
                                if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{
                                    i = 99;
                                }
                            }else{
                                coordinates[0] = tempPiece.getRank();
                                coordinates[1] = tempPiece.getFile();
                            }
                        }else{
                            i = 99;
                        }
                    }
                }
                for(int i = 1; coordinates[3] - i >= 0; i++){
                    if(tempBoard[7-coordinates[2]][coordinates[3]-i] != null){
                        if(tempBoard[7-coordinates[2]][coordinates[3]-i].getType() == 4 && tempBoard[7-coordinates[2]][coordinates[3]-i].getColor() == turn){
                            Piece tempPiece = tempBoard[7-coordinates[2]][coordinates[3]-i];
                            if(fromA != "-1"){
                                if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{
                                    i = 99;
                                }
                            }else{
                                coordinates[0] = tempPiece.getRank();
                                coordinates[1] = tempPiece.getFile();
                            }
                        }else{
                            i = 99;
                        }
                    }
                }
                for(int i = 1; i + coordinates[3] <= 7; i++){
                    if(tempBoard[7-coordinates[2]][coordinates[3]+i] != null){
                        if(tempBoard[7-coordinates[2]][coordinates[3]+i].getType() == 4 && tempBoard[7-coordinates[2]][coordinates[3]+i].getColor() == turn){
                            Piece tempPiece = tempBoard[7-coordinates[2]][coordinates[3]+i];
                            if(fromA != "-1"){
                                if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }else{
                                    i = 99;
                                }
                            }else{
                                coordinates[0] = tempPiece.getRank();
                                coordinates[1] = tempPiece.getFile();
                            }
                        }else{
                            i = 99;
                        }
                    }
                }
                break;
                case 5:
                    for(int i = 1; coordinates[2] - i >= 0 && coordinates[3] - i >= 0; i++){
                        if(tempBoard[7-(coordinates[2]-i)][coordinates[3]-i] != null){
                            if(tempBoard[7-(coordinates[2]-i)][coordinates[3]-i].getType() == 5 && tempBoard[7-(coordinates[2]-i)][coordinates[3]-i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]-i)][coordinates[3]-i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; coordinates[2] - i >= 0 && i + coordinates[3] <= 7; i++){
                        if(tempBoard[7-(coordinates[2]-i)][coordinates[3]+i] != null){
                            if(tempBoard[7-(coordinates[2]-i)][coordinates[3]+i].getType() == 5 && tempBoard[7-(coordinates[2]-i)][coordinates[3]+i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]-i)][coordinates[3]+i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; i + coordinates[2] <= 7 && coordinates[3] - i >= 0; i++){
                        if(tempBoard[7-(coordinates[2]+i)][coordinates[3]-i] != null){
                            if(tempBoard[7-(coordinates[2]+i)][coordinates[3]-i].getType() == 5 && tempBoard[7-(coordinates[2]+i)][coordinates[3]-i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]+i)][coordinates[3]-i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; i + coordinates[2] <= 7 && i + coordinates[3] <= 7; i++){
                        if(tempBoard[7-(coordinates[2]+i)][coordinates[3]+i] != null){
                            if(tempBoard[7-(coordinates[2]+i)][coordinates[3]+i].getType() == 5 && tempBoard[7-(coordinates[2]+i)][coordinates[3]+i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]+i)][coordinates[3]+i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; coordinates[2] - i >= 0; i++){
                        if(tempBoard[7-(coordinates[2]-i)][coordinates[3]] != null){
                            if(tempBoard[7-(coordinates[2]-i)][coordinates[3]].getType() == 5 && tempBoard[7-(coordinates[2]-i)][coordinates[3]].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]-i)][coordinates[3]];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; i + coordinates[2] <= 7; i++){
                        if(tempBoard[7-(coordinates[2]+i)][coordinates[3]] != null){
                            if(tempBoard[7-(coordinates[2]+i)][coordinates[3]].getType() == 5 && tempBoard[7-(coordinates[2]+i)][coordinates[3]].getColor() == turn){
                                Piece tempPiece = tempBoard[7-(coordinates[2]+i)][coordinates[3]];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; coordinates[3] - i >= 0; i++){
                        if(tempBoard[7-coordinates[2]][coordinates[3]-i] != null){
                            if(tempBoard[7-coordinates[2]][coordinates[3]-i].getType() == 5 && tempBoard[7-coordinates[2]][coordinates[3]-i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-coordinates[2]][coordinates[3]-i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                    for(int i = 1; i + coordinates[3] <= 7; i++){
                        if(tempBoard[7-coordinates[2]][coordinates[3]+i] != null){
                            if(tempBoard[7-coordinates[2]][coordinates[3]+i].getType() == 5 && tempBoard[7-coordinates[2]][coordinates[3]+i].getColor() == turn){
                                Piece tempPiece = tempBoard[7-coordinates[2]][coordinates[3]+i];
                                if(fromA != "-1"){
                                    if(!(tempPiece.getRank() != coordinates[0] && tempPiece.getFile() != coordinates[1])){
                                        coordinates[0] = tempPiece.getRank();
                                        coordinates[1] = tempPiece.getFile();
                                    }else{
                                        i = 99;
                                    }
                                }else{
                                    coordinates[0] = tempPiece.getRank();
                                    coordinates[1] = tempPiece.getFile();
                                }
                            }else{
                                i = 99;
                            }
                        }
                    }
                break;
                case 6:
                    if(7-(coordinates[2] -1) <= 7 && coordinates[3]-1 >= 0){
                        if(tempBoard[7-(coordinates[2]-1)][coordinates[3]-1]!= null){
                            Piece temp = tempBoard[7-(coordinates[2]-1)][coordinates[3]-1];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 1");
                        }
                    }
                    if(7-(coordinates[2] -1) <= 7){
                        if(tempBoard[7-(coordinates[2]-1)][coordinates[3]]!= null){
                            Piece temp = tempBoard[7-(coordinates[2]-1)][coordinates[3]];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 2");
                        }
                    }
                    if(7-(coordinates[2]-1) <= 7 && coordinates[3]+1 <= 7){
                        if(tempBoard[7-(coordinates[2]-1)][coordinates[3]+1]!= null){
                            Piece temp = tempBoard[7-(coordinates[2]-1)][coordinates[3]+1];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 3");
                        }
                    }
                    if(coordinates[3]+1 <= 7){
                        if(tempBoard[7-coordinates[2]][coordinates[3]+1]!= null){
                            Piece temp = tempBoard[7-coordinates[2]][coordinates[3]+1];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 4");
                        }
                    }
                    if(7-(coordinates[2] +1) >= 0 && coordinates[3]+1 <= 7){
                        if(tempBoard[7-(coordinates[2]+1)][coordinates[3]+1]!= null){
                            Piece temp = tempBoard[7-(coordinates[2]+1)][coordinates[3]+1];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 5");
                        }
                    }
                    if(7-(coordinates[2] +1) >= 0){
                        if(tempBoard[7-(coordinates[2]+1)][coordinates[3]]!= null){
                            Piece temp = tempBoard[7-(coordinates[2]+1)][coordinates[3]];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 6");
                        }
                    }
                    if(7-(coordinates[2] +1) >= 0 && coordinates[3]-1 >= 0){
                        if(tempBoard[7-(coordinates[2]+1)][coordinates[3]-1]!= null){
                            Piece temp = tempBoard[7-(coordinates[2]+1)][coordinates[3]-1];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 7");
                        }
                    }
                    if(coordinates[3]-1 >= 0){
                        if(tempBoard[7-coordinates[2]][coordinates[3]-1]!= null){
                            Piece temp = tempBoard[7-coordinates[2]][coordinates[3]-1];
                            if(temp.getType() == 6 && temp.getColor() == turn){
                                coordinates[1] = temp.getFile();
                                coordinates[0] = temp.getRank();
                            }
                        }else{
                            // System.out.println("Fail 8");
                        }
                    }
                    break;
                default:System.out.println("Something has happened");break;
            }
        }else{
            coordinates[0] = Integer.parseInt(fromB);
        }
        for(int i = 0; i < 4; i++){
            if(coordinates[i] == -1){
                System.out.println("Coordinate value " + i + " is -1");
            }
        }
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
            if(board.getInCheck()){
                output+="+";
            }


        }
        return output;
    }
}

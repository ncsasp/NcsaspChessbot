import java.util.Scanner;
// import java.util.ArrayList;
public class Runner {
  public static void main(String[] args) {
    /*
        _:_
       '-.-'
      __.'.__
     |_______|
      \=====/
       )___(
      /_____\
       |   |
       |   |
       |   |
       |   |
       |   |
      /_____\
     (=======)
     }======={
    (_________)
    */
    Scanner scanner = new Scanner(System.in);
    System.out.println("This is the Nearly Complete ChessBot program, officially versioned as v1.1.0. \n You will now be asked to change settings for this instance.");
    System.out.println("Display Unicode Characters? (y/n)");
    boolean scanning = true;
    String input;
    boolean unicode = false;
    while(scanning){
      input = scanner.nextLine();
      if(input.indexOf("Y") != -1 || input.indexOf("y") != -1){
        unicode = true;
        scanning = false;
      }else if(input.indexOf("N") != -1 || input.indexOf("n") != -1){
        scanning = false;
      }else{
        System.out.println("Unrecognized input: Please say 'Y' or 'N' to respond to the prompt.");
      }
    }
    System.out.println("Unicode set to: " + unicode + ".");
    System.out.println("Please select Chess Mode:");
    System.out.println("1) Random v Random");
    System.out.println("2) Player v Random");
    System.out.println("3) Player v Player");
    System.out.println("4) Player v Smart");
    System.out.println("5) Smart v Smart");
    scanning = true;
    int choice = 0;
    while(scanning){
      input = scanner.nextLine();
      for(Integer i = 1; i <= 5; i++){
        if(input.indexOf((i.toString())) != -1){
          choice = i;
        }
      }
      if(choice != 0){
        scanning = false;
      }
    }
    switch(choice){
      case 1:ChessBot.randomGame(unicode,true);break;
      case 2:ChessBot.playerRandomGame(unicode);break;
      case 3:ChessBot.playerGame(unicode);break;
      case 4:
        System.out.println("How many moves ahead would you like the bot to look?\nRECOMMENDED VALUE: 2");
        scanning = true;
        ChessBot.playerSmartGame(unicode,scanner.nextInt());
        break;
      case 5:
      System.out.println("How many moves ahead would you like the bot to look?\nRECOMMENDED VALUE: 2");
      scanning = true;
      ChessBot.Smartgame(unicode,scanner.nextInt());
      break;
      default: System.out.println("Somehow you bypassed my security measures.");break;
    }
    scanner.close();
  }
}
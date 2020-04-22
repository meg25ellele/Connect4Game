import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {



    public HumanPlayer(String nick){
        this.nick=nick;
    }


    public int makeMove(char [][]board) {

        int column = 0;
        boolean ok = false;

        Scanner scanner = new Scanner(System.in);

        while (!ok) {
            String input = scanner.nextLine();

            if(Utils.tryParseInt(input)){

               column = Integer.parseInt(input);

                if(column > 0 && column <8) {
                    ok=true;
                }
                else {
                    System.out.println("Something went wrong. Please try again!");
                }
            } else {

                System.out.println("Something went wrong. Please try again!");
            }

        }

        return column-1;
    }

}


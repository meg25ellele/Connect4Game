import java.util.List;

public class Program {


    public static void main(String[] args){

        AbstractPlayer player1 = new Player("Jack", 'J');
        AbstractPlayer player2 = new PlayerAI(6,'X');

        //Player player2 = new Player("Anna", 'A');


        Connect4Game game = new Connect4Game(player1,player2);

        game.play();

    }






}

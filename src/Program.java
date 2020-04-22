public class Program {


    public static void main(String[] args){

        AbstractPlayer player2 = new AIPlayer(6);
        AbstractPlayer player1 = new HumanPlayer("Jack");


        //HumanPlayer player2 = new HumanPlayer("Anna", 'A');

        Connect4Game game = new Connect4Game(player2,player1);


        System.out.println(player1.getSign());
        System.out.println(player2.getSign());

        game.play();

    }






}

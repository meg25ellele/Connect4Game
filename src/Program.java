public class Program {


    public static void main(String[] args){


        //AbstractPlayer player1 = new HumanPlayer("Jack");


        HeuristicEvaluationFunction simpleEvaluation = new SimpleEvaluation();

        AbstractPlayer player1 = new AIMinMaxPlayer(6,simpleEvaluation);
        AbstractPlayer player2 = new AIMinMaxPlayer(3,simpleEvaluation);


        //HumanPlayer player2 = new HumanPlayer("Anna", 'A');

        Connect4Game game = new Connect4Game(player1,player2);


        game.play();

    }






}

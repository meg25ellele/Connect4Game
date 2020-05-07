public class Program {


    public static void main(String[] args){


        //AbstractPlayer player1 = new HumanPlayer("Jack");


        HeuristicEvaluationFunction simpleEvaluation = new SimpleEvaluation();

       // AbstractPlayer player2 = new AIMinMaxPlayer(3,simpleEvaluation,true);
        //HumanPlayer player2 = new HumanPlayer("Anna", 'A');



        AbstractPlayer player1 = new AIMinMaxPlayer(6,simpleEvaluation,true);
        AbstractPlayer player2 = new AIAlphaBetaPlayer(11,simpleEvaluation,true);



        Connect4Game game = new Connect4Game(player1,player2);
        game.play();

        System.out.println("player1" );
        System.out.println("Liczba ruchów: " + ((AIPlayer) player1).getNumberOfMoves());
        ((AIPlayer) player1).getAvgTime();

        System.out.println("player2");
        System.out.println("Liczba ruchów: " +((AIPlayer) player2).getNumberOfMoves());
        ((AIPlayer) player2).getAvgTime();


   /* for(int i=0;i<10;i++){


        AbstractPlayer player1 = new AIMinMaxPlayer(6,simpleEvaluation,true);
        AbstractPlayer player2 = new AIAlphaBetaPlayer(10,simpleEvaluation,true);



        Connect4Game game = new Connect4Game(player1,player2);
        game.play();

        System.out.println("player1" );
        System.out.println("Liczba ruchów: " + ((AIPlayer) player1).getNumberOfMoves());
        ((AIPlayer) player1).getAvgTime();

        System.out.println("player2");
        System.out.println("Liczba ruchów: " +((AIPlayer) player2).getNumberOfMoves());
        ((AIPlayer) player2).getAvgTime();

    }*/

    }

}

public class SimpleEvaluation implements HeuristicEvaluationFunction {

    public int countScore(char [][]board, char playerSign) {

        if(Connect4Game.findWinner(board) == playerSign) {
            return 1;
        }

        if(Connect4Game.findWinner(board)!=Connect4Game.EMPTYSIGN) {
            return -1;
        }

        else return 0;

    }
}

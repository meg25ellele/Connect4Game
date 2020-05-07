import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class AIAlphaBetaPlayer extends AIPlayer{


    public AIAlphaBetaPlayer(int depth, HeuristicEvaluationFunction evaluationFunction, boolean randomFirstMove) {
        super(depth, evaluationFunction, randomFirstMove);
    }

    @Override
    public int makeMove(char [][] board) {


        setOderPlayerSign();
        int move;

        if(isFirstMove() && randomFirstMove){
            move = makeRandomFirstMove();
        } else if(isFirstMove() && !randomFirstMove){
            move = FIRSTMOVE;
        }
        else {
            long startTime = System.nanoTime();
            int alpha = Integer.MIN_VALUE;
            int beta = Integer.MAX_VALUE;

            move =  AlphaBeta(depth,alpha,beta,board,this.getSign(),true,true);
            long stopTime = System.nanoTime();

            times.add(stopTime-startTime);
        }
        System.out.println(move+1);
        numberOfMoves++;


        return move;

    }

    private int AlphaBeta (int depth, int alpha, int beta, char [][] board,char playerSign,boolean maximizingPlayer,boolean init ){

        if(depth == 0) {
            return evaluationFunction.countScore(board,this.getSign());
        }

        List<Integer> moves = getMoves(board);

        if(moves.size() ==0 ) return evaluationFunction.countScore(board,this.getSign());

        //kiedy przewidujemy nasz ruch --> MAX
        if(maximizingPlayer) {

            int bestMove = FIRSTMOVE;
            int value = Integer.MIN_VALUE;

            for (Integer move : moves) {

                char[][] newBoard = simulateMove(playerSign, move, board);

                int result = AlphaBeta(depth - 1, alpha, beta ,newBoard, simulateSwitchPlayer(playerSign), false,false);
                if(result>value){
                    value = result;
                    bestMove=move;
                }

                if(value>alpha) {
                    alpha=value;
                }

                if (alpha >= beta) {
                    break;

                }
            }
            //kiedy trzeba zwrócić najlepszy ruch gracza
            if(init){
                return bestMove;
            } else {
                return value;
            }

        }
        //kiedy przewidujemy ruch drugiego gracza --> MIN
        else {
            int value = Integer.MAX_VALUE;

            for (Integer move : moves) {

                char[][] newBoard = simulateMove(playerSign, move, board);

                int result = AlphaBeta(depth - 1, alpha, beta ,newBoard, simulateSwitchPlayer(playerSign), true,false);
                if(result<value){
                    value=result;
                }

                if(value<beta){
                    beta = value;
                }

                if (alpha >= beta) {
                    break;
                }
            }
            return value;

        }

    }
}

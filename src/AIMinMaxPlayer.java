import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

public class AIMinMaxPlayer extends AIPlayer {


    public AIMinMaxPlayer(int depth, HeuristicEvaluationFunction evaluationFunction, boolean randomFirstMove) {
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
            move =  MinMax(depth,board,this.getSign(),true,true);
            long stopTime = System.nanoTime();

            times.add(stopTime-startTime);
        }
        System.out.println(move+1);
        numberOfMoves++;


        return move;
    }


    private int MinMax (int depth, char [][] board,char playerSign,boolean maximizingPlayer,boolean init ){

        if(depth == 0) {
            return evaluationFunction.countScore(board,this.getSign());
        }

        List<Integer> moves = getMoves(board);

        if(moves.size() ==0 ) return evaluationFunction.countScore(board,this.getSign());

        Hashtable<Integer,Integer > scoreTable = new Hashtable<>();

        for(int i=0;i< moves.size();i++) {

            char [][] newBoard = simulateMove(playerSign,moves.get(i),board);

            int result = MinMax(depth-1, newBoard,simulateSwitchPlayer(playerSign),!maximizingPlayer,false);
            scoreTable.put(moves.get(i),result);
        }


        //kiedy rekurencja się skończy i musimy zwrócić najlepszy ruch
        if(init) {
            int maxValue = Collections.max(scoreTable.values());

            int move = FIRSTMOVE;

            if (!allEqual(scoreTable) || board[0][FIRSTMOVE]!=Connect4Game.EMPTYSIGN) {
                for (Integer key : scoreTable.keySet()) {
                    if (scoreTable.get(key).equals(maxValue)) {
                        move = key;
                        break;
                    }
                }
            }

            return move;

        }
        //kiedy przewidujemy ruch drugiego gracza --> MIN
        if(!maximizingPlayer) {
            return Collections.min(scoreTable.values());
        }
        //kiedy przewidujemy nasz ruch --> MAX
        else {
            return Collections.max(scoreTable.values());
        }
    }
}

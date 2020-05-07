import javafx.util.Duration;

import java.util.*;

public abstract class AIPlayer extends AbstractPlayer{

    protected static int FIRSTMOVE = 3;
    private static char ODER_PLAYER_SIGN = 'J';
    protected int depth;
    protected HeuristicEvaluationFunction evaluationFunction;
    protected boolean randomFirstMove;
    protected int numberOfMoves;
    protected List<Long> times;


    public AIPlayer(int depth, HeuristicEvaluationFunction evaluationFunction, boolean randomFirstMove) {
        this.depth = depth;
        this.evaluationFunction=evaluationFunction;
        this.randomFirstMove=randomFirstMove;
        numberOfMoves=0;
        nick= "AI";
        times = new ArrayList<>();

    }
    protected void setOderPlayerSign() {

        if(this.getSign() == Connect4Game.PLAYER1) {
            ODER_PLAYER_SIGN = Connect4Game.PLAYER2;
        } else
        {
            ODER_PLAYER_SIGN = Connect4Game.PLAYER1;
        }
    }

    //pobiera ruchy, które może wykonać gracz
    protected List<Integer> getMoves (char [][] board) {

        List<Integer> moves = new ArrayList<>();

        for (int i =0; i<board[0].length; i++){

            if(board[0][i] == Connect4Game.EMPTYSIGN) {
                moves.add(i);
            }
        }

        return moves;
    }

    public abstract int makeMove(char [][] board);

    protected boolean isFirstMove(){

        return this.getNumber() == 1 && numberOfMoves==0;
    }

    protected int makeRandomFirstMove(){

        Random random = new Random();
        return random.nextInt(6);

    }


    protected char simulateSwitchPlayer(char playerSign) {

        if (playerSign == this.getSign()) {
            return ODER_PLAYER_SIGN;
        } else {
            return this.getSign();
        }
    }

    protected char [][] simulateMove(char playerSign, int column,char [][] board) {

        char [][] newBoard = Connect4Game.copyBoard(board);

        for (int i=Connect4Game.ROW-1;i>0;i--){
            if(board[i][column] == Connect4Game.EMPTYSIGN) {
                newBoard[i][column] = playerSign;
                break;
            }
        }
        return newBoard;
    }

    protected boolean allEqual(Hashtable<Integer,Integer> scoreTable) {

        Set scoreSet = scoreTable.keySet();
        Iterator iter = scoreSet.iterator();

        Object first = iter.next();

        int firstValue = scoreTable.get(first);

        for (Integer value : scoreTable.values()) {
            if(value!=firstValue) {
                return false;
            }
        }

        return true;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public float getAvgTime(){

        float sum = 0f;

        for (int i=0;i<times.size();i++){
            //System.out.print(i+1 + ": ");
            float time = (float) times.get(i)/1000000000;
            //System.out.println(time + " sekund");
            sum+=time;
        }

        float avg = sum/times.size();
        System.out.println("Średnio: " + avg + " sekund") ;

        return avg;
    }
}

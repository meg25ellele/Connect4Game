import javafx.util.Pair;

import java.util.*;

public abstract class AIPlayer extends AbstractPlayer{

    protected static int FIRSTMOVE = 3;
    protected int depth;
    private static char ODER_PLAYER_SIGN = 'J';
    protected HeuristicEvaluationFunction evaluationFunction;

    public AIPlayer(int depth, HeuristicEvaluationFunction evaluationFunction) {
        this.depth = depth;
        this.evaluationFunction=evaluationFunction;
        nick= "AI";

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




}

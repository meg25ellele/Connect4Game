import javafx.util.Pair;

import java.util.*;

public class AIPlayer extends AbstractPlayer{

    private static int FIRSTMOVE = 3;
    private int depth;
    private static char ODER_PLAYER_SIGN = 'J';

    public AIPlayer(int depth) {
        this.depth = depth;
        nick= "AI";

    }
    private void setOderPlayerSign() {

        if(this.getSign() == Connect4Game.PLAYER1) {
            ODER_PLAYER_SIGN = Connect4Game.PLAYER2;
        } else
        {
            ODER_PLAYER_SIGN = Connect4Game.PLAYER1;
        }
    }

    //pobiera ruchy, które może wykonać gracz
    private List<Integer> getMoves (char [][] board) {

        List<Integer> moves = new ArrayList<>();

        for (int i =0; i<board[0].length; i++){

            if(board[0][i] == Connect4Game.EMPTYSIGN) {
                moves.add(i);
            }
        }

        return moves;
    }

    public int countScore(char [][]board) {

        if(Connect4Game.findWinner(board)!=Connect4Game.EMPTYSIGN) {
            return -1;
        }
        if(Connect4Game.findWinner(board) == this.getSign()) {
            return 1;
        }
        else return 0;

    }

    @Override
    public int makeMove(char [][] board) {

        setOderPlayerSign();

        int move =  MinMax(depth,board,this.getSign(),false,true,true);
       System.out.println(move+1);
        return move;
    }

    private int MinMax (int depth, char [][] board,char playerSign,boolean min, boolean max,boolean init ){

        if(depth ==0) {
            return countScore(board);
        }

        List<Integer> moves = getMoves(board);

       Hashtable<Integer,Integer > scoreTable = new Hashtable<>();

        for(int i=0;i< moves.size();i++) {

            char [][] newBoard = simulateMove(playerSign,moves.get(i),board);

            int result = MinMax(depth-1, newBoard,simulateSwitchPlayer(playerSign),!min,!max,false);
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
        if(min) {
            return Collections.min(scoreTable.values());
        }
        //kiedy przewidujemy nasz ruch --> MAX
        else {
            return Collections.max(scoreTable.values());
        }
    }

    private char simulateSwitchPlayer(char playerSign) {

        if (playerSign == this.getSign()) {
            return ODER_PLAYER_SIGN;
        } else {
            return this.getSign();
        }
    }

    private char [][] simulateMove(char playerSign, int column,char [][] board) {

        char [][] newBoard = Connect4Game.copyBoard(board);
      //  System.out.println(playerSign);

        for (int i=Connect4Game.ROW-1;i>0;i--){
            if(board[i][column] == Connect4Game.EMPTYSIGN) {
                newBoard[i][column] = playerSign;
                break;
            }
        }
        return newBoard;
    }

    private boolean allEqual(Hashtable<Integer,Integer> scoreTable) {

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

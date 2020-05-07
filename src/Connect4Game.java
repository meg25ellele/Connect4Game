public class Connect4Game {

    private  AbstractPlayer player1;
    private  AbstractPlayer player2;
    private char [][] board;

    public static int ROW = 6;
    public static int COL = 7;

    private static int MAXMOVES = 42;
    public static char EMPTYSIGN ='o';

    public static char PLAYER1 = 'X';
    public static char PLAYER2 = 'Y';



    public Connect4Game(AbstractPlayer player1, AbstractPlayer player2){
        this.player1=player1;
        this.player2=player2;

        player1.setNumber(1);
        player2.setNumber(2);

        player1.setSign(PLAYER1);
        player2.setSign(PLAYER2);

        board = new char[ROW][COL];
        fillBoard(EMPTYSIGN);
    }

    public void play() {

        boolean gameOver = false;

        AbstractPlayer currentPlayer = player1;

        int move = 0;

        while (!gameOver) {

            if(move!=MAXMOVES){

                showGameBoard(board);

                System.out.println("Player " + currentPlayer.getNumber() + " " + currentPlayer.getNick() + "(" + currentPlayer.getSign() +"), please enter the column where you'd like to drop your piece.");

                int column = getPlayerMovement(currentPlayer);

                board = enterMove(currentPlayer,column,board);
                move++;


                if(findWinner(board) == currentPlayer.getSign()) {

                    System.out.println("GAME OVER!");
                    showGameBoard(board);
                    System.out.println("Player " + currentPlayer.getNumber() + " " + currentPlayer.getNick() + "(" + currentPlayer.getSign() +")! You won!!!");
                    currentPlayer.isWinner = true;
                    gameOver=true;
                }

                currentPlayer = switchPlayer(currentPlayer);

            } else {
                gameOver = true;

                System.out.println("DRAW!");
            }

        }


    }

    private int getPlayerMovement(AbstractPlayer currentPlayer) {

        boolean ok = false;
        int column = -1;

        while (!ok) {
            column = currentPlayer.makeMove(board);
            ok = checkMove(column);

            if(!ok) {
                System.out.println("That column is already full. Please try again!");
            }
        }

        return column;
    }

    private boolean checkMove(int column) {

        return board[0][column] == EMPTYSIGN;

    }

    public static char [][] enterMove(AbstractPlayer currentPlayer, int column,char [][] board) {

        char [][] newBoard = copyBoard(board);

        for (int i=ROW-1;i>=0;i--){
            if(board[i][column] == EMPTYSIGN) {
                newBoard[i][column] = currentPlayer.getSign();
                break;
            }
        }
        return newBoard;
    }

    public static char findWinner( char [][] board) {

        char winner = EMPTYSIGN;

        char horizontally = checkHorizontally(board);
        char veritacally = checkVertically(board);
        char diagnonally1 = checkDiagonally1(board);
        char diagnially2 = checkDiagonally2(board);

        if(horizontally!=EMPTYSIGN) {
            return horizontally;
        }
        if(veritacally!=EMPTYSIGN) {
            return veritacally;
        }
        if(diagnonally1!=EMPTYSIGN) {
            return diagnonally1;
        }
        if(diagnially2!=EMPTYSIGN) {
            return diagnially2;
        }
        else {
            return winner;
        }


    }

    public static char checkHorizontally(char [][] board) {

        for(int i =0; i < board.length; i++){
            for(int j = 0; j < board[i].length-3; j++){
                if(board[i][j]!=EMPTYSIGN &&
                        board[i][j]== board[i][j+1]&&
                        board[i][j]== board[i][j+2]&&
                        board[i][j]== board[i][j+3])
                {
                   // System.out.println("horizontally");
                    return board[i][j];
                }
            }
        }
        return EMPTYSIGN;
    }

    public static char checkVertically(char [][] board) {

        for(int i =0; i < board[0].length; i++){
            for(int j = 0; j < board.length-3; j++){
                if(board[j][i]!=EMPTYSIGN &&
                        board[j][i]== board[j+1][i]&&
                            board[j][i]== board[j+2][i]&&
                                board[j][i]== board[j+3][i])
                {
                  //  System.out.println("vertically");
                    return board[j][i];
                }
            }
        }

        return EMPTYSIGN;
    }

    //check for win diagonally, from top left
    public static char checkDiagonally1(char [][] board) {

        for(int i =0; i < board.length-3; i++){
            for(int j =0; j < board[i].length-3; j++){
                if(board[i][j]!=EMPTYSIGN &&
                        board[i][j]== board[i+1][j+1]&&
                            board[i][j]== board[i+2][j+2]&&
                                board[i][j]==board[i+3][j+3])
                {
                    //System.out.println("diago1");
                    return board[i][j];
                }
            }

        }

        return EMPTYSIGN;
    }


    //check for win diagonally, from top right
    public static char checkDiagonally2(char [][] board) {

        for(int i =0; i < board.length-3; i++) {
            for (int j = 3; j < board[i].length; j++) {
                if (board[i][j] != EMPTYSIGN &&
                        board[i][j] == board[i + 1][j - 1] &&
                        board[i][j] == board[i + 2][j - 2] &&
                        board[i][j] == board[i + 3][j - 3])
                {
                   // System.out.println("diago2");
                    return board[i][j];
                }
            }
        }

        return EMPTYSIGN;
    }

    private AbstractPlayer switchPlayer(AbstractPlayer currentPlayer) {

        //System.out.println(currentPlayer.getNumber());
        if (currentPlayer.getNumber() == 1) {
            return player2;
        } else {
            return player1;
        }
    }


    public static void showGameBoard(char [][] board){

        System.out.println();
        System.out.println("   1   2   3   4   5   6   7");
        for (int i=0;i<ROW;i++){
            System.out.print(" |");
            for (int j=0;j<COL;j++){
                System.out.print(" " + board[i][j] + " |");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void fillBoard(char myChar) {

        for (int i= 0; i<ROW;i++) {
            for (int j=0;j<COL;j++) {
                board[i][j] = myChar;
            }
        }
    }

    public static char [][] copyBoard (char [][] board){

        char [][] copyBoard = new char[ROW][COL];

        for(int i=0;i<ROW;i++){
            for(int j=0;j<COL;j++) {
                copyBoard[i][j] = board[i][j];
            }
        }
        return copyBoard;
    }

}

public abstract class AbstractPlayer {

    protected String nick;
    protected char sign;
    protected static int id =1;
    protected int number;

    public AbstractPlayer(){
        number = id;
        id++;
    }

    public String getNick() {
        return nick;
    }

    public char getSign() {
        return sign;
    }

    public int getNumber() {
        return number;
    }

    public abstract int makeMove(char [][] board);



}

public abstract class AbstractPlayer {

    protected String nick;
    protected char sign;
    protected int number;
    protected boolean isWinner = false;


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

    public void setNumber(int number) {
        this.number = number;
    }

    public void setSign(char sign) {
        this.sign = sign;
    }
}

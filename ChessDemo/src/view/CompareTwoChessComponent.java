package view;

import model.ChessComponent;

public class CompareTwoChessComponent implements Comparable<CompareTwoChessComponent>{
    private ChessComponent chessComponent1;
    private ChessComponent chessComponent2;
    int i;

    public ChessComponent getChessComponent1() {
        return chessComponent1;
    }

    public ChessComponent getChessComponent2() {
        return chessComponent2;
    }

    public int getI() {
        return i;
    }

    public CompareTwoChessComponent(ChessComponent chessComponent1, ChessComponent chessComponent2, int i){
        this.chessComponent1=chessComponent1;
        this.chessComponent2=chessComponent2;
        this.i=i;
    }
    @Override
    public int compareTo(CompareTwoChessComponent o) {
        if(this.i>o.i){
            return -1;
        }else if(this.i<o.i){
            return 1;
        }
        return 0;
    }
}

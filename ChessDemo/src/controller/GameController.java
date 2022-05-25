package controller;

import model.ChessColor;
import model.EmptySlotComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
public class GameController {
    private Chessboard chessboard;
    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public List<String> loadGameFromFile(String path) {
        if (path == "" || path == null) return new ArrayList<>();
        try {
            List<String> chessData = Files.readAllLines(Paths.get(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public Chessboard getChessboard() {
        return chessboard;
    }
    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public void writeGameToFile(String path) {
        try {
            List<String> lines=convertToList();
            int num=chessboard.getProccess().size();
            ArrayList<List<String>> retStack=new ArrayList<>();
            for (int i=0;i<num;i++){
                for (int j=0;j<chessboard.getProccess().peek().size();j++){
                    lines.add(chessboard.getProccess().peek().get(j));
                }
                retStack.add(chessboard.getProccess().peek());
                chessboard.getProccess().pop();
                System.out.println(lines);
            }
            for (int i=retStack.size()-1;i>=0;i--){
                chessboard.getProccess().push(retStack.get(i));
            }
            Files.write(Path.of(path), lines, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public List<String> convertToList() {
        List<String> lines = new ArrayList<>();
        String line="";
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                line+=chessboard.getChessComponents()[i][j].toString();
            }
            lines.add(line);
            line="";
        }
        if(chessboard.getCurrentColor().equals(ChessColor.BLACK)){
            lines.add("B");
        }else if(chessboard.getCurrentColor().equals(ChessColor.WHITE)){
            lines.add("W");
        }
        return lines;
    }
    public Boolean checkInput(List<String> readlines){

        int count=0,lineNum,P=0,p=0,R=0,r=0,N=0,n=0,B=0,b=0,Q=0,q=0,K=0,k=0;
        boolean bo=true;
        if(readlines.size()>=8){
            lineNum=8;
        }else {
            System.out.println("Wrong chessboard");
            return false;
        }
        if(readlines.size()==8){
            System.out.println("Has no current player");
        }
        if(!(readlines.get(8).equals("W")||readlines.get(8).equals("B"))){
            System.out.println("Wrong current player");
        }else if(readlines.get(8).equals("W")) {
            chessboard.setCurrentColor(ChessColor.WHITE);
        }else if(readlines.get(8).equals("B")){
            chessboard.setCurrentColor(ChessColor.BLACK);
        }
        for(int j=0;j<lineNum;j++){
            for (int i=0;i<readlines.get(j).length();i++){
                if(!(readlines.get(j).charAt(i)=='R'||(readlines.get(j).charAt(i)=='_')||(readlines.get(j).charAt(i)=='r')||(readlines.get(j).charAt(i)=='N')||(readlines.get(j).charAt(i)=='n')||(readlines.get(j).charAt(i)=='B'||(readlines.get(j).charAt(i)=='b')||(readlines.get(j).charAt(i)=='Q')||(readlines.get(j).charAt(i)=='q')||(readlines.get(j).charAt(i)=='K')||(readlines.get(j).charAt(i)=='k')||(readlines.get(j).charAt(i)=='P')||(readlines.get(j).charAt(i)=='p')))){
                    System.out.println("Wrong chessComponent");
                    return false;
                }
                count++;
            }
            if(count!=8){
                System.out.println("Wrong chessboard");
                return false;
            }
            count=0;
        }//可能会不对这里，因为如果给了七行chessboard,但是第八行是一个错的字母，会out错棋子而不是错棋盘
        return true;
    }
    public void convertToChessboard(List<String> readlines) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (readlines.get(i).charAt(j) == '_') {
                } else if (readlines.get(i).charAt(j) == 'r') {
                    chessboard.initRookOnBoard(i, j, ChessColor.WHITE);
                } else if (readlines.get(i).charAt(j) == 'R') {
                    chessboard.initRookOnBoard(i, j, ChessColor.BLACK);
                }else if (readlines.get(i).charAt(j) == 'N') {
                    chessboard.initKnightOnBoard(i, j, ChessColor.BLACK);
                }else if (readlines.get(i).charAt(j) == 'n') {
                    chessboard.initKnightOnBoard(i, j, ChessColor.WHITE);
                }else if (readlines.get(i).charAt(j) == 'K') {
                    chessboard.initKingOnBoard(i, j, ChessColor.BLACK);
                }else if (readlines.get(i).charAt(j) == 'k') {
                    chessboard.initKingOnBoard(i, j, ChessColor.WHITE);
                }else if (readlines.get(i).charAt(j) == 'P') {
                    chessboard.initPawnOnBoard(i, j, ChessColor.BLACK);
                }else if (readlines.get(i).charAt(j) == 'p') {
                    chessboard.initPawnOnBoard(i, j, ChessColor.WHITE);
                }else if (readlines.get(i).charAt(j) == 'Q') {
                    chessboard.initQueenOnBoard(i, j, ChessColor.BLACK);
                }else if (readlines.get(i).charAt(j) == 'q') {
                    chessboard.initQueenOnBoard(i, j, ChessColor.WHITE);
                }else if (readlines.get(i).charAt(j) == 'B') {
                    chessboard.initBishopOnBoard(i, j, ChessColor.BLACK);
                }else if (readlines.get(i).charAt(j) == 'b') {
                    chessboard.initBishopOnBoard(i, j, ChessColor.WHITE);
                }
            }
        }
        if(readlines.get(8).equals("W")) {
            chessboard.setCurrentColor(ChessColor.WHITE);
        }else if(readlines.get(8).equals("B")){
            chessboard.setCurrentColor(ChessColor.BLACK);
        }
    }
    public void addStack( List<String> readlines){
        int j=0;
        while(readlines.size()-9-j*9>=9){
            List<String> stringList=new ArrayList<>();
            for (int i=readlines.size()-9-j*9;i<readlines.size();i++){
                stringList.add(readlines.get(i));
            }
            chessboard.getProccess().push(stringList);
            j++;
        }
    }

}

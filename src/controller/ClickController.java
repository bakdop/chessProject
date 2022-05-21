package controller;


import model.ChessColor;
import model.ChessComponent;
import view.Chessboard;
import view.ChessboardPoint;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;

public class ClickController  {
    private final Chessboard chessboard;
    private ChessComponent first;
    public ClickController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }
    public void onClick(ChessComponent chessComponent) {
        File file= null;
        file = new File("hrxz.com-eymvonut3ef60433 (1)_121819_track0_121819.wav");
        AudioInputStream ais= null;
        try {
            ais = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Clip clip= null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            clip.open(ais);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        clip.start();
        clip.start();

        if (first == null) {
            if (handleFirst(chessComponent)) {
                chessComponent.setSelected(true);
                for (int i=0;i<8;i++){
                    for (int j=0;j<8;j++){
                        if(chessComponent.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))&& ((!chessboard.getChessComponents()[i][j].getChessColor().equals(chessComponent.getChessColor()))||chessboard.getChessComponents()[i][j].getChessColor().equals(ChessColor.NONE))){
                            chessboard.getChessComponents()[i][j].setCanMovePosition(true);
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
                first = chessComponent;
                first.repaint();
            }
        } else {
            if (first == chessComponent) { // 再次点击取消选取
                chessComponent.setSelected(false);
                for (int i=0;i<8;i++){
                    for (int j=0;j<8;j++){
                        if(chessComponent.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))){
                            chessboard.getChessComponents()[i][j].setCanMovePosition(false);
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
                ChessComponent recordFirst = first;
                first = null;
                recordFirst.repaint();
            } else if (handleSecond(chessComponent)) {
                //repaint in swap chess method.
                chessboard.getProccess().push(chessboard.getGameController().convertToList());
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.swapColor();
                first.setSelected(false);
                for (int i=0;i<8;i++){
                    for (int j=0;j<8;j++){
                        if(chessboard.getChessComponents()[i][j].isCanMovePosition()==true){
                            chessboard.getChessComponents()[i][j].setCanMovePosition(false);
                            chessboard.getChessComponents()[i][j].repaint();
                        }
                    }
                }
                first = null;
            }
        }
    }

    /**
     * @param chessComponent 目标选取的棋子
     * @return 目标选取的棋子是否与棋盘记录的当前行棋方颜色相同
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first棋子目标移动到的棋子second
     * @return first棋子是否能够移动到second棋子位置
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }
}

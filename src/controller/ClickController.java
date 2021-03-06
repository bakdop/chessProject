package controller;


import model.*;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;
import view.CompareTwoChessComponent;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class ClickController  {
    private final Chessboard chessboard;
    private ChessComponent first;
    javax.swing.Timer timer1;
    private ChessGameFrame chessGameFrame;
    public ClickController(Chessboard chessboard,ChessGameFrame chessGameFrame) {
        this.chessboard = chessboard;
        this.chessGameFrame=chessGameFrame;
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
            if (first == chessComponent) { // ????????????????????????
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
                if(chessGameFrame.ai==1){
                    timer1 = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("??????1");
                            boolean bo=false;
                            ArrayList<ChessComponent> chessComponents=new ArrayList<>();
                            Random num=new Random();
                            ChessComponent chessComponent2=null;
                            ChessComponent chessComponent1=null;
                            while(!bo){
                                for(int i=0;i<8;i++){
                                    for(int j=0;j<8;j++){
                                        if(chessboard.getChessComponents()[i][j].getChessColor().equals(ChessColor.BLACK)){
                                            chessComponents.add(chessboard.getChessComponents()[i][j]);
                                        }
                                    }
                                }
                                int a=(int)(num.nextDouble()*(chessComponents.size()));
                                chessComponent1=chessComponents.get(a);
                                for (int i=8;i>0;i--){
                                    for (int j=8;j>0;j--){
                                        if(chessComponent1.canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))&&!chessComponent1.getChessColor().equals(chessboard.getChessComponents()[i][j].getChessColor())){
                                            chessComponent2= chessboard.getChessComponents()[i][j];
                                            System.out.println("???????????????");
                                            bo=true;
                                            break;
                                        }
                                    }
                                    if(bo){
                                        break;
                                    }
                                }
                            }
                            chessboard.setBeforeX(chessComponent1.getChessboardPoint().getX());
                            chessboard.setBeforeY(chessComponent1.getChessboardPoint().getY());
                            chessboard.setAfterX(chessComponent2.getChessboardPoint().getX());
                            chessboard.setAfterY(chessComponent2.getChessboardPoint().getY());
                            chessboard.getProccess().push(chessboard.getGameController().convertToList());
                            chessboard.swapChessComponents(chessComponent1, chessComponent2);
                            chessboard.getChessGameFrame().setStartTime(-1);
                            chessboard.getChessGameFrame().timer.restart();
                            chessboard.swapColor();
                            timer1.stop();

                        }
                    });
                    timer1.start();
                }
                else if(chessGameFrame.ai==2){
                    timer1 = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.out.println("??????1");
                            boolean bo=false;
                            ArrayList<ChessComponent> chessComponents=new ArrayList<>();
                            Random num=new Random();
                            ChessComponent chessComponent2=null;
                            ChessComponent chessComponent1=null;
                            ArrayList<CompareTwoChessComponent> compareTwoChessComponents=new ArrayList<>();
                            for(int i=0;i<8;i++){
                                    for(int j=0;j<8;j++){
                                        if(chessboard.getChessComponents()[i][j].getChessColor().equals(ChessColor.BLACK)){
                                            chessComponents.add(chessboard.getChessComponents()[i][j]);
                                        }
                                    }
                            }
                            while(!bo){
                                for(int k=0;k<chessComponents.size();k++){
                                    for (int i=7;i>=0;i--){
                                        for (int j=7;j>=0;j--){
                                            if(chessComponents.get(k).canMoveTo(chessboard.getChessComponents(),new ChessboardPoint(i,j))&&!chessComponents.get(k).getChessColor().equals(chessboard.getChessComponents()[i][j].getChessColor())){
                                                if(chessboard.getChessComponents()[i][j] instanceof EmptySlotComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],0));
                                                }else if(chessboard.getChessComponents()[i][j] instanceof PawnChessComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],1));
                                                }else if(chessboard.getChessComponents()[i][j] instanceof KnightChessComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],2));
                                                }else if(chessboard.getChessComponents()[i][j] instanceof RookChessComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],3));
                                                }else if(chessboard.getChessComponents()[i][j] instanceof BishopChessComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],3));
                                                }else if(chessboard.getChessComponents()[i][j] instanceof QueenChessComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],4));
                                                }else if(chessboard.getChessComponents()[i][j] instanceof KingChessComponent){
                                                    compareTwoChessComponents.add(new CompareTwoChessComponent(chessComponents.get(k),chessboard.getChessComponents()[i][j],5));
                                                }
                                                System.out.println("???????????????");
                                                bo=true;
                                            }
                                        }
                                    }
                                }
                            }
                            Collections.sort(compareTwoChessComponents);
                            int a=0;
                            if(compareTwoChessComponents.get(0).getI()==0){
                                Random random=new Random();
                                a=(int)(random.nextDouble()*compareTwoChessComponents.size());
                            }
                            chessComponent1=compareTwoChessComponents.get(a).getChessComponent1();
                            chessComponent2=compareTwoChessComponents.get(a).getChessComponent2();
                            chessboard.setBeforeX(chessComponent1.getChessboardPoint().getX());
                            chessboard.setBeforeY(chessComponent1.getChessboardPoint().getY());
                            chessboard.setAfterX(chessComponent2.getChessboardPoint().getX());
                            chessboard.setAfterY(chessComponent2.getChessboardPoint().getY());
                            chessboard.getProccess().push(chessboard.getGameController().convertToList());
                            chessboard.swapChessComponents(chessComponent1, chessComponent2);
                            chessboard.getChessGameFrame().setStartTime(-1);
                            chessboard.getChessGameFrame().timer.restart();
                            chessboard.swapColor();
                            timer1.stop();
                        }
                    });
                    timer1.start();
                }
                //////////////////////////////////////////////////////////////////////////////////////////////////
                chessboard.setBeforeX(first.getChessboardPoint().getX());
                chessboard.setBeforeY(first.getChessboardPoint().getY());
                chessboard.setAfterX(chessComponent.getChessboardPoint().getX());
                chessboard.setAfterY(chessComponent.getChessboardPoint().getY());
//                if(chessComponent instanceof BishopChessComponent){
//                    chessboard.beiChile=new BishopChessComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getChessColor(),chessComponent.getClickController(),chessComponent.size);
//                }else if(chessComponent instanceof EmptySlotComponent){
//                    chessboard.beiChile=new EmptySlotComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getClickController(),chessComponent.size);
//                }else if(chessComponent instanceof KingChessComponent){
//                    chessboard.beiChile=new KingChessComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getChessColor(),chessComponent.getClickController(),chessComponent.size);
//                }else if(chessComponent instanceof KnightChessComponent){
//                    chessboard.beiChile=new KnightChessComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getChessColor(),chessComponent.getClickController(),chessComponent.size);
//                }else if(chessComponent instanceof PawnChessComponent){
//                    chessboard.beiChile=new PawnChessComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getChessColor(),chessComponent.getClickController(),chessComponent.size);
//                }else if(chessComponent instanceof QueenChessComponent){
//                    chessboard.beiChile=new QueenChessComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getChessColor(),chessComponent.getClickController(),chessComponent.size);
//                }else if(chessComponent instanceof RookChessComponent){
//                    chessboard.beiChile=new RookChessComponent(chessComponent.chessboardPoint,chessComponent.location,chessComponent.getChessColor(),chessComponent.getClickController(),chessComponent.size);
//                }
                chessboard.getProccess().push(chessboard.getGameController().convertToList());
                chessboard.swapChessComponents(first, chessComponent);
                chessboard.getChessGameFrame().setStartTime(-1);
                chessboard.getChessGameFrame().timer.restart();
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
     * @param chessComponent ?????????????????????
     * @return ????????????????????????????????????????????????????????????????????????
     */

    private boolean handleFirst(ChessComponent chessComponent) {
        return chessComponent.getChessColor() == chessboard.getCurrentColor();
    }

    /**
     * @param chessComponent first??????????????????????????????second
     * @return first???????????????????????????second????????????
     */

    private boolean handleSecond(ChessComponent chessComponent) {
        return chessComponent.getChessColor() != chessboard.getCurrentColor() &&
                first.canMoveTo(chessboard.getChessComponents(), chessComponent.getChessboardPoint());
    }
}

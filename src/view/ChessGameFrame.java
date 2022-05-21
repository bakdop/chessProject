package view;

import controller.GameController;
import model.ChessColor;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private JLabel statusLabel;
    private JLabel turnLabel;
    private JFileChooser jFileChooser=new JFileChooser();
    private GameController gameController;
    public ChessGameFrame(int width, int height) throws IOException {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addChessboard();
        addlabel();
        addLoadButton();
        addResetButton();
        addStoreButton();
        addUndoButton();
        addLabe();

    }


    /**
     * 在游戏面板中添加棋盘
     */


    private void addChessboard()  {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this);
        chessboard.initRookOnBoard(0, 0, ChessColor.BLACK);
        chessboard.initRookOnBoard(0, 7, ChessColor.BLACK);
        chessboard.initRookOnBoard(7, 0, ChessColor.WHITE);
        chessboard.initRookOnBoard(7, 7, ChessColor.WHITE);
        chessboard.initBishopOnBoard(0, 2, ChessColor.BLACK);
        chessboard.initBishopOnBoard(0, 5, ChessColor.BLACK);
        chessboard.initBishopOnBoard(7, 2, ChessColor.WHITE);
        chessboard.initBishopOnBoard(7, 5, ChessColor.WHITE);
        chessboard.initKingOnBoard(0, 4, ChessColor.BLACK);
        chessboard.initKingOnBoard(7, 4, ChessColor.WHITE);
        chessboard.initKnightOnBoard(0, 1, ChessColor.BLACK);
        chessboard.initKnightOnBoard(0, 6, ChessColor.BLACK);
        chessboard.initKnightOnBoard(7, 1, ChessColor.WHITE);
        chessboard.initKnightOnBoard(7, 6, ChessColor.WHITE);
        chessboard.initPawnOnBoard(1, 0, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 1, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 2, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 3, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 4, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 5, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 6, ChessColor.BLACK);
        chessboard.initPawnOnBoard(1, 7, ChessColor.BLACK);
        chessboard.initPawnOnBoard(6, 0, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 1, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 2, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 3, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 4, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 5, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 6, ChessColor.WHITE);
        chessboard.initPawnOnBoard(6, 7, ChessColor.WHITE);
        chessboard.initQueenOnBoard(0, 3, ChessColor.BLACK);
        chessboard.initQueenOnBoard(7, 3, ChessColor.WHITE);
        gameController = new GameController(chessboard);
        chessboard.setGameController(gameController);
        chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
        add(chessboard);

    }

    /**
     * 在游戏面板中添加标签
     */
//    private void addLabel() {
//        JLabel statusLabel = new JLabel("Sample label");
//        statusLabel.setLocation(HEIGTH, HEIGTH / 10);
//        statusLabel.setSize(200, 60);
//        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(statusLabel);
//    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addlabel() {
        if(gameController.getChessboard().getCurrentColor().equals(ChessColor.BLACK)){
            turnLabel= new JLabel("Black's turn");
        }else {
            turnLabel= new JLabel("White's turn");
        }
        turnLabel.setLocation(HEIGTH, HEIGTH / 10 );
        turnLabel.setSize(200, 60);
        turnLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        turnLabel.setOpaque(true);
        turnLabel.setBackground(Color.white);
        add(turnLabel);
    }
    public JLabel getTurnLabel() {
        return turnLabel;
    }
    public void setTurnLabel(JLabel turnLabel) {
        this.turnLabel = turnLabel;
    }
    private void addStoreButton(){
        JButton button=new JButton("Store");
        button.setLocation(HEIGTH,HEIGTH/10+120);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Store");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            gameController.writeGameToFile(path);
        });
    }
    private void addLabe()  {
        ImageIcon background=new  ImageIcon("./images/E_aO-htfpvza4872797.jpg");
        statusLabel = new JLabel(background);//
        background.setImage(background.getImage().getScaledInstance(1000,760,Image.SCALE_DEFAULT));
        statusLabel.setLocation(0, 0);
        statusLabel.setSize(1000, 760);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }
    private void addLoadButton() {
        JButton button = new JButton("Load");
        button.setLocation(HEIGTH, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this,"Input Path here");
            List<String> load=new ArrayList<>();
            load=gameController.loadGameFromFile(path);
            if (path == "" || path == null){}else {
                if(gameController.checkInput(load)){
                    remove(gameController.getChessboard());
                    remove(statusLabel);
                    Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this);
                    gameController = new GameController(chessboard);
                    chessboard.setGameController(gameController);
                    setTurnLabel(chessboard.getCurrentColor());
                    chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
                    gameController.convertToChessboard(load);
                    add(gameController.getChessboard());
                    addLabe();
                    chessboard.repaint();
                }
            }

        });
    }
    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.setLocation(HEIGTH, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Reset");
            remove(gameController.getChessboard());
            remove(statusLabel);
            Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this);
            chessboard.initRookOnBoard(0, 0, ChessColor.BLACK);
            chessboard.initRookOnBoard(0, 7, ChessColor.BLACK);
            chessboard.initRookOnBoard(7, 0, ChessColor.WHITE);
            chessboard.initRookOnBoard(7, 7, ChessColor.WHITE);
            chessboard.initBishopOnBoard(0, 2, ChessColor.BLACK);
            chessboard.initBishopOnBoard(0, 5, ChessColor.BLACK);
            chessboard.initBishopOnBoard(7, 2, ChessColor.WHITE);
            chessboard.initBishopOnBoard(7, 5, ChessColor.WHITE);
            chessboard.initKingOnBoard(0, 4, ChessColor.BLACK);
            chessboard.initKingOnBoard(7, 4, ChessColor.WHITE);
            chessboard.initKnightOnBoard(0, 1, ChessColor.BLACK);
            chessboard.initKnightOnBoard(0, 6, ChessColor.BLACK);
            chessboard.initKnightOnBoard(7, 1, ChessColor.WHITE);
            chessboard.initKnightOnBoard(7, 6, ChessColor.WHITE);
            chessboard.initPawnOnBoard(1, 0, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 1, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 2, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 3, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 4, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 5, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 6, ChessColor.BLACK);
            chessboard.initPawnOnBoard(1, 7, ChessColor.BLACK);
            chessboard.initPawnOnBoard(6, 0, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 1, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 2, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 3, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 4, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 5, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 6, ChessColor.WHITE);
            chessboard.initPawnOnBoard(6, 7, ChessColor.WHITE);
            chessboard.initQueenOnBoard(0, 3, ChessColor.BLACK);
            chessboard.initQueenOnBoard(7, 3, ChessColor.WHITE);
            gameController = new GameController(chessboard);
            chessboard.setGameController(gameController);
            setTurnLabel(chessboard.getCurrentColor());
            chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
            add(chessboard);
            addLabe();
            chessboard.repaint();
        });
    }
    public void setTurnLabel(ChessColor chessColor){
        if(chessColor.equals(ChessColor.BLACK)){
            this.turnLabel.setText("Black's turn");
        }else if(chessColor.equals(ChessColor.WHITE)){
            this.turnLabel.setText("White's turn");
        }
    }
    private void addUndoButton(){
        JButton button=new JButton("Undo");
        button.setLocation(HEIGTH,HEIGTH/10+480);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.setVisible(true);
        button.addActionListener(e -> {
            System.out.println("Click Undo");
            if(!gameController.getChessboard().getProccess().empty()){
                remove(gameController.getChessboard());
                remove(statusLabel);
                Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this);
                gameController = new GameController(chessboard);
                chessboard.setGameController(gameController);
                setTurnLabel(chessboard.getCurrentColor());
                chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
                gameController.convertToChessboard(chessboard.getProccess().peek());
                add(gameController.getChessboard());
                addLabe();
                chessboard.repaint();
            }
        });
    }
//    public void addmusic() {
//        File file= null;
//        file = new File("元子弹吉他 - 流れ行く云（流行的云）（翻自 岸部眞明）_121009_track0_121009.wav");
//        AudioInputStream ais= null;
//        try {
//            ais = AudioSystem.getAudioInputStream(file);
//        } catch (UnsupportedAudioFileException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Clip clip= null;
//        try {
//            clip = AudioSystem.getClip();
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        }
//        try {
//            clip.open(ais);
//        } catch (LineUnavailableException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        clip.loop(Clip.LOOP_CONTINUOUSLY);
//        clip.start();
//    }
//    @Override
//    public Dimension getPreferredSize() {
//        Dimension space = getParent().getSize();
//        int length = (Math.min(space.width / WIDTH, space.height / HEIGTH));
//        return new Dimension(length, length);
//    }
}

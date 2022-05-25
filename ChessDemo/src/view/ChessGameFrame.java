package view;

import controller.GameController;
import model.ChessColor;
import model.ChessComponent;
import model.EmptySlotComponent;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.Timer;
/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    JLabel statusLabel;
    private JLabel turnLabel;
    JLabel Label= new JLabel();
    public long getStartTime() {
        return startTime;
    }
    public ChessGameFrame chessGameFrame=this;
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }
    public final int ai;
    private long startTime = -1;
    public Timer timer;
    private Timer timer1;
    private Timer timer2;
    private JFileChooser jFileChooser=new JFileChooser();
    GameController gameController;
    public ChessGameFrame(int width, int height,int ai) throws IOException {
        setTitle("2022 CS102A Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.ai=ai;
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
        addTimer();
        addPlayback();
        addLabe();
    }
    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard()  {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this,new Stack<>());
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

    private void addlabel() {
        if(gameController.getChessboard().getCurrentColor().equals(ChessColor.BLACK)){
            turnLabel= new JLabel("Black's turn");
        }else {
            turnLabel= new JLabel("White's turn");
        }
        turnLabel.setLocation(HEIGTH, HEIGTH / 10 );
        turnLabel.setSize(200, 30);
        turnLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        turnLabel.setOpaque(true);
        turnLabel.setBackground(Color.white);
        add(turnLabel);
    }

    private void addTimer() {
        Label.setLocation(HEIGTH, HEIGTH / 10+60 );
        Label.setSize(200, 30);
        Label.setFont(new Font("Rockwell", Font.BOLD, 15));
        Label.setOpaque(true);
        Label.setBackground(Color.white);
        long duration = 60000;
        timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (startTime < 0) {
                    startTime = System.currentTimeMillis();
                }
                long now = System.currentTimeMillis();
                long clockTime = now - startTime;
                if (clockTime >= duration) {
                    clockTime = duration;
                    startTime=-1;
                    timer.restart();
                    ChessColor chessColor;
                    if(gameController.getChessboard().getCurrentColor().equals(ChessColor.WHITE)){
                        chessColor=ChessColor.BLACK;
                    }else {
                        chessColor=ChessColor.WHITE;
                    }
                    setTurnLabel(chessColor);
                    gameController.getChessboard().setCurrentColor(chessColor);
                }
                SimpleDateFormat df = new SimpleDateFormat("mm:ss");
                Label.setText("remaining time "+df.format(duration - clockTime));
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!timer.isRunning()) {
                    startTime = -1;
                    timer.start();
                }
            }
        });
        timer.start();
        timer.setInitialDelay(0);
        add(Label);
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
    protected void addLabe()  {
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
        button.setLocation(HEIGTH, HEIGTH / 10 + 210);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                GUIOfIO guiOfIO=null;
                try {
                     guiOfIO=new GUIOfIO(WIDTH,HEIGTH,this);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                guiOfIO.setVisible(true);
            });

        });
    }
    private void addResetButton() {
        JButton button = new JButton("Reset");
        button.setLocation(HEIGTH, HEIGTH / 10 + 300);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Reset");
            remove(gameController.getChessboard());
            remove(statusLabel);
            Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this,new Stack<>());
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
            setStartTime(-1);
            timer.restart();
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
        button.setLocation(HEIGTH,HEIGTH/10+390);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.setVisible(true);
        button.addActionListener(e -> {
            System.out.println("Click Undo");
            if(!gameController.getChessboard().getProccess().empty()){
                Stack<List<String>> stack=new Stack<>();
                ArrayList<List<String>> arrayList=new ArrayList<>();
                int num=gameController.getChessboard().getProccess().size();
                for(int i=0;i<num;i++){
                    arrayList.add(gameController.getChessboard().getProccess().peek());
                    gameController.getChessboard().getProccess().pop();
                }
                for (int i=arrayList.size()-1;i>=0;i--){
                    stack.push(arrayList.get(i));
                }
                remove(gameController.getChessboard());
                remove(statusLabel);
                Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE,this,stack);
                gameController = new GameController(chessboard);
                chessboard.setGameController(gameController);
                gameController.convertToChessboard(chessboard.getProccess().peek());
                chessboard.getProccess().pop();
                setTurnLabel(chessboard.getCurrentColor());
                chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
                add(gameController.getChessboard());
                setStartTime(-1);
                timer.restart();
                addLabe();
                chessboard.repaint();
            }else {
                System.out.println("shayebushi");
            }
        });
    }
    public void shayebushi(){
        System.out.println("Click load");
        String path = JOptionPane.showInputDialog(this,"Input Path here");
        List<String> load=new ArrayList<>();
        load=gameController.loadGameFromFile(path);
        if (path == "" || path == null){}else {
            if(gameController.checkInput(load)){
                remove(gameController.getChessboard());
                remove(statusLabel);
                Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE,CHESSBOARD_SIZE,this,new Stack<>());
                gameController = new GameController(chessboard);
                chessboard.setGameController(gameController);
                setTurnLabel(chessboard.getCurrentColor());
                chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
                gameController.convertToChessboard(load);
                gameController.addStack(load);
                add(gameController.getChessboard());
                setStartTime(-1);
                timer.restart();
                addLabe();
                chessboard.repaint();
            }
        }
    }
    public void fileChooser(){
        JFileChooser a1=new JFileChooser(new File("."));
        a1.showOpenDialog(this);
        try {
            BufferedInputStream in=new BufferedInputStream(new FileInputStream(a1.getSelectedFile()));
            JLabel b1=new JLabel();
            byte[]b=new byte[in.available()];
            in.read(b,0,b.length);
            b1.setText(new String(b,0,b.length));
            String str=new String(b,0,b.length);
            System.out.println(str);

            List<String> stringList=Arrays.asList(str.split("\r\n"));
           // stringList.remove(stringList.get(0));
            for(int i=0;i<str.split("\n").length;i++){
                System.out.println(str.split("\n")[i]);
            }
            System.out.println(str.split("\n"));
            System.out.println("shayebushi");
            for(int i=0;i<stringList.size();i++){
                System.out.println(stringList.get(i));
            }

            if(gameController.checkInput(stringList)){
                remove(gameController.getChessboard());
                remove(statusLabel);
                Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE,CHESSBOARD_SIZE,this,new Stack<>());
                gameController = new GameController(chessboard);
                chessboard.setGameController(gameController);
                setTurnLabel(chessboard.getCurrentColor());
                chessboard.setLocation(HEIGTH / 10, HEIGTH / 10);
                gameController.convertToChessboard(stringList);
                gameController.addStack(stringList);
                add(gameController.getChessboard());
                setStartTime(-1);
                timer.restart();
                addLabe();
                chessboard.repaint();
            }
            in.close();
        } catch (IOException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }
    private void addPlayback(){
        JButton button=new JButton("Playback");
        button.setLocation(HEIGTH,HEIGTH/10+480);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {
            System.out.println("Click Playback");
            List<String> stringList=gameController.convertToList();

            timer1 = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("事件1");
                    gameController.getChessboard().getChessComponents()[gameController.getChessboard().afterX][gameController.getChessboard().afterY].swapLocation(gameController.getChessboard().getChessComponents()[gameController.getChessboard().beforeX][gameController.getChessboard().beforeY]);
                    //gameController.getChessboard().putChessOnBoard(gameController.getChessboard().beiChile);
                    gameController.getChessboard().repaint();
                    timer1.stop();

                }
            });
            timer1.start();
            timer2 = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("事件2");
//                    gameController.getChessboard().remove(gameController.getChessboard().getChessComponents()[gameController.getChessboard().afterX][gameController.getChessboard().afterY]);
//                    gameController.getChessboard().initiateAEmptyChess(gameController.getChessboard().afterX,gameController.getChessboard().afterY);
                    gameController.getChessboard().getChessComponents()[gameController.getChessboard().beforeX][gameController.getChessboard().beforeY].swapLocation(gameController.getChessboard().getChessComponents()[gameController.getChessboard().afterX][gameController.getChessboard().afterY]);
//                    gameController.getChessboard().repaint();
                    timer2.stop();
                }
            });
            timer2.start();
        });
    }
    public static void BlackWin(){
        JOptionPane.showMessageDialog(null, "Black Win!", "Game End",JOptionPane.WARNING_MESSAGE);
    }
    public static void WhiteWin(){
        JOptionPane.showMessageDialog(null, "White Win!", "Game End",JOptionPane.WARNING_MESSAGE);
    }
    public static char promotion(){
        Object[] possibilities = {"Queen","Bishop", "Knight","Rook"};
        String s = String.valueOf(JOptionPane.showInputDialog(null, "Choose a chess", "CF", JOptionPane.QUESTION_MESSAGE, null, possibilities, possibilities[0]));
        switch (s){
            case "Queen": return'Q';
            case "Bishop": return 'B';
            case "Rook": return 'R';
            case "Knight":return 'N';
        }
        return 'E';
    }
}

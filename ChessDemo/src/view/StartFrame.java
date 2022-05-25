package view;
import model.users;
import java.awt.Image;
import javax.sound.sampled.*;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class StartFrame extends JFrame{
    private final int WIDTH;
    private final int HEIGTH;
    public static users currentUser;
    public static ArrayList<users> accounts=new ArrayList<>();
    public StartFrame(int WIDTH,int HEIGTH) throws IOException {
        setTitle("Welcome to International Chess");
        this.HEIGTH=HEIGTH;
        this.WIDTH=WIDTH;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addPvP();
        addlogIn();
        addRegister();
        addRanking();
        addPvC();
        addLabel();
        addmusic();
    }
    private void addLabel()  {
        ImageIcon background=new  ImageIcon("./images/E_aO-htfpvza4872797.jpg");
        JLabel statusLabel = new JLabel(background);//
        background.setImage(background.getImage().getScaledInstance(1000,760,Image.SCALE_DEFAULT));
        statusLabel.setLocation(0, 0);
        statusLabel.setSize(1000, 760);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel);
    }
    private void addPvC() {
        JButton button = new JButton("PvC");
        button.addActionListener((e) -> {
            System.out.println("start PvC game");
            SwingUtilities.invokeLater(() -> {
                ChooseAILevel chooseAILevel=null;
                try {
                    chooseAILevel=new ChooseAILevel(500,760);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                chooseAILevel.setVisible(true);
            });
        });
        button.setLocation(400, HEIGTH / 10 );
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addPvP() {
        JButton button = new JButton("PvP");
        button.addActionListener((e) -> {
            System.out.println("start PvP game");
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = null;
                try {
                    mainFrame = new ChessGameFrame(1000, 760,0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainFrame.setVisible(true);
        });
        });
        button.setLocation(400, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    private void addlogIn() {
        JButton button = new JButton("Log In");
        button.addActionListener((e) -> {
            String path = JOptionPane.showInputDialog(this,"Input account number here");
            if(path.equals("")||path.equals(null)){
                return;
            }
            boolean bo=false;
            int num=0;
            for (int i=0;i<accounts.size();i++){
                if(accounts.get(i).getAccountNumber().equals(path)){
                    bo=true;
                    num=i;
                    break;
                }
            }
            if(bo){
                String Inputpassword = JOptionPane.showInputDialog(this,"Input password here");
                if(Inputpassword.equals("")||path.equals(null)){
                    JOptionPane.showMessageDialog(this, "fail to log in");
                    return;
                }
                if(accounts.get(num).getPassword().equals(Inputpassword)){
                    currentUser=accounts.get(num);
                    JOptionPane.showMessageDialog(this, "Welcome,"+currentUser.getName());

//                    JLabel label=new JLabel("Welcome,"+currentUser.getName());
//                    this.add(label,BorderLayout.NORTH);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                }else {
                    JOptionPane.showMessageDialog(this, "wrong password");
                }
            }else {
                JOptionPane.showMessageDialog(this, "no such account");
            }
             currentUser.setWinningTimes( Integer.parseInt( JOptionPane.showInputDialog(this,"Input account number here")));
        });

        button.setLocation(400, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
    public void addmusic() {
        File file= null;
        file = new File("元子弹吉他 - 流れ行く云（流行的云）（翻自 岸部眞明）_121009_track0_121009.wav");
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
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        clip.start();
    }
    private void addRegister() {
        JButton button = new JButton("Register");
        button.addActionListener((e) -> {
            boolean bo=true;
            String path="";
            while (bo==true){
                bo=false;
                path = JOptionPane.showInputDialog(this,"Input new account number here");
                if(path.equals("")||path.equals(null)){
                    JOptionPane.showMessageDialog(this, "fail to register");
                    return;
                }
                for (int i=0;i<accounts.size();i++){
                    if(accounts.get(i).getAccountNumber().equals(path)){
                        JOptionPane.showMessageDialog(this, "has been registered");
                        bo=true;
                        break;
                    }
                }
            }
            String password = JOptionPane.showInputDialog(this,"Input password here");
            if(password.equals("")||path.equals(null)){
                JOptionPane.showMessageDialog(this, "No password");
                return;
            }
            String name = JOptionPane.showInputDialog(this,"Input your name here");
            if(name.equals("")||path.equals(null)){
                JOptionPane.showMessageDialog(this, "No name");
                return;
            }
            users user=new users(path,password,name);
            JOptionPane.showMessageDialog(this, "Register successfully");
            accounts.add(user);
        });
        button.setLocation(400, HEIGTH / 10 + 360);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    public void addRanking(){
        JButton button = new JButton("Ranking");
        button.addActionListener((e) -> {
           showRanking();
        });
        button.setLocation(400, HEIGTH / 10 + 480);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        }


    public void showRanking(){
        JFrame myframe; // JFrame通常默认使用BorderLayout布局管理器的
        TextArea tf;
        JButton exitButton;
        int number = 1;
        Label label = new Label("ranking here");
        myframe = new JFrame("Ranking");
        tf = new TextArea();
        Collections.sort(accounts);
        tf.append("ranking\t\tname\t\twinning time\n");
        for (int i=0;i<accounts.size();i++){
            tf.append((i+1)+"\t\t"+accounts.get(i).toString()+"\n");
        }
        exitButton = new JButton("退出");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        myframe.add(label);
        myframe.add(tf, BorderLayout.CENTER);
        myframe.add(exitButton, BorderLayout.SOUTH);
        myframe.setSize(400, 300);
        myframe.setVisible(true);
    }
}

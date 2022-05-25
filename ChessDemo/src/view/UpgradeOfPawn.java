package view;

import javax.swing.*;
import java.awt.*;

public class UpgradeOfPawn extends JFrame {
    private  int WIDTH;
    private  int HEIGTH;
    public UpgradeOfPawn(int width,int height){
        setTitle("Upgrade");
        this.WIDTH = width;
        this.HEIGTH = height;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addBishop();
        addKinght();
        addQueen();
        addRook();
    }
    private void addQueen(){
        JButton button=new JButton("Queen");
        button.setLocation(HEIGTH,HEIGTH/10+60);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {

        });
    }
    private void addKinght(){
        JButton button=new JButton("Kinght");
        button.setLocation(HEIGTH,HEIGTH/10+180);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {

        });
    }
    private void addBishop(){
        JButton button=new JButton("Bishop");
        button.setLocation(HEIGTH,HEIGTH/10+300);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {

        });
    }
    private void addRook(){
        JButton button=new JButton("Rook");
        button.setLocation(HEIGTH,HEIGTH/10+420);
        button.setSize(200,60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
        button.addActionListener(e -> {

        });
    }
}

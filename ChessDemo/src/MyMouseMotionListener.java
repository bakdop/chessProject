import javax.swing.*;


import java.awt.*;
import java.awt.event.*;

import javax.swing.JFrame;
import javax.swing.JButton;

    public class MyMouseMotionListener implements MouseMotionListener {

        JFrame myframe; // JFrame通常默认使用BorderLayout布局管理器的
        TextArea tf;
        JButton exitButton;
        int number = 1;

        public MyMouseMotionListener() {
            Label label = new Label("click and drag the mouse");
            myframe = new JFrame("MyMouseMotionListener");
            tf = new TextArea();
            exitButton = new JButton("退出");

            tf.addMouseMotionListener(this);
            exitButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });

            myframe.add(label, BorderLayout.NORTH);
            myframe.add(tf, BorderLayout.CENTER);
            myframe.add(exitButton, BorderLayout.SOUTH);
            myframe.setSize(400, 300);
            myframe.setVisible(true);

        }

        public static void main(String[] args) {
            new MyMouseMotionListener();

        }

        @Override
        // 负责处理鼠标拖动事件
        public void mouseDragged(MouseEvent e) {
            //getX(),getY()：获取鼠标的坐标位置
            String s = number++ + "" + "the mouse is draggered:x=" + e.getX()
                    + "y=" + e.getY() + "\n";
            tf.append(s);
        }

        @Override
        // 负责处理鼠标移动事件
        public void mouseMoved(MouseEvent e) {
            String s = number++ + "" + "the mouse is moving:x=" + e.getX() + "y="
                    + e.getY() + "\n";
            tf.append(s);
        }
    }



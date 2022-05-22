package view;

import model.users;
import view.ChessGameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseAILevel extends JFrame {
    private final int WIDTH;
    private final int HEIGTH;
    public static ArrayList<users> accounts=new ArrayList<>();
    public ChooseAILevel(int WIDTH, int HEIGTH) throws IOException {
        setTitle("Choose AI level");
        this.HEIGTH=HEIGTH;
        this.WIDTH=WIDTH;
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setLayout(null);
        addlower();
        addhigher();
        addLabel();
    }

    private void addlower() {
        JButton button = new JButton("Low Level AI");
        button.addActionListener((e) -> {
            this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = null;
                try {
                    mainFrame = new ChessGameFrame(1000, 760,1);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainFrame.setVisible(true);
            });
        });
        button.setLocation(150, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
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
    private void addhigher() {
        this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
        JButton button = new JButton("High level AI");
        button.addActionListener((e) -> {
            this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
            SwingUtilities.invokeLater(() -> {
                ChessGameFrame mainFrame = null;
                try {
                    mainFrame = new ChessGameFrame(1000, 760,2);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                mainFrame.setVisible(true);
            });
        });
        button.setLocation(150, HEIGTH / 10 + 240);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }
}

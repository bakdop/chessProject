import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;
public class demo {
    public static void main(String[]args){
        Random num=new Random();
        for(int i=0;i<8;i++){
            int a=(int)(num.nextDouble()*8);
            System.out.println(a);
        }
    }
}




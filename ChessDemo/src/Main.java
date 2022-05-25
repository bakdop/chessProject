import view.ChessGameFrame;
import view.StartFrame;
import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {




            StartFrame startFrame= null;
            try {
                startFrame = new StartFrame(1000,760);
            } catch (IOException e) {
                e.printStackTrace();
            }
            startFrame.setVisible(true);
        });
//        SwingUtilities.invokeLater(() -> {
//            ChessGameFrame mainFrame = null;
//            try {
//                mainFrame = new ChessGameFrame(1000, 760);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            mainFrame.setVisible(true);
//        });
    }
}

package controller;

import model.ChessColor;
import model.EmptySlotComponent;
import view.ChessGameFrame;
import view.Chessboard;
import view.ChessboardPoint;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GameController {
    private Chessboard chessboard;

    public GameController(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public List<String> loadGameFromFile(String path) {
        if (path == "" || path == null || path.length()==0) return new ArrayList<>();
        try {
            List<String> chessData = Files.readAllLines(Paths.get(path));
            chessboard.loadGame(chessData);
            return chessData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void resetGame(){
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1000, 760);
            mainFrame.setVisible(true);
        });

    }



}

package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这个类表示国际象棋里面的车
 */
public class QueenChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image Queen_WHITE;
    private static Image Queen_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image QueenImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (Queen_WHITE == null) {
            Queen_WHITE = ImageIO.read(new File("./images/queen-white.png"));
        }

        if (Queen_BLACK == null) {
            Queen_BLACK = ImageIO.read(new File("./images/queen-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateQueenImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                QueenImage = Queen_WHITE;
            } else if (color == ChessColor.BLACK) {
                QueenImage = Queen_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QueenChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateQueenImage(color);
    }

    /**
     * 车棋子的移动规则
     *
     * @param chessComponents 棋盘
     * @param destination     目标位置，如(0, 0), (0, 7)等等
     * @return 车棋子移动的合法性
     */

    @Override
    public boolean canMoveTo(ChessComponent[][] chessComponents, ChessboardPoint destination) {
        ChessboardPoint source = getChessboardPoint();
        int targetX = destination.getX();
        int targetY = destination.getY();
        int[][] directions = new int[][]{{0,-1},{0,1},{-1,0},{1,0},{-1,-1},{-1,1},{1,-1},{1,1}};
        int bounds = 7;
        int x = source.getX();
        int y = source.getY();
        int i = 0;
       if (x == targetX){
//           if (y > targetY){
//               i = 0;
//           }else {
//               i =1;
//           }

           return true;
       }else if(y == targetY){
           if(x>targetX){
               i = 2;
           }else {
               i = 3;
           }
       }else if (x>targetX && y>targetY){
           i = 4;
       }else if (x>targetX && y<targetY){
           i = 5;
       }else if (x<targetX && y>targetY){
           i = 6;
       }else {
           i = 7;
       }
        System.out.println(i);
        for (int j = 1; j <=bounds; j++) {
            int curX = x + directions[i][0]*j;
            int curY = y + directions[i][1]*j;
            if (curX < 0 || curX >7 || curY < 0 || curY > 7) return false;
            if (!(chessComponents[curX][curY] instanceof EmptySlotComponent) && (curX != targetX || curY != targetY)) return false;
            if (curX == targetX && curY == targetY) return true;
            }
        return false;
    }

    /**
     * 注意这个方法，每当窗体受到了形状的变化，或者是通知要进行绘图的时候，就会调用这个方法进行画图。
     *
     * @param g 可以类比于画笔
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
//        g.drawImage(rookImage, 0, 0, getWidth() - 13, getHeight() - 20, this);
        g.drawImage(QueenImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
    }
}

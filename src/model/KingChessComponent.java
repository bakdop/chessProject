package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示国际象棋里面的车
 */
public class KingChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image King_WHITE;
    private static Image King_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image KingImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (King_WHITE == null) {
            King_WHITE = ImageIO.read(new File("./images/king-white.png"));
        }
        if (King_BLACK == null) {
            King_BLACK = ImageIO.read(new File("./images/king-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiateKingImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                KingImage = King_WHITE;
            } else if (color == ChessColor.BLACK) {
                KingImage = King_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public KingChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiateKingImage(color);
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
        int targetX = destination.getX();
        int targetY = destination.getY();
        ChessboardPoint source = getChessboardPoint();
        int sourceX = source.getX();
        int sourceY = source.getY();
        int curX = sourceX;
        int curY = sourceY;
        if (targetX == sourceX && targetY > sourceY){
            curY++;
        }else if (targetX == sourceX && targetY < sourceY){
            curY--;
        }else if(targetX > sourceX && targetY == sourceY){
            curX++;
        }else if(targetX < sourceX && targetY == sourceY){
            curX--;
        }else if (targetX <sourceX && targetY < sourceY){
            curX--;
            curY--;
        }else if (targetX < sourceX && targetY > sourceY){
            curX--;
            curY++;
        }else if (targetX > sourceX && targetY < sourceY){
            curX++;
            curY--;
        }else if (targetX > sourceX && targetY > sourceY){
            curX++;
            curY++;
        }
        if (curX < 0 || curX >7 || curY < 0 || curY > 7) return false;
        if (!(chessComponents[curX][curY] instanceof EmptySlotComponent) && curX != targetX && curY != targetY ) return false;
        if (curX == targetX && curY == targetY) return true;
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
        g.drawImage(KingImage, 0, 0, getWidth() , getHeight(), this);
        g.setColor(Color.BLACK);
        if (isSelected()) { // Highlights the model if selected.
            g.setColor(Color.RED);
            g.drawOval(0, 0, getWidth() , getHeight());
        }
        if(isCanMovePosition()){
            g.setColor(Color.BLACK);
            g.drawOval(0,0,getWidth(),getHeight());
        }
    }
    @Override
    public String toString(){
        String ret="";
        if(super.chessColor.equals(ChessColor.BLACK)){
            ret="K";
        }else if(super.chessColor.equals(ChessColor.WHITE)){
            ret="k";
        }
        return ret;
    }
}

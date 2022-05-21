package model;

import controller.ClickController;
import view.ChessboardPoint;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 这个类表示国际象棋里面的车
 */
public class PawnChessComponent extends ChessComponent {
    /**
     * 黑车和白车的图片，static使得其可以被所有车对象共享
     * <br>
     * FIXME: 需要特别注意此处加载的图片是没有背景底色的！！！
     */
    private static Image Pawn_WHITE;
    private static Image Pawn_BLACK;

    /**
     * 车棋子对象自身的图片，是上面两种中的一种
     */
    private Image PawnImage;

    /**
     * 读取加载车棋子的图片
     *
     * @throws IOException
     */
    public void loadResource() throws IOException {
        if (Pawn_WHITE == null) {
            Pawn_WHITE = ImageIO.read(new File("./images/Pawn-white.png"));
        }

        if (Pawn_BLACK == null) {
            Pawn_BLACK = ImageIO.read(new File("./images/Pawn-black.png"));
        }
    }


    /**
     * 在构造棋子对象的时候，调用此方法以根据颜色确定rookImage的图片是哪一种
     *
     * @param color 棋子颜色
     */

    private void initiatePawnImage(ChessColor color) {
        try {
            loadResource();
            if (color == ChessColor.WHITE) {
                PawnImage = Pawn_WHITE;
            } else if (color == ChessColor.BLACK) {
                PawnImage = Pawn_BLACK;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PawnChessComponent(ChessboardPoint chessboardPoint, Point location, ChessColor color, ClickController listener, int size) {
        super(chessboardPoint, location, color, listener, size);
        initiatePawnImage(color);
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
        ChessColor chessColor = this.getChessColor();
        int[][] directions = null;
        int bounds = 1;
        if (chessColor.equals(ChessColor.BLACK)){
            directions = new int[][]{{1,0},{1,-1},{1,1}};
            if (sourceX == 1){
                bounds = 2;
            }
        }else {
            directions = new int[][]{{-1,0},{-1,-1},{-1,1}};
            if (sourceX == 6){
                bounds = 2;
            }
        }
        for (int j = 1; j <=bounds; j++) {
            int curX0 = sourceX + directions[0][0]*j;
            int curY0 = sourceY + directions[0][1]*j;
            if (curX0 >= 0 && curX0 <= 7 && curY0 >= 0 && curY0 <= 7 && (chessComponents[curX0][curY0] instanceof EmptySlotComponent) && curX0 == targetX && curY0==targetY
                    && (chessComponents[sourceX + directions[0][0]][sourceY + directions[0][1]] instanceof EmptySlotComponent))
                return true;
            int curX1 = sourceX + directions[1][0]*j;
            int curY1 = sourceY + directions[1][1]*j;

            if ((curX1 >= 0 && curX1 <=7 && curY1 >= 0 && curY1 <= 7 && !(chessComponents[curX1][curY1] instanceof EmptySlotComponent)&& curX1 == targetX && curY1==targetY)
            || (curX1 >= 0 && curX1 <=7 && curY1 >= 0 && curY1 <= 7 && (chessComponents[curX1][curY1] instanceof EmptySlotComponent)&& curX1 == targetX && curY1==targetY &&
                    chessComponents[sourceX][curY1] instanceof PawnChessComponent &&
                    chessComponents[sourceX][curY1].chessColor != chessComponents[curX1][curY1].chessColor)) {
                if (Math.abs(targetX-sourceX)>1&&Math.abs(targetY-sourceY)>1) return false;
                else return true;
            }
            int curX2 = sourceX + directions[2][0]*j;
            int curY2 = sourceY + directions[2][1]*j;

            if (Math.abs(targetX-sourceX)>1&&Math.abs(targetY-sourceY)>1) return false;
            if ((curX2 >= 0 && curX2 <=7 && curY2 >= 0 && curY2 <= 7 && !(chessComponents[curX2][curY2] instanceof EmptySlotComponent)&& curX2 == targetX && curY2==targetY) ||
                    (curX2 >= 0 && curX2 <=7 && curY2 >= 0 && curY2 <= 7 && (chessComponents[curX2][curY2] instanceof EmptySlotComponent)&& curX2 == targetX && curY2==targetY &&
                            chessComponents[sourceX][curY2] instanceof PawnChessComponent &&
                            chessComponents[sourceX][curY2].chessColor != chessComponents[curX2][curY2].chessColor)) {
                if (Math.abs(targetX-sourceX)>1||Math.abs(targetY-sourceY)>1) return false;
                else return true;
            }
        }
        return false;
    }


    @Override
    public String toString(){
        String ret="";
        if(super.chessColor.equals(ChessColor.BLACK)){
            ret="P";
        }else if(super.chessColor.equals(ChessColor.WHITE)){
            ret="p";
        }
        return ret;
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
        g.drawImage(PawnImage, 0, 0, getWidth() , getHeight(), this);
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
}

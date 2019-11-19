import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x, y;

    public static final int tankmoveX = 5;
    public static final int tankmovey = 5;

    private boolean D_UP = false,D_DOWN = false,D_LEFT = false,D_RIGTH = false;



    enum Direction{U,UR,R,RD,D,DL,L,LU,STOP}
    private Direction dir = Direction.STOP;

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 30, 30);
        g.setColor(c);
    }

    /**
     * 判断按的键位
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
                D_RIGTH=true;
                break;
            case KeyEvent.VK_DOWN:
                D_DOWN=true;
                break;
            case KeyEvent.VK_LEFT:
                D_LEFT=true;
                break;
            case KeyEvent.VK_UP:
                D_UP=true;
                break;
        }

        judge();
    }

    /**
     * 判断松开的键
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_RIGHT:
                D_RIGTH=false;
                break;
            case KeyEvent.VK_DOWN:
                D_DOWN=false;
                break;
            case KeyEvent.VK_LEFT:
                D_LEFT=false;
                break;
            case KeyEvent.VK_UP:
                D_UP=false;
                break;
        }

        judge();
    }

    /**
     * 判断方向
     */

    private void  judge(){
        if(D_UP&&!D_DOWN&&!D_LEFT&&!D_RIGTH) dir = Direction.U;
        if(D_UP&&!D_DOWN&&!D_LEFT&&D_RIGTH) dir = Direction.UR;
        if(!D_UP&&!D_DOWN&&!D_LEFT&&D_RIGTH) dir = Direction.R;
        if(!D_UP&&D_DOWN&&!D_LEFT&&D_RIGTH) dir = Direction.RD;
        if(!D_UP&&D_DOWN&&!D_LEFT&&!D_RIGTH) dir = Direction.D;
        if(!D_UP&&D_DOWN&&D_LEFT&&!D_RIGTH) dir = Direction.DL;
        if(!D_UP&&!D_DOWN&&D_LEFT&&!D_RIGTH) dir = Direction.L;
        if(D_UP&&!D_DOWN&&D_LEFT&&!D_RIGTH) dir = Direction.LU;

        move();
    }

    /**
     * 移动
     */
    public void move(){
        switch (dir){
            case U:
                y-=tankmovey;
                break;
            case UR:
                y-=tankmovey;
                x+=tankmoveX;
                break;
            case R:
                x+=tankmoveX;
                break;
            case RD:
                y+=tankmovey;
                x+=tankmoveX;
                break;
            case D:
                y+=tankmovey;
                break;
            case DL:
                y+=tankmovey;
                x-=tankmoveX;
                break;
            case L:
                x-=tankmoveX;
                break;
            case LU:
                y-=tankmovey;
                x-=tankmoveX;
                break;
    }


    }



}

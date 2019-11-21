import java.awt.*;
import java.awt.event.KeyEvent;

public class Tank {
    private int x, y;
    private TankWarClient tc;

    public static final int tankmoveX = 5;
    public static final int tankmovey = 5;

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private boolean D_UP = false,D_DOWN = false,D_LEFT = false,D_RIGTH = false;
    enum Direction{U,UR,R,RD,D,DL,L,LU,STOP}

    //坦克移动方向
    private Direction dir = Direction.STOP;
    //炮筒方向
    private Direction ptdir = Direction.D;



    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Tank(int x, int y, TankWarClient tc) {
       this(x,y);
        this.tc = tc;
    }



    public void draw(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(c);

        switch (ptdir){
            case U:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y);
                break;
            case UR:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH,y);
                break;
            case R:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH,y+Tank.HEIGHT/2);
                break;
            case RD:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH,y+Tank.HEIGHT);
                break;
            case D:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x+Tank.WIDTH/2,y+Tank.HEIGHT);
                break;
            case DL:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x,y+Tank.HEIGHT);
                break;
            case L:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x,y+Tank.HEIGHT/2);
                break;
            case LU:
                g.drawLine(x+Tank.WIDTH/2,y+Tank.HEIGHT/2,x,y);
                break;
        }
    }

    /**
     * 判断按的键位
     * @param e
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_CONTROL:
                tc.m = fire();
                break;
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
     * 开火
     *
     */
    private Missile fire() {
        int x = this.x+Tank.WIDTH/2-Missile.WIDTH/2;
        int y = this.y+Tank.HEIGHT/2-Missile.HEIGHT/2;
        return new Missile(x,y,ptdir);
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
        if(!D_UP&&!D_DOWN&&!D_LEFT&&!D_RIGTH) dir = Direction.STOP;
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
            case STOP:
                break;
         }
         if(this.dir!=Direction.STOP){
             this.ptdir = this.dir;
         }



    }



}

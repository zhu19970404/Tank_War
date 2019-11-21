import java.awt.*;


public class Missile {
    int x,y;
    private TankWarClient tc;

    //炮弹方向，每一次移动多少，大小
    Tank.Direction dir;
    public static final int tankmovey = 5;
    public static final int tankmoveX = 5;
    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;


    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Missile(int x, int y, TankWarClient tc, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.tc = tc;
        this.dir = dir;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        move();
    }

    private void move() {
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
        if (x<0 || y<0 || x>tc.GAME_WIDTH || y>tc.GAME_HEIGHT){
            tc.Missiles.remove(this);
        }
    }


}

import java.awt.*;


public class Missile {
    int x,y;
    Tank.Direction dir;
    public static final int tankmovey = 5;
    public static final int tankmoveX = 5;
    public Missile(int x, int y, Tank.Direction dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void draw(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,10,10);
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
    }


}

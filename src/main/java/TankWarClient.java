import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWarClient extends Frame {

    public static final int GAME_WIDTH =800;
    public static final int GAME_HEIGHT = 600;

    Tank tank = new Tank(50,50,this);
    Missile m = null;

    Image offScreenImage = null;
    public void paint(Graphics g){
        if(m != null)
            m.draw(g);
        tank.draw(g);

    }

    /**
     * 防止闪烁
     * @param g
     */
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);

    }


    /**
     * 设置边框
     */
    public void lauchFrame(){
        this.setLocation(400,300);
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setTitle("TankWar");
        //使用匿名类来监听关闭
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

        //禁止改变边框大小
        this.setResizable(false);

        //注入键盘监听器
        this.addKeyListener(new KeyMonitor());
        setVisible(true);
        new Thread(new PrintThread()).start();
    }

    /**
     * 写一个内部类线程来驱动坦克
     */
    private class PrintThread implements Runnable{
        public void run() {
            while (true) {
                try {
                    Thread.sleep(30);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 键盘监听器
     */
    private class KeyMonitor extends KeyAdapter{

        /**
         * 监听键盘按下
         * @param e
         */
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }

        /**
         * 监听键盘抬起
         */
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
        }

    }



    public static void main(String[] args) {
        TankWarClient client = new TankWarClient();
        client.lauchFrame();
    }
    }


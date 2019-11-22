import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
public class TankWarClient extends Frame {

    public static final int GAME_WIDTH =800;
    public static final int GAME_HEIGHT = 600;

    Tank gtank = new Tank(50,50,this,true);
    Tank btank = new Tank(50,200,this,false);

    List<Missile> Missiles  = new ArrayList<Missile>();

    Image offScreenImage = null;


    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("missile count:"+Missiles.size(),10,50);
        g.setColor(c);

        if(Missiles != null){
         for (int i=0;i<Missiles.size();i++){
            Missile m =  Missiles.get(i);
            m.hitTant(btank);
            m.draw(g);

         }
        }
        gtank.draw(g);
        btank.draw(g);


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
            gtank.keyPressed(e);
        }

        /**
         * 监听键盘抬起
         */
        public void keyReleased(KeyEvent e) {
            gtank.keyReleased(e);
        }

    }



    public static void main(String[] args) {
        TankWarClient client = new TankWarClient();
        client.lauchFrame();
    }
    }


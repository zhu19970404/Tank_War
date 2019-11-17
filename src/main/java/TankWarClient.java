import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWarClient extends Frame {

    int x= 50,y=50;

    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,30,30);
        g.setColor(c);

        y+=5;
    }
    /**
     * 设置边框
     */
    public void lauchFrame(){
        this.setLocation(400,300);
        this.setSize(800,600);
        this.setTitle("TankWar");
        //使用匿名类来监听关闭
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        //禁止改变边框大小
        this.setResizable(false);
        this.setBackground(Color.GREEN);
        setVisible(true);
        new Thread(new PrintThread()).start();
    }

    public static void main(String[] args) {
        TankWarClient client = new TankWarClient();
        client.lauchFrame();
    }

    /**
     * 写一个内部类线程来驱动坦克
     */
    private class PrintThread implements Runnable{
        public void run() {
            while (true) {
                try {
                    Thread.sleep(50);
                    repaint();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

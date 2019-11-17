import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankWarClient extends Frame {

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
        setVisible(true);
    }

    public static void main(String[] args) {
        TankWarClient client = new TankWarClient();
        client.lauchFrame();
    }
}

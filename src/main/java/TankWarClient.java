import java.awt.*;

public class TankWarClient extends Frame {

    /**
     * 设置边框
     */
    public void lauchFrame(){
        this.setLocation(400,300);
        this.setSize(800,600);
        setVisible(true);
    }

    public static void main(String[] args) {
        TankWarClient client = new TankWarClient();
        client.lauchFrame();
    }
}

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        TankFrame tf=new TankFrame();
        int initialTankCount = Integer.parseInt((String)PropertyMgr.get("initialTankCount"));
        for(int i=0;i<initialTankCount;i++){
            tf.enemy.add(new Tank(i*10,i*15,Dir.DOWN,tf,Group.BAD,2));
        }
        while(true){
            Thread.sleep(50);
            tf.repaint();
        }

    }
}

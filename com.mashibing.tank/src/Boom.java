import java.awt.*;

public class Boom {
    private static final int WIDTH = ResourseMgr.Boom[0].getWidth();
    private static final int HEIGHT = ResourseMgr.Boom[0].getHeight();
    private int x,y;
    TankFrame tankframe = null;
    private int step = 0;
    public Boom(int x, int y, TankFrame tankframe) {
        this.x = x;
        this.y = y;
        this.tankframe = tankframe;
    }
    public void boompaint(Graphics g){
        if(step < ResourseMgr.Boom.length){
            g.drawImage(ResourseMgr.Boom[step++],x,y,null);
        }else{
            tankframe.boom.remove(this);
        }
    }
}

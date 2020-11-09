import java.awt.*;

public class Bullet {
    private static final int SPEED = 20 ;
    static final int WIDTH = ResourseMgr.bulletD.getWidth() , HIGHT = ResourseMgr.bulletD.getHeight();
    private Dir dir ;
    private int x , y ;
    private boolean living = true;
    private TankFrame tf = null;
    Group group = Group.BAD;
    Rectangle bulletRec = new Rectangle();


    public Bullet(Dir dir, int x, int y,TankFrame tf,Group group) {
        this.dir = dir;
        this.x = x;
        this.y = y;
        this.tf = tf;
        this.group = group;

        bulletRec.x = this.x;
        bulletRec.y = this.y;
        bulletRec.width = this.WIDTH;
        bulletRec.height = this.HIGHT;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void bulletPaint(Graphics g){
        if(!living)tf.bulletList.remove(this);
        else{
            switch (dir){
                case DOWN:
                    g.drawImage(ResourseMgr.bulletD,x,y,null);
                    break;
                case LEFT:
                    g.drawImage(ResourseMgr.bulletL,x,y,null);
                    break;
                case TOP:
                    g.drawImage(ResourseMgr.bulletU,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(ResourseMgr.bulletR,x,y,null);
                    break;
            }
        move();
        }
    }
    public void move(){
        if(!living)return;
            switch (dir){
                case DOWN:
                    y+=SPEED;
                    break;
                case TOP:
                    y-=SPEED;
                    break;
                case LEFT:
                    x-=SPEED;
                    break;
                case RIGHT:
                    x+=SPEED;
                    break;
            }
        bulletRec.x = this.x;
        bulletRec.y = this.y;
        bulletRec.width = this.WIDTH;
        bulletRec.height = this.HIGHT;
        if(x < 0 ||y < 0 ||x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) living = false;
    }
    public void die() {
        living = false;
    }
}

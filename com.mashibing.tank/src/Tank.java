import java.awt.*;
import java.util.Random;

public class Tank {
    int x=100;
    int y=100;
    int SPEED=5;
    private boolean moving=true;
    private TankFrame tankframe = null;
    private static final int WIDTH = ResourseMgr.tankD.getWidth();
    private static final int HEIGHT = ResourseMgr.tankD.getHeight();
    Dir dir = Dir.DOWN;
    private boolean living = true;
    private Random random = new Random();
    Group group = Group.BAD;
    Rectangle tankRec = new Rectangle();


    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Tank(int x, int y, Dir dir,TankFrame tankframe,Group group,int SPEED) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankframe = tankframe;
        this.group = group;
        this.SPEED = SPEED;

        tankRec.x = this.x;
        tankRec.y = this.y;
        tankRec.width = this.WIDTH;
        tankRec.height = this.HEIGHT;
    }

    public void tankpaint(Graphics g){
        if(!living)return;
            switch (dir){
                case DOWN:
                    g.drawImage(this.group == Group.GOOD?ResourseMgr.tankD:ResourseMgr.enemyD,x,y,null);
                    break;
                case LEFT:
                    g.drawImage(this.group == Group.GOOD?ResourseMgr.tankL:ResourseMgr.enemyL,x,y,null);
                    break;
                case TOP:
                    g.drawImage(this.group == Group.GOOD?ResourseMgr.tankU:ResourseMgr.enemyU,x,y,null);
                    break;
                case RIGHT:
                    g.drawImage(this.group == Group.GOOD?ResourseMgr.tankR:ResourseMgr.enemyR,x,y,null);
                    break;
            }

        move();
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void move(){
        if(!moving||!living)return;
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
        if (this.group == Group.BAD&&random.nextInt(100) > 95){
            fire();
            enemyTankChangeDir();
        }
        boundCheck();

        tankRec.x = this.x;
        tankRec.y = this.y;
        tankRec.width = this.WIDTH;
        tankRec.height = this.HEIGHT;

    }
    public void boundCheck(){
        if (this.x < 0)this.x =0;
        if (this.y > tankframe.GAME_HEIGHT-this.HEIGHT)y = tankframe.GAME_HEIGHT - this.HEIGHT;
        if (this.x >tankframe.GAME_WIDTH-this.WIDTH) x = tankframe.GAME_WIDTH - this.WIDTH;
        if (this.y < 0) y = 0;
    }

    public void fire(){
        tankframe.bulletList.add( new Bullet(dir,x+WIDTH/2-Bullet.WIDTH/2,y+HEIGHT/2-Bullet.HIGHT/2,tankframe,group));
    }

    public void collideWith(Bullet bullet) {
        //这里有一个小bug，每刷新一次，都会做一次碰撞，对当前位置，但是这就导致每检测一次，都new出一个rec，玩了很多次之后，可能会内存溢出。
//        tankRec = new Rectangle(x,y,WIDTH,HEIGHT);
//        bullet.bulletRec = new Rectangle(bullet.getX(),bullet.getY(),Bullet.WIDTH,Bullet.HIGHT);
        if(tankRec.intersects(bullet.bulletRec)&&group!=bullet.group) {
            this.die();
            bullet.die();
            tankframe.boom.add(new Boom(x,y,tankframe));
        }
    }

    public void die() {
        this.living = false;
        tankframe.enemy.remove(this);
    }
    public void enemyTankChangeDir(){
        int changeNumber = random.nextInt(4);
        switch (changeNumber){
            case 0:
                this.setDir(Dir.LEFT);
                break;
            case 1:
                this.setDir(Dir.RIGHT);
                break;
            case 2:
                this.setDir(Dir.DOWN);
                break;
            case 3:
                this.setDir(Dir.TOP);
                break;
        }
    }
}
 enum Dir{
    LEFT,RIGHT,DOWN,TOP
}
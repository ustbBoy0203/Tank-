import javax.tools.ToolProvider;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
     static final int GAME_WIDTH = 800;
     static final int GAME_HEIGHT = 800;
     List<Bullet>  bulletList = new ArrayList();
     List<Tank> enemy = new ArrayList<>();
     Tank tank=new Tank(10,200,Dir.DOWN,this,Group.GOOD,10);
     List<Boom> boom = new ArrayList<>();
    Boolean Bl=false;
    Boolean Br=false;
    Boolean Bt=false;
    Boolean Bd=false;


    public TankFrame(){
        setSize(GAME_WIDTH,GAME_WIDTH);
        setResizable(false);//默认是可以改变大小的
        setTitle("Tank War");
        setVisible(true);//可视化,True或false。

        addWindowListener(new WindowAdapter() {   //添加一个win监听器,监听winsclose这件事

            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);//系统退出
            }
        });
        addKeyListener(new MyKeyListener());
    }

    //解决闪烁问题，不必深究。
    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {     //每次窗口需要重新绘制时，都会调用一次此函数，在窗口内绘制
        g.setColor(Color.red);
        g.drawString("当前子弹的数量"+bulletList.size(),50,50);
        g.drawString("当前敌人的数量"+enemy.size(),50,70);
        g.drawString("当前爆炸的数量"+boom.size(),50,90);

        tank.tankpaint(g);

        for(int i=0;i<bulletList.size();i++){
            for(int j=0;j<enemy.size();j++){
                enemy.get(j).collideWith(bulletList.get(i));
            }
        }

        for(int i=0;i<enemy.size();i++){
            enemy.get(i).tankpaint(g);
        }

        for(int i=0;i<bulletList.size();i++){
            bulletList.get(i).bulletPaint(g);
        }
        for(int i=0;i<boom.size();i++){
            boom.get(i).boompaint(g);
        }
    }

    class MyKeyListener extends KeyAdapter{     //内部类，因为此类只给TankFrame用，所以写成内部类。
        @Override
        public void keyReleased(KeyEvent e) {
            int value=e.getKeyCode();
            switch (value){
                case KeyEvent.VK_LEFT:
                    Bl=false;
                    break;
                case KeyEvent.VK_RIGHT:
                    Br=false;
                    break;
                case KeyEvent.VK_UP:
                    Bt=false;
                    break;
                case KeyEvent.VK_DOWN:
                    Bd=false;
                    break;
//                case KeyEvent.VK_SPACE:
//                    tank.fire();
//                    break;

            }setDir(); 
        }

        @Override
        public void keyPressed(KeyEvent e) {
                int value=e.getKeyCode();
                switch (value){
                    case KeyEvent.VK_LEFT:
                        Bl=true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        Br=true;
                        break;
                    case KeyEvent.VK_UP:
                        Bt=true;
                        break;
                    case KeyEvent.VK_DOWN:
                        Bd=true;
                        break;
                    case KeyEvent.VK_SPACE:
                    tank.fire();
                    break;
                }setDir();
            }

        }

    public void setDir(){
        if(!Bl&&!Br&&!Bt&&!Bd)tank.setMoving(false);
        else {
            tank.setMoving(true);
            if (Bl) tank.setDir(Dir.LEFT);
            if (Br) tank.setDir(Dir.RIGHT);
            if (Bt) tank.setDir(Dir.TOP);
            if (Bd) tank.setDir(Dir.DOWN);
        }

    }
    }





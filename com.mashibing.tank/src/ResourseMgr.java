import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourseMgr {
    public static BufferedImage tankL , tankR , tankU , tankD;
    public static BufferedImage bulletL , bulletR , bulletU , bulletD;
    public static BufferedImage[] Boom =new BufferedImage[8];
    public static BufferedImage enemyL , enemyR , enemyU , enemyD;


    static {
        try {
            tankL = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            tankU = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            tankD = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            tankR = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
            bulletR = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/missileR.gif"));
            bulletU = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/missileD.gif"));
            bulletD = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/missileU.gif"));
            bulletL = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/missileL.gif"));
            enemyL = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/enemy2L.gif"));
            enemyR = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/enemy2R.gif"));
            enemyU = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/enemy2U.gif"));
            enemyD = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/enemy2D.gif"));


            for(int i=1;i<=Boom.length;i++){
                Boom[i-1] = ImageIO.read(ResourseMgr.class.getClassLoader().getResourceAsStream("images/blast"+i+".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

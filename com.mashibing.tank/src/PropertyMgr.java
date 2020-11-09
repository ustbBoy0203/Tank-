import java.io.IOException;
import java.util.Properties;
//此类专门用来控制配置文件和这个项目初始值之间的关系。
public class PropertyMgr {
    static Properties props = new Properties();

    //自动将config里面的内容加载到props里面，然后在进行相应的读取。
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if( props == null) return null;
        return props.get(key);
    }

    public static void main(String[] args) {
        System.out.println(get("initialTankCount"));
    }
}

package jm.task.core.jdbc.util;

import java.io.IOException;
import java.util.Properties;

public final class PropertiesUtil {

    private static final Properties PROPERTIES = new Properties();

    static {
        loadPropirties();
    }


    private  PropertiesUtil(){
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
    private static void loadPropirties() {
       try (var inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(inputStream);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }

    }
}

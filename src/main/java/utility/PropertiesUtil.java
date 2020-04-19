package utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    static InputStream inputStream;
    static String propFileName = "config.properties";
    static Properties prop;
    static {
        prop = new Properties();
    }
    public static String uploadsPath() {
        inputStream = PropertiesUtil.class.getClassLoader().getResourceAsStream(propFileName);
        String uploads = "";
        try {
            prop.load(inputStream);
            uploads = prop.getProperty("uploads");
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return uploads;
    }
}

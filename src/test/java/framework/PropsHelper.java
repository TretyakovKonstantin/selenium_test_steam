package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropsHelper {

    private static Properties props = getProps("src\\test\\resources\\config.properties");
    private static Properties locProps;
    private static String locPropsPlace = "src\\test\\resources\\localisation\\loc_%s.properties";


    public static String getProperty(String key) {
        return props.getProperty(key);
    }

    private static Properties getProps(String path) {
        try {
            InputStream input = new FileInputStream(path);
            Properties props = new Properties();
            props.load(input);
            return props;
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Не удалось загрузить конфигурационный файл");
            System.exit(-1);
            return null;
        }
    }

    public static Properties getProps() {
        return props;
    }

    private static Properties getLocProps() {
        if (locProps == null) {
            String lang = props.getProperty("language");
            locProps = getProps(String.format(locPropsPlace, lang));
        }

        return locProps;
    }

    public static String getLocProperty(String property) {
        return getLocProps().getProperty(property);
    }
}

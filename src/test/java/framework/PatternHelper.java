package framework;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternHelper {
    public static double getIntFromString(String origin) {

        Pattern pattern = Pattern.compile("\\d+[.]?\\d*");
        Matcher matcher = pattern.matcher(origin);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        } else {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Ошибка при преобразовании к числу: число не найдено");
            return 0;
        }

    }
}

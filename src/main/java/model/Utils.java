package model;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

public class Utils {

    public static List<String> matches(Matcher m) {
        List<String> matches = new LinkedList<>();
        while(m.find()) {
            matches.add(m.group());
        }
        return matches;
    }
}

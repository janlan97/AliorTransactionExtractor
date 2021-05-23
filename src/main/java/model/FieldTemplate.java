package model;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class represents schema of parsing single field data from string against specified stringified pdf input
 * @param <T> Target type which is held by this instance's value
 */
public final class FieldTemplate<T> {

    /**
     * Represents how to parse value of T type from passed String
     */
    private final Function<String, T> parseFunction;

    /**
     * Value which is held by the field
     */
    private T val;

    /**
     *  Pattern is used to create a matcher that will match the input
     */
    private final Pattern pattern;

    /**
     * Defines index of matched sequence (group) that will be parsed
     */
    private final int groupIndex;

    public FieldTemplate(String regex, Function<String, T> parseFunction, int flags, int groupIndex) {
        pattern = Pattern.compile(regex, flags);
        this.parseFunction = parseFunction;
        this.groupIndex = groupIndex;
    }

    /**
     * Method for parsing field value from given string
     * @param txt - Stringified PDF context
     * @return parsed field value of given T type
     */
    T value(String txt) {
        Matcher matcher = pattern.matcher(txt);
        if(matcher.find()) {
            String result = matcher.group(groupIndex);
            val = parseFunction.apply(result);
        }
        return val;
    }
}
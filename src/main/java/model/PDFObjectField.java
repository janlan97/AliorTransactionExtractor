package model;

/**
 *
 * @param <T> Target type which is held by this instance's value
 */
public class PDFObjectField<T> {
    private T value;
    private final String name;
    private final FieldTemplate<T> template;

    public PDFObjectField(String name, FieldTemplate<T> template) {
        this.name = name;
        this.template = template;
    }

    public void setValue(String rawPdfText) {
        if (value == null) {
            value = template.value(rawPdfText);
        }
    }

    public String getName() {
        return name;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "\"" + getValue().toString() + "\"";
    }
}

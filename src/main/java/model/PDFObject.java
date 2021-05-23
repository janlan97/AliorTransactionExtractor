package model;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class PDFObject<T> implements CSVData<T> {
    protected Map<String, PDFObjectField> fields;

    public PDFObject(String rawPdfText) {
        setFields();
        for (String k : getFields().keySet()) {
            getFields().get(k).setValue(rawPdfText);
        }
    }

    public Map<String, PDFObjectField> getFields() {
        return fields;
    }

    /**
     * Used for initialization of Map<String, PDFObjectField2>, where key is field's name and value is PDFObjectField instance.
     * Pass
     */
    protected void setFields() {
        try {
            throw new Exception("Fields are not set in implementation class.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toCsvLine() {
        return this.getFields().values().stream().map(PDFObjectField::toString).collect(Collectors.joining(","));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " {\n" + fields.entrySet()
                .stream()
                .map(e -> e.getKey() + " = " + e.getValue())
                .collect(Collectors.joining("" +
                        "\n")) + "\n}";
    }
}
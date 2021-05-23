package model;

import format.ConvertibleFormat;

import java.io.File;
import java.util.List;

public class CSVFormat<T> implements ConvertibleFormat<PDFObject<T>> {
    @Override
    public String convertTo(PDFObject<T> elem) {
        return null;
    }

    @Override
    public PDFObject<T> convertBack(String elem) {
        return null;
    }

    @Override
    public List<PDFObject<T>> readFrom(String path) {
        return null;
    }

    @Override
    public File writeTo(List<PDFObject<T>> elems, String path) {
        return null;
    }
}

package model;

public interface CSVData<T> {

    String toCsvLine();
    //T read(String path);
}

package format;

import java.io.File;
import java.util.List;

/**
 * Interface that helps with conversion from format T to desired format.
 * With this interface you can implement rules to implement ex. POJO object to CSV entry
 * @param <T> type of object to be serialized
 */
public interface ConvertibleFormat<T> {

    /**
     *
     * @param elem element of type T to be serialized to desired format
     * @return string representation of formatted object
     */
    String convertTo(T elem);

    /**
     *
     * @param elem formatted object to be serialized to type T
     * @return object of type T serialized back from String
     */
    T convertBack(String elem);

    /**
     *
     * @param path file to path with objects to be read from
     * @return Collection of serialized objects of type T
     */
    List<T> readFrom(String path);

    /**
     *
     * @param elems objects to be written to file specified by path argument
     * @param path path to file for objects to be written to
     * @return File object with written objects
     */
    File writeTo(List<T> elems, String path);
}

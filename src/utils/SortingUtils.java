package utils;

import java.util.Comparator;
import java.util.List;

public class SortingUtils {


    public static <T> void sort(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }


    public static <T> void sortByToString(List<T> list) {
        list.sort((a, b) -> a.toString().compareTo(b.toString()));
    }
}

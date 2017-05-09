package converters;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class ArrayConverter {
    public static ArrayList<Double> convertDoublePlainArrayToList(double[] array){
        ArrayList<Double> retArr = new ArrayList<>();
        IntStream.range(0,array.length).forEach(value -> retArr.add(array[value]));
        return retArr;
    }
}

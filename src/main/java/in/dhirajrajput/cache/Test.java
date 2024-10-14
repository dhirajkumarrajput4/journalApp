package in.dhirajrajput.cache;
import java.util.*;


public class Test {
    public static void main(String[] args) {
        int [] arr={1,4,3,5,2,6};

       Optional<Integer> sencond= Arrays.stream(arr)
        .boxed()
        .sorted((a,b)->b-a)
        .skip(1)
        .findFirst();

        System.out.println(sencond.get());
    }
}

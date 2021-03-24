import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Pair<T>
{
    T a;
    T b;
    public Pair() {}
}
public class Main {


    public static void main(String[] Args) {

        ArrayList<Integer> list = new ArrayList<Integer>();
        try
        {
            Files.lines(Paths.get("./primes.txt")).forEach(n -> list.add(Integer.parseInt(n)));
        }
        catch (IOException e)
        {
            System.out.println("heh");
        }

        Predicate<Integer> pr = new IsPrime<>();

        Pair<Long> time1 = new Pair<Long>();
        Pair<Long> time2 = new Pair<Long>();
        Pair<Long> time3 = new Pair<Long>();
       time1.a = System.nanoTime();
        boolean anw1 = Solution1.solve(list);
        time1.b = System.nanoTime();

        time2.a = System.nanoTime();
        int n = 8;
        boolean anw2 = Solution2.solve(list, pr,n);
        time2.b =System.nanoTime();

        time3.a =  System.nanoTime();
        boolean anw3 = Solution3.solve(list,pr);
        time3.b = System.nanoTime();
        System.out.println("The time 1 " + (time1.b - time1.a)/1000000);
        System.out.println("The time 2 " + (time2.b - time2.a)/1000000);
        System.out.println("The time 3 " + (time3.b - time3.a)/1000000);
    }
}

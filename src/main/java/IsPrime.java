import java.lang.Math;
import java.util.function.Predicate;

public class IsPrime<Integer> implements Predicate<Integer> {
    @Override
    public boolean test(Integer integer) {
        int num = (int) integer;
        if (num < 2)
            return true;
        int sqr = (int) Math.sqrt(num);

        for (int i = 2; i <= sqr; i++)
        {
            if (num % i == 0)
                return true;
        }
        return false;
    }

}

import java.util.ArrayList;
import java.util.function.Predicate;

public class Solution3 {
    public static boolean solve(ArrayList<Integer> list, Predicate<Integer> pr)
    {
        return list.parallelStream().anyMatch(pr);
    }
}

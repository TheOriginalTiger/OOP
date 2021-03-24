import java.util.ArrayList;
import java.util.Iterator;
public class Solution1 {

    public static boolean solve(ArrayList<Integer> list)
    {
        IsPrime ip = new IsPrime();
        Iterator<Integer> iter = list.iterator();
        while(iter.hasNext()) {
            if (ip.test(iter.next()))
                return true;
        }
        return false;
    }
}

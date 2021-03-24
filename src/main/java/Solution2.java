import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


class Kostil
{
    volatile boolean res;

    Kostil() {
        res = false;
    }
    public synchronized void setTrue() {
        res = true;
    }
}

class Threads<T> extends Thread {
    Iter<T> it;
    Predicate<T> pr;
    Kostil res;
    int num;

    public Threads(Iter<T> it, Predicate<T> pr, Kostil res, int num ) {
        this.it = it;
        this.pr = pr;
        this.res = res;
        this.num = num;
    }

    @Override
    public void run() {
        while(it.hasNext() && !res.res) {
            T elem = it.next();
            if (pr.test(elem)) {
                res.setTrue();
            }
        }
    }
}

public class Solution2 {
    public static boolean solve(ArrayList<Integer> list, Predicate<Integer> pr, int n) //throws InterruptedException
    {
        Kostil res = new Kostil();
        ArrayList<Threads<Integer>> threads = new ArrayList<Threads<Integer>>();
        for (int i = 0; i < n; i++)
        {
            Iter<Integer> iterator = new Iter<Integer>(list.iterator(), n, i);
            threads.add(new Threads<Integer>(iterator, pr,res, i));
        }

        for(Threads<Integer> thread : threads)
        {
            thread.start();
        }
        for(Threads<Integer> thread : threads)
        {
            try {
                thread.join();
            }
            catch (InterruptedException e) {System.out.println(e);}
        }

        return res.res;
    }
}

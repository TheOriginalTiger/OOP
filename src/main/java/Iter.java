import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Iter<T> implements Iterator<T> {
    private Iterator<T> it;
    private int n;
    private int ind;
    private int currInd;
    private int lastNextInt;


    public Iter(Iterator<T> it, int n, int shift) {
        this.it = it;
        T elem;
        for(int i = 0; i < shift && this.it.hasNext(); i++)
        {
            elem = this.it.next();
        }
        currInd = lastNextInt = 0 ;
        this.n = n ;
    }

    @Override
    public boolean hasNext() {
        if (lastNextInt == 0) {
            return true;
        }
        else if (currInd == lastNextInt) {
            T elem;
            for (int i =0; i < n-1 && it.hasNext(); i++)
            {
                elem = it.next();
            }
            currInd++;
            return it.hasNext();
        }
        else
            return true;
    }

    @Override
    public T next() {
        if (currInd == lastNextInt ) {
            if (this.hasNext())
            {
                lastNextInt++;
                if (currInd == 0 )
                    currInd++;
                return it.next();
            }
        }
        else
        {
            if (this.hasNext()) {
                lastNextInt++;
                return it.next();
            }
            else
            {
                throw new NoSuchElementException("iterator is empty");
            }
        }
        return it.next();
    }
}

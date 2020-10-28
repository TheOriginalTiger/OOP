import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack <T> implements Iterable <T>{

    private int cap;
    private T[] arr;
    private int am;

    private class Iter <T> implements Iterator <T> {

        private int id = 0;

        public boolean hasNext()
        {
            if (id < am)
                return true;
            else
                return false;
        }
        @SuppressWarnings("unchecked")
        public T next()
        {
            return (T) arr[id++];
        }
        public void remove()
        {
            throw new UnsupportedOperationException("remove is not supported for stack");
        }
    }


    public Iterator<T> iterator() {

        return new Iter<T>();
    }

    @SuppressWarnings("unchecked")
    public Stack (int cap)
    {
        this.cap = cap;
        arr = (T[]) new Object[cap];
        am = 0;
    }
    public int getCapacity()
    {
        return cap;
    }
    @SuppressWarnings("unchecked")
    private void realloc()
    {
        T[] newArray = (T[]) new Object[cap * 2];
        System.arraycopy(arr, 0, newArray, 0, am);
        arr = newArray;
        cap *=2;
    }
    public void push(T elem)
    {
        if (am == cap)
            realloc();

        arr[am++] = elem;

    }
    public T pop() throws EmptyStackException
    {
        if (am == 0)
            throw new EmptyStackException();
        else
            return arr[am--];
    }
}

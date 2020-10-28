import java.util.EmptyStackException;
import java.util.Iterator;

public class Stack <T> implements Iterable <T>{

    private int cap;
    private T[] arr;
    private int am;

    //живешь такой себе живешь. А потом тебе какой то неуч с бизнес тренингов говорит для стека итерибл писать
    //может чисто ради прикола сделать его мутабельным во время итерации?
    //Итернулся такой с дуру по стеку, попишь из него потом, а тебе по лицу эксепшеном пустого стека прилетает
    //ибо нефиг, блин, структуры данных коверкать.
    private class Iter <T> implements Iterator <T> {

        private int id = 0;
        //ну ага, сто раз блин. У нас стек вверх растет, а у меня лектор придумал хэзнекстить из него
        public boolean hasNext()
        {
            if (id < am)
                return true;
            else
                return false;
        }
        //скорее всего даже Аллах не знает, как итерироваться по стеку, но коли вот настолько чешется,
        //будем до упора притворяться что это треклятый массив.
        @SuppressWarnings("unchecked")
        public T next()
        {
            return (T) arr[id++];
        }
        // день, когда эта штука для стека станет саппортед однозначно будет последним для человечества
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

    /**
     * simple amount getter
     * @return Stack.AmountOfElems
     */
    public int getAmount()
    {
        return am;
    }
    @SuppressWarnings("unchecked")
    private void realloc()
    {
        T[] newArray = (T[]) new Object[cap * 2];
        System.arraycopy(arr, 0, newArray, 0, am);
        arr = newArray;
        cap *=2;
    }

    /**
     * method for adding to the Stack will automatically realloc array if needed
     * @param elem
     */
    public void push(T elem)
    {
        if (am == cap)
            realloc();

        arr[am++] = elem;

    }

    /**
     * method for popping from stack.
     * @return the last added elem from the stack
     * @throws EmptyStackException if you'll try to pop from empty stack. Use getAmount() for avoiding it
     */
    public T pop() throws EmptyStackException
    {
        if (am == 0)
            throw new EmptyStackException();
        else
            return arr[--am];
    }
}

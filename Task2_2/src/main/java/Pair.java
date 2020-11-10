public class Pair <T,V> {
    private T first;
    private V second;
    public Pair(T f, V s)
    {
        first = f;
        second = s;
    }

    public T getFirst()
    {
        return first;
    }

    public V getSecond() {
        return second;
    }
}

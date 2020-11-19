import static org.junit.Assert.*;
import org.junit.Test;

public class PriorityQueueTest {

    //simple test
    @Test
    public void test1()
    {
        PriorityQueue <String> q = new PriorityQueue<>(1);
        q.insert(new Pair<Integer, String>(19, "Rofle"));
        q.insert(new Pair<Integer, String>(30, "Rof"));
        q.insert(new Pair<Integer, String>(10, "Rofler"));
        q.insert(new Pair<Integer, String>(20, "Rofl"));
        Pair<Integer, String> res = q.extractMax();
        assertEquals(30, (int) res.getFirst());
        assertEquals("Rof", res.getSecond());
        res = q.extractMax();
        assertEquals(20, (int) res.getFirst());
        assertEquals("Rofl", res.getSecond());
        res = q.extractMax();
        assertEquals(19, (int) res.getFirst());
        assertEquals("Rofle", res.getSecond());
        res = q.extractMax();
        assertEquals(10, (int) res.getFirst());
        assertEquals("Rofler", res.getSecond());
    }

    //test of stream
    @Test
    public void test2()
    {
        PriorityQueue <String> q = new PriorityQueue<>(1);
        q.insert(new Pair<Integer, String>(10, "Rofler"));
        q.insert(new Pair<Integer, String>(19, "Rofle"));
        q.insert(new Pair<Integer, String>(20, "Rofl"));
        q.insert(new Pair<Integer, String>(30, "Rof"));
        assertEquals(4, q.stream().count());
    }


}
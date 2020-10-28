import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.*;

public class StackTest {
    //simple test
    @Test
    public void test1()
    {
        Stack <Integer> s = new Stack<>(5);
        for (int i =0 ; i < 5; i++)
            s.push(i);

        for (int i =4; i>=0; i--)
            assertEquals(i, (int) s.pop());
    }
    //simple test w/ different type
    @Test
    public void test2()
    {
        Stack <String> s = new Stack<>(3);
        s.push("prodam garage");
        s.push("lol");
        s.push("kek");
        assertEquals("kek", s.pop());
        assertEquals("lol", s.pop());
        assertEquals("prodam garage", s.pop());
    }
    // test of the realloc
    @Test
    public void test3()
    {
        Stack <Integer> s = new Stack<>(1);
        for (int i =0 ; i < 100; i++)
            s.push(i);
        for (int i =99 ; i >= 0; i--)
            assertEquals(i, (int) s.pop());
    }
    //simple test for exception
    @Test(expected = EmptyStackException.class)
    public void test4()
    {
        Stack <Integer> s = new Stack<>(3);
        s.pop();
    }
    //exception after realloc
    @Test(expected = EmptyStackException.class)
    public void test5()
    {
        Stack <Integer> s = new Stack<>(1);
        for (int i =0 ; i < 100; i++)
            s.push(i);
        for (int i =0 ; i <= 100; i++)
            s.pop();
    }
}
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class HeapTest extends TestCase {
    @Test
    public void test1()
    {
        int[] testArray = {5,4,3,2,1};
        testArray= HeapUtils.heapSort(testArray);
        Assert.assertArrayEquals( new int[] {1,2,3,4,5},testArray);
    }
    @Test
    public void test2()
    {
        int[] testArray = {5};
        testArray= HeapUtils.heapSort(testArray);
        Assert.assertArrayEquals( new int[] {5},testArray);
    }
    @Test
    public void test3()
    {
        int[] testArray = {1,5};
        testArray= HeapUtils.heapSort(testArray);
        Assert.assertArrayEquals( new int[] {1,5},testArray);
    }
    @Test
    public void test4()
    {
        int[] testArray = {23,17,5,0,20,7,29,2,14,31,0,29,13,13,43,-1,14,0,4,12,55,43};
        testArray= HeapUtils.heapSort(testArray);
        Assert.assertArrayEquals( new int[] {-1, 0, 0, 0, 2, 4, 5, 7, 12, 13, 13, 14, 14, 17, 20, 23, 29, 29, 31, 43, 43, 55},testArray);
    }
}
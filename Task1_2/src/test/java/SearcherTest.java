import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;


public class SearcherTest extends TestCase {

    //simple test
    @Test
    public void test1() throws IOException
    {
        String subs = "ra";
        String path ;//write down path to your input file here
        try{
            FileWriter fw = new FileWriter(path);
            fw.write("abra kadabda");
            fw.close();
            int result = Searcher.search(subs, path);
            Assert.assertEquals(2, result);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

    }

    //large test
    @Test
    public void test2() throws IOException
    {
        String subs = "a";
        String path ; //write down path to your input file here
        int chars = 1024;
        try{
            FileWriter fw = new FileWriter(path);
            fw.write("");
            for(int i = 0 ; i < chars; i++)
            {
                if (i != 999)
                    fw.append("b");
                else
                    fw.append("a");
            }
            fw.close();
            int result = Searcher.search(subs, path);
            Assert.assertEquals(999, result);

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
    //really large test
    @Test
    public void test3() throws IOException
    {
        String subs = "a";
        String path ;//write down path to your input file here
        int chars = 999999;//268435456; watch out! The original version of this test was perfomed using the commented number
                            // it took hell of a time but was a success. Left it here in case any1 wanted to make sure it actually worked
                            // the number is ~256 Mb and doesnt seem to be very much, but for some reason writing to file took more than 10 minutes
                            // C whould have done for like 2 mins :P

        File file = new File(path);
        FileOutputStream stream = null;
        try{
            stream = new FileOutputStream(file);
            for (int i = 0; i< chars; i++ )
            {
                if (i!= 999099) //268430456
                    stream.write("b".getBytes(StandardCharsets.UTF_8));
                else
                    stream.write("a".getBytes(StandardCharsets.UTF_8));

            }
            int result = Searcher.search(subs, path);
            Assert.assertEquals(999099, result);//268430456

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}
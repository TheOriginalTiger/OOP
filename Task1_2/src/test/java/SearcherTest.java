import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class SearcherTest extends TestCase {

    //simple test
    @Test
    public void test1() throws IOException
    {
        String subs = "ra";

        File tmp = File.createTempFile("input", ".txt",null);
        try (FileWriter fw = new FileWriter(tmp) )
        {
            fw.write("abra kadabda");
        }
        try ( FileInputStream stream =  new FileInputStream(tmp) ){

            int result = Searcher.search(subs, stream);
            Assert.assertEquals(2, result);
        }
        tmp.deleteOnExit();

    }

    //large test
    @Test
    public void test2() throws IOException
    {
        String subs = "a";
        File tmp = File.createTempFile("input",".txt", null);;
        int chars = 1024;
        try (FileWriter fw = new FileWriter(tmp)) {

            fw.write("");
            for (int i = 0; i < chars; i++) {
                if (i != 999)
                    fw.append("b");
                else
                    fw.append("a");
            }
        }
        try (FileInputStream stream = new FileInputStream(tmp)) {
            int result = Searcher.search(subs, stream);
            Assert.assertEquals(999, result);

        }
        tmp.deleteOnExit();
    }
    //really large test
    @Test
    public void test3() throws IOException
    {
        String subs = "a";
        int chars = 999999;//268435456; watch out! The original version of this test was perfomed using the commented number
                            // it took hell of a time but was a success. Left it here in case any1 wanted to make sure it actually worked
                            // the number is ~256 Mb and doesnt seem to be very much, but for some reason writing to file took more than 10 minutes
                            // C whould have done for like 2 mins :P

        File tmp = tmp = File.createTempFile("input", ".txt", null);;

        try (FileOutputStream stream = new FileOutputStream(tmp)) {
            for (int i = 0; i < chars; i++) {
                if (i != 999099) //268430456
                    stream.write("b".getBytes(StandardCharsets.UTF_8));
                else
                    stream.write("a".getBytes(StandardCharsets.UTF_8));
            }
        }
        try (FileInputStream stream2 = new FileInputStream(tmp)) {
            int result = Searcher.search(subs, stream2);
            Assert.assertEquals(999099, result); //268430456
        }
        tmp.deleteOnExit();
    }
}
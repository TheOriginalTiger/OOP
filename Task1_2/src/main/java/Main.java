import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;

class Searcher
{

    /**
     * Method for searching a substring in the file. it opens a Stream to the file and reads data to the buffer which is 2*len(Substring)
     * after that it searches for substring in buffer and either stops it and return
     * @param subString the String you will be looking for
     * @param stream is the opened
     * @return the result is either a non-negative number- index of the first substring occurrence or -1 if there is none
     * @throws IOException can throw IOException if the standart lib will disagree with you for some reason:P
     */
    static int search(String subString, InputStream stream) throws IOException
    {
        int result = -1 ;
        String str;
        try {
            int len = subString.length();

            if (len == 0)
                return result;

            byte[] buffer = new byte[len * 2];
            int readingResult = 0;
            readingResult = stream.read(buffer, 0, len * 2);

            str = new String(buffer, StandardCharsets.UTF_8);
            result = str.indexOf(subString);

            if (result != -1)
                return result;

            int offset = len;
            System.arraycopy(buffer, len, buffer, 0, len);
            while ((readingResult = stream.read(buffer, len, len)) != -1)
            {
                str = new String(buffer, StandardCharsets.UTF_8);
                result = str.indexOf(subString);
                if (result != -1)
                    return result + offset;
                System.arraycopy(buffer, len, buffer, 0, len);
                offset += len;
            }
            return result;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return result;
        }
        finally {
            stream.close();
        }

    }
}
public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        String ss = "e";//aba
        Searcher srch = new Searcher();
        String path ; // write down path to your input file here
        File file = new File(path);
        FileInputStream stream = new FileInputStream(file);
        int result = srch.search(ss,stream);
        System.out.println(result);

    }
}
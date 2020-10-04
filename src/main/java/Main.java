import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.io.FileNotFoundException;

class Searcher
{

    static int search(String subString, String path) throws IOException
    {
        int result = -1 ;
        String str;
        try {

            File file = new File(path);
            FileInputStream stream = new FileInputStream(file);
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
            while ((readingResult = stream.read(buffer, len, len)) != -1) {
                str = new String(buffer, StandardCharsets.UTF_8);
                result = str.indexOf(subString);
                if (result != -1)
                    return result + offset;
                System.arraycopy(buffer, len, buffer, 0, len);
                offset += len;
            }
            stream.close();
            return result;
        }
        catch (FileNotFoundException e)
        {
            System.out.println(e.getMessage());
            return result;
        }

    }
}
public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
            String ss = "a";//aba
            Searcher srch = new Searcher();
            String path; // write down path to your input file here
            int result = srch.search(ss,path);
            System.out.println(result);

    }
}
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

class Searcher
{


    private static int min(int a, int b)
    {
        return ( a < b ? a : b);
    }

    //So as it is too expensive to create Strings in while loop and they are also immutable
    //we need to write our own searcher for byte-array. Z-func sounds like a good idea
    private static int searchInByteArray(byte[] array, String substring,char[] finalArray,int[] z)
    {
        int len  = array.length + substring.length() + 2;

        // preparing our array for z-func calculation
        // [SubString...0...array]
        for (int i = 0; i < len - 1; i++)
        {
            z[i] = 0 ; // also clearing z from the last call of the func , as we keep it common for all calls
            if (i < substring.length())
            {
                finalArray[i] = substring.charAt(i);
            } else if (i == substring.length())
            {
                finalArray[i] = 127; // kind of a special symbol
            } else
            {
                finalArray[i] = (char) array[i - substring.length() - 1 ];
            }
        }
        finalArray[len - 1] = 0;
        //now calc z-func
        int r = 0 ;
        int l = 0 ;
        for(int i = 1 ; i < len ; i++)
        {
            if (i <= r)
            {
                z[i] = min(r - i + 1, z[i-l]);
            }
            while (i+z[i] < len && finalArray[z[i]] == finalArray[i+z[i]])
                    z[i]++;
            if (i+z[i]-1 > r)
            {
                l = i;
                r = i+z[i]-1;
            }
            if (z[i] == substring.length())
                return i - substring.length() - 1;
        }
        return -1 ;
    }


    /**
     * Method for searching a substring in the file. Reads data to the buffer which is 2*len(Substring)
     * after that it searches for a substring in buffer and either finds it in there and returns index of the first occurrence
     * or keeps reading until it finds subString or the file will end
     * @param subString the String to search for
     * @param stream is the opened stream to read the string from. Can be acquired from any inheritance of abstract InputStream
     * @return the result is either a non-negative number-index of the first substring occurrence or -1 if there is none
     * @throws IOException can throw IOException if the standart lib will disagree with you for some reason:P
     */
    static int search(String subString, InputStream stream) throws IOException
    {
        int result = -1 ;
        String str;

        int len = subString.length();
        if (len == 0)
            return result;

        byte[] buffer = new byte[len * 2];

        if (stream.read(buffer, 0, len * 2) == -1 )
        {
            return -1;
        }
        
        //these 2 are for z-func calculation. It's not wise to allocate them every time we calc it, as the
        // buff and substring lengths are constant
        char[] finalArray = new char[subString.length() + buffer.length + 2 ];
        int[] z = new int[subString.length() + buffer.length + 2 ];
        result = searchInByteArray(buffer, subString, finalArray, z);

        if (result != -1)
            return result;
        int offset = len;

        System.arraycopy(buffer, len, buffer, 0, len);
        while ( stream.read(buffer, len, len) != -1)
        {
            result = searchInByteArray(buffer, subString, finalArray, z);
            if (result != -1)
                return result + offset;
            System.arraycopy(buffer, len, buffer, 0, len);
            offset += len;
        }
        return result;

    }
}
public class Main {

    public static void main(String[] args) throws IOException, FileNotFoundException {
        FileInputStream stream = null;
        String ss = "l";//aba
        String path =  System.getProperty("user.dir").concat("\\src\\input.txt");; // write down path to your input file here
        System.out.println("the dir is");
        System.out.println(path);
        try {
            File file = new File(path);
            stream = new FileInputStream(file);
            int result = Searcher.search(ss, stream);
            System.out.println(result);
        } finally {
            stream.close(); // after thinking it over, decided to let the user close his own InputStream himself :p
        }
    }
}
import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class jsonConverter {

    static public void loadToJSON(String file, NoteBook nb) //throws IOException
    {
        Gson gson = new Gson();
        try (Writer writer = new FileWriter(file))
        {
            gson.toJson(nb, writer);
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    static public NoteBook loadFromJSON(Path file) throws IOException
    {
        String str = new String(Files.readAllBytes(file));
        Gson gson = new Gson();
        return gson.fromJson(str, NoteBook.class);
    }
}

import java.time.LocalDateTime;

public class NoteBookRecord {
    String title;
    LocalDateTime time;
    String text;
    public NoteBookRecord(String t, LocalDateTime tm, String txt)
    {
        title = t;
        time = tm;
        text = txt;
    }
}

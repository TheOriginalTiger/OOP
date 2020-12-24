import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class NoteBook {
    ArrayList<NoteBookRecord> records;

    public NoteBook()
    {
        records = new ArrayList<NoteBookRecord>();
    }

    public void addRecord(NoteBookRecord r)
    {
        records.add(r);
    }

    private void printer( NoteBookRecord r)
    {
        System.out.print(r.title);
        System.out.print(" ");
        System.out.print(r.time);
        System.out.print(" ");
        System.out.println(r.text);
    }

    public void showRecords()
    {
        records.forEach(this::printer);
    }

    public void showFilteredRecords(LocalDateTime from ,LocalDateTime to, List<String> titles)
    {
        for (NoteBookRecord record: records)
        {
            int cmp1 = record.time.compareTo(from);
            int cmp2 = record.time.compareTo(to);
            boolean match = titles.stream().allMatch(record.title :: contains);
            if ( cmp1 >= 0 && cmp2 <=0 && match )
            {
                printer(record);
            }
        }
    }

    public void removeRecord(String title)
    {
        records.removeIf(r -> r.title.equals(title));
    }


}

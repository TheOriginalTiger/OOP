import java.util.ArrayList;
import java.util.Collections;

public class Course {
    private final String name;
    private final int duration;
    private ArrayList<ArrayList<String>> subjects;

    public Course(String name,  String[][] subjects)
    {
        this.name = name;
        this.duration = subjects.length;
        this.subjects =  new ArrayList<ArrayList<String>>();

        for (int i = 0; i < subjects.length; i++)
        {
            ArrayList<String> tmp = new ArrayList<String>();
            Collections.addAll(tmp, subjects[i]);
            this.subjects.add(tmp);
        }

    }

    public String getName()
    {
        return name;
    }

    public int getDuration()
    {
        return duration;
    }

    /**
     * @return a copy of subjects matrix
     */
    public ArrayList<ArrayList<String>> getSubjects()
    {
        return subjects;
    }
}

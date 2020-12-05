
public class Main {
    public static void main(String args[])
    {
        String[][] courses = {{"C", "haskell", "math"}, {"puzan", "AI", "java"}};

        Course course = new Course("FIT", courses);
        Student me = new Student("Vladimir Parnachev", 19, 1);
        RecordBook rb = new RecordBook(me, course);
        for (int i = 0 ;  i < courses.length; i++)
        {
            for (int j = 0; j < courses[i].length; j++)
            {
                rb.evaluate(5, courses[i][j], i);
            }
        }
        rb.evaluate(4, "puzan", 1);
        System.out.println(rb.averageMark());
        System.out.println(rb.redDiploma());
        System.out.println(rb.scholarship());

    }
}

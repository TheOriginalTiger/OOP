import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RecordBookTest {

    private Course gimmeNoviyPotochek()
    {
        String[][] courses = {
                {"C", "haskell", "mathAnalysis", "mathLog", "Computing Platform"},
                {"C", "haskell", "mathAnalysis", "mathLog", "Computing Platform"},
                {"Models", "AI", "OOP", "TeamProject", "Diff/TFCV"},
                {"s1","s2","s3", "s4", "s5", "s6"},
                {"s1","s2","s3", "s4", "s5", "s6"},
                {"s1","s2","s3", "s4", "s5", "s6"},
                {"s1","s2","s3", "s4", "s5", "s6"},
                {"s1","s2","s3", "s4", "s5", "s6", "Дипломная работа"}
        };
        return new Course("Fit2.0", courses);
    }

    private void evaluateAll(RecordBook book, int mark)
    {
        Course course = book.getCourse();
        for (int i = 0; i < course.getDuration(); i++)
        {
            for(int j =0 ; j < course.getSubjects().get(i).size(); j++)
            {
                book.evaluate(mark, course.getSubjects().get(i).get(j), i);
            }
        }
    }

    @Test
    public void tstWithMyStats()
    {
        Course course = gimmeNoviyPotochek();
        Student s = new Student("Vladimir Parnachev", 19, 2);
        RecordBook book = new RecordBook(s, course);
        evaluateAll(book, 5);
        assertEquals(5.0, book.averageMark(), 0.0001);
        assertTrue(book.redDiploma());
        assertTrue(book.scholarship());
    }

    @Test
    public void tstIdiot()
    {
        Course course = gimmeNoviyPotochek();
        Student s = new Student("Danil Korchagin", 19, 1);
        RecordBook book = new RecordBook(s, course);
        evaluateAll(book, 3);
        assertEquals(3.0, book.averageMark(), 0.0001);
        assertFalse(book.redDiploma());
        assertFalse(book.scholarship());
    }

    @Test
    public void goodGraduate()
    {
        Course course = gimmeNoviyPotochek();
        Student s = new Student("Good Graduate", 19, 7);
        RecordBook book = new RecordBook(s, course);
        evaluateAll(book, 5);
        book.evaluate(4, "C", 0);
        book.evaluate(4, "haskell", 1);
        book.evaluate(4, "s2", 4);
        assertTrue(book.averageMark() >= 4.9);
        assertTrue(book.redDiploma());
        assertTrue(book.scholarship());
    }

    @Test
    public void badGraduate()
    {
        Course course = gimmeNoviyPotochek();
        Student s = new Student("Good Graduate", 19, 7);
        RecordBook book = new RecordBook(s, course);
        evaluateAll(book, 4);
        book.evaluate(3, "C", 0);
        book.evaluate(3, "haskell", 1);
        book.evaluate(3, "s2", 4);
        assertFalse(book.redDiploma());
        assertFalse(book.scholarship());
    }
    @Test
    public void almostGoodGraduate()
    {
        Course course = gimmeNoviyPotochek();
        Student s = new Student("Good Graduate", 19, 7);
        RecordBook book = new RecordBook(s, course);
        evaluateAll(book, 5);
        book.evaluate(4, "C", 0);
        book.evaluate(4, "haskell", 1);
        book.evaluate(4, "s2", 4);
        book.evaluate(4, "Дипломная работа", 7);

        assertTrue(book.averageMark() >= 4.8);
        assertFalse(book.redDiploma());
        assertFalse(book.scholarship());
    }

}
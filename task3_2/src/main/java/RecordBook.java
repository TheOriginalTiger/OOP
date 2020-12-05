
public class RecordBook {
    private Student student;
    private Course course;
    private int[][] marks;

    public RecordBook(Student s, Course cs)
    {
        student = s ;
        course = cs;
        int size = course.getSubjects().size();
        marks = new int[size][];
        for (int i = 0; i < size; i++)
        {
            marks[i] = new int[course.getSubjects().get(i).size()];
        }
    }

    //господи, прости меня за то, что решил пользоваться ArrayList'ом

    /**
     * function for placing a mark in the Record Book
     * @param mark mark for the exam
     * @param subject the name of the subject. Must be in course
     * @param semester Because the course can have the same subjects in different semesters
     *                you have to manually point out the right one.
     */
    public void evaluate(int mark, String subject, int semester)
    {
        //мне физически плохо от этого обращения
        marks[semester][course.getSubjects().get(semester).indexOf(subject)] = mark;
    }

    /**
     *
     * @return the average mark for all the semesters student completed
     */
    public double averageMark()
    {
        int sum = 0 ;
        int amount = 0;
        for(int i = 0 ; i <= student.getCurrentSemester(); i++)
        {
            for(int j = 0 ; j < course.getSubjects().get(i).size(); j++)
            {
                sum += marks[i][j];
                amount++;
            }
        }
        return (double) sum / amount;
    }

    /**
     * checks whether the student can have a red diploma
     * @return
     */
    public boolean redDiploma()
    {
        int exs = 0 ;
        int amount = 0 ;
        for(int i = 0 ; i <= student.getCurrentSemester(); i++)
        {
            for(int j = 0 ; j < course.getSubjects().get(i).size(); j++)
            {
                if (marks[i][j] == 5)
                    exs++;
                else if (marks[i][j] < 3)
                    return false;
                amount++;
            }
        }
        double average = (double) exs / (double) amount;
        if (average > 0.75)
        {
            if (student.getCurrentSemester() == course.getDuration() - 1)
            {
                int ind = course.getSubjects().get(course.getDuration() - 1 ).indexOf("Дипломная работа");

                if (ind == -1 || marks[course.getDuration() - 1][ind] == 5)
                    return true;

                return false;
            }
            return true;
        }
        return false;
        //its a ladder! :)
    }

    /**
     * checks whether the student will have a scholarship in the current semester
     * considering the results of last session
     * @return
     */
    public boolean scholarship()
    {
        for(int i = 0; i < course.getSubjects().get(student.getCurrentSemester()).size(); i++)
        {
            if (marks[student.getCurrentSemester()][i] != 5)
                return false;
        }
        return true;
    }

    public Course getCourse() {
        return course;
    }
}

public class Student {
    private String name;
    private int age;
    private int currentSemester;
    public Student(String name, int age, int sem)
    {
        this.age = age;
        this.name = name;
        currentSemester = sem;
    }

    public int getCurrentSemester()
    {
        return currentSemester;
    }

    public String getName() {
        return name;
    }
}

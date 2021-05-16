import static java.lang.Math.round;

public class Baker {
    final int id;
    final int experience;

    public Baker(int id, int experience)
    {
        this.id = id;
        this.experience = experience;
    }

    public synchronized void cook(Order o) {
        try
        {
            //TODO somehow use experience
            Thread.sleep(o.cookingDuration);
        }
        catch (InterruptedException e)
        {
            System.out.format("baker %d was interrupted", id);
        }
    }
}

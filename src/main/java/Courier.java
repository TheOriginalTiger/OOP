public class Courier {
    final int id;
    final int trunkSize;

    public Courier(int id, int trunkSize)
    {
        this.id = id;
        this.trunkSize = trunkSize;
    }

    public synchronized void deliver(Order[] orders, int cap)
    {
        long time = 0;
        for(int i = 0 ; i < cap; i++)
        {
            time += orders[i].deliveryDuration;
        }

        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            System.out.format("courier %d was interrupted", id);
        }
    }
}

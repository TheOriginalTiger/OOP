import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer extends Thread {
    ArrayBlockingQueue<Order> orders;
    int amount;
    Synchronizer sync;

    public Producer(ArrayBlockingQueue<Order> orders, int amount,Synchronizer sync)
    {
        this.orders = orders;
        this.amount = amount;
        this.sync = sync;
    }

    @Override
    public void run()
    {

        for (int i =0 ; i < amount; i++)
        {
            int r1 = ThreadLocalRandom.current().nextInt(100, 10000);
            int r2 = ThreadLocalRandom.current().nextInt(100, 10000);
            int r3 = ThreadLocalRandom.current().nextInt(100, 5000);
            Order o = new Order(i, r1,r2);
            orders.add(o);
            try
            {
                //TODO somehow use experience
                Thread.sleep(r3);
            }
            catch (InterruptedException e)
            {
                System.out.println("producer was interrupted");
            }
        }
        sync.setFalse();
    }

}

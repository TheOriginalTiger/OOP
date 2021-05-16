import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

public class Main {

    public static void main(String[] args)
    {

        Warehouse wh = new Warehouse(228);
        Baker[] bakers = new Baker[5];
        for(int i = 0; i < bakers.length; i++)
        {
            bakers[i] = new Baker(i,5);
        }

        Courier[] couriers = new Courier[1];
        for(int i = 0; i < couriers.length; i++)
        {
            couriers[i] = new Courier(i,1);
        }

        ArrayBlockingQueue<Order> orders = new ArrayBlockingQueue<Order>(15);

        Synchronizer s = new Synchronizer();

        Bakery bk = new Bakery(bakers, couriers, orders, wh, s);
        Producer pr = new Producer(orders, 10, s);


        bk.start();
        pr.start();
        try
        {
            bk.join();
        }
        catch (InterruptedException e) {System.out.println("hui");}


    }


}

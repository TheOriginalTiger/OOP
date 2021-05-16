import java.util.Arrays;
import java.util.Iterator;

public class CourierThread extends Thread{
    final Courier courier;
    private volatile boolean ready;

    private Order[] orders;
    volatile int ordersCap;

    public CourierThread(Courier courier)
    {
        this.courier = courier;
        ready = true;
        orders = new Order[courier.trunkSize];
    }

    public synchronized boolean isReady()
    {
        return ready;
    }

    public synchronized void setNotReady()
    {
        ready = false;
    }
    public synchronized void setOrders(Order[] orders, int actualCapacity)
    {
        if (orders.length > courier.trunkSize)
            throw new IllegalArgumentException("the length is incorrect");
        this.orders = orders.clone();
        ordersCap = actualCapacity;
    }

    public synchronized Order[] getOrders()
    {
        return orders;
    }

    public synchronized int getCap()
    {
        return ordersCap;
    }

    public void run()
    {
        ready = false;
        courier.deliver(orders, ordersCap);
        ready = true;
    }
}

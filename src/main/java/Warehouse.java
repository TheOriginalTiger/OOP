import java.util.ArrayDeque;

public class Warehouse {
    int maxSize;
    ArrayDeque<Order> ordersQ;
    int currCap;

    public Warehouse(int maxSize)
    {
        this.maxSize = maxSize;
        ordersQ = new ArrayDeque<Order>(maxSize);
        currCap = 0 ;
    }

    public boolean isNotFull()
    {
        if (maxSize <= ordersQ.size())
            return false;
        return true;
    }
    public boolean addOrder(Order o)
    {
        if (isNotFull())
        {
            ordersQ.addLast(o);
            currCap++;
            return true;
        }
        return false;
    }

    public int getCurrCap()
    {
        return currCap;
    }
    public Order getOrder()
    {
        if (ordersQ.size() > 0 )
        {
            currCap--;
            return ordersQ.pollFirst();
        }
        else
            return null;
    }

}

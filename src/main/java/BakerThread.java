public class BakerThread extends Thread{
    final Baker b;
    private boolean ready;
    private Order currOrder;

    public BakerThread(Baker baker)
    {
        this.b = baker;
        ready = true;
        currOrder = null;
    }

    public boolean isReady()
    {
        return ready;
    }


    public synchronized void setNotReady()
    {
        ready = false;
    }

    public synchronized void setCurrOrder(Order o)
    {
        currOrder = o;
    }
    public synchronized Order getCurrOrder()
    {
        return currOrder;
    }

    @Override
    public void run()
    {
        ready = false;
        b.cook(currOrder);
        ready = true;
    }
}

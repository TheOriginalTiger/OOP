import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;

import static java.util.concurrent.TimeUnit.MILLISECONDS;


//consumer
public class Bakery extends Thread{
    private Warehouse wh;
    private ArrayBlockingQueue<Order> orders;
    Baker[] bakers;
    Courier[] couriers;
    /*
     вообще если бы мне правда хотелось заниматься чем то подобным,
     вместо обычного массива тут чешется иметь что то вроде дерева, с возможностью
     блокироваться при отсутствии элементов т.к. нам наверняка хочется чтобы самые
     опытные повара и самые быстрые курьеры занимались только самыми объёмными заказами
     и самыми дальними доставками. Но поскольку это просто курс по джаве, а от сформулированной
     задачи явно пахнет задачей на оптимизацию с весьма печальными оценками сложностей,
     будем иметь обычный массив и радоваться жизни
    */
    private ArrayBlockingQueue<BakerThread> bakersQueue;
    private ArrayBlockingQueue<CourierThread> couriersQueue;


    private final Synchronizer synch;

    public Bakery(Baker[] bakers, Courier[] couriers, ArrayBlockingQueue<Order> orders,Warehouse wh, Synchronizer synch)
    {
        this.orders = orders;
        this.wh = wh;
        this.synch = synch;
        this.bakers = bakers.clone();
        this.couriers = couriers.clone();

        bakersQueue = new ArrayBlockingQueue<BakerThread>(bakers.length);
        couriersQueue = new ArrayBlockingQueue<CourierThread>(couriers.length);

        for(int i =0 ; i < bakers.length; i++)
        {
            bakersQueue.add(new BakerThread(bakers[i]));
        }

        for(int i =0 ; i < couriers.length; i++)
        {
            couriersQueue.add(new CourierThread(couriers[i]));
        }
    }

    @Override
    public void run()
    {
        System.out.println("started");
        ArrayList<BakerThread> busyBakers = new ArrayList<BakerThread>(bakers.length);

        ArrayList<CourierThread> busyCouriers = new ArrayList<CourierThread>(couriers.length);

        while (synch.val || busyBakers.size() > 0 || busyCouriers.size() > 0 || orders.peek() != null || wh.currCap != 0 )
        {

                //1. отправить готовиться новые заказы
                //2. отправить на склад готовые
                //3. отправить курьеров с готовыми

                BakerThread baker = bakersQueue.peek();
                Order currOrder = orders.peek();
                while(baker != null && currOrder != null)
                {
                    baker = bakersQueue.poll();
                    currOrder = orders.poll();
                    System.out.format("Order %d is received\n", currOrder.id);

                    baker.setCurrOrder(currOrder);
                    busyBakers.add(baker);
                    baker.setNotReady();
                    baker.start();

                    baker = bakersQueue.peek();
                    currOrder = orders.peek();
                }

                //getting ready orders to warehouse

                if (!busyBakers.isEmpty())
                {

                    for(int i = 0; i < busyBakers.size(); i++)
                    {
                        BakerThread b = busyBakers.get(i);
                        if(!wh.isNotFull())
                        {
                            break;
                        }
                        if (b.isReady())
                        {
                            wh.addOrder(b.getCurrOrder());
                            System.out.format("Order %d, is cooked\n", b.getCurrOrder().id);

                            bakersQueue.add(new BakerThread(b.b));
                            busyBakers.remove(b);
                            i--;
                        }
                    }
                }

                CourierThread courier = couriersQueue.peek();
                Order ord;
                while(courier != null && wh.getCurrCap() !=0)
                {
                    courier = couriersQueue.poll();
                    Order[] arr = new Order[Math.min(courier.courier.trunkSize, wh.getCurrCap())];
                    int counter = 0;
                    for( ; counter < courier.courier.trunkSize; counter++)
                    {
                        ord = wh.getOrder();
                        arr[counter] = ord;
                        System.out.format("Order %d, was picked by courier\n", ord.id);
                        if(wh.getCurrCap() == 0)
                            break;
                    }
                    courier.setOrders(arr,arr.length);
                    busyCouriers.add(courier);
                    courier.setNotReady();
                    courier.start();
                    courier = couriersQueue.peek();
                }
                if(!busyCouriers.isEmpty())
                {
                    for( int i = 0; i < busyCouriers.size(); i++)
                    {
                        CourierThread c = busyCouriers.get(i);
                        if(c.isReady())
                        {
                            Order[] cOrders = c.getOrders();
                            for (int j = 0; j < c.getCap();j++)
                            {
                                System.out.format("Order %d is delivered\n",cOrders[j].id);
                            }
                            couriersQueue.add(new CourierThread(c.courier));
                            busyCouriers.remove(c);
                            i--;
                        }
                    }
                }
               // System.out.println(busyCouriers.size());


        }
    }


}

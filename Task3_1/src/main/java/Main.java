public class Main {

    public static void main(String args[])
    {
        int[] d = {17, 12, 2020};
        GregorianDate date = new GregorianDate(d);
        date.getClosestFridayThirteen().printHumanDate();

    }

}



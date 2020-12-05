public class Main {

    public static void main(String args[])
    {
        int[] d = {14,8,2021};

        GrigorianDate date = new GrigorianDate(d);
        date.getClosestFridayThirteen().printHumanDate();
       // System.out.print(date.getDayOfTheWeek());
    }

}



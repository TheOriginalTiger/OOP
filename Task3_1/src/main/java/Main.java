public class Main {

    public static void main(String args[])
    {
        int[] d = {6, 12, 2020};
        GregorianDate date = new GregorianDate(d);

        int[] heh = {31,12,2020};
        GregorianDate newYear = new GregorianDate(heh);
        newYear = newYear.subtractDates(date);

    }

}



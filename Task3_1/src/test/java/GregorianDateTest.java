import org.junit.Test;

import static org.junit.Assert.*;

public class GregorianDateTest {

    @Test
    public void question1()
    {
        GregorianDate date = new GregorianDate(new int[] {6, 12, 2020});
        date.addDays(1024);
        assertArrayEquals(new int[]{26, 9, 2023}, date.getHumanDate());
        assertEquals(Day.TUESDAY, date.getDayOfTheWeek());
    }

    @Test
    public void question2()
    {
        GregorianDate date = new GregorianDate(new int[] {6, 12, 2020});

        int[] endOfWarDays = {9,5,1945};
        GregorianDate endOfWarDate = new GregorianDate(endOfWarDays);

        GregorianDate sub = date.subtractDates(endOfWarDate);
        assertArrayEquals(new int[] {29, 6, 75 }, sub.getHumanDate());
    }

    @Test
    public void question3()
    {
        GregorianDate date = new GregorianDate(new int[] {29, 5, 2001});

        assertEquals(Day.TUESDAY, date.getDayOfTheWeek());
    }

    @Test
    public void question4()
    {
        GregorianDate date = new GregorianDate(new int[] {6, 12, 2020});

        date.addDays(17 * 7);

        assertEquals(4, date.getHumanDate()[1]);
    }

    @Test
    public void question5()
    {
        GregorianDate date = new GregorianDate(new int[] {6, 12, 2020});

        GregorianDate newYear = new GregorianDate(new int[] {31,12,2020});
        GregorianDate diff = newYear.subtractDates(date);

        assertEquals(24, diff.getDays());

    }

    @Test
    public void question6()
    {
        GregorianDate date = new GregorianDate(new int[] {6, 12, 2020});
        GregorianDate friday = date.getClosestFridayThirteen();
        assertArrayEquals(new int[] {13,8,2021}, friday.getHumanDate());
    }


}
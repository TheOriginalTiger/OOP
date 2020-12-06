import org.jetbrains.annotations.NotNull;

import static java.lang.Math.abs;

enum Day{
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY,
    NOTaDAY
}

public class GregorianDate {

    private int days;
    private int[] humanDate;

    private boolean isLeap(int year)
    {
        if ( ( year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0 )
        {
            return true;
        }
        return false;
    }

    private int monthLong(int month, int year)
    {
        if (month == 4 || month == 6 || month == 9 || month == 11 )
        {
            return 30;
        }
        else if (month == 2)
        {
            if (isLeap(year))
            {
                return 29;
            }
            else
            {
                return 28;
            }
        }
        else
        {
            return 31;
        }
    }

    private int convertFromArrayToDays(int[] date)
    {
        int d = 0 ;
        for(int i = date[2] - 1; i > 0; i--)
        {
            if ( isLeap(i) )
            {
                d += 366;
            }
            else
            {
                d += 365;
            }
        }

        for (int i = date[1] - 1 ; i > 0; i-- )
        {
            d += monthLong(i, date[2]);
        }

        d += date[0];
        return d;
    }

    private int yearHandler(int year)
    {
        if (isLeap(year))
        {
            return 366;
        }
        else
        {
            return 365;
        }
    }

    private int[] convertFromDaysToArray(int days)
    {
        int years = 1;
        int months = 1;
        while (days > yearHandler(years))
        {
            if (isLeap(years))
            {
                days -= 366;
            }
            else
            {
                days -= 365;
            }
            years++;
        }

        while (days > monthLong(months, years))
        {
            days -= monthLong(months, years);
            months++;
        }


        return new int[] {days, months, years};

    }

    /**
     * constructs a date in gregorian calendar from int array
     * @param date must be [days, months, years]
     * if the array is different will throw IllegalArgumentException
     */
    public GregorianDate(@NotNull int[] date) {
        if (date.length != 3 || date[0] < 0 || date[1] > 12 || monthLong(date[1], date[2]) < date[0] )
        {
            throw new IllegalArgumentException("wrong date params");
        }
        days = convertFromArrayToDays(date);
        humanDate = date.clone();
    }

    /**
     * construct a date in gregorian calendar from total number of days
     * since the start of counting
     * @param days number of days
     */
    public GregorianDate(int days)
    {
        if (days < 0)
        {
            throw new IllegalArgumentException("wrong days params");
        }
        this.days = days;
        humanDate = convertFromDaysToArray(days);
    }

    private Day getDayOfTheWeek(int days)
    {
       return  switch (days % 7 )
               {
                   case(0) -> Day.SUNDAY;
                   case(1) -> Day.MONDAY;
                   case(2) -> Day.TUESDAY;
                   case(3) -> Day.WEDNESDAY;
                   case(4) -> Day.THURSDAY;
                   case(5) -> Day.FRIDAY;
                   case(6) -> Day.SATURDAY;
                   default -> Day.NOTaDAY;
               };
    }

    public Day getDayOfTheWeek()
    {
        return getDayOfTheWeek(days);
    }

    /**
     * adds 'days' number of days to current date
     * @param days number of days to add
     */
    public void addDays(int days)
    {
        this.days += days;
        humanDate = convertFromDaysToArray(this.days);
    }

    /**
     * adds 'months' number of months to the current date.
     * As it is not quite certain how ho add a month to the date
     * the function actually converts all months to days,
     * considering amount of days int the current month and incrementing it
     * and then builds the new date from the number of days
     * because of this behaviour it doesn't create 31 of the june and stuff like that
     * @param months number of months to add
     */
    public void addMonths(int months)
    {
        int days = 0;
        int currMonth = humanDate[1];
        int currYear = humanDate[2];
        for(int i = 0; i < months; i++)
        {
            if (currMonth > 12)
            {
                currYear++;
                currMonth = 1;
            }
            days += monthLong(currMonth, currYear);
            currMonth++;
        }
        addDays(days);
    }

    /**
     * adds 'years' number of years to current date
     * behaves exactly like addMonths func but now it converts
     * years to number of days in 'em, considering whether the year is Leap
     * @param years number of years to add
     */
    public void addYears(int years)
    {
        int days = 0;
        int currYear = humanDate[2];
        for (int i = 0 ; i < years; i++)
        {
            days += yearHandler(currYear);
            currYear++;
        }
        addDays(days);
    }

    /**
     * chooses the older date from argument and the current one
     * and subtract lower one from it
     * its not recommended to use that date in further calculations
     * as it has zero days and months while its not natural for gregorian calendar
     * @param date to subtract
     * @return result of subtraction
     */

    public GregorianDate subtractDates(GregorianDate date)
    {

        int days = abs(this.days - date.getDays()) ;
        // I dont know why, I dont want to know why,
        // I dont have to know why, but for some motherfucking reason
        // I keep having the result of subtracting two dates
        // higher than I need by exactly one
        // that's why we have to do this terribleness
        int[] tmp = convertFromDaysToArray(days);
        tmp[0] -= 1;
        tmp[1] -= 1;
        tmp[2] -= 1;
        return new GregorianDate(tmp);
    }

    /**
     * find the closest 13th friday. ¯\_(ツ)_/¯
     * @return date of the closest 13th friday
     */
    public GregorianDate getClosestFridayThirteen()
    {
        int[] myDate = humanDate.clone();
        int myDays = days;
        while(!(myDate[0] == 13 && getDayOfTheWeek(myDays) == Day.FRIDAY) ) {

            myDays = myDays + (monthLong(myDate[1], myDate[2]) - myDate[0]) + 13;
            myDate[0] = 13;
            if (myDate[1] == 12) {
                myDate[1] = 1;
                myDate[2]++;
            }
            else
            {
                myDate[1]++;
            }
            GregorianDate tmp = new GregorianDate(myDays);
        }
        return new GregorianDate(myDate);
    }

    public int[] getHumanDate()
    {
        return humanDate.clone();
    }

    public int getDays()
    {
        return days;
    }

    /**
     * exists for debug purposes as it is tiresome to
     * always do that for(...) with date.getHumanDate()[i] shit
     */
    public void printHumanDate()
    {
        for(int i = 0 ; i < 3; i++)
        {
            System.out.print(humanDate[i]);
            System.out.print(" ");
        }
    }
}

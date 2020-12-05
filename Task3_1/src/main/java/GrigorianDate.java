import org.jetbrains.annotations.NotNull;

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

public class GrigorianDate {

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

    public int convertFromArrayToDays(int[] date)
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

    public int[] convertFromDaysToArray(int days)
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

    public GrigorianDate(@NotNull int[] date)
    {
        if (date[0] < 0 || date[1] > 12 || monthLong(date[1], date[2]) < date[0])
            throw new IllegalArgumentException("wrong date params");
        days = convertFromArrayToDays(date);
        humanDate = date.clone();
    }
    public GrigorianDate(int days)
    {
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
    public void addDays(int days)
    {
        this.days += days;
        humanDate = convertFromDaysToArray(this.days);
    }

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
    // I dont know why, I dont want to know why,
    // I dont have to know why, but for some motherfucking reason
    // I keep having the result of subtracting two dates
    // higher than I need by exactly one
    // that's why we have to do this terribleness
    public GrigorianDate subtractDates(GrigorianDate date)
    {
        int days = this.days - date.getDays() ;
        int[] tmp = convertFromDaysToArray(days);
        tmp[0] -= 1;
        tmp[1] -= 1;
        tmp[2] -= 1;
        return new GrigorianDate(tmp);
    }

    public GrigorianDate getClosestFridayThirteen()
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
            GrigorianDate tmp = new GrigorianDate(myDays);
        }
        return new GrigorianDate(myDate);
    }

    public int[] getHumanDate()
    {
        return humanDate.clone();
    }

    public int getDays()
    {
        return days;
    }

    public void printHumanDate()
    {
        for(int i = 0 ; i < 3; i++)
        {
            System.out.print(humanDate[i]);
            System.out.print(" ");
        }
    }
}

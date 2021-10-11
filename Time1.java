/**
 * Represents time - hours:minutes.
 * @author Ramy Bachayev
 * @version 12/4/2020
 */

public class Time1
{   //instance variables:
    private int _hour;//represents hour in a day
    private int _minute;//represents minute in a day

    //defining final variables:
    public final int MIN_TIME=0;//min minute or hour
    public final int MAX_HOUR=23;//max hour in one day
    public final int MAX_MINUTE=59;//max minute in one hour
    public final int ONE_HOUR=60;//represents one hour in minutes
    public final int ONE_DAY=1440;//represents one day in minutes

    //constructors:
    /**
     * Constructs a Time1 object.
     * Construct a new time instance with the specified hour and minute.
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise they should be set to 0.
     * @param h the hour in a day
     * @param m the minute in a day
     */

    public Time1(int h, int m)
    {//calculation of time
        if((h>=MIN_TIME)&&(h<=MAX_HOUR))
            _hour=h;
        else//if hour received an illegal number so hour will be 0
            _hour=MIN_TIME;

        if((m>=MIN_TIME)&&(m<=MAX_MINUTE))
            _minute=m;
        else//if minute received an illegal number so minute will be 0
            _minute=MIN_TIME;       
    } 

    /**
     * Copy constructor for Time1.
     * Constructs a time with the same variables as another time.
     * @param other The time object from which to construct the new time
     */

    public Time1(Time1 other)
    {//creates a copy of this time
        _hour= other._hour;
        _minute= other._minute;
    }

    /**
     * Returns the hour of the time
     * @return the hour of the time
     */

    public int getHour()
    {
        return _hour;   
    }

    /**
     * Returns the minutes of the time
     * @return the minutes of the time
     */

    public int getMinute()
    {
        return _minute;
    }

    /**
     * Changes the hour of the time.
     * If an illegal number is received hour will remain unchanged
     * @param num the value of the new hour 
     */

    public void setHour(int num)
    {//sets a new hour in time
        if((num>=MIN_TIME)&&(num<=MAX_HOUR))
            _hour= num;       
    }

    /**
     * Changes the minute of the time.
     * If an illegal number is received minute will remain unchanged.
     * @param num the value of the new minute
     */

    public void setMinute(int num)
    {//sets a new minutes in time
        if((num>=MIN_TIME)&&(num<=MAX_MINUTE))
            _minute= num;
    }

    /**
     * Returns a string representation of this time ("hh:mm")
     * @return a string representation of this time ("hh:mm")
     */

    public String toString()
    {//organizing the time that will be hh:mm
        String s="";//defining String s - represents the string ("hh:mm")

        if(_hour<10)//if hour 0-9 so it will be 00-09
            s+="0";

        s+=_hour;
        s+=":";

        if(_minute<10)//if minute 0-9 so it will be 00-09
            s+="0";

        s+=_minute;

        return s;
    }

    /**
     * Returns the amount of minutes since midnight
     * @return the amount of minutes since midnight (original time doesn't change)
     */

    public int minFromMidnight()
    {//calculation of time that will represent minutes from midnight
        int fromMidnight= (_hour*ONE_HOUR)+_minute;//defining variable fromMidnight
        return fromMidnight;
    }

    /**
     * Checks if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return true if both times are equal 
     */

    public boolean equals (Time1 other)
    {
        return (_hour==other._hour)&&(_minute==other._minute);
    }

    /**
     * Checks if this time is before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before the other time  
     */

    public boolean before (Time1 other)
    { 
        if((other._hour>_hour)||((other._minute>_minute)&&(other._hour==_hour)))
            return true; 

        return false;
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after the other time
     */

    public boolean after (Time1 other)
    {//using the opposite method - before
        return other.before(this);
    }

    /**
     * Calculates the difference (in minutes) between two times.
     * @param other The time to check the difference with.
     * Assumption: this time is after other time
     * @return the difference between two times
     */

    public int difference(Time1 other)
    {//calculation of the difference between two times         
        int t1=(_hour*ONE_HOUR)+_minute;//time 1 calculation 
        int t2=(other._hour*ONE_HOUR)+other._minute;//time 2 calculation      

        return t1-t2;
    }

    /**
     * Adds num Minutes to time.
     * @param num The number of minutes to add
     * @return the update time (original time doesn't change) 
     */

    public Time1 addMinutes(int num)
    {   
        int x=num/ONE_DAY;//defining variable x- check if the added minutes more than one day

        if(x!=0)//calculation of num- if added minutes bigger than (+/-)one day
            num=num-(x*ONE_DAY);

        int timeInMinute=(_hour*ONE_HOUR)+_minute;//defining variable timeInMinute + calculation         
        int finalTime=MIN_TIME;//defining variable finalTime

        //calculate the new time by one of the following possibilities:
        if((timeInMinute+num)>ONE_DAY)
            finalTime=num-(ONE_DAY-timeInMinute);

        if((timeInMinute+num)<MIN_TIME)
            finalTime=ONE_DAY+(num+timeInMinute);

        if(((timeInMinute+num)<ONE_DAY)&&((timeInMinute+num)>MIN_TIME))
            finalTime=timeInMinute+num;    

        if(((timeInMinute+num)==MIN_TIME)||((timeInMinute+num)==ONE_DAY))
            finalTime=MIN_TIME;

        //defining time t = old time + added minutes
        Time1 t= new Time1(finalTime/ONE_HOUR,finalTime%ONE_HOUR);

        return t;
    }
}    
/**
 * Represents time by the minutes from midnight.
 * Values must represent a proper time.
 * @author Ramy Bachayev
 * @version 12/4/2020
 */

public class Time2
{   //instance variables:
    private int _minFromMid;//represents minutes from midnight

    //defining final variables:
    public final int MIN_TIME=0;//min minute or hour
    public final int MAX_HOUR=23;//max hour in one day
    public final int MAX_MINUTE=59;//max minute in one hour
    public final int ONE_HOUR=60;//represents one hour in minutes
    public final int ONE_DAY=1440;//represents one day in minutes

    //constructors:
    /**
     * Constructs a Time2 object.
     * Construct a new time instance with the specified hour and minute.
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise they should be set to 0.
     * @param h the hour in a day
     * @param m the minute in a day
     */

    public Time2(int h, int m)
    {//calculation of time 
        if((h>=MIN_TIME)&&(h<=MAX_HOUR)&&(m>=MIN_TIME)&&(m<=MAX_MINUTE))
            _minFromMid=(h*ONE_HOUR)+m; 

        else//if time received an illegal number so time will be 0
            _minFromMid=MIN_TIME;   
    } 

    /**
     * Copy constructor for Time2.
     * Constructs a time with the same variables as another time.
     * @param other The time object from which to construct the new time
     */

    public Time2(Time2 other)
    {//creates a copy of this time
        _minFromMid= other._minFromMid;
    }

    /**
     * Returns the hour of the time
     * @return the hour of the time (original time doesn't change)
     */

    public int getHour()
    {//calculation of the hour 
        int hour=_minFromMid/ONE_HOUR;//defining variable hour        
        return hour;   
    }

    /**
     * Returns the minutes of the time
     * @return the minutes of the time (original time doesn't change)
     */

    public int getMinute()
    {//calculation of the minutes 
        int minute=_minFromMid%ONE_HOUR;//defining variable minute        
        return minute;
    }

    /**
     * Changes the hour of the time.
     * If an illegal number is received hour will remain unchanged
     * @param num the value of the new hour 
     */

    public void setHour(int num)
    {//sets a new hour in time 
        if((num>=MIN_TIME)&&(num<=MAX_HOUR))
            _minFromMid=(_minFromMid%ONE_HOUR)+(num*ONE_HOUR);//calculation of the new hour       
    }

    /**
     * Changes the minute of the time.
     * If an illegal number is received minute will remain unchanged.
     * @param num the value of the new minute
     */

    public void setMinute(int num)
    {//sets a new minutes in time
        if((num>=MIN_TIME)&&(num<=MAX_MINUTE))
        {
            _minFromMid-=(_minFromMid%ONE_HOUR);//restart the minutes from 0, hour doesn't change
            _minFromMid+=num;//calculation of the new minutes
        }
    }

    /**
     * Returns a string representation of this time ("hh:mm")
     * @return a string representation of this time ("hh:mm")
     */

    public String toString()
    {//organizing the time that will be hh:mm
        String s="";//defining String s - represents the string ("hh:mm")

        if((_minFromMid/ONE_HOUR)<10)//if hour 0-9 so it will be 00-09
            s+="0";

        s+=(_minFromMid/ONE_HOUR);
        s+=":";

        if((_minFromMid%ONE_HOUR)<10)//if minute 0-9 so it will be 00-09
            s+="0";

        s+=(_minFromMid%ONE_HOUR);

        return s;       
    }

    /**
     * Returns the amount of minutes since midnight
     * @return the amount of minutes since midnight
     */

    public int minFromMidnight()
    {       
        return _minFromMid;
    }

    /**
     * Checks if the received time is equal to this time.
     * @param other The time to be compared with this time
     * @return true if both times are equal 
     */

    public boolean equals (Time2 other)
    { 
        return _minFromMid==other._minFromMid;
    }

    /**
     * Checks if this time is before a received time.
     * @param other The time to check if this time is before
     * @return true if this time is before the other time  
     */

    public boolean before (Time2 other)
    {
        if(other._minFromMid>_minFromMid)
            return true; 

        return false;
    }

    /**
     * Check if this time is after a received time.
     * @param other The time to check if this time is after
     * @return true if this time is after the other time
     */

    public boolean after (Time2 other)
    {//using the opposite method - before
        return other.before(this);
    }

    /**
     * Calculates the difference (in minutes) between two times.
     * @param other The time to check the difference with.
     * Assumption: this time is after other time
     * @return the difference between two times
     */

    public int difference(Time2 other)
    {//calculation of the difference between two times
        return _minFromMid-other._minFromMid; 
    }

    /**
     * Adds num Minutes to time.
     * @param num The number of minutes to add
     * @return the update time (original time doesn't change)  
     */

    public Time2 addMinutes(int num)
    {        
        int x=num/ONE_DAY;//defining variable x- check if the added minutes more than one day

        if(x!=0)//calculation of num- if added minutes bigger than (+/-)one day
            num=num-(x*ONE_DAY);

        int finalTime=MIN_TIME;//defining variable finalTime

        //calculate the new time by one of the following possibilities:
        if(((_minFromMid+num)<ONE_DAY)&&((_minFromMid+num)>MIN_TIME))
            finalTime=_minFromMid+num;

        if((_minFromMid+num)>ONE_DAY)
            finalTime=num-(ONE_DAY-_minFromMid);

        if((_minFromMid+num)<MIN_TIME)
            finalTime=ONE_DAY+(num+_minFromMid);

        if(((_minFromMid+num)==MIN_TIME)||((_minFromMid+num)==ONE_DAY))
            finalTime=MIN_TIME;

        //defining time t = old time + added minutes    
        Time2 t= new Time2(finalTime/ONE_HOUR,finalTime%ONE_HOUR);

        return t;
    }
}    
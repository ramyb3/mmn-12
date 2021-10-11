/**
 * Represents a Train
 * @author Ramy Bachayev
 * @version 12/4/2020
 */

public class Train
{   //instance variables:
    private String _destination;//represents the destination of the train 
    private Time1 _departure;//represents the departure time in - hh:mm
    private int _duration;//represents the duration of the train in minutes 
    private int _passengers;//represents the passengers in the train
    private int _seats;//represents the seats in the train
    private int _price;//represents the price for one passenger  
    
    //defining final variable:
    public final int MINIMUM=0; 

    //constructors:
    /**
     * Constructor of class Train, constructs a new train.
     * if h and/or m will not be in the range that defined in Time1 class, this will be handled in Time1 class. 
     * duration should be positive, otherwise it should be set to 0.
     * pass should be positive, otherwise it should be set to 0.
     * pass should be less than seats otherwise it should be set to seats.
     * seats should be positive, otherwise it should be set to 0.
     * price should be positive, otherwise it should be set to 0. 
     * @param dest the destination of the train
     * @param h the hour of departure
     * @param m the minute of departure  
     * @param duration the duration of the travel
     * @param pass the number of passeners
     * @param seats the number of seats in the train
     * @param price the price of the travel
     */

    public Train(String dest, int h, int m, int duration,
    int pass, int seats, int price)  
    {//defining a train
        if(pass>seats)
            pass=seats;

        _passengers=pass;
        _seats=seats;

        if(pass<MINIMUM)
            _passengers=MINIMUM;
        else
            _passengers=pass;    

        if(seats<MINIMUM)
            _seats=MINIMUM;
        else
            _seats=seats;

        if(duration<MINIMUM)
            _duration=MINIMUM;
        else
            _duration=duration;

        if(price<MINIMUM)
            _price=MINIMUM;
        else
            _price=price;

        _destination= dest;    
        _departure= new Time1(h, m);//uses the time constructor in Time1 class
    }

    /**
     * Copy constructor for Train.
     * Construct a train with the same instance variables as another train.
     * @param other The train object from which to construct the new train
     */

    public Train(Train other)
    {//creates a copy of this train
        _destination=other._destination;
        _departure= new Time1(other._departure);//uses the copy constructor in Time1 class
        _duration=other._duration;  
        _passengers=other._passengers;
        _seats=other._seats;
        _price=other._price;         
    }  

    /**
     * Returns the arrival time
     * @return the arrival time (original times doesn't change)
     */

    public Time1 getArrivalTime()
    {//creating copies of _duration and _departure to avoid Aliasing 
        Time1 departureNew= new Time1(_departure);//using copy constructor in Time1 class
        int durationNew=_duration;//creates a copy of _duration

        return departureNew.addMinutes(durationNew);//calculation of the ArrivalTime    
    }//using the method addMinutes in Time1 class

    /**
     * Returns the departure time
     * @return the departure time
     */

    public Time1 getDeparture() 
    {        
        return _departure;
    } 

    /**
     * Returns the destination
     * @return the destination of the train
     */

    public String getDestination()
    {
        return _destination;      
    }

    /**
     * Returns the duration of the train
     * @return the duration of the train 
     */

    public int getDuration()
    {
        return _duration;
    }

    /**
     * Returns the number of passengers
     * @return the number of passengers
     */

    public int getPassengers()
    {
        return _passengers;   
    }

    /**
     * Returns the price of the train
     * @return the price of the train
     */

    public int getPrice()
    {
        return _price;
    }

    /**
     * Returns the number of seats
     * @return the number of seats
     */

    public int getSeats()
    {
        return _seats;
    }

    /**
     * Updates the departure time of the train. t in not null. 
     * @param t the new departure time of the train
     */

    public void setDeparture(Time1 t)
    {//defining a new train departure time by using Time1 class
        _departure= new Time1(t);
    }

    /**
     * Updates the destination of the train. d in not null.
     * @param d the new detination of the train
     */

    public void setDestination(String d)
    {        
        _destination=d;
    }

    /**
     * Updates the duration of the train.
     * d should be positive or zero, otherwise duration is unchanged
     * @param d the new duration of the train
     */

    public void setDuration(int d)
    {
        if(d>=MINIMUM)
            _duration=d;
    }

    /**
     * Updates the number of passengers.
     * p should be positive or zero, otherwise passengers is unchanged.
     * p should be less than seats otherwise it should be set to seats.
     * @param p the new number of passengers
     */

    public void setPassengers(int p)
    {
        if(p>=MINIMUM)
            _passengers=p;

        if(p>_seats)
            _passengers=_seats;  
    }

    /**
     * Updates the price. p should be positive or zero, otherwise price is unchanged.
     * @param p the new price
     */

    public void setPrice(int p)
    {
        if(p>=MINIMUM)
            _price=p;
    }

    /**
     * Updates the number of seats.
     * s should be positive or zero, otherwise seats is unchanged.
     * s should be larger or equal than passengers, otherwise seats is unchanged.
     * @param s the new number of seats
     */

    public void setSeats(int s)
    {
        if((s>=MINIMUM)&&(s>=_passengers))
            _seats=s;
    }  

    /**
     * Return a string representation of the train.
     * If train is full the string will be:
     * Train to "destination" departs at "departure". Train is full.
     * If train is not full the string will be:
     * Train to "destination" departs at "departure". Train is not full.
     * @return String representation of the train
     */

    public String toString()
    {//using the method isFull()  
        if(isFull()==true)
            return "Train to "+_destination+" departs at "+_departure+". Train is full.";

        return "Train to "+_destination+" departs at "+_departure+". Train is not full.";
    }

    /**
     * Add num passengers to the train.
     * If num and passengers are bigger the seats, passengers will not change. 
     * @param num The number of passengers to add, num is positive
     * @return True if the total number of current passengers and num is less or equal to seats
     */

    public boolean addPassengers(int num)
    {
        if(num+_passengers<=_seats)
        {
            setPassengers(num+_passengers);//using the method setPassengers
            return true;
        }
        return false;
    }

    /**
     * Returns true if the price for this train is cheaper than the other train.
     * other is not null. 
     * @param other the other train to compare price with
     * @return true if the price for this train is cheaper than the other train
     */

    public boolean isCheaper(Train other)
    {
        return this._price<other._price;  
    }

    /**
     * Check if the received train is equal to this train.
     * The two trains are compared in: destination, seats, departure time
     * @param other The train to be compared with this train
     * @return True if the received train is equal to this train
     */

    public boolean equals(Train other)
    {//comparison of both specific attributes of the 2 trains 
        return (_destination.equals(other._destination))&&(_seats==other._seats)&&
        (_departure.equals(other._departure)); 
    }//departure comparison is using the method equals in Time1 class 

    /**
     * Returns true if train is full (all seats are occupied).
     * @return true if train is full
     */

    public boolean isFull()
    {
        if(_passengers==_seats)
            return true;

        return false;
    }

    /**
     * Returns true if the arrival time of this train is earlier than the arrival time of the other train.
     * other is not null.
     * @param other the other train to compare arrival time with
     * @return true if the arrival time of this train is earlier than arrival time of the other train
     */

    public boolean arrivesEarlier(Train other)
    {//using the method before in Time1 class 
        return  (this.getArrivalTime()).before(other.getArrivalTime());  
    }

    /**
     * Returns the total price for all passengers.
     * @return the total price for all passengers 
     */

    public int totalPrice()
    {//calculation of the totalPrice
        return _price*_passengers;
    }
}  
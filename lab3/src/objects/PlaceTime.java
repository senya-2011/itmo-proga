package objects;

import Enums.Time;

public class PlaceTime {

    public static void setTime(Time time){
        if (time == Time.Night){
            System.out.print("в ночь");
        }else if (time == Time.TimeByTime){
            System.out.print(" время от времени");
        }else if (time == Time.NotForAMoment){
            System.out.print("ни на мгновенье");
        }
    }
}

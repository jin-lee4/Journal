package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//Returns date in string format
public class Date {

    //MODIFIES: time
    //EFFECTS: returns date as a string in format "yyyy/mm/dd hr:sec AM/PM"
    public String dateToString(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm a");
        return date.format(formatter);
    }
}

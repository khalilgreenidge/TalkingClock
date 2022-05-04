package com.khalilgreenidge.app.TalkingClock;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.ws.rs.BadRequestException;
import java.util.HashMap;

@Service
public class TimeService {

    public String translate(String payload) throws BadRequestException {
        String time = "";
        int hours;
        int mins;

        if (payload.length() > 5)
            throw new BadRequestException("Time must be only 5 characters.");


        if (!payload.contains(":"))
            throw new BadRequestException("Time must contain a :");



        String []timeArray = payload.split(":");

        try{
            hours = Integer.parseInt(timeArray[0]);
            mins = Integer.parseInt(timeArray[1]);
        }
        catch (Exception e){ throw new BadRequestException("Time must contain numbers");}


        HashMap<Integer,String> numToWords = new HashMap<>();
        numToWords.put(1, "one");
        numToWords.put(2, "two");
        numToWords.put(3, "three");
        numToWords.put(4, "four");
        numToWords.put(5, "five");
        numToWords.put(6, "six");
        numToWords.put(7, "seven");
        numToWords.put(8, "eight");
        numToWords.put(9, "nine");
        numToWords.put(10, "ten");
        numToWords.put(11, "eleven");
        numToWords.put(12, "twelve");
        numToWords.put(13, "thirteen");
        numToWords.put(14, "fourteen");
        numToWords.put(15, "fifteen");
        numToWords.put(16, "sixteen");
        numToWords.put(17, "seventeen");
        numToWords.put(18, "eighteen");
        numToWords.put(19, "nineteen");
        numToWords.put(20, "twenty");
        numToWords.put(21, "twenty one");
        numToWords.put(22, "twenty two");
        numToWords.put(23, "twenty three");
        numToWords.put(24, "twenty four");
        numToWords.put(25, "twenty five");
        numToWords.put(26, "twenty six");
        numToWords.put(27, "twenty seven");
        numToWords.put(28, "twenty eight");
        numToWords.put(29, "twenty nine");



        if (hours > 23)
            throw new BadRequestException("Invalid hours");

        else if (hours > 12)
            hours -= 12;

        else if (hours == 00)
            hours = 12;

        if (mins > 59)
            throw new BadRequestException("Invalid minutes");

        else if (mins > 30) {
            mins = 60 - mins;

            time = StringUtils.capitalize(numToWords.get(mins)) + " to " + numToWords.get(++hours);
        }

        else if (mins == 30)
            time = "Half past " + numToWords.get(hours);

        else if (mins < 30 && mins > 0)
            time = StringUtils.capitalize(numToWords.get(mins)) + " past " + numToWords.get(hours);

        else
            time = StringUtils.capitalize(numToWords.get(hours)) + " o'clock";

        return time;
    }
}

package time;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * time class for conversion of Calendar values to printable String values
 * <p>
 * Time class also contains validation methods for validating whether a certain date is valid
*/

public class time {

    /**
     * Constant attribute determining the factor to convert milliseconds to days
     */
    private final static long MILLIS_TO_DAYS = 1000 * 60 * 60 * 24;
    private final static long TO_UTC_PLUS_8 = 28800000;

    /**
     * Method for formatting LocalDate values into formatted String
     *
     * @param date LocalDate object date
     * @return String of date formatted in d/MM/yyyy.
     */
    public static String formatToStringDate(LocalDate date) {
        String day, month, year;
        year = date.getYear() + "";
        month = date.getMonthValue() + "";
        day = date.getDayOfMonth() + "";
        return day + "/" + month + "/" + year;
    }

    /**
     * Method for formatting LocalTime values into formatted String
     *
     * @param time LocalTime object date
     * @return String of time formatted in HH:mm
     */
    public static String formatToStringTime(LocalTime time) {
        String hour, minute;
        hour = time.getHour() + "";
        minute = ((time.getMinute() == 0) ? "00" : time.getMinute()) + "";
        return hour + ":" + minute;
    }

    /**
     * Formats a passed in String to a LocalDate object
     * The String must strictly follow a given format: DD/MM/YYYY
     *
     * @param date String containing date in the specified format
     * @return A LocalDate variable
     * @throws DateTimeParseException When an incorrect format of date and time String has been passed in
     */
    public static LocalDate formatToLocalDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        return LocalDate.parse(date, formatter);
    }

    /**
     * Formats a passed in String to a LocalTime object
     * The String must strictly follow a given format: HH:MM
     *
     * @param time String containing  time in the specified format
     * @return A LocalTime variable
     * @throws DateTimeParseException When an incorrect format of date and time String has been passed in
     */
    public static LocalTime formatToLocalTime(String time) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return LocalTime.parse(time, formatter);
    }

    /**
     * Method to get today's date or 30 days later in LocalDate object
     *
     * @param getNextMonth boolean variable to determine if getting today's date or date one month from now
     * @return LocalDate object containing today's date and time value
     */
    public static LocalDate getTodayDate(boolean getNextMonth) {
        if (!getNextMonth)
            return LocalDate.ofEpochDay(getSysTimeMillisWithSGTimeZone() / MILLIS_TO_DAYS);
        else {
            return LocalDate.ofEpochDay(getSysTimeMillisWithSGTimeZone() / MILLIS_TO_DAYS + 30);
        }
    }

    /**
     * Function to get time now (without date), in UTC+8 (Singapore Time),
     * truncated to include seconds, without nanoseconds
     *
     * @return LocalTime variable containing the time now.
     */
    public static LocalTime getTimeNow() {
        return LocalTime.now().truncatedTo(ChronoUnit.SECONDS);
    }

    /**
     * Method to compare between two times, determining how much time left until the desired time
     *
     * @param time1 first LocalTime attribute
     * @param time2 second LocalTime attribute
     * @return long variable determining the time in minutesremaining from time1 until time2
     */
    public static long getTimeDifferenceMinutes(LocalTime time1, LocalTime time2) {
        return time1.until(time2, ChronoUnit.MINUTES);
    }

    /**
     * Method for comparing if input date is after current date/time.
     *
     * @param inputDate LocalDate variable to be compared
     * @return True is input date is after today, false if is same or before today.
     */
    public static boolean compareIfBeforeToday(LocalDate inputDate) {

        //For debug purposes
        //Printout passed in date and today's date.
        return inputDate.isBefore(LocalDate.ofEpochDay(getSysTimeMillisWithSGTimeZone() / MILLIS_TO_DAYS));
    }

    /**
     * Formats time since Unix Epoch (1/1/2017 00:00:00) to a DateTime String
     *
     * @param millis time in milliseconds since Epoch
     * @return Formatted DateTime String
     */
    public static String formatMillisToDateTime(long millis) {
        SimpleDateFormat sdf = new SimpleDateFormat("d/M/yyyy HH:mm");
        return sdf.format(new Date(millis));
    }



    /**
     * Method to add 8 hours to retrieval of system time to make it UTC+8
     * Note: Plus 8 hours = 28,800,000 millis
     *
     * @return currentTimeMillis plus 8 hours in long
     */
    
    public static long getSysTimeMillisWithSGTimeZone() {
        return System.currentTimeMillis() + TO_UTC_PLUS_8;
    }


}


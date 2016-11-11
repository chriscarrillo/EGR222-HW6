/**
 * Created by Chris on 11/8/16.
 * Time class has hour, minute, and isPM
 */
public class Time {
    private int hour;
    private int minute;
    private boolean isPM;

    // Constructor checks hours and minutes properly
    public Time(int hour, int minute, boolean isPM) {
        // hour should be 1-12
        if (hour < 1 || hour > 12)
            throw new IllegalArgumentException("Hour should be between 1-12");

        // minute should be 0-59
        if (minute < 0 || minute > 59)
            throw new IllegalArgumentException("Minute should be between 0-59");

        // set the hour, minute, and isPM variables
        this.hour = hour;
        this.minute = minute;
        this.isPM = isPM;
    }

    // Checks the format of the time, if wrong, throws exceptions
    private static void checkFormat(String str) {
        // Check format of str
        if (str.length() != 8)
            throw new IllegalArgumentException("Length has to be 8.");

        try {
            Integer.parseInt(str.substring(0, 2));
            Integer.parseInt(str.substring(3, 5));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Incorrect number format");
        }

        if (str.charAt(2) != ':') {
            throw new IllegalArgumentException("Must have a colon (:) separating the time");
        }

        if (str.charAt(5) != ' ') {
            throw new IllegalArgumentException("Must have a space between time and AM/PM");
        }

        String newStr = str.substring(6);
        if (!newStr.equals("PM") && !newStr.equals("AM")) {
            throw new IllegalArgumentException("Must have an AM or PM");
        }
    }

    // fromString checks the format and returns the new Time with proper hour, minutes, and AM/PM
    public static Time fromString(String str) {
        checkFormat(str);
        int h = Integer.parseInt(str.substring(0, 2)); // HH
        int m = Integer.parseInt(str.substring(3, 5)); // MM
        String mStr = str.substring(6);
        boolean b = mStr.equals("PM");
        return new Time(h, m, b);
    }

    // Returns the value of hour
    public int getHour() { return hour; }

    // Returns the value of minute
    public int getMinute() { return minute; }

    // Returns the value of isPM (True/False)
    public boolean isPM() { return isPM; }

    // Override the equals method so that we can compare Time objects
    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Time other = (Time) obj;
            return (hour == other.hour && minute == other.minute && isPM == other.isPM);
        }
        else
            return false;
    }

    // Must override hashCode after equals to make sure the hashCodes are the same
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (isPM ? 1: 0);
        result = 31 * result + minute;
        result = 31 * result + hour;
        return result;
    }

    // shift will shift the time by the given number of minutes
    public void shift(int minutes) {
        if (minutes < 0)
            throw new IllegalArgumentException("Minutes should not be negative");

        // 1) Update minute
        minute += minutes; // 2:15 + 120
        int hoursToAdd = minute/60 % 24;
        minute %= 60;

        if (hoursToAdd == 0) return;

        // 2) Update AM/PM
        int adjustedHour = hour;
        if (adjustedHour == 12) adjustedHour = 0;
        adjustedHour += hoursToAdd;

        int numFlip = adjustedHour/12;
        if (numFlip % 2 == 1) isPM = !isPM;

        // 3) Update hour
        hour += hoursToAdd;
        hour %= 12;
        if (hour == 0) hour = 12;
    }

    // Override the toString method to display the time in the proper format
    @Override
    public String toString() {
        String timeOfDay = "";
        if (isPM)
            timeOfDay = "PM";
        else
            timeOfDay = "AM";

        String hourStr = String.format("%02d", hour);
        String minuteStr = String.format("%02d", minute);
        return hourStr + ":" + minuteStr + " " + timeOfDay;
    }
}
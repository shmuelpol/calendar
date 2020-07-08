// a small data storage class for maman14 project, Q2, calender.
// receives a date, and returns date-string.


package calenderApp;

public class MyDate {
    private final int year;
    private final int month;
    private final int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public String tostring() {
        return ((Integer) year).toString() + "/" + ((Integer) month).toString() + "/" + ((Integer) day).toString();
    }
}

// main calender data class for maman14 project, Q2, calender.
// stores data, handles queries from frontend CalenderPanel, and send month painting data
// to CalenderPanel according to the matching month setting.


package calenderApp;


import javax.swing.*;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.HashMap;

public class MyCalender {

    private CalenderPanel myPanel;
    private HashMap<String, String> database = new HashMap<>();
    YearMonth yearMonth;

    public MyCalender(CalenderPanel myPanel) {
        this.myPanel = myPanel;
        yearMonth = YearMonth.of(2000, 1);

    }

    public void displayDaysOfMonth() {
        // calls calls CalenderPanel with orders for adding DayButtons,
        // how many and where to add ont the table to add them.

        Calendar dayInstance = Calendar.getInstance();
        dayInstance.set(yearMonth.getYear(), yearMonth.getMonthValue(), 1);

        int DAYS_A_WEEK = 7;
        int row = 0;
        int col = dayInstance.get(Calendar.DAY_OF_WEEK) - 1; // cols start from 0.

        for (int i = 1; i <= yearMonth.lengthOfMonth(); i++) {
            dayInstance.set(yearMonth.getYear(), yearMonth.getMonthValue(), i);
            MyDate currentDay = new MyDate(yearMonth.getYear(), yearMonth.getMonthValue(), i);
            myPanel.paintDayButton(currentDay, row, col);
            // advancing column and row:
            if (col == DAYS_A_WEEK - 1) row++;
            col = (col + 1) % DAYS_A_WEEK;
        }
    }


    public void SetMeeting(MyDate date) {
        String meeting = JOptionPane.showInputDialog("current meeting is: " + database.get(date.tostring())
                + "\nplease write your meeting");
        if (meeting.length() != 0) database.put(date.tostring(), meeting);
        else{
            database.remove(date.tostring());
        }
        myPanel.repaint();
    }

    public int getCurrentYear() {
        return yearMonth.getYear();
    }

    public void setCurrentYear(int currentYear) {
        yearMonth = YearMonth.of(currentYear, yearMonth.getMonthValue());
        myPanel.repaint();

    }

    public String getCurrentMonth() {
        return yearMonth.getMonth().name();
    }

    public void setCurrentMonth(int month) throws Exception {
        if (month > 12 || month < 1) throw new Exception("month not authorised.");
        yearMonth = YearMonth.of(yearMonth.getYear(), month);
        myPanel.repaint();
    }

    public HashMap<String, String> getDatabase() {
        return database;
    }
}

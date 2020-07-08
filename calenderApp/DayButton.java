// a small Jbutton class for maman14 project, Q2, calender.
// sets button text to initialization date, and set actionListener to change date meeting.


package calenderApp;

import javax.swing.*;

public class DayButton extends JButton {

    public DayButton(MyCalender cal, MyDate date) {
        this.setText(((Integer) date.getDay()).toString());
        addActionListener(action -> cal.SetMeeting(date));
    }
}

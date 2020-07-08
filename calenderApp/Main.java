// a main class for maman14 project, Q2, calender.
// runs frame, panel, and a calender.


package calenderApp;

import javax.swing.*;

public class Main {
    private static final int FRAME_HEIGHT = 700;
    private static final int FRAME_WIDTH = 900;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        CalenderPanel myJPanel = new CalenderPanel();
        frame.add(myJPanel);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        MyCalender cal = new MyCalender(myJPanel);
        myJPanel.setCal(cal);
    }
}

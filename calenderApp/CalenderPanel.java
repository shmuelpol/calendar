// a modified JPanel class for maman14 project, Q2, calender.
// main frontend class. handles user interface, displays buttons,
// and sends queries to Mycalender.


package calenderApp;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CalenderPanel extends JPanel {
    private static final int CHOICE_BUTTONS_X = 150;
    private static final int CHOICE_BUTTONS_Y = 550;
    private static final int CHOICE_BUTTONS_WIDTH = 300;
    private static final int CHOICE_BUTTONS_HEIGHT = 70;
    private static final int MONTH_STRING_Y = 530;
    private static final int FONT_SIZE = 16;
    private static final int DAY_BUTTON_STARTER_GAP_X = 60;
    private static final int DAY_BUTTON_WIDTH = 90;
    private static final int DAY_BUTTON_STARTER_GAP_Y = 60;
    private static final int DAY_BUTTON_HEIGHT = 60;
    private MyCalender cal;
    private boolean firstRun = true;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        removeAll();
        paintBackground(g);
        cal.displayDaysOfMonth();
        printCurrentMonthString(g);
        paintYearChoiceButton();
        printMonthChoiceButton();
    }

    private void paintBackground(Graphics g) {
        final BufferedImage calender;
        try {
            calender = ImageIO.read(new File("pic.jpg"));
            g.drawImage(calender, 0, 0, null);
        } catch (IOException e) {
            if (firstRun) {
                firstRun = false;
                JOptionPane.showMessageDialog(this,"Background image could not be loaded. please confirm that working " +
                        "directory contains file 'pic.jpg'");
            }
        }
    }

    private void printCurrentMonthString(Graphics g) {
        g.setFont(new Font("default", Font.BOLD, FONT_SIZE));
        g.drawString(cal.getCurrentMonth() + " " + ((Integer) cal.getCurrentYear()).toString(),
                CHOICE_BUTTONS_X, MONTH_STRING_Y);
    }

    private void printMonthChoiceButton() {
        JButton chooseMonth = new JButton("Choose month");
        chooseMonth.addActionListener(action -> {
                    String month = JOptionPane.showInputDialog("" +
                            "Enter a month (from 1 - January to 12 - December)");
                    try {
                        cal.setCurrentMonth(Integer.parseInt(month));
                    } catch (Exception NumberFormatException) {
                        JOptionPane.showMessageDialog(this,
                                "'" + month + "'" + " is not a valid month." +
                                        " months can be from 1 - January, to 12 - December.");
                    }
                }
        );
        add(chooseMonth);
        chooseMonth.setBounds(CHOICE_BUTTONS_X, CHOICE_BUTTONS_Y,
                CHOICE_BUTTONS_WIDTH, CHOICE_BUTTONS_HEIGHT);
    }

    private void paintYearChoiceButton() {
        // basically does the same as printMonthChoiceButton, but saw no way to unify them in short.
        JButton chooseYear = new JButton("choose year");
        chooseYear.addActionListener(action -> {
                    String year = JOptionPane.showInputDialog("enter a year");
                    try {
                        cal.setCurrentYear(Integer.parseInt(year));
                    } catch (Exception NumberFormatException) {
                        JOptionPane.showMessageDialog(this,
                                "'" + year + "'" + " is not a valid year.");
                    }
                }
        );
        add(chooseYear);
        chooseYear.setBounds(CHOICE_BUTTONS_X + CHOICE_BUTTONS_WIDTH, CHOICE_BUTTONS_Y,
                CHOICE_BUTTONS_WIDTH, CHOICE_BUTTONS_HEIGHT);
    }

    public void paintDayButton(MyDate day, int row, int col) {
        // add DayButton on screen, in place on days table, according to row and column
        DayButton dayButton = new DayButton(cal, day);
        dayButton.setBounds(DAY_BUTTON_STARTER_GAP_X + col * DAY_BUTTON_WIDTH,
                DAY_BUTTON_STARTER_GAP_Y + row * DAY_BUTTON_HEIGHT, DAY_BUTTON_WIDTH, DAY_BUTTON_HEIGHT);
        if (cal.getDatabase().containsKey(day.tostring())) {
            dayButton.setText(cal.getDatabase().get(day.tostring()));
        }
        add(dayButton);
    }

    public MyCalender getCal() {
        return cal;
    }

    public void setCal(MyCalender cal) {
        this.cal = cal;
    }
}

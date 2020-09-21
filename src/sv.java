import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * sv class with static constant attributes and static methods.
 * sv - static values. Used in all classes for readability purpose.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class sv {
    // Final static colors
    public final static Color pink = new Color(183, 132, 204);
    public final static Color purple = new Color(103, 52, 154);
    public final static Color lightPurple = new Color(153, 102, 204);
    public final static Color darkGray = new Color(60,65,65);
    // Final static date and time format for sql and app main time
    public final static String dateString = "MM-dd-yyyy";
    public final static String dateFormatter = "yyyy-MM-dd";
    public final static String timeString = "hh:mm:ss a";
    public final static String timeFormatter = "HH:mm:ss";
    // JOptionpane.showMessageDialog messages
    public final static String warning = "User ID or password incorrect!";
    public final static String successReg = "Registration successful!";
    public final static String successUpdateAccount = "Account successfully updated!";
    public final static String successBooking = "Booking successful!";
    public final static String warningDeleteBooking = "Booking deleted successfully!";
    public final static String successAddedEvent = "Event added!";
    public final static String warningDeleteEvent = "Event deleted successfully!";
    // Final static fonts
    public final static Font timeLabelFont = new Font("Times New Roman", Font.PLAIN, 20);
    public final static Font timeTextFont = new Font("Sheriff", Font.ITALIC + Font.BOLD, 16);
    public final static Font titleFont = new Font("Times New Roman", (Font.BOLD + Font.ITALIC), 32);
    public final static Font eventsFont = new Font("Times New Roman", Font.BOLD, 22);
    public final static Font buttonFont = new Font("Sheriff", Font.BOLD, 20);
    public final static Font inputLabel = new Font("Times New Roman", Font.BOLD + Font.ITALIC, 20);
    public final static Font idTextFont = new Font("Times New Roman", Font.BOLD, 18);
    // Database query strings //
    // User student and admin query
    public static String allStudents = "SELECT * FROM student";
    public static String allAdmin = "SELECT * FROM administrator";
    public static String insertStudent = "INSERT INTO `student`(`universityid`, `password`, `firstname`, `lastname`, `email`) VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s');";
    public static String selectStudent = "SELECT * FROM `student` WHERE `universityid` = %s";
    public static String studentUpdate = "UPDATE `student` SET `universityid` = \"%1$s\", password = \"%2$s\", firstname = \"%3$s\", lastname = \"%4$s\", email = \"%5$s\" WHERE `universityid` = \"%6$s\"";
    public static String adminUpdate = "UPDATE `administrator` SET `universityid` = \"%1$s\", password = \"%2$s\", firstname = \"%3$s\", lastname = \"%4$s\", email = \"%5$s\" WHERE `universityid` = \"%6$s\"";
    public static String selectAdmin = "SELECT * FROM `administrator` WHERE `universityid` = %s";
    // Event query
    public static String selectEvent = "SELECT * FROM `event` WHERE `eventid` = %s";
    public static String eventByDate = "SELECT * FROM `event` WHERE `date` >= %s AND `bookingrequired` = 1";
    public static String eventByUser = "SELECT * FROM `event`, `student` WHERE student.universityid = %s AND event.studentid = student.studentid";
    public static String eventDetail = "Title: %1$s \n Description: %2$s \n Category %3$s \n Time: %4$s / Date: %5$s \n Location: %6$s \n Room: %7$s \n URL: %8$s \n Available places: %9$s \n Required booking: %10$s";
    public static String insertEvent = "INSERT INTO `event`(`studentid`, `eventtitle`, `eventdescription`, `category`, `date`, `time`, `location`, `roomno`, `URL`, `placesavailable`, `bookingrequired`) VALUES ('%1$s', '%2$s', '%3$s', '%4$s', '%5$s', '%6$s', '%7$s', '%8$s', '%9$s', '%10$s', '%11$s')";
    public static String deleteEvent = "DELETE FROM `event` WHERE `eventid` = %s";
    public static String updateEvent = "UPDATE `event` SET `eventtitle` = \"%1$s\", `eventdescription` = \"%2$s\", `category` = \"%3$s\", `date` = \"%4$s\", `time` = \"%5$s\", `location` = \"%6$s\", `roomno` = \"%7$s\", `URL` = \"%8$s\", `placesavailable` = \"%9$s\", `bookingrequired` = \"%10$s\" WHERE `studentid` = \"%11$s\"";
    // Booking query
    public static String insertBooking = "INSERT INTO `booking`(`studentid`, `eventid`, `status`) VALUES (%1$s, %2$s, 'pending')";
    public static String selectedBookings = "SELECT * FROM `student`, `event`, `booking` WHERE student.universityid = %1$s AND booking.studentid = student.studentid AND booking.eventid = event.eventid";
    public static String deletedBooking = "DELETE FROM `booking` WHERE `bookingid` = %s";
    public static String deleteBookingByEvent = "DELETE FROM `booking` WHERE `eventid` = %s";

    /**
     * static method for time and date
     * @param str - time or date format string, ex: "yyyy-MM-dd", "HH:mm:ss"
     * @return - formatted time and date
     */
    public static String getTimeDateFormat(String str) {
        SimpleDateFormat format = new SimpleDateFormat(str);
        Date timeData = new Date(System.currentTimeMillis());
        return format.format(timeData);
    }

    /**
     * static method for booking conversion from 1 to "yes" or 0 to "no"
     * @param str - string data from database as 1 or 0
     * @return - "Yes" or "No" - for displaying an event booking required column
     */
    public static String getBooking(String str) {
        if (str.equals("1")) {
            return "Yes";
        }
        return "No";
    }
    /**
     * static method for booking conversion from "yes" to 1 or "no" to 0.
     * getBooking() method inverted
     * @param str - string from the user as "Yes" or "No"
     * @return - 1 or 0 - for inserting to database
     */
    public static int setBooking(String str) {
        if (str.equals("Yes")) {
            return 1;
        }
        return 0;
    }
}

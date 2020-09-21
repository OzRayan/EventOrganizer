import java.awt.*;
import javax.swing.*;

/**
 * Student class extends JPanel.
 * This class prepares a JPanel for the main frame for Student menu options.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class Student extends JPanel {

    protected CreateButton eventsButton;
    protected CreateButton bookingsButton;
    protected CreateButton editButton;
    protected CreateButton signOutButton;

    // 'north' & 'south" panels
    protected CreatePanel north;
    protected CreatePanel south;

    // app window width & height
    protected int width;
    protected int height;

    /**
     * Constructor.
     *
     * @param width - panel width
     * @param height - panel height
     */
    public Student(int width, int height) {
        this.width = width;
        this.height = height;

        // setting up main panel.
        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, this.width, this.height);
        setVisible(false);

        // North panel for info display
        north = new CreatePanel("grid", false, true);
        north.setBackground(Color.red);
        north.setBounds(0, 0, this.width, this.height - 291);
        // South panel for buttons
        south = new CreatePanel("null", false, true);
        south.setBackground(Color.blue);
        south.setBounds(0, north.getHeight(), this.width,  131);

        // Buttons
        eventsButton = new CreateButton("Events", sv.lightPurple, Color.lightGray, sv.buttonFont);
        eventsButton.setBounds(51,45, 150, 30);

        bookingsButton = new CreateButton("Bookings", sv.lightPurple, Color.lightGray, sv.buttonFont);
        bookingsButton.setBounds(eventsButton.getWidth() + 102, 45, 153, 30);

        editButton = new CreateButton("Edit account", sv.lightPurple, Color.lightGray, sv.buttonFont);
        editButton.setBounds(eventsButton.getWidth() * 2 + 153, 45, 153, 30);

        signOutButton = new CreateButton("Sign Out", sv.lightPurple, Color.lightGray, sv.buttonFont);
        signOutButton.setBounds(eventsButton.getWidth() * 3 + 204, 45, 153, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'south' panel
        south.add(eventsButton);
        south.add(bookingsButton);
        south.add(editButton);
        south.add(signOutButton);
    }

}

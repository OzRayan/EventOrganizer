import java.awt.*;
import javax.swing.*;

/**
 * Admin class extends JPanel.
 * This class prepares a JPanel for the main frame for administrator.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class Admin extends JPanel {

    // Buttons
    protected CreateButton eventButton;
    protected CreateButton bookingButton;
    protected CreateButton rightsButton;
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
    public Admin(int width, int height) {
        this.width = width;
        this.height = height;

        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, this.width, this.height);
        setVisible(false);

        north = new CreatePanel("grid", false, true);
        north.setBackground(Color.red);
        north.setBounds(0, 0, this.width, this.height - 291);

        south = new CreatePanel("null", false, true);
        south.setBackground(Color.blue);
        south.setBounds(0, north.getHeight(), this.width,  131);

        eventButton = new CreateButton("Events", sv.lightPurple, Color.lightGray, sv.buttonFont);
        eventButton.setBounds(51,45, 150, 30);

        bookingButton = new CreateButton("Bookings", sv.lightPurple, Color.lightGray, sv.buttonFont);
        bookingButton.setBounds(eventButton.getWidth() + 102, 45, 153, 30);

        rightsButton = new CreateButton("Accounts", sv.lightPurple, Color.lightGray, sv.buttonFont);
        rightsButton.setBounds(eventButton.getWidth() * 2 + 153, 45, 153, 30);

        signOutButton = new CreateButton("Sign Out", sv.lightPurple, Color.lightGray, sv.buttonFont);
        signOutButton.setBounds(eventButton.getWidth() * 3 + 204, 45, 153, 30);

        add(north);
        add(south);
        south.add(eventButton);
        south.add(bookingButton);
        south.add(rightsButton);
        south.add(signOutButton);
    }

}

import java.awt.*;
import javax.swing.*;

/**
 * Organiser class extends JPanel.
 * This class prepares a JPanel for the main frame for organiser.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class Organizer extends JPanel {
    // Buttons
    protected CreateButton addEventButton;
    protected CreateButton editEventButton;
    protected CreateButton editAccountButton;
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
    public Organizer(int width, int height) {
        this.width = width;
        this.height = height;

        // setting up main panel.
        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, this.width, this.height);
        setBackground(Color.yellow);
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
        addEventButton = new CreateButton("Add event", sv.lightPurple, Color.lightGray, sv.buttonFont);
        addEventButton.setBounds(51,45, 150, 30);

        editEventButton = new CreateButton("Edit event", sv.lightPurple, Color.lightGray, sv.buttonFont);
        editEventButton.setBounds(addEventButton.getWidth() + 102, 45, 153, 30);

        editAccountButton = new CreateButton("Edit account", sv.lightPurple, Color.lightGray, sv.buttonFont);
        editAccountButton.setBounds(addEventButton.getWidth() * 2 + 153, 45, 153, 30);

        signOutButton = new CreateButton("Sign Out", sv.lightPurple, Color.lightGray, sv.buttonFont);
        signOutButton.setBounds(addEventButton.getWidth() * 3 + 204, 45, 153, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'north' and 'south' panel
        south.add(addEventButton);
        south.add(editEventButton);
        south.add(editAccountButton);
        south.add(signOutButton);
    }

}

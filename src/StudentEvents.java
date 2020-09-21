import javax.swing.*;
import java.awt.*;

/**
 * StudentEvents class extends JPanel.
 * This class prepares a JPanel for the main frame for booking an event.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class StudentEvents extends JPanel {
    // Scroll pane for displaying a list of records
    // It's used in EventManager class, a JTable is added
    protected JScrollPane listScroll;

    protected CreateButton booking;
    protected CreateButton back;

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
    public StudentEvents(int width, int height) {
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
        north.setBounds(0, 0, this.width, this.height - 161);
        // South panel for buttons
        south = new CreatePanel("null", false, true);
        south.setBackground(Color.blue);
        south.setBounds(0, north.getHeight(), this.width,  131);

        // Scroll for table
        listScroll = new JScrollPane();
        listScroll.setPreferredSize(new Dimension(this.width, this.height - 302));
        listScroll.setBorder(null);

        // Buttons
        booking = new CreateButton("Book", sv.lightPurple, Color.lightGray, sv.buttonFont);
        booking.setBounds(this.width / 2,15, 150, 30);

        back = new CreateButton("Back", sv.lightPurple, Color.lightGray, sv.buttonFont);
        back.setBounds(this.width / 2,35 + booking.getHeight(), 150, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'north' and 'south' panel
        north.add(listScroll);
        south.add(booking);
        south.add(back);
    }
}

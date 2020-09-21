import javax.swing.*;
import java.awt.*;

/**
 * StudentBooking class extends JPanel.
 * This class prepares a JPanel for the main frame for canceling a booking.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class StudentBooking extends JPanel {
    // Scroll pane for displaying a list of records
    // It's used in EventManager class, a JTable is added
    protected JScrollPane listScroll;

    // Buttons
    protected CreateButton cancel;
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
    public StudentBooking(int width, int height) {
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
        cancel = new CreateButton("Cancel", sv.lightPurple, Color.lightGray, sv.buttonFont);
        cancel.setBounds(this.width / 2,15, 150, 30);

        back = new CreateButton("Back", sv.lightPurple, Color.lightGray, sv.buttonFont);
        back.setBounds(this.width / 2,35 + cancel.getHeight(), 150, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'north' and 'south' panel
        north.add(listScroll);
        south.add(cancel);
        south.add(back);
    }
}

import javax.swing.*;
import java.awt.*;

/**
 * EditEventMenu class extends JPanel.
 * This class prepares a JPanel for the main frame a menu for editing an event.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class EditEventMenu extends JPanel {
    // Scroll pane for displaying a list of records
    // It's used in EventManager class, a JTable is added
    protected JScrollPane listScroll;

    // Buttons
    protected CreateButton edit;
    protected CreateButton delete;
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
    public EditEventMenu(int width, int height) {
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
        edit = new CreateButton("Edit", sv.lightPurple, Color.lightGray, sv.buttonFont);
        edit.setBounds(255,45, 153, 30);
        edit.setEnabled(false);

        delete = new CreateButton("Delete", sv.lightPurple, Color.lightGray, sv.buttonFont);
        delete.setBounds(edit.getWidth() * 2 + 153,45, 153, 30);
        edit.setEnabled(false);

        back = new CreateButton("Back", sv.lightPurple, Color.lightGray, sv.buttonFont);
        back.setBounds(edit.getWidth() * 3 + 204,45, 153, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'north' and 'south' panel
        north.add(listScroll);
        south.add(edit);
        south.add(delete);
        south.add(back);
    }
}

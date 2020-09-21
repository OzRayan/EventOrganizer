import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;

/**
 * CreateTable class extends JTable.
 * Custom JTable which creates a table with customized header and column width.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class CreateTable extends JTable {
    protected JTableHeader head;
    protected int width;
    protected int height;

    /**
     * Constructor.
     *
     * @param header - String array for column names in the header
     * @param data - a 2 dimensional Object array for feeding the data to table
     * @param width - panel width
     * @param height - panel height
     */
    public CreateTable(String[] header, Object[][] data, int width, int height) {
        super(data, header);    // calling parent class for header and data
        this.width = width;
        this.height = height;


        head = getTableHeader();
        head.setBackground(sv.purple);
        head.setForeground(Color.lightGray);
        head.setFont(sv.timeLabelFont);

        // Setting up the columns width, 1. for ID, 2. Title/description
        TableColumn col;
        for (int i = 0; i < 4; i++) {
            col = getColumnModel().getColumn(i);
            if (i == 1) {
                col.setPreferredWidth(this.width - 195);
            } else if (i == 0) {
                col.setPreferredWidth(35);
            } else {
                col.setPreferredWidth(80);
            }
        }
        setRowHeight(27);   // maximum row height 27
        setFillsViewportHeight(true);   // fills up the available height on the panel
        setBackground(sv.lightPurple);
        setFont(sv.inputLabel);
        setShowGrid(false);
        setForeground(Color.lightGray);
        setDragEnabled(false);  // Restricting column drag and drop
    }
}

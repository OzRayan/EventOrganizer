import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;

/**
 * CreateTextField class extends JTextField.
 * Custom text field with design.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class CreateTextField extends JTextField {
    protected Color background;
    protected Color foreground;
    protected Font font;
    protected int column;

    /**
     * Constructor.
     *
     * @param bgd - background color
     * @param fgd - foreground color
     * @param font - font
     * @param clm - column
     */
    public CreateTextField(Color bgd, Color fgd, Font font, int clm) {
        super();
        this.background = bgd;
        this.foreground = fgd;
        this.font = font;
        this.column = clm;

        setBorder(null);
        setBackground(background);
        setForeground(foreground);
        setFont(this.font);
        setColumns(column);
    }
}

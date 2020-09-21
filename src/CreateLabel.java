import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;

/**
 * CreateLabel class extends JLabel.
 * Custom label builder with design.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class CreateLabel extends JLabel {
    protected Color foreground;
    protected Font font;

    /**
     * Constructor.
     *
     * @param name - displayed label
     * @param color - foreground color
     * @param font - font
     */
    public CreateLabel(String name, Color color, Font font) {
        super(name);
        this.foreground = color;
        this.font = font;

        setForeground(foreground);
        setFont(this.font);
    }
}

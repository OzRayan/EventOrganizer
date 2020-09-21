import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * CreateButton class extends JButton.
 * Custom button builder with mouse listener and design
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class CreateButton extends JButton {
    protected Color background;
    protected Color foreground;
    protected Font font;

    /**
     * Constructor.
     *
     * @param name - name of the button
     * @param bgd - background color
     * @param fgd - foreground color
     * @param font - font
     */
    public CreateButton(String name, Color bgd, Color fgd, Font font) {
        super(name);    // calling parent class for button name initialization
        this.background = bgd;
        this.foreground = fgd;
        this.font = font;

        setBorder(null);
        setFocusPainted(false);
        setForeground(foreground);
        setBackground(background);
        setFont(this.font);

        addMouseListener(new MouseListener() {
            /**
             * It changes to original the background and foreground by clicking.
             * @param e - MouseEvent object;
             */
            @Override
            public void mouseClicked(MouseEvent e) {
//                setForeground(foreground);
//                setBackground(background);
            }
            /**
             * It changes the background and foreground by pressing.
             * @param e - MouseEvent object;
             */
            @Override
            public void mousePressed(MouseEvent e) {
                setForeground(background);
                setBackground(foreground);
            }
            /**
             * It changes back to the original the background and foreground by releasing.
             * @param e - MouseEvent object;
             */
            @Override
            public void mouseReleased(MouseEvent e) {
                setForeground(foreground);
                setBackground(background);
            }
            /**
             * It changes mouse cursor to 'hand' when mouse enter.
             * @param e - MouseEvent object;
             */
            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
            /**
             * It changes mouse cursor to default when mouse exits.
             * @param e - MouseEvent object;
             */
            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

    }
}

import javax.swing.JPanel;
import java.awt.GridLayout;

/**
 * CreatePanel class extends JPanel.
 * Custom panel for different views.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class CreatePanel extends JPanel {
    protected String layout;
    protected boolean opaque;
    protected boolean visible;

    /**
     * Constructor.
     *
     * @param layout - for setting the layout to "null" or GridLayout()
     * @param opaque - to set the background opacity
     * @param visible - to set the panel visible
     */
    public CreatePanel(String layout, boolean opaque, boolean visible){
        this.layout = layout;
        this.opaque = opaque;
        this.visible = visible;

        switch (layout) {
            case "null":
                setLayout(null);
                break;
            case "grid":
                setLayout(new GridLayout());
                break;
            default:
                break;
        }
        setOpaque(this.opaque);
        setBorder(null);
        setVisible(this.visible);
    }
}

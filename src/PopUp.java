import javax.swing.*;

/**
 * PopUp class extends JOptionPane.
 * This class shows a message of warning, info or plain text.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class PopUp extends JOptionPane {
    protected static int type;

    public static void message(String mess, String title, String flag) {
        switch (flag) {
            case "i":
                type = JOptionPane.INFORMATION_MESSAGE;
                break;
            case "p":
                type = JOptionPane.PLAIN_MESSAGE;
                break;
            case "w":
                type = JOptionPane.WARNING_MESSAGE;
                break;
        }
        showMessageDialog(null, mess, title, type);
    }
}

import java.awt.*;
import javax.swing.*;

/**
 * SignIn class extends JPanel.
 * This class prepares a JPanel for the main frame for sign in.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class SignIn extends JPanel {
    // Button labels
    protected JLabel idLabel;
    protected CreateTextField idText;
    protected JLabel passwordLabel;
    protected JPasswordField passwordText;

    // Buttons
    // Drop down combobox for accounts option
    protected CreateButton signIn;
    protected CreateButton signUp;
    protected JComboBox<String> accountType;

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
    public SignIn (int width, int height) {
        this.width = width;
        this.height = height;

        // setting up main panel.
        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, this.width, this.height);
        setVisible(true);

        // North panel for info display
        int elementHeight = this.height / 100 * 8;
        north = new CreatePanel("null", false, true);
        north.setBackground(Color.red);
        north.setBounds(0, 0, this.width, this.height - 161);
        // South panel for buttons
        south = new CreatePanel("null", false, true);
        south.setBackground(Color.blue);
        south.setBounds(0, north.getHeight(), this.width,  131);

        // Sign in fields label
        idLabel = new CreateLabel("Username", sv.purple, sv.inputLabel);
        idLabel.setBounds(20, 15, 120, 35);

        passwordLabel = new CreateLabel("Password", sv.purple, sv.inputLabel);
        passwordLabel.setBounds(20, elementHeight + 30, 80, 30);

        // Sign in fields
        idText = new CreateTextField(sv.lightPurple, Color.lightGray, sv.idTextFont, 40);
        idText.setBounds(idLabel.getWidth() + 30, 15, 150, 30);

        passwordText = new JPasswordField(40);
        passwordText.setBorder(null);
        passwordText.setForeground(Color.lightGray);
        passwordText.setFont(sv.idTextFont);
        passwordText.setBackground(sv.lightPurple);
        passwordText.setBounds(idLabel.getWidth() + 30, 35 + idText.getHeight(), 150, 30);

        // Sign in options
        String[] accounts = {"Student", "Organiser", "Administrator"};
        accountType = new JComboBox<>(accounts);
        accountType.setBounds(width - 180, 15, 160, 30);
        accountType.setBorder(null);
        accountType.setBackground(Color.lightGray);
        accountType.setForeground(sv.purple);
        accountType.setFont(sv.idTextFont);
        accountType.setToolTipText("Select your account type");

        // Sign in/sign out buttons
        signIn = new CreateButton("Login", sv.lightPurple, Color.lightGray, sv.buttonFont);
        signIn.setBounds(this.width / 2,15, 150, 30);

        signUp = new CreateButton("Register", sv.lightPurple, Color.lightGray, sv.buttonFont);
        signUp.setBounds(this.width / 2,35 + signIn.getHeight(), 150, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'south' panel
        south.add(idLabel);
        south.add(idText);
        south.add(passwordLabel);
        south.add(passwordText);
        south.add(accountType);
        south.add(signIn);
        south.add(signUp);
    }
}

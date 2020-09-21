import javax.swing.*;
import java.awt.*;

/**
 * SignUp class extends JPanel..
 * This class prepares a JPanel for the main frame for sign out.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class SignUp extends JPanel {
    // Input field labels
    protected CreateLabel idLabel;
    protected CreateLabel firstNameLabel;
    protected CreateLabel lastNameLabel;
    protected CreateLabel emailLabel;
    protected CreateLabel passwordLabel;

    // Input fields
    protected CreateTextField idText;
    protected CreateTextField firstName;
    protected CreateTextField lastName;
    protected CreateTextField email;
    protected JPasswordField password;

    // Buttons
    protected CreateButton signUp;
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
    public SignUp(int width, int height) {
        this.width = width;
        this.height = height;

        // setting up main panel.
        setOpaque(false);
        setLayout(null);
        setBounds(0, 0, this.width, this.height);
        setVisible(false);

        // North panel for info display
        north = new CreatePanel("null", false, true);
        north.setBackground(Color.red);
        north.setBounds(0, 0, this.width, this.height - 161);
        // South panel for buttons
        south = new CreatePanel("null", false, true);
        south.setBackground(Color.blue);
        south.setBounds(0, north.getHeight(), this.width,  131);

        int xPosition = this.width / 2;

        // Labels
        idLabel = new CreateLabel("University ID", Color.lightGray, sv.inputLabel);
        idLabel.setBounds(xPosition - 200,45,130,30);

        firstNameLabel = new CreateLabel("First name", Color.lightGray, sv.inputLabel);
        firstNameLabel.setBounds(xPosition - 200,105,130,30);

        lastNameLabel = new CreateLabel("Last name", Color.lightGray, sv.inputLabel);
        lastNameLabel.setBounds(xPosition - 200,165,130,30);

        emailLabel = new CreateLabel("Email", Color.lightGray, sv.inputLabel);
        emailLabel.setBounds(xPosition - 200,225,130,30);

        passwordLabel = new CreateLabel("Password", Color.lightGray, sv.inputLabel);
        passwordLabel.setBounds(xPosition - 200,295,130,30);

        // Input fields
        idText = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        idText.setBounds(xPosition, 45, 170, 30);

        firstName = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        firstName.setBounds(xPosition, 105, 170, 30);

        lastName = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        lastName.setBounds(xPosition, 165, 170, 30);

        email = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        email.setBounds(xPosition, 225, 170, 30);

        password = new JPasswordField(40);
        password.setBorder(null);
        password.setForeground(sv.purple);
        password.setFont(sv.idTextFont);
        password.setBackground(Color.lightGray);
        password.setBounds(xPosition, 295, 170, 30);

        // Buttons
        signUp = new CreateButton("Register", sv.lightPurple, Color.lightGray, sv.buttonFont);
        signUp.setBounds(this.width / 2, 15, 150, 30);

        back = new CreateButton("Back", sv.lightPurple, Color.lightGray, sv.buttonFont);
        back.setBounds(this.width / 2,35 + signUp.getHeight(), 150, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'north' and 'south' panel
        north.add(idLabel);
        north.add(firstNameLabel);
        north.add(lastNameLabel);
        north.add(emailLabel);
        north.add(passwordLabel);

        north.add(idText);
        north.add(firstName);
        north.add(lastName);
        north.add(email);
        north.add(password);

        south.add(signUp);
        south.add(back);
    }
}

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.Objects;

/**
 * This application it's just for study purposes only and it's based on the university studies!
 * Uses GUI interface to easier user interaction to register to an event management.
 * Registered user can login as student, organiser or administrator.
 * If not registered, a user it's able to register as a student.
 * Functionality:
 * as Student: - a student can book an event or cancel an own booking.
 *             - a student can edit own registration details.
 *
 * as Organiser: - a student can be organiser by admin permission.
 *               - an organiser can add event, edit or delete an own event.
 *               - an organiser can edit own registration details.
 *
 * as Administrator: - can login and logout, all other functionality are not implemented yet.
 *
 * Action listeners are implemented using lambda e ->
 *
 * imports used:
 *      - java.sql.*
 *      - javax.swing.*
 *      - javax.swing.DateFormatter
 *      - javax.swing.table.JTableHeader, -.TableColumn
 *      - javax.swing.text.DateFormatter
 *      - java.awt.*
 *      - java.awt.event.MouseEvent, -.MouseListener
 *      - java.awt.GridLayout
 *      - java.text.SimpleDateFormat
 *      - java.util.Date, -.Objects
 *
 * classes used:
 *      Customized classes:
 *              - CreateButton
 *              - CreateLabel
 *              - CreatePanel
 *              - CreateTable
 *              - CreateTextField
 *              - Drawing - main background
 *              - PopUp - JOptionPane dialog window
 *              - sv - static values
 *      Built-in classes:
 *              - Thread - for time display
 *              - Dimensions - to set the width and height of os screen
 *              - Toolkit - to get the width and height of os screen
 *
 * EventManager class extends JFrame.
 * Main class of the application. A frame is created for all the displayed
 * panels and their own elements.
 * App width and height it's achieved by using Dimension class.
 * Each button action changes the visibility of one panel or another.
 * All panels are initialized before it's used, which panel is brought forward is depending
 * on the user choice.
 *
 * Only this class connects and uses the DataBase class.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
public class EventManager extends JFrame {
    // Constants for the main frame, - Resizable, - Undecorated, - Visible
    protected final boolean RESIZABLE = false;
    protected final boolean VISIBLE = true;
    protected String user = "";
    protected String role = "";
    protected String fName = "";
    protected String lName = "";

    // Getting system screen size
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();

    // Size of app window => width 70% , height 80%
    protected final int width = size.width / 100 * 45;
    protected final int height = size.height / 100 * 70;

    // Position of window on screen => x, y
    // half screen minus (-) half window size
    protected final int x = size.width / 2 - width / 2;
    protected final int y = size.height / 2 - height / 2;

    // Upper area components: time, date, title and labels with it
    private CreateLabel timeText;
    private CreateLabel dateText;
    private CreateLabel title;
    private CreatePanel northPanel;
    private CreatePanel southPanel;
    private int eventId;
    private String studentId;
    private int bookingId;
    private String eventTitle;
    private String bookingRequired;
    private String accountType;

    // New instances for each "page"
    protected Drawing background = new Drawing(width, height);
    protected SignIn signIn = new SignIn(width, height - 130);
    protected Student student = new Student(width, height);
    protected SignUp signUp = new SignUp(width, height - 130);
    protected StudentEvents studentEvent = new StudentEvents(width, height - 130);
    protected EditAccount editAccount = new EditAccount(width, height - 130);
    protected StudentBooking studentBooking = new StudentBooking(width, height - 130);
    protected Organizer organiser = new Organizer(width, height);
    protected AddEvent event = new AddEvent(width, height - 130);
    protected EditEvent editEvent = new EditEvent(width, height - 130);
    protected EditEventMenu editMenu = new EditEventMenu(width, height - 130);
    protected Admin admin = new Admin(width, height);

    // DataBase instance - used later on for querying
    private DataBase db = new DataBase();

    /**
     * Main method - calls new Runnable
     *
     * @param args - default argument
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EventManager inst = new EventManager();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Constructor.
     *
     * Calls super/parent class for naming the app window
     *
     * initialize() method call - contains all functionality
     *
     * clock() - new Thread class for displaying the time and date
     */
    public EventManager() {
        super("Event Manager");   // Naming the main window
        initialize();                // Initialize method call
        clock();                     // clock() method call for displaying time and date
    }

    /**
     * clock() method.
     *
     * Uses Thread to display time and date.
     */
    public void clock(){
        Thread clock = new Thread(() -> {
            try{
                for(;;){
                    // Setting up the displayed date text
                    dateText.setText(sv.getTimeDateFormat(sv.dateString));
                    timeText.setText(sv.getTimeDateFormat(sv.timeString));
                    Thread.sleep(1000);    // sleeping 1000 milliseconds == 1 second
                }
            }catch (InterruptedException e){e.printStackTrace();}
        });
        clock.start();      // clock Thread start
    }

    /**
     * initialize method.
     *
     * Initialize all components.
     */
    public void initialize() {
        // Main Frame
        setLayout(new GridLayout());
        pack();
        setLocation(this.x, this.y);
        setSize(this.width, this.height);
        setVisible(VISIBLE);
        setResizable(RESIZABLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main JPanel for north-, center-, southPanel with NULL Layout
        background.setBounds(0, 0, this.width, this.height);
        background.setBackground(sv.lightPurple);
        background.setLayout(null);
        background.setVisible(true);
        /*
        * North-, South-JPanels for all the visible elements
        * */
        // North JPanel for time, date and page title
        northPanel = new CreatePanel("null", false, true);
        northPanel.setBounds(0, 0, this.width, 130);
        northPanel.setBackground(Color.white);

        southPanel = new CreatePanel("null", false, true);
        southPanel.setBounds(0, northPanel.getHeight(), this.width, this.height - northPanel.getHeight());
        southPanel.setBackground(Color.green);

        /*
        * MAIN page elements
        * */
        // Time JLabel for Label and time display
        CreateLabel timeLabel = new CreateLabel("Time:", sv.purple, sv.timeLabelFont);
        timeLabel.setBounds(15, 20, 60, 30);
        timeText = new CreateLabel("", Color.darkGray, sv.timeTextFont);
        timeText.setBounds(timeLabel.getWidth() + 15, 24, 120, 25);
        // Date JLabel for Label and date display
        CreateLabel dateLabel = new CreateLabel("Date:", sv.purple, sv.timeLabelFont);
        dateLabel.setBounds(15, timeLabel.getHeight() * 3 - 15, 60, 30);
        dateText = new CreateLabel("", Color.darkGray, sv.timeTextFont);
        dateText.setBounds(dateLabel.getWidth() + 15, timeText.getHeight() * 3 + 2, 120, 25);
        // Title for recent action page
        title = new CreateLabel("Login", sv.purple, sv.titleFont);
        int dist = (this.width - (timeLabel.getWidth() + timeText.getWidth()) - 30);
        title.setBounds((this.width - dist) * 2, 30, dist, 40);

        // Adding elements to northPanel
        northPanel.add(timeLabel);
        northPanel.add(timeText);
        northPanel.add(dateLabel);
        northPanel.add(dateText);
        northPanel.add(title);

        validate();

        // Adding all panels to south panel
        setUpPanels();

        // Action listener
        // Sign in / sign out and edit account listener
        signInListener();
        signUpListener();
        editAccountListener();

        // Student listener
        studentListener();
        studentEventListener();
        studentBookingListener();

        // Organiser listener
        organizerListener();
        addEventListener();
        editEventMenuListener();
        editEventListener();

        // Admin listener
        adminListener();

    }

    /**
     * Prepares all panels for 'south' panel which displays
     * all elements by selection.
     */
    public void setUpPanels() {
        // Adding main background JPanel to main Frame
        add(background);
        // Adding northPanel to main background JPanel
        background.add(northPanel);
        // Adding southPanel to main background JPanel
        background.add(southPanel);

        southPanel.add(signIn);
        southPanel.add(signUp);
        southPanel.add(editAccount);

        southPanel.add(student);
        southPanel.add(studentEvent);
        southPanel.add(studentBooking);

        southPanel.add(organiser);
        southPanel.add(event);
        southPanel.add(editEvent);
        southPanel.add(editMenu);

        southPanel.add(admin);

        validate();
    }

    /**
     * Sign in Listener.
     *
     * Implemented buttons:
     * signIn - makes sure that correct user logs in.
     * signOut - logs out any user.
     * accountType - sets user type by the dropdown selection option
     */
    public void signInListener() {
        signIn.signIn.addActionListener(e -> {
            accountType = Objects.requireNonNull(signIn.accountType.getSelectedItem()).toString();
            switch (accountType) {
                case "Student":
                    try {
                        // selects all 'student' users for the next() step
                        db.myRs = db.myStmt.executeQuery(sv.allStudents);
                        String newRole;
                        while (db.myRs.next()) {
                            String str = db.myRs.getString("universityid");
                            String password = db.myRs.getString("password");
                            newRole = db.myRs.getString("role");
                            // Matches user university id and password with the input text and password
                            if (str.equals(signIn.idText.getText()) &&
                                    password.equals(signIn.passwordText.getText()) &&
                                    newRole.equals(accountType.toLowerCase())) {
                                user = str;
                                role = accountType.toLowerCase();
                                fName = db.myRs.getString("firstname");
                                lName = db.myRs.getString("lastname");
                                // Sets title to user name
                                title.setText(role + ": " + fName + " " + lName);
                                // Puts sign in to background
                                signIn.setVisible(false);
                                // brings student panel to front
                                student.setVisible(true);
                                validate();
                            }
                        }
                    } catch (SQLException ex) {ex.printStackTrace();}
                    break;
                case "Organiser":
                    try {
                        // selects all 'organiser' users for the next() step
                        db.myRs = db.myStmt.executeQuery(sv.allStudents);
                        String newRole;
                        while (db.myRs.next()) {
                            String str = db.myRs.getString("universityid");
                            String password = db.myRs.getString("password");
                            newRole = db.myRs.getString("role");
                            // Matches user university id and password with the input text and password
                            // Makes sure that it's an organiser login
                            if ((str.equals(signIn.idText.getText()) &&
                                    password.equals(signIn.passwordText.getText()) &&
                                    newRole.equals(accountType.toLowerCase()))) {
                                user = str;
                                role = newRole;
                                fName = db.myRs.getString("firstname");
                                lName = db.myRs.getString("lastname");
                                // Sets title to user name
                                title.setText(role + ": " + fName + " " + lName);
                                // Puts sign in to background
                                signIn.setVisible(false);
                                // brings organizer panel to front
                                organiser.setVisible(true);
                                validate();
                            }
                        }
                    } catch (SQLException ex) {ex.printStackTrace();}
                    break;
                case "Administrator":
                    try {
                        // selects all 'admin' users for the next() step
                        db.myRs = db.myStmt.executeQuery(sv.allAdmin);
                        while (db.myRs.next()) {
                            String str = db.myRs.getString("university id");
                            String password = db.myRs.getString("password");
                            // Matches admin university id and password with the input text and password
                            if (str.equals(signIn.idText.getText()) &&
                                    password.equals(signIn.passwordText.getText())) {
                                user = str;
                                role = accountType.toLowerCase();
                                fName = db.myRs.getString("firstname");
                                lName = db.myRs.getString("lastname");
                                // Sets title to user name
                                title.setText(role + ": " + fName + " " + lName);
                                // Puts sign in to background
                                signIn.setVisible(false);
                                // brings admin panel to front
                                admin.setVisible(true);
                                validate();
                            }
                        }
                    } catch (SQLException ex) {ex.printStackTrace();}
                default:
                    // Sets all values to 0 or empty string
                    eventId = 0;
                    role = "";
                    user = "";
                    studentId = "";
                    bookingId = 0;
                    accountType = "";
                    break;
            }
        });
        signIn.signUp.addActionListener(e -> {
            signUp.idText.setText("");
            signUp.password.setText("");
            signUp.firstName.setText("");
            signUp.lastName.setText("");
            signUp.email.setText("");
            title.setText("New registration");
            // Puts sign in to background
            signIn.setVisible(false);
            // brings register panel to front
            signUp.setVisible(true);
            validate();
        });
        signIn.accountType.addActionListener(e ->
                accountType = Objects.requireNonNull(signIn.accountType.getSelectedItem()).toString());
    }

    /**
     * Sign up Listener.
     *
     * Implemented buttons:
     * signUp - creates a new user as student.
     * back - goes back to main menu.
     */
    public void signUpListener() {
        signUp.signUp.addActionListener(e -> {
            try {
                db.myStmt.executeUpdate(String.format(sv.insertStudent,
                        signUp.idText.getText(),
                        signUp.password.getText(),
                        signUp.firstName.getText(),
                        signUp.lastName.getText(),
                        signUp.email.getText()));
                PopUp.message(sv.successReg, "Registration", "p");
                title.setText("Login");
                // Resets account type to be able to re-sign in
                accountType = "";
                // Puts sign up to background
                signUp.setVisible(false);
                // brings signIn to front
                signIn.setVisible(true);
                validate();

            } catch (SQLException ex) {ex.printStackTrace();}
        });
        signUp.back.addActionListener(e -> {
            title.setText("Login");
            // Resets sign in input fields for new sign in
            signIn.idText.setText("");
            signIn.passwordText.setText("");
            accountType = "";
            // hiding signUp
            signUp.setVisible(false);
            // make signIn visible again
            signIn.setVisible(true);
            validate();
        });
    }

    /**
     * edit account Listener.
     *
     * Implemented buttons:
     * edit - updates a user info.
     * back - goes back account menu.
     */
    public void editAccountListener() {
        editAccount.edit.addActionListener(e -> {
            // By default query string for admin update
            String updatedUser = sv.adminUpdate;
            try {
                if (!role.equals("administrator")) {
                    // If user is not admin, query string for student or organiser update
                    updatedUser = sv.studentUpdate;
                }
                db.myStmt.executeUpdate(String.format(updatedUser,
                        editAccount.idText.getText(),
                        editAccount.password.getText(),
                        editAccount.firstName.getText(),
                        editAccount.lastName.getText(),
                        editAccount.email.getText(), user));
            } catch (SQLException ex){ex.printStackTrace();}

            PopUp.message("Account updated", "Account", "p");
            // Sets title to user name
            title.setText(role + ": " + fName + " " + lName);
            // Puts edit account panel to background
            editAccount.setVisible(false);
            // Based on what user is logged in brings the compatible panel upfront
            if (role.equals("student")) {
                student.setVisible(true);
            } else if (role.equals("organiser")) {
                organiser.setVisible(true);
            }
            validate();
        });
        editAccount.back.addActionListener(e -> {
            // Sets title to user name
            title.setText(role + ": " + fName + " " + lName);
            // Puts edit account panel to background
            editAccount.setVisible(false);
            // Based on what user is logged in brings the compatible panel upfront
            if (role.equals("student")) {
                student.setVisible(true);
            } else if (role.equals("organiser")) {
                organiser.setVisible(true);
            }
            validate();
        });
    }

    /**
     * logout Listener.
     *
     * Resets all variables to default.
     * Selected account type to default 'student'
     */
    public void logout() {
        title.setText("Login");
        signIn.idText.setText("");
        signIn.passwordText.setText("");
        signIn.accountType.setSelectedIndex(0);
        signIn.setVisible(true);
        eventId = 0;
        role = "";
        user = "";
        studentId = "";
        bookingId = 0;
        accountType = "";
        validate();
    }

    /**
     * Student options Listener.
     *
     * Implemented buttons:
     * signOutButton - sign out from current user mode.
     * eventsButton - prepares a JTable to list all available events for booking.
     * editButton - prepares user data for editing.
     * bookingButton - prepares a JTable to list all bookings for cancellation.
     */
    public void studentListener() {
        student.signOutButton.addActionListener(e -> {
            // Sets student panel to visible
            student.setVisible(false);
            // Call of the logout() method to reset default values
            logout();
        });
        student.eventsButton.addActionListener(e -> {
            // Setting 'booking' button to disabled mode
            // Not clickable till event selected from the table
            studentEvent.booking.setEnabled(false);
            // Creates a table to display events
            CreateTable table;
            // Table header columns
            String[] header = {"ID", "Title", "Places", "Booking"};
            try {
                // Obtains row count for Object[][] creation
                db.myRs = db.myStmt.executeQuery(String.format(sv.eventByDate, sv.getTimeDateFormat(sv.dateString)));
                int rowCount = 0;
                int index = 0;
                while (db.myRs.next()) {
                    rowCount++;
                }
                // 2 dimensional Object for table population. 'rowCount' - determinate the length of the array
                Object[][] data = new Object[rowCount][];
                db.myRs = db.myStmt.executeQuery(String.format(sv.eventByDate, sv.getTimeDateFormat(sv.dateString)));
                while (db.myRs.next()) {
                    data[index] = new Object[] {
                            db.myRs.getString("eventid"),
                            db.myRs.getString("eventtitle"),
                            db.myRs.getString("placesavailable"),
                            sv.getBooking(db.myRs.getString("bookingrequired"))
                    };
                    index++;
                }
                table = new CreateTable(header, data, width, height);
                // Adding the table to a scroll pane
                studentEvent.listScroll.getViewport().add(table);
                studentEvent.listScroll.repaint();

                // Table selection listener to set the 'eventId' for later use
                table.getSelectionModel().addListSelectionListener(e2 -> {
                    eventId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                    // Enables the booking button when a row is selected
                    studentEvent.booking.setEnabled(true);
                    // Call of listHandler() method to display event details in a PopUp message box
                    listHandler(eventId);
                });

            } catch (SQLException ex) {ex.printStackTrace();}
            title.setText("All events");
            // Puts student panel to invisible
            student.setVisible(false);
            // Brings up the studentEvent
            studentEvent.setVisible(true);
            validate();
        });
        student.editButton.addActionListener(e -> {
            // Puts student panel to invisible
            student.setVisible(false);
            // Brings up the editAccount panel
            editAccount.setVisible(true);
            // Call of the editAccounts() method which sets input fields to registered data for editing
            editAccounts();
        });
        student.bookingsButton.addActionListener(e -> {
            // Setting 'cancel' button to disabled mode
            // Not clickable till event selected from the table
            studentBooking.cancel.setEnabled(false);
            // Creates a table to display event own bookings
            CreateTable table;
            // Table header columns
            String[] header = {"ID", "Date booked", "S.ID", "E.ID"};
            try {
                // Obtains row count for Object[][] creation
                db.myRs = db.myStmt.executeQuery(String.format(sv.selectedBookings, user));
                int rowCount = 0;
                int index = 0;
                while (db.myRs.next()) {
                    rowCount++;
                }
                // 2 dimensional Object for table population. 'rowCount' - determinate the length of the array
                Object[][] data = new Object[rowCount][];
                db.myRs = db.myStmt.executeQuery(String.format(sv.selectedBookings, user));
                while (db.myRs.next()) {
                    data[index] = new Object[] {
                            db.myRs.getString("bookingid"),
                            db.myRs.getString("datebooked"),
                            db.myRs.getString("studentid"),
                            db.myRs.getString("eventid")
                    };
                    index++;
                }
                table = new CreateTable(header, data, width, height);
                // Adding the table to a scroll pane
                studentBooking.listScroll.getViewport().add(table);
                studentBooking.listScroll.repaint();

                // Table selection listener to set the 'eventId', 'bookingId for later use
                table.getSelectionModel().addListSelectionListener(e12 -> {
                    bookingId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                    eventId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 3).toString());
                    // Enables the cancel button when a row is selected
                    studentBooking.cancel.setEnabled(true);
                    // Call of listHandler() method to display booking details in a PopUp message box
                    listHandler(eventId);
                });

            } catch (SQLException ex) {ex.printStackTrace();}
            title.setText("Booking list");
            // Puts studentBooking panel to invisible
            studentBooking.setVisible(true);
            // Brings up the student panel
            student.setVisible(false);
            validate();
        });
    }

    /**
     * StudentEvent options Listener.
     *
     * Implemented buttons:
     * booking - insert a booking in database.
     * back - goes back to student panel.
     */
    public void studentEventListener() {
        studentEvent.back.addActionListener(e -> {
            title.setText(role + ": " + fName + " " + lName);
            // Sets studentEvent panel to invisible
            studentEvent.setVisible(false);
            // Sets student panel to visible
            student.setVisible(true);
            validate();
        });
        studentEvent.booking.addActionListener(e -> {
            String id = "";
            try {
                // Obtains student id to insert a booking under that id
                db.myRs = db.myStmt.executeQuery(String.format(sv.selectStudent, user));
                while (db.myRs.next()) {
                    id = db.myRs.getString("studentid");
                }
                // Inserts a booking in the table
                db.myStmt.executeUpdate(String.format(sv.insertBooking, id, eventId));
                PopUp.message("Event booked", "Booking", "p");
            } catch (SQLException ex) {ex.printStackTrace();}
        });
    }

    /**
     * StudentBooking options Listener.
     *
     * Implemented buttons:
     * cancel - deletes a booking from the database.
     * back - goes back to student panel.
     */
    public void studentBookingListener() {
        studentBooking.back.addActionListener(e -> {
            title.setText(role + ": " + fName + " " + lName);
            // Sets studentBooking panel to invisible
            studentBooking.setVisible(false);
            // Sets student visible
            student.setVisible(true);
            validate();
        });
        studentBooking.cancel.addActionListener(e -> {
            try {
                // Deletes a booking based on the booking id defined before
                db.myStmt.executeUpdate(String.format(sv.deletedBooking, bookingId));
                title.setText(role + ": " + fName + " " + lName);
                // Sets studentBooking panel to invisible
                studentBooking.setVisible(false);
                // Sets student visible
                student.setVisible(true);
                validate();
                PopUp.message("Success removing from booking list!", "Booking", "i");
            } catch (SQLException ex) {ex.printStackTrace();}
        });
    }

    /**
     * Organiser Listener.
     *
     * Implemented buttons:
     * addEventButton - add new event.
     * editEventButton - prepares a table to display own events/ goes in the editMenu panel.
     * editAccountButton - edit account.
     * signOutButton - signs out from the current user mode.
     */
    public void organizerListener() {
        organiser.addEventButton.addActionListener(e -> {
            title.setText("New event");
            // Sets organiser panel to invisible
            organiser.setVisible(false);
            // Sets event panel to visible
            event.setVisible(true);
            validate();
        });
        organiser.editEventButton.addActionListener(e -> {
            // Disables edit and delete buttons till row selected in table
            editMenu.edit.setEnabled(false);
            editMenu.delete.setEnabled(false);
            // Creates a table to display events for editing
            CreateTable table;
            // Table header columns
            String[] header = {"ID", "Title", "S.ID", "Places"};
            try {
                // Obtains row count for Object[][] creation
                db.myRs = db.myStmt.executeQuery(String.format(sv.eventByUser, user));
                int rowcount = 0;
                int index = 0;
                while (db.myRs.next()) {
                    rowcount++;
                }
                // 2 dimensional Object for table population. 'rowCount' - determinate the length of the array
                Object[][] data = new Object[rowcount][];
                db.myRs = db.myStmt.executeQuery(String.format(sv.eventByUser, user));
                while (db.myRs.next()) {
                    data[index] = new Object[] {
                            db.myRs.getString("eventid"),
                            db.myRs.getString("eventtitle"),
                            db.myRs.getString("studentid"),
                            db.myRs.getString("placesavailable")
                    };
                    index++;
                }
                table = new CreateTable(header, data, width, height);
                // Adding the table to a scroll pane
                editMenu.listScroll.getViewport().add(table);
                editMenu.listScroll.repaint();

                // Table selection listener to set the 'eventId' for later use
                table.getSelectionModel().addListSelectionListener(e1 -> {
                    eventId = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                    // Enables buttons to edit or delete a selected row
                    editMenu.edit.setEnabled(true);
                    editMenu.delete.setEnabled(true);
                    // Call of listHandler() method to display event details in a PopUp message box
                    listHandler(eventId);
                });

            } catch (SQLException ex) {ex.printStackTrace();}
            title.setText("All events");
            // Sets organiser panel to invisible
            organiser.setVisible(false);
            // Sets editMenu visible
            editMenu.setVisible(true);
            validate();
        });
        organiser.editAccountButton.addActionListener(e -> {
            // Sets organiser to invisible
            organiser.setVisible(false);
            // Sets editAccount visible
            editAccount.setVisible(true);
            // Call of the editAccounts() method for current user details editing
            editAccounts();
        });
        organiser.signOutButton.addActionListener(e -> {
            // Sets organiser to invisible
            organiser.setVisible(false);
            // Call of the logout() method to reset default values
            logout();
        });
    }

    /**
     * add event Listener.
     *
     * Implemented buttons:
     * create - create a new event.
     * back - goes back to organiser panel.
     */
    public void addEventListener() {
        event.create.addActionListener(e -> {
            try {
                String id = "";
                // Selected user based on 'user' type
                db.myRs = db.myStmt.executeQuery(String.format(sv.selectStudent, user));
                while (db.myRs.next()) {
                    id = db.myRs.getString("studentid");
                }
                // Inserts an event in the database
                db.myStmt.executeUpdate(String.format(sv.insertEvent,
                        id,
                        event.title.getText(),
                        event.description.getText(),
                        Objects.requireNonNull(event.category.getSelectedItem()).toString(),
                        event.date.getTextField().getText(),
                        event.time.getTextField().getText(),
                        event.location.getText(),
                        event.roomNo.getText(),
                        event.url.getText(),
                        event.placesAvailable.getText(),
                        sv.setBooking(Objects.requireNonNull(event.bookingRequired.getSelectedItem()).toString())));
                PopUp.message(sv.successAddedEvent, "Event", "i");
                // Sets event panel invisible
                event.setVisible(false);
                // Call of the resetFields method to reset al default values to original state
                resetFields();

            } catch (SQLException ex) {ex.printStackTrace();}
        });
        event.back.addActionListener(e -> {
            // Sets event panel invisible
            event.setVisible(false);
            // Call of the resetFields method to reset al default values to original state
            resetFields();
        });
    }

    /**
     * edit event Listener.
     *
     * Implemented buttons:
     * update - updates an event.
     * back - goes back to organiser panel.
     */
    public void editEventListener() {
        editEvent.update.addActionListener(e -> {
            try {
                // Updates an event
                db.myStmt.executeUpdate(String.format(sv.updateEvent,
                        editEvent.title.getText(),
                        editEvent.description.getText(),
                        Objects.requireNonNull(editEvent.category.getSelectedItem()).toString(),
                        editEvent.date.getTextField().getText(),
                        editEvent.time.getTextField().getText(),
                        editEvent.location.getText(),
                        editEvent.roomNo.getText(),
                        editEvent.url.getText(),
                        editEvent.placesAvailable.getText(),
                        sv.setBooking(Objects.requireNonNull(editEvent.bookingRequired.getSelectedItem()).toString()),
                        studentId
                ));
                PopUp.message(sv.successAddedEvent, "Event", "i");
                // Sets edit event panel to invisible
                editEvent.setVisible(false);
                // Call of the resetFields method to reset al default values to original state
                resetFields();
            } catch (SQLException ex) {ex.printStackTrace();}
        });
        editEvent.back.addActionListener(e -> {
            // Sets editEvent to invisible
            editEvent.setVisible(false);
            // Call of the resetFields method to reset al default values to original state
            resetFields();
        });
    }

    /**
     * edit event menu Listener.
     *
     * Implemented buttons:
     * edit - fills input fields with event data from database.
     * delete - deletes an event and all bookings connected to that event.
     * back - goes back to organiser panel
     */
    public void editEventMenuListener() {
        editMenu.edit.addActionListener(e -> {
            try {
                // Selects event based on eventId
                db.myRs = db.myStmt.executeQuery(String.format(sv.selectEvent, eventId));
                String newURL = "";
                while (db.myRs.next()) {
                    // If URL column is null, lets empty, otherwise populates url field with URL from database
                    if (db.myRs.getString("URL") != null) {
                        newURL = db.myRs.getString("URL").trim();
                    }
                    editEvent.title.setText(db.myRs.getString("eventtitle").trim());
                    editEvent.description.setText(db.myRs.getString("eventdescription").trim());
                    editEvent.location.setText(db.myRs.getString("location").trim());
                    editEvent.roomNo.setText(db.myRs.getString("roomno").trim());
                    editEvent.url.setText(newURL);
                    editEvent.placesAvailable.setText(db.myRs.getString("placesavailable").trim());
                    editEvent.dateSpinner.setValue(db.myRs.getDate("date"));
                    editEvent.timeSpinner.setValue(db.myRs.getTime("time"));
                    editEvent.category.setSelectedItem(db.myRs.getObject("category"));
                    editEvent.bookingRequired.setSelectedItem(sv.getBooking(db.myRs.getString("bookingrequired")));
                }
            } catch (SQLException ex) {ex.printStackTrace();}
            title.setText("Edit event");
            // Sets editMenu invisible
            editMenu.setVisible(false);
            // Sets editEvent visible
            editEvent.setVisible(true);
            // Enables edit and delete buttons
            editMenu.edit.setEnabled(false);
            editMenu.delete.setEnabled(false);
            validate();
        });
        editMenu.delete.addActionListener(e -> {
            try {
                // Deletes event and all booking connected to that event
                db.myStmt.executeUpdate(String.format(sv.deleteBookingByEvent, eventId));
                db.myStmt.executeUpdate(String.format(sv.deleteEvent, eventId));
                title.setText(role + ": " + fName + " " + lName);
                // Sets editMenu to invisible
                editMenu.setVisible(false);
                // Sets organiser to visible
                organiser.setVisible(true);
                // Disables edit and delete buttons
                editMenu.delete.setEnabled(false);
                editMenu.edit.setEnabled(false);
                PopUp.message("Event deleted!", "Event", "w");
            } catch (SQLException ex) {ex.printStackTrace();}
        });
        editMenu.back.addActionListener(e -> {
            title.setText(role + ": " + fName + lName);
            // Sets editMenu to invisible
            editMenu.setVisible(false);
            // Sets organiser to visible
            organiser.setVisible(true);
            validate();
        });
    }

    /**
     * admin Listener.
     *
     * Implemented buttons:
     * eventButton - prepares events menu - not implemented.
     * bookingButton - prepares bookings menu - not implemented.
     * rightsButton - prepares granting user menu - not implemented.
     * signOutButton - logout from admin user mode
     */
    public void adminListener() {
        // Action listener not implemented yet!
        admin.eventButton.addActionListener(e -> {});
        // Action listener not implemented yet!
        admin.bookingButton.addActionListener(e -> {});
        // Action listener not implemented yet!
        admin.rightsButton.addActionListener(e -> {});

        admin.signOutButton.addActionListener(e -> {
            // Sets admin invisible
            admin.setVisible(false);
            // Call of the logout() method to reset default values
            logout();
        });
    }

    /**
     * resetFields method.
     *
     * Resets all default values to original state
     */
    public void resetFields() {
        title.setText(role + ": " + fName + " " + lName);
        event.title.setText("");
        event.description.setText("");
        event.location.setText("");
        event.roomNo.setText("");
        event.url.setText("");
        event.placesAvailable.setText("");
        event.category.setSelectedIndex(0);
        event.bookingRequired.setSelectedIndex(0);
        // Sets organiser visible
        organiser.setVisible(true);
        validate();
    }

    /**
     * edit account method.
     *
     * Prepares input fields with user data based on account type
     */
    public void editAccounts() {
        if (role.equals("student") | role.equals("organiser") | role.equals("administrator")) {
            try {
                String selectedUser = sv.selectAdmin;
                if (!role.equals("administrator")) {
                    selectedUser = sv.selectStudent;
                }
                db.myRs = db.myStmt.executeQuery(String.format(selectedUser, user));
                while (db.myRs.next()) {
                    editAccount.idText.setText(db.myRs.getString("universityid").trim());
                    editAccount.password.setText(db.myRs.getString("password").trim());
                    editAccount.firstName.setText(db.myRs.getString("firstname").trim());
                    editAccount.lastName.setText(db.myRs.getString("lastname").trim());
                    editAccount.email.setText(db.myRs.getString("email").trim());
                }

            } catch (SQLException ex) {ex.printStackTrace();}
        }
        title.setText("Edit: " + fName + " " + lName);
        validate();
    }

    /**
     * listHandler method.
     *
     * Prepares event details in JOptionPane using PopUp class
     */
    public void listHandler(int id) {
        try {
            db.myRs = db.myStmt.executeQuery(String.format(sv.selectEvent, id));
            while (db.myRs.next()) {
                String message = String.format(sv.eventDetail,
                        db.myRs.getString("eventtitle"),
                        db.myRs.getString("eventdescription"),
                        db.myRs.getString("category"),
                        db.myRs.getString("time"),
                        db.myRs.getString("date"),
                        db.myRs.getString("location"),
                        db.myRs.getString("roomno"),
                        db.myRs.getString("URL"),
                        db.myRs.getString("placesavailable"),
                        db.myRs.getString("bookingrequired"));
                PopUp.message(message, "Event detail", "p");
            }
        } catch (SQLException ex) {ex.printStackTrace();}
    }
}

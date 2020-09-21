import javax.swing.*;
import javax.swing.text.DateFormatter;
import java.awt.*;

/**
 * EditEvent class extends JPanel..
 * This class prepares a JPanel for the main frame for editing an event.
 *
 * Place:     University of Bedfordshire
 * Author:    Oszkar Feher
 * Date:      7/4/20
 */
class EditEvent extends JPanel {
    // Input field labels
    protected CreateLabel titleLabel;
    protected CreateLabel descriptionLabel;
    protected CreateLabel roomLabel;
    protected CreateLabel urlLabel;
    protected CreateLabel locationLabel;
    protected CreateLabel categoryLabel;
    protected CreateLabel bookingRequiredLabel;
    protected CreateLabel dateLabel;
    protected CreateLabel timeLabel;
    protected CreateLabel placeAvailableLabel;

    // Input fields
    protected CreateTextField title;
    protected JTextArea description;
    protected JScrollPane descScroll;
    protected CreateTextField roomNo;
    protected CreateTextField url;
    protected CreateTextField placesAvailable;
    protected JTextArea location;
    protected JScrollPane locScroll;

    // Scroll pane for displaying a list of records
    // It's used in EventManager class, a JTable is added
    protected JComboBox<String> category;
    protected JComboBox<String> bookingRequired;

    // Date model, spinner and formatter
    protected SpinnerDateModel dateModel;
    protected SpinnerDateModel timeModel;
    protected DateFormatter dateFormatter;
    protected DateFormatter timeFormatter;
    protected JSpinner.DateEditor date;
    protected JSpinner.DateEditor time;

    protected JSpinner dateSpinner;
    protected JSpinner timeSpinner;

    // Buttons
    protected CreateButton update;
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
    public EditEvent(int width, int height) {
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
        titleLabel = new CreateLabel("Title", Color.lightGray, sv.inputLabel);
        titleLabel.setBounds(xPosition - 220, 13, 170, 30);

        descriptionLabel = new CreateLabel("Description", Color.lightGray, sv.inputLabel);
        descriptionLabel.setBounds(xPosition - 220, 52, 170, 30);

        roomLabel = new CreateLabel("Room No.", Color.lightGray, sv.inputLabel);
        roomLabel.setBounds(xPosition - 220, 91, 170, 30);

        urlLabel = new CreateLabel("URL", Color.lightGray, sv.inputLabel);
        urlLabel.setBounds(xPosition - 220, 130, 170, 30);

        locationLabel = new CreateLabel("Location", Color.lightGray, sv.inputLabel);
        locationLabel.setBounds(xPosition - 220, 169, 170, 30);

        categoryLabel = new CreateLabel("Category", Color.lightGray, sv.inputLabel);
        categoryLabel.setBounds(xPosition - 220, 208, 170, 30);

        bookingRequiredLabel = new CreateLabel("Booking required", Color.lightGray, sv.inputLabel);
        bookingRequiredLabel.setBounds(xPosition - 220, 247, 170, 30);

        dateLabel = new CreateLabel("Date", Color.lightGray, sv.inputLabel);
        dateLabel.setBounds(xPosition - 220, 286, 170, 30);

        timeLabel = new CreateLabel("Time", Color.lightGray, sv.inputLabel);
        timeLabel.setBounds(xPosition - 220, 325, 170, 30);

        placeAvailableLabel = new CreateLabel("Available places", Color.lightGray, sv.inputLabel);
        placeAvailableLabel.setBounds(xPosition - 220, 364, 170, 30);

        // Input fields
        title = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        title.setBounds(xPosition, 13, 270, 30);

        description = new JTextArea();
        description.setBackground(Color.lightGray);
        description.setForeground(sv.purple);
        description.setFont(sv.idTextFont);
        description.setRows(3);
        descScroll = new JScrollPane(description);
        descScroll.setBounds(xPosition, 52, 270, 30);
        descScroll.setBorder(null);
        descScroll.setHorizontalScrollBar(null);

        roomNo = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        roomNo.setBounds(xPosition, 91, 270, 30);

        url = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        url.setBounds(xPosition, 130, 270, 30);

        location = new JTextArea();
        location.setBackground(Color.lightGray);
        location.setForeground(sv.purple);
        location.setFont(sv.idTextFont);
        location.setRows(3);
        locScroll = new JScrollPane(location);
        locScroll.setBounds(xPosition, 169, 270, 30);
        locScroll.setBorder(null);
        locScroll.setHorizontalScrollBar(null);

        // Category options when an event is created
        String[] categoryType = { "Lecture", "Workshop", "Hackathon", "External Visit" };
        category = new JComboBox<>(categoryType);
        category.setBounds(xPosition, 208, 270, 30);
        category.setBorder(null);
        category.setBackground(Color.lightGray);
        category.setForeground(sv.purple);
        category.setFont(sv.idTextFont);

        // Booking required options
        String[] booking = { "Yes", "No"};
        bookingRequired = new JComboBox<>(booking);
        bookingRequired.setBounds(xPosition, 247, 270, 30);
        bookingRequired.setBorder(null);
        bookingRequired.setBackground(Color.lightGray);
        bookingRequired.setForeground(sv.purple);
        bookingRequired.setFont(sv.idTextFont);

        // Date Model. For editing an event it's need 2 date model
        // One for date to store just the date
        // One for time to store just time
        // - editEvent.dateSpinner.setValue(db.myRs.getDate("date")) - in main class
        // set the value for time to default value
        dateModel = new SpinnerDateModel();
        timeModel = new SpinnerDateModel();

        // Spinners and formatter for date and time
        dateSpinner = new JSpinner(dateModel);
        dateSpinner.setBorder(null);
        date = new JSpinner.DateEditor(dateSpinner, sv.dateFormatter);
        date.setBounds(xPosition, 286, 270, 30);
        date.setBorder(null);
        date.getComponent(0).setForeground(sv.purple);
        date.getComponent(0).setBackground(Color.lightGray);
        date.getComponent(0).setFont(sv.idTextFont);
        dateFormatter = (DateFormatter) date.getTextField().getFormatter();
        dateFormatter.setAllowsInvalid(false);
        dateFormatter.setOverwriteMode(true);

        timeSpinner = new JSpinner(timeModel);
        timeSpinner.setBorder(null);
        time = new JSpinner.DateEditor(timeSpinner, sv.timeFormatter);
        time.setBounds(xPosition, 325, 270, 30);
        time.getComponent(0).setBackground(Color.lightGray);
        time.getComponent(0).setForeground(sv.purple);
        time.getComponent(0).setFont(sv.idTextFont);
        timeFormatter = (DateFormatter) time.getTextField().getFormatter();
        timeFormatter.setAllowsInvalid(false);
        timeFormatter.setOverwriteMode(true);

        placesAvailable = new CreateTextField(Color.lightGray, sv.purple, sv.idTextFont, 40);
        placesAvailable.setBounds(xPosition, 364, 270, 30);

        // Buttons
        update = new CreateButton("Update", sv.lightPurple, Color.lightGray, sv.buttonFont);
        update.setBounds(this.width / 2, 15, 150, 30);

        back = new CreateButton("Back", sv.lightPurple, Color.lightGray, sv.buttonFont);
        back.setBounds(this.width / 2,35 + update.getHeight(), 150, 30);

        // Adding 'north' and 'south panel to main panel
        add(north);
        add(south);
        // Adding all elements to 'north' and 'south' panel
        north.add(titleLabel);
        north.add(descriptionLabel);
        north.add(roomLabel);
        north.add(urlLabel);
        north.add(locationLabel);
        north.add(categoryLabel);
        north.add(dateLabel);
        north.add(timeLabel);
        north.add(placeAvailableLabel);
        north.add(bookingRequiredLabel);

        north.add(title);
        north.add(descScroll);
        north.add(roomNo);
        north.add(url);
        north.add(locScroll);
        north.add(category);
        north.add(date);
        north.add(time);
        north.add(placesAvailable);
        north.add(bookingRequired);

        south.add(update);
        south.add(back);
    }
}

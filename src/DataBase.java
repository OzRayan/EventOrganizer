import java.sql.*;
/**
 * DataBase class for connecting to the database.
 * url: "jdbc:mysql://localhost/eems"
 * DRIVER: "com.mysql.jdbc.Driver"
 *
 * University of Bedfordshire
 * Oszkar Feher
 * 7/4/20
 */
public class DataBase {

    // Protected variables
    protected Connection myConn = null;
    protected Statement myStmt = null;
    protected ResultSet myRs = null;
    protected String DRIVER = "com.mysql.jdbc.Driver";
    protected String URL = "jdbc:mysql://localhost/eems";
    protected String user = "root";
    protected String pass = "";

    /**
     * Constructor
     */
    public DataBase() {
        try {
            Class.forName(DRIVER);
            // Connection
            myConn = DriverManager.getConnection(URL, user, pass);
            myStmt = myConn.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}

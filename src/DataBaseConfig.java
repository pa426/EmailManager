
import java.io.File;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.apache.derby.drda.NetworkServerControl;

public class DataBaseConfig {

    private Connection con;
    private Statement st;
    private ResultSet rs;
    int id = 0;

    public DataBaseConfig() {

        String sourceURL = "jdbc:derby://localhost:1527/" + new File("EmailDB").getAbsolutePath() + ";";

        try {
            NetworkServerControl server = new NetworkServerControl();
            server.start(null);
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");

            con = DriverManager.getConnection(sourceURL, "student", "student");
            st = con.createStatement();

        } catch (Exception ex) {
            System.out.println("Error1: " + ex);
        }
    }

    public void setData(String emailAddress, String recipient, String subject, String message) {

        try {

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String date = dateFormat.format(cal.getTime());
            String sender = emailAddress;
            int nid = getID();

            String query = " insert into STUDENT.MESSAGES (id, priority, sender, recipient, label, subject, message, date)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, nid);
            preparedStmt.setString(2, "");
            preparedStmt.setString(3, sender);
            preparedStmt.setString(4, recipient);
            preparedStmt.setString(5, "");
            preparedStmt.setString(6, subject);
            preparedStmt.setString(7, message);
            preparedStmt.setString(8, date);

            preparedStmt.execute();

            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    
    public Object[][] getData(String emailAddress) {

        Object[][] row = new Object[99][6];

        try {
            String query = "select * from STUDENT.MESSAGES where recipient = '"+emailAddress+"'"
                    + "  OR sender = '"+emailAddress+"' ORDER BY id ASC ";
            rs = st.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String priority = rs.getString("priority");
                String recipient = rs.getString("recipient");
                String labelDb = rs.getString("label");
                String subject = rs.getString("subject");
                String date = rs.getString("date");

                row[i][0] = id;
                row[i][1] = priority;
                row[i][2] = recipient;
                row[i][3] = labelDb;
                row[i][4] = subject;
                row[i][5] = date;

                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error2: " + ex);
        }
        return row;
    }

    public Object[][] sentBox(String emailAddress) {

        Object[][] row = new Object[99][6];

        try {
            String query = "select * from STUDENT.MESSAGES where sender = '"+ emailAddress +"'  ORDER BY id ASC";
            rs = st.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String priority = rs.getString("priority");
                String recipient = rs.getString("recipient");
                String labelDb = rs.getString("label");
                String subject = rs.getString("subject");
                String date = rs.getString("date");

                row[i][0] = id;
                row[i][1] = priority;
                row[i][2] = recipient;
                row[i][3] = labelDb;
                row[i][4] = subject;
                row[i][5] = date;

                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error3: " + ex);
        }
        return row;
    }

    public Object[][] inBox(String emailAddress) {

        Object[][] row = new Object[99][6];

        try {
            String query = "select * from STUDENT.MESSAGES where recipient = '"+ emailAddress +"' ORDER BY id ASC";
            rs = st.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String priority = rs.getString("priority");
                String sender = rs.getString("sender");
                String labelDb = rs.getString("label");
                String subject = rs.getString("subject");
                String date = rs.getString("date");

                row[i][0] = id;
                row[i][1] = priority;
                row[i][2] = sender;
                row[i][3] = labelDb;
                row[i][4] = subject;
                row[i][5] = date;
                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error4: " + ex);
        }
        return row;
    }

    public int getID() {

        try {
            String query = "SELECT * FROM STUDENT.MESSAGES ORDER BY id DESC ";

            rs = st.executeQuery(query);

            while (rs.next()) {
                id = rs.getInt("id");
                id++;
                break;
            }
        } catch (Exception ex) {
            System.out.println("Error5: " + ex);
        }
        return id;
    }

    public void updatePriority(String newPriority, int newID) {

        try {

            String query = " update STUDENT.MESSAGES set priority = ? where id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, newPriority);
            preparedStmt.setInt(2, newID);

            preparedStmt.execute();

        } catch (Exception e) {
            System.err.println("Got an update exception!");
            System.err.println(e.getMessage());
        }

    }

    public void updateLabel(String newLabel, int newID) {

        try {

            String query = " update STUDENT.MESSAGES set label = ? where id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, newLabel);
            preparedStmt.setInt(2, newID);

            preparedStmt.execute();

        } catch (Exception e) {
            System.err.println("Got an update exception!");
            System.err.println(e.getMessage());
        }

    }

    public void deleteData(int delId) {

        try {

            String query = " DELETE FROM STUDENT.MESSAGES WHERE id=?";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setInt(1, delId);

            preparedStmt.execute();

        } catch (Exception e) {
            System.err.println("Got an update exception!");
            System.err.println(e.getMessage());
        }

    }

    public Object[][] searchData(String searchWord, String emailAddress) {
        Object[][] row = new Object[30][6];

        try {
            String query = "SELECT * FROM STUDENT.MESSAGES WHERE recipient = '"+emailAddress+ "' AND sender = '"+emailAddress+"'"
                    + "AND sender LIKE '% " + searchWord + "%' "
                    + "OR recipient LIKE '%" + searchWord + "%' OR label LIKE '%" + searchWord + "%' "
                    + "OR subject LIKE '%" + searchWord + "%' OR message LIKE '%" + searchWord + "%' "
                    + " ORDER BY id ASC ";

            rs = st.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                int id = rs.getInt("id");
                String priority = rs.getString("priority");
                String recipient = rs.getString("recipient");
                String labelDb = rs.getString("label");
                String subject = rs.getString("subject");
                String date = rs.getString("date");

                row[i][0] = id;
                row[i][1] = priority;
                row[i][2] = recipient;
                row[i][3] = labelDb;
                row[i][4] = subject;
                row[i][5] = date;

                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error6: searchData " + ex);
        }
        return row;

    }

    public Object[] readData(int id) {
        Object[] read = new Object[30];

        try {
            PreparedStatement prepareStatement = con.prepareStatement("SELECT * from STUDENT.MESSAGES where id = ?");
            prepareStatement.setInt(1, id);
            rs = prepareStatement.executeQuery();

            while (rs.next()) {
                String priority = rs.getString("priority");
                String sender = rs.getString("sender");
                String recipient = rs.getString("recipient");
                String labelDb = rs.getString("label");
                String subject = rs.getString("subject");
                String message = rs.getString("message");
                String date = rs.getString("date");

                read[0] = priority;
                read[1] = recipient;
                read[2] = labelDb;
                read[3] = subject;
                read[4] = sender;
                read[5] = message;
                read[6] = date;
            }
        } catch (Exception ex) {
            System.out.println("Error7: " + ex);
        }
        return read;
    }

    public Object[] getlogInData(String mailAddress) {

        Object[] row = new Object[30];

        try {

            PreparedStatement prepareStatement = con.prepareStatement("select * from STUDENT.LOGIN  where email = ?");
            prepareStatement.setString(1, mailAddress);
            rs = prepareStatement.executeQuery();

            int i = 0;
            while (rs.next()) {

                String email = rs.getString("email");
                String lastName = rs.getString("lastname");
                String firstName = rs.getString("firstname");
                String password = rs.getString("password");
                String imageurl = rs.getString("imageurl");

                row[0] = email;
                row[1] = lastName;
                row[2] = firstName;
                row[3] = password;
                row[4] = imageurl;

                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error8: " + ex);
        }
        return row;
    }

    public void setlogInData(String email, String lastName, String firstName, String password, String imageUrl) {

        try {
            String query = " insert into STUDENT.LOGIN (lastname, firstname, password, email, imageurl)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = con.prepareStatement(query);

            preparedStmt.setString(1, lastName);
            preparedStmt.setString(2, firstName);
            preparedStmt.setString(3, password);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, imageUrl);

            preparedStmt.execute();

            con.close();
        } catch (Exception e) {
            System.err.println("Got an exception update logInData!");
            System.err.println(e.getMessage());
        }

    }
    
     public String[] getUsersLogin() {
         int nr = countUsers();
        String[] row = new String[nr];

        try {
            String query = "select * from STUDENT.login";
            rs = st.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                
                String email = rs.getString("email");
                row[i] = email;
                i++;
            }
        } catch (Exception ex) {
            System.out.println("Error2: " + ex);
        }
        return row;
    }
     
     public int countUsers() {

        int nr = 0;

        try {
            String query = "select * from STUDENT.login";
            rs = st.executeQuery(query);

            int i = 0;
            while (rs.next()) {
                nr++;
            }
        } catch (Exception ex) {
            System.out.println("Error2: " + ex);
        }
        return nr;
    }

}

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login {
    Connection con;
    Statement st;
    ResultSet rs;

    JFrame f = new JFrame("User Login");
    JLabel l = new JLabel("Username:");
    JLabel l1 = new JLabel("Password:");
    JTextField t = new JTextField(10);
    JTextField t1 = new JTextField(10);
    JButton b = new JButton("Login");

    public Login () {
        connect();
        frame();
    }

    public void connect() {
        try {
            String driver = "sun,jdbc.odbc.JdbcOdbcDriver";
            Class.forName(driver);

            String db = "jdbc:odbc:db1"; // db1 ist Name der Datenbank
            con = DriverManager.getConnection(db);
            st = con.createStatement();
        } catch (Exception e) {
           
        }
    }

    public void frame() {
        f.setSize(600, 400);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        JPanel p = new JPanel();
        p.add(l);
        p.add(t);
        p.add(t1);
        p.add(l1);
        p.add(b);

        f.add(p);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String user = t.getText().trim();
                    String pass = t1.getText().trim();

                    String sqlQuery = "select user, pass from Table2 where user = '" + user + "'and pass = '" + pass + "'";
                    rs = st.executeQuery(sqlQuery);

                    int count = 0;

                    while(rs.next()) {
                        count = count++;
                    }
                    if (count == 1) {
                         JOptionPane.showMessageDialog(null, "User found, access granted.");
                    }
                    else if (count > 1) {
                        JOptionPane.showMessageDialog(null, "Duplicate User, access denied.");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "User not found.");
                    } 
                } catch (Exception ex) {
                    
                }
            }
        });
    }
    public static void main(String[] args) {
        new Login();
    }

}
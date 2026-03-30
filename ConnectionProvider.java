
package dorm.app;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
public class ConnectionProvider {
        
    // Database Credentials (Change these if your MySQL setup is different)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dorm_db?useSSL=false";
    private static final String DB_USER = "root"; // Default XAMPP user
    private static final String DB_PASS = "1997";     // Default XAMPP password (often empty)
    
    public static Connection getCon() {
        try {
            // 1. Load the MySQL JDBC Driver class
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // 2. Establish the connection using DriverManager
            Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            return con;
            
        } catch (Exception e) {
            // If anything goes wrong (driver not found, wrong password, DB down)
            JOptionPane.showMessageDialog(null, "DB Connection Error: " + e.getMessage());
            return null;
        }
    }
    
}

package dorm.app;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;
public class StudentManageFrame extends javax.swing.JFrame {

JTextField txtName, txtMobile, txtEmail, txtAddress;
    JComboBox<String> cmbRoomNumber;
    JComboBox<String> cmbStatus;
    JTable table;
    DefaultTableModel tableModel; // The data handler for the JTable
    String selectedStudentId = null; // To store ID when editing a specific row
    public StudentManageFrame() {
        
        setTitle("Manage Students");
        setSize(900, 600);
        setLocationRelativeTo(null);
        // DISPOSE_ON_CLOSE ensures only this window closes, not the whole app (dashboard remains)
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // --- WEST PANEL: Input Fields ---
        JPanel pnlInput = new JPanel(new GridLayout(7, 2, 10, 10));
        pnlInput.setBorder(BorderFactory.createTitledBorder("Student Details"));
        pnlInput.setPreferredSize(new Dimension(300, 0));

        // Add form components
        

        
        
        
        initComponents();
        setLocationRelativeTo(null);
    
    // 1. Load the room options into the combo box FIRST
    fillRoomComboBox(); 
    
    // 2. Load the main student data into the table
    loadStudentData();
    }
// Inside StudentManagementFram.java

/**
 * loadStudentData(): Connects to the database, fetches all students, and displays them in tblStudents.
 */
public void loadStudentData() {
    // 1. Get the table model
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
    model.setRowCount(0); // Clear old data

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    try {
        con = ConnectionProvider.getCon();
        if (con == null) return; 

        st = con.createStatement();
        // 2. Execute the query to get all student data
        rs = st.executeQuery("SELECT * FROM STUDENT ORDER BY StudentID");
        
        while (rs.next()) {
            // 3. Add the fetched data as a new row to the JTable model
            model.addRow(new Object[]{
                rs.getString("StudentID"),
                rs.getString("StudentName"),
                rs.getString("RoomNo"),      // Foreign Key data
                rs.getString("MobileNo"),
                rs.getString("Address"),
                rs.getString("LivingStatus")
            });
        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error fetching student data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    } finally {
        // 4. Close all resources
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
public void fillRoomComboBox() {
    jComboBox2.removeAllItems(); // Clear previous items

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    try {
        con = ConnectionProvider.getCon();
        if (con == null) return;
        
        st = con.createStatement();
        // Fetch all room numbers
        rs = st.executeQuery("SELECT RoomNumber FROM ROOM ORDER BY RoomNumber");
        
        while (rs.next()) {
            // Add each RoomNumber to the combo box
            jComboBox2.addItem(rs.getString("RoomNumber"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading Room Numbers: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) { }
    }
}
// Implement this method for your tblStudents JTable in Design View -> Events -> Mouse -> mouseClicked

private void tblStudentsMouseClicked(java.awt.event.MouseEvent evt) {                                      
    int selectedRow = jTable1.getSelectedRow();
    if (selectedRow >= 0) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        // 1. Get data from the table columns (Column 0 is StudentID, 1 is Name, etc.)
        String studentID = model.getValueAt(selectedRow, 0).toString();
        String studentName = model.getValueAt(selectedRow, 1).toString(); 
        String roomNo = model.getValueAt(selectedRow, 2).toString(); 
        String mobileNo = model.getValueAt(selectedRow, 3).toString();
        String address = model.getValueAt(selectedRow, 4).toString();
        String status = model.getValueAt(selectedRow, 5).toString();
        
        // 2. Populate the input fields
        jTextField1.setText(studentID);
        jTextField2.setText(studentName);
        jTextField4.setText(mobileNo);
        jTextField5.setText(address);
        
        // 3. Select the correct item in the Combo Boxes
        jComboBox2.setSelectedItem(roomNo);
        jComboBox1.setSelectedItem(status);
        
        // 4. Crucial: Disable editing the ID field when updating
        jTextField1.setEditable(false); 
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Add/Update Student");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Student ID:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Student Name:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Room no:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Mobile No:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Adress:");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Living status:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Living", "Leaved" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(255, 0, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 204, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("ADD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 204, 204));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(153, 0, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("BACK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jTextField1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(48, 48, 48)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1)
                                .addGap(79, 79, 79))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton3)
                                .addGap(74, 74, 74)
                                .addComponent(jButton4)
                                .addGap(18, 18, 18))))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jButton5)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jButton2)
                    .addContainerGap(224, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addComponent(jLabel5))
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(292, Short.MAX_VALUE)
                    .addComponent(jButton2)
                    .addGap(91, 91, 91)))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel8.setText("All Students");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Student ID", "Student Name", "Room No", "Mobile NO", "Adress", "Lving Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 548, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 960, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(26, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(25, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 502, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
// --- 1. Gather Input Data ---
    String studentID = jTextField1.getText().trim().toUpperCase();
    String studentName = jTextField2.getText().trim();
    String roomNo = (String) jComboBox2.getSelectedItem(); // Get RoomNo from the combo box
    String mobileNo = jTextField4.getText().trim();
    String address = jTextField5.getText().trim();
    // Default the status for a new student
    String livingStatus = "Living"; 
    
    // --- 2. Basic Validation ---
    if (studentID.isEmpty() || studentName.isEmpty() || roomNo == null || mobileNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Student ID, Name, Mobile, and Room No are required.", 
                                      "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = ConnectionProvider.getCon();
        if (con == null) return; // Connection failed
        
        // --- 3. SQL INSERT Statement ---
        String sql = "INSERT INTO STUDENT (StudentID, StudentName, RoomNo, MobileNo, Address, LivingStatus) VALUES (?, ?, ?, ?, ?, ?)";
        ps = con.prepareStatement(sql);
        
        // Set parameters based on their position in the SQL query:
        ps.setString(1, studentID);
        ps.setString(2, studentName);
        ps.setString(3, roomNo);      // FK to the ROOM table
        ps.setString(4, mobileNo);
        ps.setString(5, address);
        ps.setString(6, livingStatus);

        // --- 4. Execute and Confirm ---
        ps.executeUpdate();
        JOptionPane.showMessageDialog(this, "Student " + studentID + " registered successfully!", 
                                      "Success", JOptionPane.INFORMATION_MESSAGE);
        
        // --- 5. Clean Up and Refresh ---
        loadStudentData(); // Reloads the table to show the new student
        
        // Call the clear method using the correct ActionEvent instance
        jButton3ActionPerformed(new java.awt.event.ActionEvent(this, 
                                                               java.awt.event.ActionEvent.ACTION_PERFORMED, 
                                                               "Clear")); 
        
    } catch (SQLException e) {
        if (e.getErrorCode() == 1062) { // MySQL error code for Duplicate Primary Key (StudentID)
            JOptionPane.showMessageDialog(this, "Error: Student ID " + studentID + " already exists.", 
                                          "Duplicate Entry", JOptionPane.ERROR_MESSAGE);
        } else if (e.getErrorCode() == 1452) { // Foreign Key constraint failure (RoomNo not found)
            JOptionPane.showMessageDialog(this, "Error: Room Number " + roomNo + " does not exist or is invalid.", 
                                          "Data Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Database Error during ADD: " + e.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        // --- 6. Close Resources ---
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) { 
             e.printStackTrace(); 
        }
    }       
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
// 1. Reset Text Fields to empty strings
    jTextField1.setText("");
    jTextField2.setText("");
    jTextField4.setText("");
    jTextField5.setText("");
    
    // 2. Reset Combo Boxes 
    // Set the Room Number selector back to the first item
    if (jComboBox2.getItemCount() > 0) {
        jComboBox2.setSelectedIndex(0); 
    }
    // Set the Living Status selector back to the default (e.g., 'Living')
    if (jComboBox1.getItemCount() > 0) {
        // You may need to find the index of "Living" or just set to index 0
        jComboBox1.setSelectedIndex(0); 
    }
    
    // 3. Re-enable the Student ID field (in case it was disabled for an update)
    jTextField1.setEditable(true); 
    
    // 4. Set the cursor focus back to the primary input field
    jTextField1.requestFocus();     
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
jComboBox2.removeAllItems(); // Clear previous items

    Connection con = null;
    Statement st = null;
    ResultSet rs = null;

    try {
        con = ConnectionProvider.getCon();
        if (con == null) return;
        
        st = con.createStatement();
        // Fetch all room numbers
        rs = st.executeQuery("SELECT RoomNumber FROM ROOM ORDER BY RoomNumber");
        
        while (rs.next()) {
            // Add each RoomNumber to the combo box
            jComboBox2.addItem(rs.getString("RoomNumber"));
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error loading Room Numbers: " + e.getMessage(), "DB Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null) con.close();
        } catch (SQLException e) { }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // --- 1. Gather Input Data ---
    // The StudentID is used in the WHERE clause (it identifies the record)
    String studentID = jTextField1.getText().trim().toUpperCase();
    
    // The rest of the fields contain the NEW data
    String studentName = jTextField2.getText().trim();
    String roomNo = (String) jComboBox2.getSelectedItem(); 
    String mobileNo = jTextField4.getText().trim();
    String address = jTextField5.getText().trim();
    String livingStatus = (String) jComboBox1.getSelectedItem(); 
    
    // --- 2. Basic Validation ---
    if (studentID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select a student ID to update.", 
                                      "Input Required", JOptionPane.WARNING_MESSAGE);
        return;
    }

    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = ConnectionProvider.getCon();
        if (con == null) return;

        // --- 3. SQL UPDATE Statement ---
        // UPDATE all fields WHERE the StudentID matches the input field.
        String sql = "UPDATE STUDENT SET StudentName=?, RoomNo=?, MobileNo=?, Address=?, LivingStatus=? WHERE StudentID=?";
        ps = con.prepareStatement(sql);
        
        // Set parameters based on their position in the SQL query:
        ps.setString(1, studentName);
        ps.setString(2, roomNo);
        ps.setString(3, mobileNo);
        ps.setString(4, address);
        ps.setString(5, livingStatus);
        ps.setString(6, studentID); // WHERE clause identifier

        // --- 4. Execute and Confirm ---
        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Student " + studentID + " updated successfully!", 
                                          "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Student " + studentID + " not found or no changes were made.", 
                                          "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // --- 5. Clean Up and Refresh ---
        loadStudentData();
        
        // Use the corrected call to clear the input fields
        jButton3ActionPerformed(new java.awt.event.ActionEvent(this, 
                                                               java.awt.event.ActionEvent.ACTION_PERFORMED, 
                                                               "Clear")); 

    } catch (SQLException e) {
        if (e.getErrorCode() == 1452) { // Foreign Key constraint failure (RoomNo not found)
            JOptionPane.showMessageDialog(this, "Error: Room Number " + roomNo + " does not exist.", 
                                          "Data Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Database Error during UPDATE: " + e.getMessage(), 
                                          "Error", JOptionPane.ERROR_MESSAGE);
        }
    } finally {
        // --- 6. Close Resources ---
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) { 
             e.printStackTrace(); 
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
String studentID = jTextField1.getText().trim().toUpperCase();

    // --- 1. Basic Validation ---
    if (studentID.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please select or enter a Student ID to delete.", 
                                      "Input Required", JOptionPane.WARNING_MESSAGE);
        return;
    }

    // --- 2. Confirmation Dialog (CRUCIAL) ---
    int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to permanently delete student record ID: " + studentID + "?",
            "Confirm Deletion", JOptionPane.YES_NO_OPTION);

    if (confirm != JOptionPane.YES_OPTION) {
        return; // User cancelled the deletion
    }

    Connection con = null;
    PreparedStatement ps = null;

    try {
        con = ConnectionProvider.getCon();
        if (con == null) return;

        // --- 3. SQL DELETE Statement ---
        // We delete the record WHERE the StudentID matches.
        String sql = "DELETE FROM STUDENT WHERE StudentID = ?";
        ps = con.prepareStatement(sql);
        ps.setString(1, studentID); // Identifies the specific student (Primary Key)

        // --- 4. Execute and Confirm ---
        int rowsAffected = ps.executeUpdate();

        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(this, "Student ID " + studentID + " deleted successfully!", 
                                          "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Student ID " + studentID + " not found or could not be deleted.", 
                                          "Warning", JOptionPane.WARNING_MESSAGE);
        }

        // --- 5. Clean Up and Refresh ---
        loadStudentData(); // Reload the table to reflect the deletion
        
        // Use the corrected call to clear the input fields
        jButton3ActionPerformed(new java.awt.event.ActionEvent(this, 
                                                               java.awt.event.ActionEvent.ACTION_PERFORMED, 
                                                               "Clear")); 

    } catch (SQLException e) {
        // Note: Students usually don't have FKs pointing to them, so 1451 error is unlikely, 
        // but general DB errors are still handled.
        JOptionPane.showMessageDialog(this, "Database Error during DELETE: " + e.getMessage(), 
                                      "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // --- 6. Close Resources ---
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) { 
             e.printStackTrace(); 
        }
    }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
     this.hide();
          DashboardFrame a= new DashboardFrame();
          a.show();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentManageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentManageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentManageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentManageFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentManageFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}

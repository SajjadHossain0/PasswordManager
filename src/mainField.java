import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class mainField {
    JFrame frame;
    JPanel panel1,panel2, panel3,
            panel4,panel5,panel6,
            panel7,panel8, panel9;
    JLabel id_lbl, text_lbl, name_lbl,
            username_lbl,password_lbl, details_lbl;
    JButton back_btn, reload_btn, update_btn,
            remove_btn, reset_btn, save_btn;
    JTextField id_txtField, name_field,
            username_field, password_field, details_field;
    JTable table;
    JScrollPane scrollPane;
    private int userId;

    public mainField(int userId) {

        // pass the user_id that is connected user_data table through foreign key(user_id) from users table.
        // we will match that user_id from users table
        this.userId = userId;

        frame = new JFrame();
        frame.setTitle("Password Manager");
        //frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

//....................... data table................

// use border layout to view the table
        panel1 = new JPanel(new BorderLayout());
        panel1.setBackground(Color.cyan);
        panel1.setBounds(20,10,550,200);
// Create a table model
        DefaultTableModel model = new DefaultTableModel();
// Create a JTable with the model
        table = new JTable(model);
// Add the table to a scroll pane
        scrollPane = new JScrollPane(table);
// Add the scroll pane to the frame
        panel1.add(scrollPane);
// call fetchAndDisplayUserData() method to fetch and display table from database
        fetchAndDisplayUserData();

        frame.add(panel1,BorderLayout.CENTER);


// ........Remove and update Data label....
        panel2 = new JPanel(null);
        panel2.setBounds(35,215,500,30);

        text_lbl = new JLabel("Remove Data / Update Data ");
        text_lbl.setBounds(0,0,500,30);
        text_lbl.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel2.add(text_lbl);

        frame.add(panel2);

// .........remove and update data field......

        panel3 = new JPanel(null);
        panel3.setBounds(0,240,600,50);

        id_lbl = new JLabel("ID : ");
        id_lbl.setBounds(35,10,150,40);
        id_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel3.add(id_lbl);

        id_txtField = new JTextField();
        id_txtField.setBounds(80,15, 300,30);
        id_txtField.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel3.add(id_txtField);

// button for update data
        update_btn = new JButton("Update");
        update_btn.setBounds(400,15,85,30);
        update_btn.setFont(new Font("Verdana", Font.PLAIN, 12));
        update_btn.setFocusable(false);
        update_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == update_btn){
                    // call updateUserData() method for data update logic
                    updateUserData();
                }
            }
        });
        panel3.add(update_btn);

// button for remove data
        remove_btn = new JButton("Remove");
        remove_btn.setBounds(490,15,85,30);
        remove_btn.setFont(new Font("Verdana", Font.PLAIN, 12));
        remove_btn.setFocusable(false);
        remove_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == update_btn){
                    // call deleteRowViaID() method for data delete logic
                    deleteRowViaID();
                }
            }
        });
        panel3.add(remove_btn);

        frame.add(panel3);

// .............Data Entry label.........

        panel4 = new JPanel(null);
        panel4.setBounds(35,298,500,30);

        text_lbl = new JLabel("Data entry / Data Update ");
        text_lbl.setBounds(0,0,400,30);
        text_lbl.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel4.add(text_lbl);

        frame.add(panel4);

// Name field............
        panel5 = new JPanel(null);
        panel5.setBounds(0,330,600,50);

        name_lbl = new JLabel("Name : ");
        name_lbl.setBounds(35,10,150,40);
        name_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel5.add(name_lbl);

        name_field = new JTextField();
        name_field.setBounds(175,15, 400,30);
        name_field.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel5.add(name_field);

        frame.add(panel5);

// username/email field.......
        panel6 = new JPanel(null);
        panel6.setBounds(0,365,600,50);

        username_lbl = new JLabel("Username/Email : ");
        username_lbl.setBounds(35,10,150,40);
        username_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel6.add(username_lbl);

        username_field = new JTextField();
        username_field.setBounds(175,15, 400,30);
        username_field.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel6.add(username_field);

        frame.add(panel6);

// password field......
        panel7 = new JPanel(null);
        panel7.setBounds(0,400,600,50);

        password_lbl = new JLabel("Password : ");
        password_lbl.setBounds(35,10,150,40);
        password_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel7.add(password_lbl);

        password_field = new JTextField();
        password_field.setBounds(175,15, 400,30);
        password_field.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel7.add(password_field);

        frame.add(panel7);

// details field......
        panel8 = new JPanel(null);
        panel8.setBounds(0,450,600,62);

        details_lbl = new JLabel("Details : ");
        details_lbl.setBounds(35,10,150,40);
        details_lbl.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel8.add(details_lbl);

        details_field = new JTextField();
        details_field.setBounds(175,0, 400,60);
        details_field.setFont(new Font("Verdana", Font.PLAIN, 15));
        panel8.add(details_field);

        frame.add(panel8);

// ..............back , reset and save button for new data.....

        panel9 = new JPanel(null);
        panel9.setBounds(0,520,600,40);

// back button for came back to the login page
        back_btn = new JButton("Back");
        back_btn.setBounds(20,5,70,30);
        back_btn.setFont(new Font("Verdana", Font.PLAIN, 12));
        back_btn.setFocusable(false);
        back_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                login login = new login();
            }
        });
        panel9.add(back_btn);

// reload button for reload the table after saving data
        reload_btn = new JButton("Reload");
        reload_btn.setBounds(310,5,80,30);
        reload_btn.setFont(new Font("Verdana", Font.PLAIN, 12));
        reload_btn.setFocusable(false);
        reload_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fetchAndDisplayUserData();
            }
        });
        panel9.add(reload_btn);

// reset button for resent the text fields
        reset_btn = new JButton("Reset");
        reset_btn.setBounds(410,5,70,30);
        reset_btn.setFont(new Font("Verdana", Font.PLAIN, 12));
        reset_btn.setFocusable(false);
        reset_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name_field.setText(null);
                username_field.setText(null);
                password_field.setText(null);
                details_field.setText(null);
            }
        });
        panel9.add(reset_btn);

// save button for save the data from text field to database table
        save_btn = new JButton("Save");
        save_btn.setBounds(500,5,70,30);
        save_btn.setFont(new Font("Verdana", Font.PLAIN, 12));
        save_btn.setFocusable(false);
        save_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // call saveData() method to perform data saving logic to database
                saveData();
            }
        });
        panel9.add(save_btn);

        frame.add(panel9);


        frame.setVisible(true);

    }
    private void fetchAndDisplayUserData() {
        try {
            Connection connection = ConnectionProvider.getConnection();
            String table_data = "SELECT * FROM user_data WHERE User_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(table_data);
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();

            // Create a table model
            DefaultTableModel model = new DefaultTableModel();

            // Add cols headers to the model
            int colsCount = rsmd.getColumnCount();
            for (int cols = 1; cols <= colsCount; cols++) {
                model.addColumn(rsmd.getColumnName(cols));
            }

            // Add rows to the model
            while (rs.next()) {
                Object[] rowData = new Object[colsCount];
                for (int i = 0; i < colsCount; i++) {
                    rowData[i] = rs.getObject(i + 1);
                }
                model.addRow(rowData);
            }
            connection.close();

            // Set the model for the table
            table.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveData(){
        // if text field are empty then it will show error.
        if(name_field.getText().isEmpty() && username_field.getText().isEmpty() && password_field.getText().isEmpty() && details_field.getText().isEmpty()){

            JOptionPane.showMessageDialog(null,"You have to fill all the field with Data","Data Entry", JOptionPane.ERROR_MESSAGE);

        }
        else {
            String name = name_field.getText();
            String email = username_field.getText();
            String password = password_field.getText();
            String details = details_field.getText();
            String add_data = "insert into user_data(Name, Email, Password, Details, User_id) values(?,?,?,?,?)";

            try{
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement pstmnt = connection.prepareStatement(add_data);
                pstmnt.setString(1,name);
                pstmnt.setString(2,email);
                pstmnt.setString(3,password);
                pstmnt.setString(4,details);
                pstmnt.setInt(5,userId);
                pstmnt.executeUpdate();

                connection.close();

                // again calling this fetchAndDisplayUserData() method so that table could be up-to-date all the time.
                fetchAndDisplayUserData();

            }catch (Exception e){
                e.getStackTrace();
            }
        }

    }

    private void updateUserData(){
        int id = Integer.parseInt(id_txtField.getText());
        String name = name_field.getText();
        String email = username_field.getText();
        String password = password_field.getText();
        String details = details_field.getText();
        String update_data = "update user_data set Name = ?, Email = ?, Password = ?, Details = ? where ID = ?;";

        try{
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmnt = connection.prepareStatement(update_data);
            pstmnt.setString(1,name);
            pstmnt.setString(2,email);
            pstmnt.setString(3,password);
            pstmnt.setString(4,details);
            pstmnt.setInt(5,id);
            pstmnt.executeUpdate();

            connection.close();
            fetchAndDisplayUserData();

            // after updating all the field will be null as new.
            id_txtField.setText(null);
            name_field.setText(null);
            username_field.setText(null);
            password_field.setText(null);
            details_field.setText(null);

        }catch (Exception e){
            e.getStackTrace();
        }
    }

    private void deleteRowViaID(){
        int id = Integer.parseInt(id_txtField.getText());
        String remove_id = "delete from user_data where id= ?";
        try{
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement pstmnt = connection.prepareStatement(remove_id);
            pstmnt.setInt(1,id);
            pstmnt.executeUpdate();

            connection.close();
            fetchAndDisplayUserData();

            id_txtField.setText(null);

        }catch (Exception exception){
            exception.getStackTrace();
        }
    }
}

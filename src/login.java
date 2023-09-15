import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class login {
    JFrame frame;
    JPanel panel,panel1, panel2;
    JLabel username_lbl,password_lbl;
    JTextField username_txtField, password_txtField;
    JButton register_btn, login_btn;

    public login() {
        frame = new JFrame();
        frame.setTitle("Password Manager");
        //frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

//username field.......
        // set panel for null layout so that bounds the contents.
        panel = new JPanel(null);
        panel.setBounds(45, 140, 500, 60);

        username_lbl = new JLabel("Username : ");
        username_lbl.setBounds(50,10,150,40);
        username_lbl.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(username_lbl);

        username_txtField = new JTextField();
        username_txtField.setBounds(200,10,200,40);
        username_txtField.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel.add(username_txtField);

// pssword field..........
        panel1 = new JPanel(null);
        panel1.setBounds(45, 200, 500, 60);

        password_lbl = new JLabel("Password : ");
        password_lbl.setBounds(50,10,150,40);
        password_lbl.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel1.add(password_lbl);

        password_txtField = new JTextField();
        password_txtField.setBounds(200,10, 200,40);
        password_txtField.setFont(new Font("Verdana", Font.PLAIN, 20));
        panel1.add(password_txtField);

        frame.add(panel);
        frame.add(panel1);

// buttons......
        panel2 = new JPanel(null);
        panel2.setBounds(45, 300, 500, 60);

        register_btn = new JButton("Register");
        register_btn.setBounds(50,10,150,40);
        register_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        register_btn.setFocusable(false);
        register_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == register_btn){
                    // call RegisterNewUser() method where all the logic are written
                    RegisterNewUser();
                }
            }
        });
        panel2.add(register_btn);

        login_btn = new JButton("Login");
        login_btn.setBounds(280,10,150,40);
        login_btn.setFont(new Font("Verdana", Font.PLAIN, 20));
        login_btn.setFocusable(false);
        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (e.getSource()==login_btn){
                    // Login() method for login logics
                    Login();
                }

            }
        });
        panel2.add(login_btn);

        frame.add(panel2);

        frame.setVisible(true);
    }

    private void Login(){
        try{
            Connection connection = ConnectionProvider.getConnection();

            // get data from text fields
            String user = username_txtField.getText();
            String pass = password_txtField.getText();

            Statement stmnt = connection.createStatement();
            String q = "select * from users where username ='"+user+"' and password ='"+pass+"';";

            ResultSet rs = stmnt.executeQuery(q);
            if (rs.next()){
                frame.dispose();
                int userId = rs.getInt("user_id");
                mainField mainField = new mainField(userId);
            }
            else {
                JOptionPane.showMessageDialog(null,"E-mail or Password wrong","Login", JOptionPane.ERROR_MESSAGE);
                username_txtField.setText("");
                password_txtField.setText("");
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }

    private void RegisterNewUser(){
        String username = username_txtField.getText(),
                password = password_txtField.getText();
        String register = "insert into users(username, password) values (?,?)";

        if(username_txtField.getText().isEmpty() && password_txtField.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Enter Username and password to register","Register", JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                Connection connection = ConnectionProvider.getConnection();
                PreparedStatement pstmnt = connection.prepareStatement(register);
                pstmnt.setString(1, username);
                pstmnt.setString(2, password);
                pstmnt.executeUpdate();

                connection.close();

            } catch (Exception exception) {
                exception.getStackTrace();
            }
        }
    }
}

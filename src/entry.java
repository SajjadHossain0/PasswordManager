import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class entry {
    JFrame frame;
    JPanel panel, panel1;
    JLabel label;
    JButton button;

    public entry() {
        frame = new JFrame();
        frame.setTitle("Password Manager");
        //frame.getContentPane().setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

//Panel and label for welcome text
        panel = new JPanel();
        panel.setBounds(0, 180, 600, 100);

        label = new JLabel();
        label.setText("Welcome to Password Manager");
        label.setFont(new Font("Verdana", Font.PLAIN, 30));

        panel.add(label);
        frame.add(panel);

// button for enter login field.....
        panel1 = new JPanel();
        panel1.setBounds(0, 320, 600, 80);

        button = new JButton("Enter");
        button.setFont(new Font("Verdana", Font.PLAIN, 30));
        button.setFocusable(false);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                login login = new login();
            }
        });
        panel1.add(button);
        frame.add(panel1);

        frame.setVisible(true);

    }
}

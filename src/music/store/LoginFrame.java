package music.store;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

    private final JPanel contentPane;
    private final JTextField txtUser;
    private final JTextField txtPassword;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    LoginFrame frame = new LoginFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public LoginFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        txtUser = new JTextField();
        txtUser.setBounds(154, 69, 134, 28);
        panel.add(txtUser);
        txtUser.setColumns(10);

        txtPassword = new JTextField();
        txtPassword.setText("\n");
        txtPassword.setBounds(154, 109, 134, 28);
        panel.add(txtPassword);
        txtPassword.setColumns(10);

        JLabel lblLogin = new JLabel("Login:");
        lblLogin.setBounds(101, 75, 79, 16);
        panel.add(lblLogin);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(77, 115, 79, 16);
        panel.add(lblPassword);

        final JTextArea debugBox = new JTextArea();
        debugBox.setEditable(false);
        debugBox.setBounds(23, 190, 389, 59);
        panel.add(debugBox);

        JButton btnEnterTheStore = new JButton("Enter the Store");
        btnEnterTheStore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {

                MusicStoreQuery newQuery = new MusicStoreQuery();

                newQuery.setCredentials(txtUser.getText(), txtPassword.getText());

                String[] credentials = new String[2];

                credentials[0] = txtUser.getText();
                credentials[1] = txtPassword.getText();

                try {
                    if (newQuery.testConnecton()) {
                        debugBox.setText("Connected Successfully!");
                        Menu menu = new Menu();
                        menu.createMenu(credentials);
                        dispose();
                    }
                } catch (SQLException e) {
                    debugBox.setText("Incorrect Login Credentials!");
                }
            }

        });
        btnEnterTheStore.setBounds(154, 149, 134, 29);
        panel.add(btnEnterTheStore);

        JLabel lblMusicStore = new JLabel("Music Store");
        lblMusicStore.setFont(new Font("Mshtakan", Font.PLAIN, 25));
        lblMusicStore.setBounds(144, 30, 134, 23);
        panel.add(lblMusicStore);
    }
}

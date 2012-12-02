package music.store;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AdminApplications extends JFrame {

    private final JPanel contentPane;
    private final Action action = new BackToMainAction();

    private static String[] credentials = new String[2];

    public void createAdminApplications(String[] creds) {

        credentials = creds;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    AdminApplications frame = new AdminApplications();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    AdminApplications() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 250, 1400, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel lblAdminApplications = new JLabel("Admin Applications\n");
        lblAdminApplications.setFont(new Font("Mshtakan", Font.PLAIN, 25));
        lblAdminApplications.setBounds(636, 30, 226, 40);
        panel.add(lblAdminApplications);

        JButton button = new JButton("<< Back to Main Menu\n");
        button.setAction(action);
        button.setBounds(17, 622, 181, 29);
        panel.add(button);
    }

    private class BackToMainAction extends AbstractAction {
        public BackToMainAction() {
            putValue(NAME, "<< Back to Main Menu");
            putValue(SHORT_DESCRIPTION, "go back");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = new Menu();
            menu.createMenu(credentials);
            dispose();
        }
    }
}

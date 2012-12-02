package music.store;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class MusicApplications extends JFrame {

    private final JPanel contentPane;
    private final JTextField isbnField;
    private final JTextField titleField;
    private final JTextField YearField;
    private final JTextField ProducerField;
    private final JTextField PriceField;
    private final JTextField GenreField;
    private final JTextField VendorField;
    private final JTextField MusicTypeField;

    private final Action backToMenuAction = new MenuListener();
    private final Action addMusicAction = new AddMusicAction();
    private final Action queryMusicAction = new QueryAction();
    private final Action editMusicAction = new UpdateAction();

    JTextArea queryResultsWindow = new JTextArea();

    private static String[] credentials = new String[2];

    public void startMusicApplication(String[] creds) {

        credentials = creds;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MusicApplications frame = new MusicApplications();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MusicApplications() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 250, 1400, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        // Heading / Title
        JLabel MusicApplicationsTitle = new JLabel("Music Applications\n");
        MusicApplicationsTitle.setFont(new Font("Mshtakan", Font.PLAIN, 25));
        MusicApplicationsTitle.setBounds(600, 22, 219, 40);
        panel.add(MusicApplicationsTitle);

        // ISBN
        JLabel isbnLabel = new JLabel("ISBN");
        isbnLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        isbnLabel.setBounds(17, 99, 61, 16);
        panel.add(isbnLabel);

        isbnField = new JTextField();
        isbnField.setBounds(117, 93, 134, 28);
        panel.add(isbnField);
        isbnField.setColumns(10);

        // Title
        JLabel lblTitle = new JLabel("Title");
        lblTitle.setBounds(17, 139, 61, 16);
        panel.add(lblTitle);

        titleField = new JTextField();
        titleField.setBounds(117, 133, 134, 28);
        panel.add(titleField);
        titleField.setColumns(10);

        // Year
        JLabel lblYear = new JLabel("Year");
        lblYear.setBounds(17, 182, 61, 16);
        panel.add(lblYear);

        YearField = new JTextField();
        YearField.setBounds(117, 176, 134, 28);
        panel.add(YearField);
        YearField.setColumns(10);

        // Producer
        JLabel lblProducer = new JLabel("Producer");
        lblProducer.setBounds(17, 234, 61, 16);
        panel.add(lblProducer);

        ProducerField = new JTextField();
        ProducerField.setBounds(117, 228, 134, 28);
        panel.add(ProducerField);
        ProducerField.setColumns(10);

        // Price
        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(17, 282, 61, 16);
        panel.add(lblPrice);

        PriceField = new JTextField();
        PriceField.setBounds(117, 276, 134, 28);
        panel.add(PriceField);
        PriceField.setColumns(10);

        // Genre
        JLabel lblGenre = new JLabel("Genre");
        lblGenre.setBounds(18, 326, 61, 16);
        panel.add(lblGenre);

        GenreField = new JTextField();
        GenreField.setBounds(117, 320, 134, 28);
        panel.add(GenreField);
        GenreField.setColumns(10);

        // Vendor ID
        JLabel lblVendorId = new JLabel("Vendor ID");
        lblVendorId.setBounds(18, 363, 78, 16);
        panel.add(lblVendorId);

        VendorField = new JTextField();
        VendorField.setBounds(117, 357, 134, 28);
        panel.add(VendorField);
        VendorField.setColumns(10);

        // Music Type
        JLabel lblMusicType = new JLabel("Music Type");
        lblMusicType.setBounds(17, 403, 79, 16);
        panel.add(lblMusicType);

        MusicTypeField = new JTextField();
        MusicTypeField.setBounds(117, 397, 134, 28);
        panel.add(MusicTypeField);
        MusicTypeField.setColumns(10);

        // Add Music Button
        JButton btnAddMusic = new JButton("Add Music");
        btnAddMusic.setAction(addMusicAction);
        btnAddMusic.setBounds(117, 437, 134, 29);
        panel.add(btnAddMusic);

        // Edit Music Button
        JButton btnEditMusic = new JButton("Edit Music");
        btnEditMusic.setAction(editMusicAction);
        btnEditMusic.setBounds(117, 478, 134, 29);
        panel.add(btnEditMusic);

        // Query Music Button
        JButton btnQueryMusic = new JButton("Query Music");
        btnQueryMusic.setAction(queryMusicAction);
        btnQueryMusic.setBounds(117, 519, 134, 29);
        panel.add(btnQueryMusic);
        queryResultsWindow.setBounds(276, 93, 1108, 455);
        panel.add(queryResultsWindow);

        // back to main menu button
        JButton backButton = new JButton("<< Back to Main Menu\n");
        backButton.setAction(backToMenuAction);
        backButton.setBounds(18, 621, 181, 29);
        panel.add(backButton);
    }

    private class MenuListener extends AbstractAction {
        public MenuListener() {
            putValue(NAME, "<< Back to Main Menu");
            putValue(SHORT_DESCRIPTION, "Go back to the start.");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Menu menu = new Menu();
            menu.createMenu(credentials);
            dispose();
        }
    }

    private class AddMusicAction extends AbstractAction {
        public AddMusicAction() {
            putValue(NAME, "Add Music");
            putValue(SHORT_DESCRIPTION, "Add Music to the Database");
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            MusicStoreQuery msq = new MusicStoreQuery();
            msq.setCredentials(credentials[0], credentials[1]);

            try {

                String values = PriceField.getText() + ", '" + ProducerField.getText() + "', '" + titleField.getText() + "', " + isbnField.getText() + ", " + YearField.getText() + ", '" + GenreField.getText() + "', " + VendorField.getText() + ", '" + MusicTypeField.getText() + "'";
                msq.updateQuery("INSERT INTO music (price, producer, music_title, music_ISBN, year, genre, vendor_ID, music_type) VALUES (" + values + ")");

                queryResultsWindow.setText("Record inserted succssfully!\n\n");

            } catch (SQLException e1) {
                e1.printStackTrace();
                queryResultsWindow.setText("" + e1);
            }
        }
    }

    private class QueryAction extends AbstractAction {
        public QueryAction() {
            putValue(NAME, "Query Music");
            putValue(SHORT_DESCRIPTION, "Looking for something?");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MusicStoreQuery msq = new MusicStoreQuery();

            msq.setCredentials(credentials[0], credentials[1]);
            try {
                ArrayList<HashMap<String, String>> musicTable = msq.queryDatabase("SELECT * FROM music WHERE music_ISBN='" + isbnField.getText() + "'");

                // query by title, and other conditions...

                if (!musicTable.isEmpty()) {
                    for (int rowNumber = 0; rowNumber < musicTable.size(); rowNumber++) {
                        queryResultsWindow.setText("" + musicTable.get(rowNumber));
                    }
                } else
                    queryResultsWindow.setText("No record found!");

            } catch (SQLException e1) {
                queryResultsWindow.setText("" + e1);
            }
        }
    }

    private class UpdateAction extends AbstractAction {
        public UpdateAction() {
            putValue(NAME, "Update Music");
            putValue(SHORT_DESCRIPTION, "Update things.");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MusicStoreQuery msq = new MusicStoreQuery();
            msq.setCredentials(credentials[0], credentials[1]);

            try {
                // TODO: this is not done
                msq.updateQuery("UPDATE music SET ...");
            } catch (SQLException e1) {
                queryResultsWindow.setText("" + e1);
            }
        }
    }
}

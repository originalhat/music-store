package music.store;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class Menu extends JFrame {
    private final Action musicListener = new MusicAppListener();
    private final Action orderListener = new OrderMenuListener();
    private final Action salesListener = new SalesMenuListener();
    private final Action adminListener = new AdminMenuListener();
    private final Action exitListener = new ExitListener();

    private static String[] credentials = new String[2];

    public void createMenu(String[] creds) {

        // set current session's connection
        credentials = creds;

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Menu frame = new Menu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Menu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnMainMenu = new JMenu("Main Menu");
        menuBar.add(mnMainMenu);

        JMenuItem mntmMusicApplications = new JMenuItem("Music Applications");
        mntmMusicApplications.setAction(musicListener);
        mnMainMenu.add(mntmMusicApplications);

        JMenuItem mntmOrderingApplications = new JMenuItem("Ordering Applications");
        mntmOrderingApplications.setAction(orderListener);
        mnMainMenu.add(mntmOrderingApplications);

        JMenuItem mntmSalesApplications = new JMenuItem("Sales Applications");
        mntmSalesApplications.setAction(salesListener);
        mnMainMenu.add(mntmSalesApplications);

        JMenuItem mntmAdministrativeApplications = new JMenuItem("Administrative Applications");
        mntmAdministrativeApplications.setAction(adminListener);
        mnMainMenu.add(mntmAdministrativeApplications);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.setAction(exitListener);
        mnMainMenu.add(mntmExit);
    }

    private class MusicAppListener extends AbstractAction {
        public MusicAppListener() {
            putValue(NAME, "Music Applications");
            putValue(SHORT_DESCRIPTION, "Add Music, Query Music");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MusicApplications musicAppsFrame = new MusicApplications();

            musicAppsFrame.startMusicApplication(credentials);
            dispose();
        }
    }

    private class OrderMenuListener extends AbstractAction {
        public OrderMenuListener() {
            putValue(NAME, "Order Applications");
            putValue(SHORT_DESCRIPTION, "Query Orders, Place New Order");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderApplications csa = new OrderApplications();
            csa.createOrderApplications();
            dispose();
        }
    }

    private class SalesMenuListener extends AbstractAction {
        public SalesMenuListener() {
            putValue(NAME, "Sales Applications");
            putValue(SHORT_DESCRIPTION, "Sell Music Title");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            SalesApplications csa = new SalesApplications();
            csa.CreateSalesApplications();
            dispose();
        }
    }

    private class AdminMenuListener extends AbstractAction {
        public AdminMenuListener() {
            putValue(NAME, "Admin Applications");
            putValue(SHORT_DESCRIPTION, "Search, Add, Remove Employees");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AdminApplications adminApps = new AdminApplications();
            adminApps.createAdminApplications(credentials);
            dispose();
        }
    }

    private class ExitListener extends AbstractAction {
        public ExitListener() {
            putValue(NAME, "Exit");
            putValue(SHORT_DESCRIPTION, "Some short description");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            /** exit program **/
            dispose();
        }
    }
}

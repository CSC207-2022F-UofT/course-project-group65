package screens;

import javax.swing.*;

public class homeScreen extends JFrame{
    private JLabel header;
    private JButton btLogIn;
    private JButton btCreateAccount;
    private JPanel homeScreen;

    public homeScreen() {
        setContentPane(homeScreen);
        setTitle("Home Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        homeScreen homeScreen = new homeScreen();
    }
}

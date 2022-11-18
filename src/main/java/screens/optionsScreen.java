package screens;

import screens.logIn.logInInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.activation.ActivationInstantiator;

public class optionsScreen extends JFrame implements ActionListener {
    private JLabel lbHeader;
    private JButton btCreateBracket;
    private JButton btJoinNew;
    private JButton btJoinExisting;
    private JButton btLogOut;
    private JPanel optionsScreen;

    public optionsScreen() {
        setContentPane(optionsScreen);
        setTitle("Options Screen");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btCreateBracket.addActionListener(this);
        btJoinNew.addActionListener(this);
        btJoinExisting.addActionListener(this);
        btLogOut.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btCreateBracket){
            // TODO
        }

        if (e.getSource() == btJoinExisting){
            // TODO
        }

        if (e.getSource() == btJoinNew){
            // TODO
        }

        if (e.getSource() == btLogOut){
            logInInfo logInScreen = new logInInfo();
            this.dispose();
            logInScreen.setVisible(true);
        }
    }
}

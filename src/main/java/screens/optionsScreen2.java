package screens;

import entities.UserFactory;
import screens.teamCreation.UserInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class optionsScreen2 extends JFrame {
    private JButton btJoinTeam;
    private JButton btCreateTeam;
    private JButton btPrevious;
    private JPanel optionsScreen2;


    public optionsScreen2() {
        setContentPane(optionsScreen2);
        setTitle("Options Screen2");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        btJoinTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
                optionsScreen2.setVisible(false);
//                JFrame createTeam = new UserInput();
//                createTeam.setVisible(true);
            }
        });
        btCreateTeam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                optionsScreen2.setVisible(false);
//                JFrame joinTeam = new bracketView();
//                joinTeam.setVisible(true);
            }
        });


    }
}

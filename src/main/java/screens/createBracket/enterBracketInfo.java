package screens.createBracket;

import useCases.createBracket.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class enterBracketInfo extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JLabel windowName;
    private JButton createButton;
    private JTextField bracketName;
    private JComboBox<String> bracketType;
    private JComboBox<Integer> numTeams;
    private JComboBox<Integer> winCondition;
    private JComboBox<Integer> teamSizes;

    private CreateBracketController createBracketController;


    public enterBracketInfo(CreateBracketController controller) {
        super("Create Bracket");

        this.createBracketController = controller;

        bracketType.addItem("Default");

        numTeams.addItem(2);
        numTeams.addItem(4);
        numTeams.addItem(8);
//        numTeams.addItem(16);

        for (int i = 1; i < 14; i++) {
            winCondition.addItem(i);
        }

        for (int i = 1; i < 11; i++) {
            teamSizes.addItem(i);
        }

        createButton.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());


        try {
            String bracketName = this.bracketName.getText();
            String bracketType = (String) this.bracketType.getSelectedItem();
            int numTeams = (int) this.numTeams.getSelectedItem();
            int winCondition = (int) this.winCondition.getSelectedItem();
            int teamSizes = (int) this.teamSizes.getSelectedItem();
            System.out.println(bracketName);
            System.out.println(bracketType);
            System.out.println(numTeams);
            System.out.println(winCondition);
            System.out.println(teamSizes);

            CreateBracketOD outputData = createBracketController.create(bracketType, bracketName, numTeams, teamSizes, winCondition);


        } catch (Exception exception) {
            System.out.println("Error: " + exception);
        }
    }
}



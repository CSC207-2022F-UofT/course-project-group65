package screens.createBracket;

import screens.bracketView;
import useCases.createBracket.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class enterBracketInfo extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton createButton;
    private JTextField bracketName;
    private JComboBox<String> bracketType;
    private JComboBox<Integer> numTeams;
    private JComboBox<Integer> winCondition;
    private JComboBox<Integer> teamSizes;
    private JLabel currentUser;
    private CreateBracketController createBracketController;


    public enterBracketInfo(CreateBracketController controller) {
        super("Create Bracket");

        this.createBracketController = controller;

        bracketType.addItem("Default");

//        numTeams.addItem(2);
        numTeams.addItem(4);
//        numTeams.addItem(8);
//        numTeams.addItem(16);

        for (int i = 1; i < 14; i++) {
            winCondition.addItem(i);
        }

        for (int i = 1; i < 6; i++) {
            teamSizes.addItem(i);
        }

        createButton.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
    }

    public void setCurrentUser(String username) {
        currentUser.setText("Logged in: " + username);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Click " + e.getActionCommand());


        try {
            if (this.numTeams.getSelectedItem() == null) {
                throw new Exception("Please select a number of teams");
            }
            if (this.winCondition.getSelectedItem() == null) {
                throw new Exception("Please select a win condition");
            }
            if (this.teamSizes.getSelectedItem() == null) {
                throw new Exception("Please select a team size");
            }
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

            CreateBracketOD outputData = createBracketController.create(bracketType, bracketName, numTeams, teamSizes,
                    winCondition);
//            System.out.println(outputData.getBracketID());

//            Will need to add controller initialization here when we have the controllers as
//            parameters to the view constructor
            bracketView view = new bracketView();
            view.setBracketName(outputData.getBracketName());
            view.setCurrentUser(outputData.getUsername());
            view.setCurrentTournament(outputData.getBracketID());
            this.dispose();
            view.setVisible(true);


        } catch (Exception exception) {
            System.out.println("Error: " + exception);
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }
}



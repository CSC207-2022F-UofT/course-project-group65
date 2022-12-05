package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.create_bracket.CreateBracketController;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.start_tournament.StartTournController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterBracketInfo extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton createButton;
    private JTextField bracketName;
    private JComboBox<String> bracketType;
    private JComboBox<Integer> winCondition;
    private JLabel currentUser;
    private JTextField teamNumField;
    private JTextField teamSizeField;
    private final CreateBracketController createBracketController;
    private final NextScreenData nextScreenData;


    public EnterBracketInfo(CreateBracketController createBracketController, NextScreenData nextScreenData) {
        super("Create Bracket");

        this.createBracketController = createBracketController;
        this.nextScreenData = nextScreenData;

        bracketType.addItem("Default");
        for (int i = 1; i < 20; i++) {
            winCondition.addItem(i);
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
        try {
            if (this.winCondition.getSelectedItem() == null) {
                throw new Exception("Please select a win condition");
            }
            String bracketName = this.bracketName.getText();
            String bracketType = (String) this.bracketType.getSelectedItem();
            int numTeams = Integer.parseInt(this.teamNumField.getText().trim());
            if (Math.log(numTeams) / Math.log(2) % 1 != 0 || numTeams < 2) {
                throw new Exception("Number of teams must be a power of 2 and greater than one");
            }
            int winCondition = (int) this.winCondition.getSelectedItem();
            int teamSizes = Integer.parseInt(this.teamSizeField.getText().trim());
            if (teamSizes <= 1) {
                throw new Exception("Team size must be greater than 1");
            }

            createBracketController.setPresenterData(nextScreenData);
            createBracketController.create(bracketType, bracketName, numTeams, teamSizes, winCondition);
            EndTournController endTournController = new EndTournController(nextScreenData.getCurrentUser(), nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
            StartTournController startTournController = new StartTournController(nextScreenData.getCurrentUser(), nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
            JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(),
                    nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());

            ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController, joinTeamController);
            this.dispose();
            view.setVisible(true);


        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }
}



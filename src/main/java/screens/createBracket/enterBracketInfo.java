package screens.createBracket;

import screens.ExtendedView;
import screens.NextScreenData;
//import screens.bracketView;
import screens.endTourn.EndTournController;
import screens.joinTeam.JoinTeamController;
import screens.startTourn.StartTournController;
import use_cases.create_bracket.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class enterBracketInfo extends JFrame implements ActionListener {
    private JPanel mainPanel;
    private JButton createButton;
    private JTextField bracketName;
    private JComboBox<String> bracketType;
//    private JComboBox<Integer> numTeams;
    private JComboBox<Integer> winCondition;
//    private JComboBox<Integer> teamSizes;
    private JLabel currentUser;
    private JTextField teamNumField;
    private JTextField teamSizeField;
    private CreateBracketController createBracketController;
    private NextScreenData nextScreenData;


    public enterBracketInfo(CreateBracketController createBracketController, NextScreenData nextScreenData) {
        super("Create Bracket");

        this.createBracketController = createBracketController;
        this.nextScreenData = nextScreenData;

        bracketType.addItem("Default");

//        numTeams.addItem(2);
//        numTeams.addItem(4);
//        numTeams.addItem(8);
//        numTeams.addItem(16);

        for (int i = 1; i < 20; i++) {
            winCondition.addItem(i);
        }

//        for (int i = 1; i < 6; i++) {
//            teamSizes.addItem(i);
//        }

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
//        System.out.println("Click " + e.getActionCommand());
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
//            int numTeams = (int) this.numTeams.getSelectedItem();
            int winCondition = (int) this.winCondition.getSelectedItem();
            int teamSizes = Integer.parseInt(this.teamSizeField.getText().trim());
//            int teamSizes = (int) this.teamSizes.getSelectedItem();
//            System.out.println(bracketName);
//            System.out.println(bracketType);
//            System.out.println(numTeams);
//            System.out.println(winCondition);
//            System.out.println(teamSizes);

            CreateBracketOD outputData = createBracketController.create(bracketType, bracketName, numTeams, teamSizes,
                    winCondition);
            //System.out.println(outputData);
//            System.out.println(outputData.getBracketID());

//            Will need to add controller initialization here when we have the controllers as
//            parameters to the view constructor

//            Overseer Controls controllers
//            EndTournOB endTournOB = new EndTournPresenter();
//            EndTournIB endTournIB = new EndTournUC(endTournOB, outputData.getUsername(), nextScreenData.getInformationRecord(), outputData.getBracketID());
            EndTournController endTournController = new EndTournController(outputData.getUsername(), nextScreenData.getInformationRecord(), outputData.getBracketID());

//            StartTournOB startTournOB = new StartTournPresenter();
//            StartTournIB startTournIB = new StartTournUC(startTournOB, outputData.getUsername(), nextScreenData.getInformationRecord(), outputData.getBracketID());
            StartTournController startTournController = new StartTournController(outputData.getUsername(), nextScreenData.getInformationRecord(), outputData.getBracketID());

            //NextScreenData nextScreenData = new NextScreenData();
            //nextScreenData.setBrackets(outputData.getBrackets());
            //nextScreenData.setAccounts(outputData.getAccounts());
            nextScreenData.setCurrentUser(outputData.getUsername());
            nextScreenData.setCurrentBracketID(outputData.getBracketID());

            JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(),
                    outputData.getBracketID(), outputData.getUsername());

            ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController, joinTeamController);
//            view.setGames(nextScreenData.getGameToTeams(), nextScreenData.getGameToScores(),
//                    nextScreenData.getGameToWinner(), nextScreenData.getTeamToPlayers());
//            view.setTeams(nextScreenData.getTeamToPlayers());
//            view.setReferees(nextScreenData.getGameToReferee(), nextScreenData.getReferees());

//            bracketView view = new bracketView(nextScreenData, endTournController, startTournController, joinTeamController);
//            view.setBracketName(outputData.getBracketName());
//            view.setCurrentUser(outputData.getUsername());
//            view.setCurrentTournament(outputData.getBracketID());
//            view.setPlayerInvite(outputData.getPlayerInvite());
//            view.setObserverInvite(outputData.getObserverInvite());
            this.dispose();
            view.setVisible(true);


        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(this, "Please enter a valid integer");
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(this, exception.getMessage());
        }
    }
}



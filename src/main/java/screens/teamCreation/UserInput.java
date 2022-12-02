package screens.teamCreation;


import screens.ExtendedView;
import screens.NextScreenData;
//import screens.bracketView;
import screens.endTourn.EndTournController;
import screens.endTourn.EndTournPresenter;
import screens.startTourn.StartTournController;
import screens.startTourn.StartTournPresenter;
import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournOB;
import useCases.endTourn.EndTournUC;
import useCases.startTourn.StartTournIB;
import useCases.startTourn.StartTournOB;
import useCases.startTourn.StartTournUC;
import useCases.teamCreation.teamCreationIB;
import useCases.teamCreation.teamCreationOB;
import useCases.teamCreation.teamCreationOD;
import useCases.teamCreation.teamCreationUC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class UserInput extends JFrame implements ActionListener{
    private JLabel TeamName;
    private JTextField tfTeamName;
    private JButton btnCreate;
    private JPanel inputPanel;
    private TeamCreationController teamCreationController;
//    public bracketView view;
    private NextScreenData nextScreenData;
    public ExtendedView extendedView;

//    public UserInput(TeamCreationController controller, bracketView viewChange, NextScreenData nextScreenData) {
//
//        this.teamCreationController = controller;
//        this.view = viewChange;
//        this.nextScreenData = nextScreenData;
//
//        this.setContentPane(inputPanel);
//        this.setTitle("Create A Team");
//        this.setSize(450,300);
////        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setVisible(true);
//        btnCreate.addActionListener(this);
//    }

    public UserInput(TeamCreationController controller, ExtendedView viewChange, NextScreenData nextScreenData) {

        this.teamCreationController = controller;
        this.extendedView = viewChange;
        this.nextScreenData = nextScreenData;

        this.setContentPane(inputPanel);
        this.setTitle("Create A Team");
        this.setSize(450,300);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
        btnCreate.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
//        System.out.println("Click " + evt.getActionCommand());

        try {
            if(tfTeamName.getText() == null) {
                throw new Exception("Please enter a team name");
            }
            teamCreationOD outputData = teamCreationController.createNewTeam(tfTeamName.getText());
            EndTournOB endTournOB = new EndTournPresenter();
//            EndTournIB endTournIB = new EndTournUC(endTournOB, outputData.getUsername(), nextScreenData.getInformationRecord(),
//                    outputData.getBracketID());
            EndTournIB endTournIB = new EndTournUC(endTournOB, outputData.getUsername(), nextScreenData.getInformationRecord(),
                    nextScreenData.getCurrentBracketID());
            EndTournController endTournController = new EndTournController(endTournIB);

            StartTournOB startTournOB = new StartTournPresenter();
//            StartTournIB startTournIB = new StartTournUC(startTournOB, outputData.getUsername(),
//                    nextScreenData.getInformationRecord(), outputData.getBracketID());
            StartTournIB startTournIB = new StartTournUC(startTournOB, outputData.getUsername(),
                    nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
            StartTournController startTournController = new StartTournController(startTournIB);

//            NextScreenData nextScreenData = new NextScreenData();
//            nextScreenData.setBrackets(outputData.getBrackets());
//            nextScreenData.setAccounts(outputData.getAccounts());
            nextScreenData.setCurrentUser(outputData.getUsername());
//            nextScreenData.setCurrentBracketID(outputData.getBracketID());
            nextScreenData.bundleData();
            extendedView.replaceTeam(outputData.getNewTeam(), outputData.getOldTeam(), nextScreenData.getGameToTeams());

//            bracketView view = new bracketView(nextScreenData,endTournController, startTournController, joinTeamController);

//            ArrayList<String> teams = outputData.getTeams();
//            ArrayList<ArrayList<String>> teamMembers = outputData.getTeamMembers();
//            view.setTeam1Name(teams.get(0));
//            String[] t1Players = new String[teamMembers.get(0).size()];
//            t1Players = teamMembers.get(0).toArray(t1Players);
//            view.setTeam1PlayerList(t1Players);
//
//            view.setTeam2Name(teams.get(1));
//            String[] t2Players = new String[teamMembers.get(1).size()];
//            t2Players = teamMembers.get(1).toArray(t2Players);
//            view.setTeam2PlayerList(t2Players);
//
//            view.setTeam3Name(teams.get(2));
//            String[] t3Players = new String[teamMembers.get(2).size()];
//            t3Players = teamMembers.get(2).toArray(t3Players);
//            view.setTeam1PlayerList(t3Players);
//
//            view.setTeam4Name(teams.get(3));
//            String[] t4Players = new String[teamMembers.get(3).size()];
//            t4Players = teamMembers.get(0).toArray(t4Players);
//            view.setTeam1PlayerList(t4Players);
//
//            this.dispose();
//            view.setVisible(true);
            this.dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}

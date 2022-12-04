package frameworks_and_drivers;

import frameworks_and_drivers.ExtendedView;
import interface_adapters.NextScreenData;
//import screens.bracketView;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.join_tournament.JoinTournamentController;
import interface_adapters.join_tournament.JoinTournamentFailed;
import interface_adapters.start_tournament.StartTournController;
import use_cases.join_tournament.JoinTournamentOD;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinTournamentInfo extends JFrame implements ActionListener {
    private JPanel joinTournament;
    private JButton btSubmit;
    private JTextField tfInvite;
    private JLabel lbInvite;
    private JoinTournamentController controller;
    private NextScreenData nextScreenData;

    public JoinTournamentInfo(JoinTournamentController controller, NextScreenData nextScreenData) {
        this.controller = controller;
        this.nextScreenData = nextScreenData;
        setTitle("Join Tournament");
        setSize(450, 300);
        setVisible(true);
        btSubmit.addActionListener(this);
        setContentPane(joinTournament);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String invite = tfInvite.getText();
        try{
            controller.setPresenterData(nextScreenData);
            controller.joinTournament(invite);
            EndTournController endTournController = new EndTournController(nextScreenData.getCurrentUser(), nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
            StartTournController startTournController = new StartTournController(nextScreenData.getCurrentUser(), nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
            JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
            nextScreenData.bundleData();

            ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController, joinTeamController);

            dispose();
            view.setVisible(true);
        }
        catch (JoinTournamentFailed ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

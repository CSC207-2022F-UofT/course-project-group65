package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.join_tournament.JoinTournamentController;
import interface_adapters.join_tournament.JoinTournamentFailed;
import interface_adapters.start_tournament.StartTournController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinTournamentInfo extends JFrame implements ActionListener {
    private JPanel joinTournament;
    private JButton btSubmit;
    private JTextField tfInvite;
    private JLabel lbInvite;
    private JButton backButton;
    private final JoinTournamentController controller;
    private final NextScreenData nextScreenData;

    public JoinTournamentInfo(JoinTournamentController controller, NextScreenData nextScreenData) {
        this.controller = controller;
        this.nextScreenData = nextScreenData;
        lbInvite.setVisible(true);
        setTitle("Join Tournament");
        setSize(450, 300);
        setVisible(true);
        btSubmit.addActionListener(this);
        backButton.addActionListener(this);
        setContentPane(joinTournament);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btSubmit){
            String invite = tfInvite.getText();
            try{
                controller.setPresenterData(nextScreenData);
                controller.joinTournament(invite);
                EndTournController endTournController = new EndTournController(nextScreenData.getCurrentUser(),
                        nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
                StartTournController startTournController = new StartTournController(nextScreenData.getCurrentUser(),
                        nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
                JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(),
                        nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
                nextScreenData.bundleData();

                ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController, joinTeamController);

                dispose();
                view.setVisible(true);
            } catch (JoinTournamentFailed ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (ae.getSource() == backButton){
            nextScreenData.setCurrentUser(nextScreenData.getCurrentUser());
            OptionsScreen optionsScreen = new OptionsScreen(nextScreenData);
            this.dispose();
            optionsScreen.setVisible(true);
        }
    }
}

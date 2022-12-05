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
    private final JoinTournamentController CONTROLLER;
    private final NextScreenData NEXT_SCREEN_DATA;

    public JoinTournamentInfo(JoinTournamentController controller, NextScreenData nextScreenData) {
        CONTROLLER = controller;
        NEXT_SCREEN_DATA = nextScreenData;
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
                CONTROLLER.setPresenterData(NEXT_SCREEN_DATA);
                CONTROLLER.joinTournament(invite);
                EndTournController endTournController = new EndTournController(NEXT_SCREEN_DATA.getCurrentUser(),
                        NEXT_SCREEN_DATA.getInformationRecord(), NEXT_SCREEN_DATA.getCurrentBracketID());
                StartTournController startTournController = new StartTournController(NEXT_SCREEN_DATA.getCurrentUser(),
                        NEXT_SCREEN_DATA.getInformationRecord(), NEXT_SCREEN_DATA.getCurrentBracketID());
                JoinTeamController joinTeamController = new JoinTeamController(NEXT_SCREEN_DATA.getInformationRecord(),
                        NEXT_SCREEN_DATA.getCurrentBracketID(), NEXT_SCREEN_DATA.getCurrentUser());
                NEXT_SCREEN_DATA.bundleData();

                ExtendedView view = new ExtendedView(NEXT_SCREEN_DATA, endTournController, startTournController, joinTeamController);

                dispose();
                view.setVisible(true);
            } catch (JoinTournamentFailed ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (ae.getSource() == backButton){
            NEXT_SCREEN_DATA.setCurrentUser(NEXT_SCREEN_DATA.getCurrentUser());
            OptionsScreen optionsScreen = new OptionsScreen(NEXT_SCREEN_DATA);
            this.dispose();
            optionsScreen.setVisible(true);
        }
    }
}

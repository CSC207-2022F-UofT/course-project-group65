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
        setContentPane(joinTournament);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
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
        }
        catch (JoinTournamentFailed ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

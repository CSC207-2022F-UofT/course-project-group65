package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.start_tournament.StartTournController;
import interface_adapters.view_tournament.ViewTournamentController;
import interface_adapters.view_tournament.ViewTournamentFailed;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewTournamentInfo extends JFrame implements ActionListener{
    private JPanel viewTournament;
    private JButton btSubmit;
    private JLabel lbTournamentID;
    private JComboBox<Integer> tournamentIDs;
    private JButton goBackButton;
    private final ViewTournamentController controller;
    private final NextScreenData nextScreenData;

    public ViewTournamentInfo(ViewTournamentController controller, NextScreenData nextScreenData) {
        this.controller = controller;
        this.nextScreenData = nextScreenData;
        lbTournamentID.setVisible(true);
        setTitle("View Tournament");
        setSize(450, 300);
        setVisible(true);
        btSubmit.addActionListener(this);
        goBackButton.addActionListener(this);
        setContentPane(viewTournament);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setTournaments(ArrayList<Integer> tournaments){
        for (Integer tournament : tournaments){
            tournamentIDs.addItem(tournament);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btSubmit){
            try {
                int tournID = tournamentIDs.getItemAt(tournamentIDs.getSelectedIndex());
                controller.setPresenterData(nextScreenData);
                controller.viewTournament(tournID);
                EndTournController endTournController = new EndTournController(nextScreenData.getCurrentUser(),
                        nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
                StartTournController startTournController = new StartTournController(nextScreenData.getCurrentUser(),
                        nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
                JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(),
                        nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
                nextScreenData.bundleData();
                ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController,
                        joinTeamController);
                dispose();
                view.setVisible(true);
            }
            catch (NullPointerException e){
                JOptionPane.showMessageDialog(this, "No tournament selected");
            }
            catch (NumberFormatException nex){
                JOptionPane.showMessageDialog(this, "Tournament ID is an integer.");
            }
            catch (ViewTournamentFailed ex){
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        } else if (ae.getSource() == goBackButton) {
            nextScreenData.setCurrentUser(nextScreenData.getCurrentUser());
            OptionsScreen optionsScreen = new OptionsScreen(nextScreenData);
            this.dispose();
            optionsScreen.setVisible(true);
        }

    }
}

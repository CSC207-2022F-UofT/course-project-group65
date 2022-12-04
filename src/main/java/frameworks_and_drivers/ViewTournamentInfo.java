package frameworks_and_drivers;

import frameworks_and_drivers.ExtendedView;
import interface_adapters.NextScreenData;
//import screens.bracketView;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.start_tournament.StartTournController;
import interface_adapters.view_tournament.ViewTournamentController;
import interface_adapters.view_tournament.ViewTournamentFailed;
import use_cases.view_tournament.ViewTournamentOD;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ViewTournamentInfo extends JFrame implements ActionListener{
    private JPanel viewTournament;
    private JButton btSubmit;
    private JLabel lbTournamentID;
    private JComboBox<Integer> tournamentIDs;
    private JTextField tournamentID;
    private ViewTournamentController controller;
    private NextScreenData nextScreenData;

    public ViewTournamentInfo(ViewTournamentController controller, NextScreenData nextScreenData) {
        this.controller = controller;
        this.nextScreenData = nextScreenData;
        setTitle("View Tournament");
        setSize(450, 300);
        setVisible(true);
        btSubmit.addActionListener(this);
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
        try {
            int tournID = tournamentIDs.getItemAt(tournamentIDs.getSelectedIndex());
            controller.setPresenterData(nextScreenData);
            controller.viewTournament(tournID);
            EndTournController endTournController = new EndTournController(nextScreenData.getCurrentUser(), nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());
            StartTournController startTournController = new StartTournController(nextScreenData.getCurrentUser(), nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID());

            JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
            nextScreenData.bundleData();
            ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController, joinTeamController);
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
    }
}

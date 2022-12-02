package screens.viewTournament;

import screens.ExtendedView;
import screens.NextScreenData;
//import screens.bracketView;
import screens.endTourn.EndTournController;
import screens.joinTeam.JoinTeamController;
import screens.startTourn.StartTournController;
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
//    private JComboBox<Integer> cbTournamentID;
    private ViewTournamentController controller;
    private NextScreenData nextScreenData;

    public ViewTournamentInfo(ViewTournamentController controller, NextScreenData nextScreenData) {
        this.controller = controller;
        this.nextScreenData = nextScreenData;
        setTitle("View Tournament");
        setSize(450, 300);
        setVisible(true);
        //cbTournamentID.add() adding tournaments from users current tournaments
        // may just change to a Jtextfield if too difficult
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
        //int tournamentID = (Integer) cbTournamentID.getSelectedItem();

//        if (tournamentIDs.getSelectedItem() == null){
//            JOptionPane.showMessageDialog(null, "Please select a tournament");
//        }

        try {
            int tournID = tournamentIDs.getItemAt(tournamentIDs.getSelectedIndex());
            ViewTournamentOD outData = controller.viewTournament(tournID);

//            EndTournOB endTournOB = new EndTournPresenter();
//            EndTournIB endTournIB = new EndTournUC(endTournOB, outData.getUsername(), nextScreenData.getInformationRecord(), outData.getTournamentID());
            EndTournController endTournController = new EndTournController(outData.getUsername(), nextScreenData.getInformationRecord(), outData.getTournamentID());


//            StartTournOB startTournOB = new StartTournPresenter();
//            StartTournIB startTournIB = new StartTournUC(startTournOB, outData.getUsername(), nextScreenData.getInformationRecord(), outData.getTournamentID());
            StartTournController startTournController = new StartTournController(outData.getUsername(), nextScreenData.getInformationRecord(), outData.getTournamentID());


            JoinTeamController joinTeamController = new JoinTeamController(nextScreenData.getInformationRecord(), outData.getTournamentID(), outData.getUsername());

//            NextScreenData nextScreenData = new NextScreenData(this.nextScreenData.getInformationRecord());
            //nextScreenData.setBrackets(outData.getBrackets());
            //nextScreenData.setAccounts(outData.getAccounts());
            nextScreenData.setCurrentUser(outData.getUsername());
            nextScreenData.setCurrentBracketID(outData.getTournamentID());

            // Add a layer in between. This does not follow clean architecture.
//            BundleBracketData bundleBracketData = new BundleBracketData();
//            bundleBracketData.bundleBracket(nextScreenData.getBrackets().getBracket(nextScreenData.getCurrentBracketID()));
            nextScreenData.bundleData();
            ExtendedView view = new ExtendedView(nextScreenData, endTournController, startTournController, joinTeamController);
//
//            bracketView view = new bracketView(nextScreenData, endTournController, startTournController, joinTeamController);
//            view.setBracketName(outData.getTournamentName());
//            view.setCurrentUser(outData.getUsername());
//            view.setCurrentTournament(outData.getTournamentID());
//            view.setPlayerInvite(outData.getInvite("Player"));
//            view.setObserverInvite(outData.getInvite("Observer"));
//            view.setObserverListGame1(outData.getReferees());
//            view.setObserverListGame2(outData.getReferees());
//            view.setObserverListGame3(outData.getReferees());
//
//            LinkedHashMap<Integer, ArrayList<String>> gameToTeams = nextScreenData.getGameToTeams();
//            LinkedHashMap<Integer, ArrayList<Integer>> gameToScores = nextScreenData.getGameToScores();
//
//            ArrayList<Integer> games = new ArrayList<>(gameToTeams.keySet());
//
//            for (Integer gameID : games){
//                ArrayList<String> teams = gameToTeams.get(gameID);
//                ArrayList<Integer> scores = gameToScores.get(gameID);
//                if (gameID == 1){
//                    try{
//                        view.setGame1Label(teams.get(0), teams.get(1), scores.get(0), scores.get(1));
//                    } catch (IndexOutOfBoundsException e){
//                        if (scores.size() == 1){
//                            view.setGame1Label(teams.get(0), "", scores.get(0), 0);
//                        }
//                    }
//                }
//
//                if (gameID == 2){
//                    try{
//                        view.setGame2Label(teams.get(0), teams.get(1), scores.get(0), scores.get(1));
//                    } catch (IndexOutOfBoundsException e){
//                        if (scores.size() == 1){
//                            view.setGame2Label(teams.get(0), "", scores.get(0), 0);
//                        }
//                    }
//                }
//
//                if (gameID == 3){
//                    try{
//                        view.setGame3Label(teams.get(0), teams.get(1), scores.get(0), scores.get(1));
//                    } catch (IndexOutOfBoundsException e){
//                        if (scores.size() == 1){
//                            view.setGame3Label(teams.get(0), "", scores.get(0), 0);
//                        }
//                    }
//                }
//            }
//
//            ArrayList<String> teams = new ArrayList<>();
//            for (ArrayList<String> teamsList : outData.getGameToTeams().values()){
//                for (String team : teamsList){
//                    if (!teams.contains(team)){
//                        teams.add(team);
//                    }
//                }
//            }
//            LinkedHashMap<String, ArrayList<String>> teamToPlayers = nextScreenData.getTeamToPlayers();
//
//            for (String team : teams){
//                if (team.equals(teams.get(0))) {
//                    view.setTeam1Name(teams.get(0));
//                    view.setTeam1PlayerList(teamToPlayers.get(teams.get(0)).toArray(new String[0]));
//                }
//
//                if (team.equals(teams.get(1))) {
//                    view.setTeam2Name(teams.get(1));
//                    view.setTeam2PlayerList(teamToPlayers.get(teams.get(1)).toArray(new String[0]));
//                }
//
//                if (team.equals(teams.get(2))) {
//                    view.setTeam3Name(teams.get(2));
//                    view.setTeam3PlayerList(teamToPlayers.get(teams.get(2)).toArray(new String[0]));
//                }
//
//                if (team.equals(teams.get(3))) {
//                    view.setTeam4Name(teams.get(3));
//                    view.setTeam4PlayerList(teamToPlayers.get(teams.get(3)).toArray(new String[0]));
//                }
//
//            }

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

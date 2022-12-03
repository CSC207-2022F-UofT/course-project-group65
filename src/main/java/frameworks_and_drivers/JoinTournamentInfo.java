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
            JoinTournamentOD outData = controller.joinTournament(invite);

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

//            bracketView view = new bracketView(nextScreenData, endTournController, startTournController, joinTeamController);
//            view.setObserverListGame1(outData.getReferees());
//            view.setObserverListGame2(outData.getReferees());
//            view.setObserverListGame3(outData.getReferees());
//            view.setBracketName(outData.getTournamentName());
//            view.setCurrentUser(outData.getUsername());
//            view.setCurrentTournament(outData.getTournamentID());
//            view.setPlayerInvite(outData.getInvite("Player"));
//            view.setObserverInvite(outData.getInvite("Observer"));
//
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
        catch (JoinTournamentFailed ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

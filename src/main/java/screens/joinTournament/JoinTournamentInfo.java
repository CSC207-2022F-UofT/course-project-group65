package screens.joinTournament;

import database.JoinTeam.JoinTeamFileWriter;
import screens.NextScreenData;
import screens.bracketView;
import screens.endTourn.EndTournController;
import screens.endTourn.EndTournPresenter;
import screens.joinTeam.JoinTeamController;
import screens.joinTeam.JoinTeamPresenter;
import screens.startTourn.StartTournController;
import screens.startTourn.StartTournPresenter;
import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournOB;
import useCases.endTourn.EndTournUC;
import useCases.generalClasses.bundleBracketData.BundleBracketData;
import useCases.joinTeam.JoinTeamGateway;
import useCases.joinTeam.JoinTeamIB;
import useCases.joinTeam.JoinTeamOB;
import useCases.joinTeam.JoinTeamUC;
import useCases.joinTournament.JoinTournamentOD;
import useCases.startTourn.StartTournIB;
import useCases.startTourn.StartTournOB;
import useCases.startTourn.StartTournUC;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class JoinTournamentInfo extends JFrame implements ActionListener {
    private JPanel joinTournament;
    private JButton btSubmit;
    private JTextField tfInvite;
    private JLabel lbInvite;
    private JoinTournamentController controller;

    public JoinTournamentInfo(JoinTournamentController controller){
        this.controller = controller;
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

            EndTournOB endTournOB = new EndTournPresenter();
            EndTournIB endTournIB = new EndTournUC(endTournOB, outData.getUsername(), outData.getAccounts(),
                    outData.getBrackets(), outData.getTournamentID());
            EndTournController endTournController = new EndTournController(endTournIB);

            StartTournOB startTournOB = new StartTournPresenter();
            StartTournIB startTournIB = new StartTournUC(startTournOB, outData.getUsername(), outData.getAccounts(),
                    outData.getBrackets(), outData.getTournamentID());
            StartTournController startTournController = new StartTournController(startTournIB);

            JoinTeamController joinTeamController = new JoinTeamController(outData.getBrackets(),
                    outData.getAccounts(), outData.getTournamentID(), outData.getUsername());

            NextScreenData nextScreenData = new NextScreenData();
            nextScreenData.setBrackets(outData.getBrackets());
            nextScreenData.setAccounts(outData.getAccounts());
            nextScreenData.setCurrentUser(outData.getUsername());
            nextScreenData.setCurrentBracketID(outData.getTournamentID());

            // Add a layer in between. This does not follow clean architecture.
            BundleBracketData bundleBracketData = new BundleBracketData();
            bundleBracketData.bundleBracket(nextScreenData.getBrackets().getBracket(nextScreenData.getCurrentBracketID()));

            bracketView view = new bracketView(nextScreenData, endTournController, startTournController, joinTeamController);
            view.setObserverListGame1(outData.getReferees());
            view.setObserverListGame2(outData.getReferees());
            view.setObserverListGame3(outData.getReferees());
            view.setBracketName(outData.getTournamentName());
            view.setCurrentUser(outData.getUsername());
            view.setCurrentTournament(outData.getTournamentID());
            view.setPlayerInvite(outData.getInvite("Player"));
            view.setObserverInvite(outData.getInvite("Observer"));


            LinkedHashMap<Integer, ArrayList<String>> gameToTeams = bundleBracketData.getGameToTeams();
            LinkedHashMap<Integer, ArrayList<Integer>> gameToScores = bundleBracketData.getGameToScores();

            ArrayList<Integer> games = new ArrayList<>(gameToTeams.keySet());

            for (Integer gameID : games){
                ArrayList<String> teams = gameToTeams.get(gameID);
                ArrayList<Integer> scores = gameToScores.get(gameID);
                try {
                    if (gameID == 1) {
                        view.setGame1Label(teams.get(0), teams.get(1), scores.get(0), scores.get(1));
                    } else if (gameID == 2) {
                        view.setGame2Label(teams.get(0), teams.get(1), scores.get(0), scores.get(1));
                    } else if (gameID == 3) {
                        view.setGame3Label(teams.get(0), teams.get(1), scores.get(0), scores.get(1));
                    }
                } catch (Exception e){
                    System.out.println("Teams not yet filled");
                }
            }


            ArrayList<String> teams = new ArrayList<>();
            outData.getGameToTeams().forEach((gameID, team) -> {
                teams.addAll(team);
            });
            System.out.println(bundleBracketData.getTeamToPlayers().get(teams.get(0)));
            System.out.println(bundleBracketData.getTeamToPlayers().get(teams.get(1)));
            System.out.println(bundleBracketData.getTeamToPlayers().get(teams.get(2)));
            System.out.println(bundleBracketData.getTeamToPlayers().get(teams.get(3)));
//            outData.getGameToTeams().values().forEach(teams::addAll);
            if (teams.get(0) != null){
                view.setTeam1Name(teams.get(0));
//                String[] playerList = bundleBracketData.getTeamToPlayers().get(teams.get(0)).toArray(new String[0]);
//                System.out.println(playerList);
                view.setTeam1PlayerList(bundleBracketData.getTeamToPlayers().get(teams.get(0)).toArray(new String[0]));
            }

            if (teams.get(1) != null){
                view.setTeam2Name(teams.get(1));
//                String[] playerList = bundleBracketData.getTeamToPlayers().get(teams.get(1)).toArray(new String[0]);
//                System.out.println(playerList);
                view.setTeam2PlayerList(bundleBracketData.getTeamToPlayers().get(teams.get(1)).toArray(new String[0]));
            }

            if (teams.get(2) != null){
                view.setTeam3Name(teams.get(2));
//                String[] playerList = bundleBracketData.getTeamToPlayers().get(teams.get(1)).toArray(new String[0]);
//                System.out.println(playerList);
                view.setTeam3PlayerList(outData.getTeamToPlayers().get(teams.get(2)).toArray(new String[0]));
            }

            if (teams.get(3) != null){
                view.setTeam4Name(teams.get(3));
//                String[] playerList = bundleBracketData.getTeamToPlayers().get(teams.get(1)).toArray(new String[0]);
//                System.out.println(bundleBracketData.getTeamToPlayers().get(teams.get(1)).size());
//                //System.out.println(playerList.length);
                view.setTeam4PlayerList(bundleBracketData.getTeamToPlayers().get(teams.get(3)).toArray(new String[0]));
            }



            dispose();
            view.setVisible(true);
        }
        catch (JoinTournamentFailed ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

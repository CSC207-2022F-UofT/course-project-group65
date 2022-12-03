package frameworks_and_drivers;

import interface_adapters.NextScreenData;
import interface_adapters.advance_team.AdvanceTeamController;
import interface_adapters.assign_observer.AssignObserverController;
import frameworks_and_drivers.bracket_operations.DoBracketOperation;
import interface_adapters.change_points.ChangePointsController;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.declare_winner.DeclareWinnerController;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.log_in.LogInController;
import interface_adapters.start_tournament.StartTournController;
import interface_adapters.team_creation.TeamCreationController;
import use_cases.assign_observer.AssignObserverOD;
import use_cases.join_team.JoinTeamOD;
import use_cases.start_tournament.StartTournOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class ExtendedView extends JFrame implements ActionListener, IBracketView {

    private JTabbedPane mainTabbedPane;
    private JPanel mainPanel;
    private JScrollPane pnlBracketScrollPane;
    private JPanel pnlBracket;
    private JPanel pnlTeams;
    private JPanel pnlObserver;
    private JPanel pnlOverseer;
    private ArrayList<JLabel> lblBracketGameScores = new ArrayList<>();
    private ArrayList<JLabel> lblBracketGameWinner = new ArrayList<>();
    private ArrayList<JButton> btnBracketGame = new ArrayList<>();
    private JLabel lblTeamList;
    private JComboBox<String> cmbJoinTeam;
    private JButton btnJoinTeam;
    private JButton btnCreateTeam;
    private JLabel lblSelectGame;
    private JComboBox<Integer> cmbSelectGame;
    private JLabel lblAssignObserver;
    private JComboBox<String> cmbAssignObserver;
    private JButton btnAssignObserver;
    private JButton btnStart;
    private JButton btnEnd;
    private JLabel lblPLInv;
    private JLabel lblOBInv;
    private JLabel lblCurrUser;
    private JLabel lblTournamentName;
    private JLabel lblTournamentID;
    private JButton btnOptions;
    private JButton btnLogOut;
    private JScrollPane teamMembers;
    private JScrollPane observerAssignments;
    public NextScreenData nextScreenData;
    private String[][] teamMemberData;
    private String[][] observerData;
    private EndTournController endTournController;
    private JoinTeamController joinTeamController;
    private StartTournController startTournController;

    public ExtendedView(NextScreenData nsdata, EndTournController endTournController, StartTournController startTournController,
                        JoinTeamController joinTeamController) {
        super ("Tournament View");
        this.nextScreenData = nsdata;
        nextScreenData.bundleData();
        LinkedHashMap<Integer, ArrayList<String>> gameToTeams = nextScreenData.getGameToTeams();
        LinkedHashMap<Integer, ArrayList<Integer>> gameToScore = nextScreenData.getGameToScores();
        LinkedHashMap<Integer, String> gameToWinner = nextScreenData.getGameToWinner();
        LinkedHashMap<String, ArrayList<String>> teamToPlayers = nextScreenData.getTeamToPlayers();
        ArrayList<String> referees = nextScreenData.getReferees();
        LinkedHashMap<Integer, String> gameToReferee = nextScreenData.getGameToReferee();

        //Tabbed Pane
        this.endTournController = endTournController;
        this.startTournController = startTournController;
        this.joinTeamController = joinTeamController;
        lblCurrUser.setText("Logged In: " + nextScreenData.getCurrentUser());
        lblTournamentName.setText("Bracket Name: " + nextScreenData.getTournamentName());
        lblTournamentID.setText("ID: " + nextScreenData.getTournamentID());
        btnOptions.addActionListener(this);
        btnLogOut.addActionListener(this);

        //Bracket View
        int currGame = gameToTeams.size();
        int index = 0;
        int numTeams = teamToPlayers.size();
        int rounds = ((Double)(Math.log(numTeams)/Math.log(2))).intValue();
        String scoreText = "";
        String winnerText;
        ArrayList<String> teams;

        //JComponent ui = new JPanel(new BorderLayout(5, 5));
        //ui.add(new JScrollPane(mainPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS));
//        pnlBracket.add(new JScrollPane(pnlBracket));


        // Team members added to team view
        String[] column = {"Team Name", "Team Members"};
        this.teamMemberData = new String[teamToPlayers.size()][nextScreenData.getMaxTeamSize(nextScreenData.getCurrentBracketID())];
        int index1 = 0;
        for (String team : teamToPlayers.keySet()) {
            teamMemberData[index1][0] = team;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < teamToPlayers.get(team).size(); i++) {
                if (i == teamToPlayers.get(team).size() - 1) {
                    sb.append(teamToPlayers.get(team).get(i));
                } else {
                    sb.append(teamToPlayers.get(team).get(i)).append(", ");
                }
            }
            teamMemberData[index1][1] = String.valueOf(sb);
            index1++;
        }

        JTable teamMemberTable = new JTable(teamMemberData, column);
        teamMembers.add(teamMemberTable);
        teamMembers.getViewport().add(teamMemberTable);
        teamMembers.setVisible(true);
        teamMemberTable.setVisible(true);

        // Observer Assignments
        String[] obsColumn = {"Game", "Observer"};
        this.observerData = new String[gameToReferee.size()][2];
        int index2 = 0;
        for (Integer game : gameToReferee.keySet()) {
            observerData[index2][0] = String.valueOf(game);
            if (gameToReferee.get(game) != null) {
                observerData[index2][1] = gameToReferee.get(game);
            } else {
                observerData[index2][1] = "No Observer Assigned";
            }
            index2++;
        }
        JTable observerTable = new JTable(observerData, obsColumn);
        observerAssignments.add(observerTable);
        observerAssignments.getViewport().add(observerTable);
        observerAssignments.setVisible(true);
        observerTable.setVisible(true);

        pnlBracket.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        for(int i=1; i<=rounds; i++){
            gbc.gridx = i;
            for(int j= ((Double)(numTeams/Math.pow(2, rounds-i))).intValue();
                j<= Math.pow(2, rounds + 1); j+= Math.pow(2, i + 1)){
                gbc.gridy = j - 1;
                pnlBracket.add(new JLabel(""), gbc);
                teams = gameToTeams.get(currGame);
                switch (teams.size()){
                    case 0: scoreText = " [] - [] ";
                        break;
                    case 1: scoreText = teams.get(0) + " [" + gameToScore.get(currGame).get(0) + "] - [] ";
                        break;
                    case 2: scoreText = teams.get(0) + " [" + gameToScore.get(currGame).get(0) + "] - ["
                            + gameToScore.get(currGame).get(1) + "] " + teams.get(1);
                        break;
                }
                lblBracketGameScores.add(new JLabel(scoreText));
                gbc.gridy = j;
                pnlBracket.add(lblBracketGameScores.get(index), gbc);
                if(gameToWinner.containsKey(currGame) && !(gameToWinner.get(currGame) == null)){
                    winnerText = "Winner: " + gameToWinner.get(currGame);
                }
                else {
                    winnerText = "";
                }
                lblBracketGameWinner.add(new JLabel(winnerText));
                gbc.gridy = j + 1;
                pnlBracket.add(lblBracketGameWinner.get(index), gbc);
                btnBracketGame.add(new JButton("Game " + currGame));
                btnBracketGame.get(index).addActionListener(this);
                gbc.gridy = j + 2;
                pnlBracket.add(btnBracketGame.get(index), gbc);
                currGame --;
                index ++;
            }
        }

        //Teams View
        btnCreateTeam.addActionListener(this);
        btnJoinTeam.addActionListener(this);
        //setTeams(teamToPlayers);
        for(String team: teamToPlayers.keySet()){
            cmbJoinTeam.addItem(team);
        }
        cmbJoinTeam.addActionListener(this);

        //Observer View
        for(int gameID: gameToReferee.keySet()){
            cmbSelectGame.addItem(gameID);
        }
        for(String ref: referees){
            cmbAssignObserver.addItem(ref);
        }
        System.out.println("Referees: " + referees);
        cmbSelectGame.addActionListener(this);
        cmbAssignObserver.addActionListener(this);
        btnAssignObserver.addActionListener(this);

        //Overseer View
        btnStart.addActionListener(this);
        btnEnd.addActionListener(this);
        lblPLInv.setText("Player: " + nextScreenData.getRoleToInvite().get("Player"));
        lblOBInv.setText("Observer: " + nextScreenData.getRoleToInvite().get("Observer"));

        lblCurrUser.setVisible(true);
        lblTournamentName.setVisible(true);
        lblTournamentID.setVisible(true);
        lblAssignObserver.setVisible(true);
        lblSelectGame.setVisible(true);
        lblTeamList.setVisible(true);
        lblOBInv.setVisible(true);
        lblPLInv.setVisible(true);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(1200, 800));
        pack();
        setVisible(true);
    }

    public void updateGameScore(int gameID, ArrayList<String> teams, ArrayList<Integer> points) {
        int index = lblBracketGameScores.size() - gameID;
        String score = "";
        switch (teams.size()) {
            case 0:
                score = " [] - [] ";
                break;
            case 1:
                score = teams.get(0) + " [" + points.get(0) + "] - [] ";
                break;
            case 2:
                score = teams.get(0) + " [" + points.get(0) + "] - ["
                        + points.get(1) + "] " + teams.get(1);
                break;
        }
        lblBracketGameScores.get(index).setText(score);
    }

    public void updateWinner(int gameID, String winner){
        int index = lblBracketGameScores.size() - gameID;
        lblBracketGameWinner.get(index).setText("Winner: " + winner);
    }

//    public void addObserver(LinkedHashMap<Integer, String> gameToReferee){
//        cmbAssignObserver.removeAllItems();
//        ArrayList<String> referees = new ArrayList<>(gameToReferee.values());
//        for(String ref: referees){
//            cmbAssignObserver.addItem(ref);
//        }
//    }

    public void replaceTeam(String newTeam, String oldTeam, LinkedHashMap<Integer, ArrayList<String>> gameToTeams){
        for(int i=0; i<cmbJoinTeam.getItemCount(); i++){
            if(cmbJoinTeam.getItemAt(i).contains(oldTeam)){
                cmbJoinTeam.insertItemAt(newTeam, i);
                cmbJoinTeam.removeItem(oldTeam);
                break;
            }
        }

        LinkedHashMap<Integer, ArrayList<String>> change = new LinkedHashMap<>();
        for(int id: gameToTeams.keySet()){
            if(gameToTeams.get(id).contains(newTeam)){
                change.put(id, gameToTeams.get(id));
            }
        }

        int numGames = lblBracketGameScores.size();
        for(int id: change.keySet()){
            String score = lblBracketGameScores.get(numGames - id).getText();

            int a = score.indexOf('[');
            if (score.substring(0, a).contains(oldTeam)) {
                String lastHalf = score.substring(a);
                lblBracketGameScores.get(numGames - id).setText(newTeam + " " + lastHalf);
            } else {
                a = score.lastIndexOf(']');
                String firstHalf = score.substring(0, a + 2);
                lblBracketGameScores.get(numGames - id).setText(firstHalf + newTeam);
            }

            String winner = lblBracketGameWinner.get(numGames - id).getText();
            if(!winner.equals("") && winner.contains(oldTeam)){
                lblBracketGameWinner.get(numGames - id).setText("Winner: " + newTeam);
            }
        }
    }

    public void updateTeamMembers(HashMap<String, ArrayList<String>> teamToPlayers){
        String[] column = {"Team Name", "Team Members"};
        this.teamMemberData = new String[teamToPlayers.size()][nextScreenData.getMaxTeamSize(nextScreenData.getCurrentBracketID())];
        int index1 = 0;
        for (String team : teamToPlayers.keySet()) {
            teamMemberData[index1][0] = team;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < teamToPlayers.get(team).size(); i++) {
                if (i == teamToPlayers.get(team).size() - 1) {
                    sb.append(teamToPlayers.get(team).get(i));
                } else {
                    sb.append(teamToPlayers.get(team).get(i)).append(", ");
                }
            }
            teamMemberData[index1][1] = String.valueOf(sb);
            index1++;
        }

        JTable teamMemberTable = new JTable(teamMemberData, column);
        teamMembers.add(teamMemberTable);
        teamMembers.getViewport().add(teamMemberTable);
        teamMemberTable.setVisible(true);
    }

    public void updateObserverAssignments(LinkedHashMap<Integer, String> gameToReferee){
        String[] obsColumn = {"Game", "Observer"};
        this.observerData = new String[gameToReferee.size()][2];
        int index2 = 0;
        for (Integer game : gameToReferee.keySet()) {
            observerData[index2][0] = String.valueOf(game);
            if (gameToReferee.get(game) != null) {
                observerData[index2][1] = gameToReferee.get(game);
            } else {
                observerData[index2][1] = "No Observer Assigned";
            }
            index2++;
        }
        JTable observerTable = new JTable(observerData, obsColumn);
        observerAssignments.add(observerTable);
        observerAssignments.getViewport().add(observerTable);
        observerTable.setVisible(true);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        AdvanceTeamController advanceTeamController = new AdvanceTeamController(nextScreenData.getInformationRecord(),
                nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
        DeclareWinnerController declareWinnerController = new DeclareWinnerController(
                nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(),
                nextScreenData.getCurrentUser());
        ChangePointsController changePointsController = new ChangePointsController(nextScreenData.getInformationRecord(),
                nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
//
        if(e.getSource() == btnOptions){
            nextScreenData.setCurrentUser(nextScreenData.getCurrentUser());
            OptionsScreen optionsScreen = new OptionsScreen(nextScreenData);
            this.dispose();
            optionsScreen.setVisible(true);
        }
        else if(e.getSource() == btnLogOut){
            CreateAccountController createAccountController = new CreateAccountController(nextScreenData.getInformationRecord());
            LogInController logInController = new LogInController(nextScreenData.getInformationRecord());
            HomeScreen homeScreen = new HomeScreen(createAccountController, logInController, nextScreenData);
            this.dispose();
            homeScreen.setVisible(true);
        }
        else if(e.getSource() == btnCreateTeam){
            TeamCreationController controller = new TeamCreationController(nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
            UserInput inputScreen = new UserInput(controller, this, this.nextScreenData);
            inputScreen.setVisible(true);
            this.nextScreenData.bundleData();

            //refactor
            cmbJoinTeam.removeAllItems();
            for(String team: nextScreenData.getTeamToPlayers().keySet()) {
                cmbJoinTeam.addItem(team);
            }
        }
        else if(e.getSource() == btnJoinTeam){
            String teamName = (String)cmbJoinTeam.getSelectedItem();
            try {
                JoinTeamOD outputData = joinTeamController.joinTeam(teamName);
//                ArrayList<String> names = outputData.getMembersNames();
//                nextScreenData.bundleData();
                updateTeamMembers(outputData.getTeamToPlayers());
            }
            catch (Exception exception) {
                System.out.println( "Error: " + exception);
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
        else if(e.getSource() == btnAssignObserver){
            try {
                int gameID = (Integer) cmbSelectGame.getSelectedItem();
                String assignee = (String) cmbAssignObserver.getSelectedItem();
                AssignObserverController controller = new AssignObserverController(nextScreenData.getInformationRecord(),
                        nextScreenData.getCurrentUser());
                AssignObserverOD output = controller.assignObserver(assignee, gameID);
//                this.nextScreenData.bundleData();
                updateObserverAssignments(output.getGameToReferee());
//                addObserver(output.getGameToReferee());
            }
            catch(RuntimeException rex ) {
                JOptionPane.showMessageDialog(this, rex.getMessage());
            }
        }
        else if(e.getSource() == btnStart){
            StartTournOD startData = startTournController.startTourn();
            ArrayList<String> startErrors = startData.getErrors();
            StartErrors errorView = new StartErrors(this.startTournController);
            for (String error : startErrors) {
                if (Objects.equals(error, "USERROLE")) {
                    errorView.setWarning1("You do not have permission to start the tournament.");
                } else if (Objects.equals(error, "NUMTEAMS")) {
                    errorView.setWarning2("There are not enough teams in the tournament.");
                } else if (Objects.equals(error, "NOOBSERVER")) {
                    errorView.setWarning3("There is at least one game that does not have an observer assigned.");
                } else if (Objects.equals(error, "TEAMNOTFULL")) {
                    errorView.setWarning4("There is at least one team that is not full.");
                }
            }
            errorView.setVisible(true);
        }
        else if(e.getSource() == btnEnd){
            try {
                endTournController.endTourn();
                JOptionPane.showMessageDialog(this, "Tournament Ended");
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
        for(int i=0; i<btnBracketGame.size(); i++){
            if (e.getSource() == btnBracketGame.get(i)){
                DoBracketOperation doBracketOperations = new DoBracketOperation(advanceTeamController,
                        declareWinnerController, changePointsController, this, nextScreenData);
                doBracketOperations.setGameForOperation(btnBracketGame.size()-i);
                doBracketOperations.setGameNumLabel("Game " + (btnBracketGame.size()-i));
                doBracketOperations.setTeamsLabel(lblBracketGameScores.get(i).getText());
                doBracketOperations.setVisible(true);
                break;
            }
        }
        repaint();
    }

//    private void createUIComponents() {
//        pnlBracket = new JPanel();
//    }
}
package frameworks_and_drivers;

import frameworks_and_drivers.bracket_operations.DoBracketOperation;
import interface_adapters.NextScreenData;
import interface_adapters.advance_team.AdvanceTeamController;
import interface_adapters.advance_team.AdvanceTeamPresenter;
import interface_adapters.assign_observer.AssignObserverController;
import interface_adapters.change_points.ChangePointsController;
import interface_adapters.change_points.ChangePointsPresenter;
import interface_adapters.create_account.CreateAccountController;
import interface_adapters.declare_winner.DeclareWinnerController;
import interface_adapters.declare_winner.DeclareWinnerPresenter;
import interface_adapters.end_tournament.EndTournController;
import interface_adapters.join_team.JoinTeamController;
import interface_adapters.log_in.LogInController;
import interface_adapters.start_tournament.StartTournController;
import interface_adapters.team_creation.TeamCreationController;
import interface_adapters.team_creation.TeamCreationPresenter;
import interface_adapters.view_interfaces.main_view_interfaces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Objects;

public class ExtendedView extends JFrame implements ActionListener, AssignObserverView, JoinTeamViewInterface,
        TeamCreationView, AdvanceTeamView, DeclareWinnerView, ChangePointsExtendedView {

    public NextScreenData nextScreenData;
    private JTabbedPane mainTabbedPane;
    private JPanel mainPanel;
    private JScrollPane pnlBracketScrollPane;
    private JPanel pnlBracket;
    private JPanel pnlTeams;
    private JPanel pnlObserver;
    private JPanel pnlOverseer;
    private final ArrayList<JLabel> lblBracketGameScores = new ArrayList<>();
    private final ArrayList<JLabel> lblBracketGameWinner = new ArrayList<>();
    private final ArrayList<JButton> btnBracketGame = new ArrayList<>();
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
    private String[][] teamMemberData;
    private String[][] observerData;
    private final EndTournController endTournController;
    private final JoinTeamController joinTeamController;
    private final StartTournController startTournController;
    public ExtendedView(NextScreenData nsdata, EndTournController endTournController, StartTournController startTournController,
                        JoinTeamController joinTeamController) {
        super("Tournament View");
        this.nextScreenData = nsdata;
        nextScreenData.bundleData();
        LinkedHashMap<Integer, ArrayList<String>> gameToTeams = nextScreenData.getGameToTeams();
        LinkedHashMap<Integer, ArrayList<Integer>> gameToScore = nextScreenData.getGameToScores();
        LinkedHashMap<Integer, String> gameToWinner = nextScreenData.getGameToWinner();
        LinkedHashMap<String, ArrayList<String>> teamToPlayers = nextScreenData.getTeamToPlayers();
        ArrayList<String> referees = nextScreenData.getReferees();
        LinkedHashMap<Integer, String> gameToReferee = nextScreenData.getGameToReferee();

        //Set up the Tabbed Pane
        this.endTournController = endTournController;
        this.startTournController = startTournController;
        this.joinTeamController = joinTeamController;
        lblCurrUser.setText("Logged In: " + nextScreenData.getCurrentUser());
        lblTournamentName.setText("Bracket Name: " + nextScreenData.getTournamentName());
        lblTournamentID.setText("ID: " + nextScreenData.getCurrentBracketID());
        btnOptions.addActionListener(this);
        btnLogOut.addActionListener(this);


        //Set up the Bracket View
        pnlBracket.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        //Local variables needed for the loop
        int currGame = gameToTeams.size();
        int index = 0;
        int numTeams = teamToPlayers.size();
        int rounds = ((Double) (Math.log(numTeams) / Math.log(2))).intValue();
        String scoreText = "";
        String winnerText;
        ArrayList<String> teams;

        //Loop through columns
        for (int i = 1; i <= rounds; i++) {
            gbc.gridx = i;
            //Loop through rows (Each game takes up 4 rows)
            for (int j = ((Double) (numTeams / Math.pow(2, rounds - i))).intValue();
                 j <= Math.pow(2, rounds + 1); j += Math.pow(2, i + 1)) {
                //First row of game display (Blank Space)
                gbc.gridy = j - 1;
                pnlBracket.add(new JLabel(""), gbc);
                //Second row of game display (Score)
                teams = gameToTeams.get(currGame);
                //Choose what format of score to display based on number of teams
                switch (teams.size()){
                    case 0: scoreText = " [] - [] ";
                        break;
                    case 1: scoreText = teams.get(0) + " [" + gameToScore.get(currGame).get(0) + "] - [] ";
                        break;
                    case 2: scoreText = teams.get(0) + " [" + gameToScore.get(currGame).get(0) + "] - ["
                            + gameToScore.get(currGame).get(1) + "] " + teams.get(1);
                        break;
                }
                //Add label for this games score to the array list
                lblBracketGameScores.add(new JLabel(scoreText));
                gbc.gridy = j;
                pnlBracket.add(lblBracketGameScores.get(index), gbc);
                //Third row of game display (Winner)
                if (gameToWinner.containsKey(currGame) && !(gameToWinner.get(currGame) == null)) {
                    winnerText = "Winner: " + gameToWinner.get(currGame);
                } else {
                    winnerText = "";
                }
                //Add label for this games winner to the array list
                lblBracketGameWinner.add(new JLabel(winnerText));
                gbc.gridy = j + 1;
                pnlBracket.add(lblBracketGameWinner.get(index), gbc);
                //Last row of game display (Game button)
                //Add button for this game to the array list
                btnBracketGame.add(new JButton("Game " + currGame));
                btnBracketGame.get(index).addActionListener(this);
                gbc.gridy = j + 2;
                pnlBracket.add(btnBracketGame.get(index), gbc);
                currGame--;
                index++;
            }
        }

        //Teams View
        btnCreateTeam.addActionListener(this);
        btnJoinTeam.addActionListener(this);
        for (String team : teamToPlayers.keySet()) {
            cmbJoinTeam.addItem(team);
        }
        cmbJoinTeam.addActionListener(this);

        //Creating the data for the Teams/Members table
        String[] column = {"Team Name", "Team Members"};
        this.teamMemberData = new String[teamToPlayers.size()][nextScreenData.getMaxTeamSize(nextScreenData.getCurrentBracketID())];
        int index1 = 0;
        //Loop through teams
        for (String team : teamToPlayers.keySet()) {
            teamMemberData[index1][0] = team;
            StringBuilder sb = new StringBuilder();
            //Loop through players in team
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
        //Creating the Teams/Members table
        JTable teamMemberTable = new JTable(teamMemberData, column);
        teamMembers.add(teamMemberTable);
        teamMembers.getViewport().add(teamMemberTable);
        teamMembers.setVisible(true);
        teamMemberTable.setVisible(true);

        //Observer View
        for (int gameID : gameToReferee.keySet()) {
            cmbSelectGame.addItem(gameID);
        }
        for (String ref : referees) {
            cmbAssignObserver.addItem(ref);
        }
        cmbSelectGame.addActionListener(this);
        cmbAssignObserver.addActionListener(this);
        btnAssignObserver.addActionListener(this);


        //Creating data for assigned Observer table
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
        //Creating assigned Observer table
        JTable observerTable = new JTable(observerData, obsColumn);
        observerAssignments.add(observerTable);
        observerAssignments.getViewport().add(observerTable);
        observerAssignments.setVisible(true);
        observerTable.setVisible(true);

        //Overseer View
        btnStart.addActionListener(this);
        btnEnd.addActionListener(this);
        lblPLInv.setText("Player: " + nextScreenData.getRoleToInvite().get("Player"));
        lblOBInv.setText("Observer: " + nextScreenData.getRoleToInvite().get("Observer"));

        //Setting visibility
        lblCurrUser.setVisible(true);
        lblTournamentName.setVisible(true);
        lblTournamentID.setVisible(true);
        lblAssignObserver.setVisible(true);
        lblSelectGame.setVisible(true);
        lblTeamList.setVisible(true);
        lblOBInv.setVisible(true);
        lblPLInv.setVisible(true);
        mainTabbedPane.setVisible(true);
        pnlOverseer.setVisible(true);
        pnlObserver.setVisible(true);
        pnlTeams.setVisible(true);
        pnlBracketScrollPane.setVisible(true);

        //Frame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(1200, 800));
        pack();
        setVisible(true);
    }

    public void updateGameScore(int gameID, ArrayList<String> teams, ArrayList<Integer> points) {
        //Index of score label in lblBracketGameScores
        int index = lblBracketGameScores.size() - gameID;
        String score = "";
        //Choose format based on number of teams in the game
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

    public void updateWinner(int gameID, String winner) {
        int index = lblBracketGameScores.size() - gameID;
        lblBracketGameWinner.get(index).setText("Winner: " + winner);
    }

    public void replaceTeam(String newTeam, String oldTeam, LinkedHashMap<Integer, ArrayList<String>> gameToTeams) {
        //Replace old team name with new one in join team combobox
        for (int i = 0; i < cmbJoinTeam.getItemCount(); i++) {
            if (cmbJoinTeam.getItemAt(i).contains(oldTeam)) {
                cmbJoinTeam.insertItemAt(newTeam, i);
                cmbJoinTeam.removeItem(oldTeam);
                break;
            }
        }

        //Determine indices of bracket labels that need to change
        LinkedHashMap<Integer, ArrayList<String>> change = new LinkedHashMap<>();
        for (int id : gameToTeams.keySet()) {
            if (gameToTeams.get(id).contains(newTeam)) {
                change.put(id, gameToTeams.get(id));
            }
        }

        int numGames = lblBracketGameScores.size();
        for (int id : change.keySet()) {
            String score = lblBracketGameScores.get(numGames - id).getText();

            int a = score.indexOf('[');
            //check if the changed team is in the first or second half of score label
            if (score.substring(0, a).contains(oldTeam)) {
                String lastHalf = score.substring(a);
                lblBracketGameScores.get(numGames - id).setText(newTeam + " " + lastHalf);
            } else {
                a = score.lastIndexOf(']');
                String firstHalf = score.substring(0, a + 2);
                lblBracketGameScores.get(numGames - id).setText(firstHalf + newTeam);
            }

            String winner = lblBracketGameWinner.get(numGames - id).getText();
            //Only change winner label if there is a winner, and it is the old team
            if (!winner.equals("") && winner.contains(oldTeam)) {
                lblBracketGameWinner.get(numGames - id).setText("Winner: " + newTeam);
            }
        }
    }

    public void updateTeamMembers(LinkedHashMap<String, ArrayList<String>> teamToPlayers) {
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
    }

    public void updateObserverAssignments(LinkedHashMap<Integer, String> gameToReferee) {
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
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnOptions) {
            nextScreenData.setCurrentUser(nextScreenData.getCurrentUser());
            OptionsScreen optionsScreen = new OptionsScreen(nextScreenData);
            this.dispose();
            optionsScreen.setVisible(true);
        } else if (e.getSource() == btnLogOut) {
            CreateAccountController createAccountController = new CreateAccountController(nextScreenData.getInformationRecord());
            LogInController logInController = new LogInController(nextScreenData.getInformationRecord());
            HomeScreen homeScreen = new HomeScreen(createAccountController, logInController, nextScreenData);
            this.dispose();
            homeScreen.setVisible(true);
        } else if (e.getSource() == btnCreateTeam) {
            TeamCreationPresenter teamCreationPresenter = new TeamCreationPresenter(this);
            TeamCreationController controller = new TeamCreationController(teamCreationPresenter,
                    nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
            UserInput inputScreen = new UserInput(controller, this, this.nextScreenData);
            inputScreen.setVisible(true);
            this.nextScreenData.bundleData();
            cmbJoinTeam.removeAllItems();
            for (String team : nextScreenData.getTeamToPlayers().keySet()) {
                cmbJoinTeam.addItem(team);
            }
        } else if (e.getSource() == btnJoinTeam) {
            String teamName = (String) cmbJoinTeam.getSelectedItem();
            try {
                joinTeamController.setPresenterView(this);
                joinTeamController.joinTeam(teamName);
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == btnAssignObserver) {
            try {
                if(cmbSelectGame.getSelectedItem() == null){
                    JOptionPane.showMessageDialog(this, "Select a game to assign an observer to.");
                }
                int gameID = (Integer) cmbSelectGame.getSelectedItem();
                String assignee = (String) cmbAssignObserver.getSelectedItem();
                AssignObserverController controller = new AssignObserverController(nextScreenData.getInformationRecord(),
                        nextScreenData.getCurrentUser());
                controller.setPresenterView(this);
                controller.assignObserver(assignee, gameID);
            } catch (RuntimeException rex) {
                JOptionPane.showMessageDialog(this, rex.getMessage());
            }
        } else if (e.getSource() == btnStart) {
            ArrayList<String> startErrors = startTournController.startTourn();
            StartErrors errorView = new StartErrors(this.startTournController);
            boolean condition = false;
            for (String error : startErrors) {
                if (Objects.equals(error, "USERROLE")) {
                    condition = true;
                } else if (Objects.equals(error, "NUMTEAMS")) {
                    errorView.setWarning1("There are not enough teams in the tournament.");
                } else if (Objects.equals(error, "NOOBSERVER")) {
                    errorView.setWarning2("There is at least one game that does not have an observer assigned.");
                } else if (Objects.equals(error, "TEAMNOTFULL")) {
                    errorView.setWarning3("There is at least one team that is not full.");
                }
            }
            if (condition) {
                JOptionPane.showMessageDialog(this, "You do not have permission to start the tournament.");
            } else {
            errorView.setVisible(true);}
        } else if (e.getSource() == btnEnd) {
            try {
                endTournController.endTourn();
                JOptionPane.showMessageDialog(this, "Tournament Ended");
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        }
        AdvanceTeamPresenter advanceTeamPresenter = new AdvanceTeamPresenter(this);
        AdvanceTeamController advanceTeamController = new AdvanceTeamController(advanceTeamPresenter,
                nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
        DeclareWinnerPresenter declareWinnerPresenter = new DeclareWinnerPresenter(this);
        DeclareWinnerController declareWinnerController = new DeclareWinnerController(declareWinnerPresenter,
                nextScreenData.getInformationRecord(), nextScreenData.getCurrentBracketID(),
                nextScreenData.getCurrentUser());
        ChangePointsPresenter changePointsPresenter = new ChangePointsPresenter(this);
        ChangePointsController changePointsController = new ChangePointsController(changePointsPresenter, nextScreenData.getInformationRecord(),
                nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());

        for (int i = 0; i < btnBracketGame.size(); i++) {
            if (e.getSource() == btnBracketGame.get(i)) {
                DoBracketOperation doBracketOperations = new DoBracketOperation(advanceTeamController,
                        declareWinnerController, changePointsController);
                doBracketOperations.setGameForOperation(btnBracketGame.size() - i);
                doBracketOperations.setGameNumLabel("Game " + (btnBracketGame.size() - i));
                doBracketOperations.setTeamsLabel(lblBracketGameScores.get(i).getText());
                doBracketOperations.setVisible(true);
                break;
            }
        }
        repaint();
    }

}

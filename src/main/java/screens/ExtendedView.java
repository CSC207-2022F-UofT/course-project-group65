package screens;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Vector;

public class ExtendedView extends JFrame implements ActionListener, IBracketView{

    private JTabbedPane mainTabbedPane;
    private JPanel mainPanel;
    private JPanel pnlBracket;
    private JPanel pnlTeams;
    private JPanel pnlObserver;
    private JPanel pnlOverseer;
    private ArrayList<JLabel> lblBracketGameScores = new ArrayList<>();
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

    public static void main(String[] args){
        ExtendedView a = new ExtendedView();
        a.createUIComponents();
    }

    public ExtendedView() {
        super ("Tournament View");
        //Tabbed Pane
        lblCurrUser.setText("Logged In: " + currUser);
        lblTournamentName.setText("Bracket Name: " + tournamentName);
        lblTournamentID.setText("ID: " + tournamentID);
        btnOptions.addActionListener(this);
        btnLogOut.addActionListener(this);


        //Bracket View
        int currGame = gameToTeams.size();
        int index = 0;
        int numTeams = teamToPlayers.size();
        int rounds = ((Double)(Math.log(numTeams)/Math.log(2))).intValue();
        String text;
        ArrayList<String> teams;

        pnlBracket.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5,5,5,5);

        for(int i=rounds; i>0; i--){
            gbc.gridx = i;
            for(int j= ((Double)(numTeams/Math.pow(2, rounds-i+1))).intValue();
                j<= rounds; j+= Math.pow(2, i)){
                teams = gameToTeams.get(currGame);
                text = teams.get(0) + " [" + gameToScore.get(currGame).get(0) + "] - ["
                        + gameToScore.get(currGame).get(1) + "] " + teams.get(1);
                lblBracketGameScores.add(new JLabel());
                lblBracketGameScores.get(index).setText(text);
                gbc.gridy = j;
                pnlBracket.add(lblBracketGameScores.get(index), gbc);
                btnBracketGame.add(new JButton());
                btnBracketGame.get(index).addActionListener(this);
                btnBracketGame.get(index).setText("Game " + currGame);
                gbc.gridy = (j + 1);
                pnlBracket.add(btnBracketGame.get(index), gbc);
                currGame --;
                index ++;
            }
        }

        //Teams View
        btnCreateTeam.addActionListener(this);
        btnJoinTeam.addActionListener(this);
        for(String team: teamToPlayers.keySet()){
            cmbJoinTeam.addItem(team);
        }
        cmbJoinTeam.addActionListener(this);

        //Observer View
        for(int gameID: gameToReferee.keySet){
            cmbSelectGame.addItem(gameID);
        }
        for(String ref: referees){
            cmbAssignObserver.addItem(ref);
        }
        cmbSelectGame.addActionListener(this);
        cmbAssignObserver.addActionListener(this);
        btnAssignObserver.addActionListener(this);

        //Overseer View
        btnStart.addActionListener(this);
        btnEnd.addActionListener(this);
        lblPLInv.setText("Player" + playerInvite);
        lblOBInv.setText("Observer" + observerInvite);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setPreferredSize(new Dimension(800, 600));
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        AdvanceTeamController advanceTeamController = new AdvanceTeamController(nextScreenData.getBrackets(),
//                nextScreenData.getAccounts(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
//        DeclareWinnerController declareWinnerController = new DeclareWinnerController(nextScreenData.getBrackets(),
//                nextScreenData.getAccounts(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
//        ChangePointsController changePointsController = new ChangePointsController(nextScreenData.getAccounts(),
//                nextScreenData.getBrackets(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
//
//        if(e.getSource() == btnOptions){
//            nextScreenData.setCurrentUser(nextScreenData.getCurrentUser());
//            optionsScreen optionsScreen = new optionsScreen(nextScreenData);
//            this.dispose();
//            optionsScreen.setVisible(true);
//        }
//        else if(e.getSource() == btnLogOut){
//            CreateAccountController createAccountController = new CreateAccountController(nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets());
//            LogInController logInController = new LogInController(nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets());
//            homeScreen homeScreen = new homeScreen(createAccountController, logInController);
//            this.dispose();
//            homeScreen.setVisible(true);
//        }
//        else if(e.getSource() == btnCreateTeam){
//            teamCreationOB presenter = new TeamCreationPresenter();
//            teamCreationIB interactor = new teamCreationUC(presenter,nextScreenData.getCurrentUser(),
//                    nextScreenData.getCurrentBracketID(),nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets());
//            TeamCreationController controller = new TeamCreationController(interactor);
//            UserInput inputScreen = new UserInput(controller, this);
//            inputScreen.setVisible(true);
//        }
//        else if(e.getSource() == btnJoinTeam){
//            String teamName = (String)cmbJoinTeam.getSelectedItem();
//            try {
//                JoinTeamOD outputData = joinTeamController.joinTeam(teamName);
//                ArrayList<String> names = outputData.getMembersNames();
//            }
//            catch (Exception exception) {
//                System.out.println( "Error: " + exception);
//                JOptionPane.showMessageDialog(this, exception.getMessage());
//            }
//        }
//        else if(e.getSource() == btnAssignObserver){
//            try {
//                int gameID = (Integer) cmbSelectGame.getSelectedItem();
//                String assignee = (String) cmbAssignObserver.getSelectedItem();
//                AssignObserverOB assignObserverOB = new AssignObserverPresenter();
//                AssignObserverIB assignObserverIB = new AssignObserverUC(assignObserverOB, nextScreenData.getBrackets(),
//                        nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
//                AssignObserverController controller = new AssignObserverController(assignObserverIB);
//                controller.assignObserver(assignee, gameID);
//            }
//            catch(RuntimeException rex ) {
//                JOptionPane.showMessageDialog(this, rex.getMessage());
//            }
//        }
//        else if(e.getSource() == btnStart){
//            StartTournOD startData = startTournController.startTourn();
//            ArrayList<String> startErrors = startData.getErrors();
//            screens.startTourn.startErrors errorView = new startErrors(this.startTournController);
//            for (String error : startErrors) {
//                if (Objects.equals(error, "USERROLE")) {
//                    errorView.setWarning1("You do not have permission to start the tournament.");
//                } else if (Objects.equals(error, "NUMTEAMS")) {
//                    errorView.setWarning2("There are not enough teams in the tournament.");
//                } else if (Objects.equals(error, "NOOBSERVER")) {
//                    errorView.setWarning3("There is at least one game that does not have an observer assigned.");
//                } else if (Objects.equals(error, "TEAMNOTFULL")) {
//                    errorView.setWarning4("There is at least one team that is not full.");
//                }
//            }
//            errorView.setVisible(true);
//        }
//        else if(e.getSource() == btnEnd){
//            try {
//                endTournController.endTourn();
//                JOptionPane.showMessageDialog(this, "Tournament Ended");
//            }
//            catch (Exception exception) {
//                JOptionPane.showMessageDialog(this, exception.getMessage());
//            }
//        }
//        for(int i=0; i<btnBracketGame.size(); i++){
//            if (e.getSource() == btnBracketGame.get(i)){
//                DoBracketOperation doBracketOperations = new DoBracketOperation(advanceTeamController,
//                        declareWinnerController, changePointsController, this);
//                doBracketOperations.setGameForOperation(1);
//                doBracketOperations.setGameNumLabel("Game 1");
//                doBracketOperations.setTeamsLabel(lblBracketGameScores.get(i).getText());
//                doBracketOperations.setVisible(true);
//                break;
//            }
//        }
    }

    private void createUIComponents() {
        pnlBracket = new JPanel();
    }
}

package screens;

import database.AdvanceTeam.AdvanceTeamFileWriter;
import database.ChangePoints.ChangePointsFileWriter;
import database.CreateAccount.CreateAccountFileWriter;
import database.DeclareWinner.DeclareWinnerFileWriter;
import screens.advanceTeam.AdvanceTeamController;
import screens.advanceTeam.AdvanceTeamPresenter;
import screens.assignObserver.AssignObserverController;
import screens.assignObserver.AssignObserverPresenter;
import screens.bracketOperations.DoBracketOperation;
import screens.changePoints.ChangePointsController;
import screens.changePoints.ChangePointsPresenter;
import screens.createAccount.CreateAccountController;
import screens.createAccount.CreateAccountPresenter;
import screens.declareWinner.DeclareWinnerController;
import screens.declareWinner.DeclareWinnerPresenter;
import screens.endTourn.EndTournController;
import screens.joinTeam.JoinTeamController;
import screens.logIn.LogInController;
import screens.logIn.LogInPresenter;
import screens.startTourn.StartTournController;
import screens.startTourn.startErrors;
import screens.teamCreation.TeamCreationController;
import screens.teamCreation.TeamCreationPresenter;
import screens.teamCreation.UserInput;
import useCases.CreateAccount.CreateAccountGateway;
import useCases.CreateAccount.CreateAccountIB;
import useCases.CreateAccount.CreateAccountOB;
import useCases.CreateAccount.CreateAccountUC;
import useCases.LogIn.LogInIB;
import useCases.LogIn.LogInOB;
import useCases.LogIn.LogInUC;
import useCases.advanceTeam.AdvanceTeamGateway;
import useCases.advanceTeam.AdvanceTeamIB;
import useCases.advanceTeam.AdvanceTeamOB;
import useCases.advanceTeam.AdvanceTeamUC;
import useCases.assignObserver.AssignObserverIB;
import useCases.assignObserver.AssignObserverOB;
import useCases.assignObserver.AssignObserverOD;
import useCases.assignObserver.AssignObserverUC;
import useCases.changePoints.ChangePointsGateway;
import useCases.changePoints.ChangePointsIB;
import useCases.changePoints.ChangePointsOB;
import useCases.changePoints.ChangePointsUC;
import useCases.declareWinner.DeclareWinnerGateway;
import useCases.declareWinner.DeclareWinnerIB;
import useCases.declareWinner.DeclareWinnerOB;
import useCases.declareWinner.DeclareWinnerUC;
import useCases.joinTeam.JoinTeamOD;
import useCases.startTourn.StartTournOD;
import useCases.teamCreation.teamCreationIB;
import useCases.teamCreation.teamCreationOB;
import useCases.teamCreation.teamCreationUC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

public class bracketView extends JFrame implements ActionListener {
//    The main panel for the bracket view screen
    private JPanel mainPanel;
    //    Panel Bar and General Info
    private JLabel currentUser;
    private JLabel bracketName;
    private JPanel bracketView;
    private JLabel currentTournament;
    private JPanel teamView;
    private JPanel observerControls;
    private JPanel tournamentControls;
    private JButton logOut;
    private JButton returnOptions;

//    Bracket View
    private JButton game2Button;
    private JButton game3Button;
    private JButton game1Button;
    private JLabel game3Label;
    private JLabel game1Label;
    private JLabel game2Label;
//    Team View
    private JButton createTeamBtn;
    private JLabel team1Name;
    private JList<String> team1List;
    private JButton joinTeam1;
    private JLabel team2Name;
    private JButton joinTeam2;
    private JList<String> team2List;
    private JLabel team3Name;
    private JList<String> team3List;
    private JButton joinTeam3;
    private JList<String> team4List;
    private JButton joinTeam4;
    private JLabel team4Name;
//    Observer Assignments
    //private JComboBox<String> observerGame1;
private JTextField observerGame1;
    private JButton assignGame1;
    //private JComboBox<String> observerGame2;
    private JButton assignGame2;
    //private JComboBox<String> observerGame3;
    private JTextField observerGame2;
    private JTextField observerGame3;
    private JButton assignGame3;
//    Overseer Controls
    private JButton startTournamentButton;
    private JButton declareWinnerEndTournamentButton;
    private JLabel playerInvite;
    private JLabel observerInvite;
    private JLabel game1Winner;
    private JLabel game2Winner;
    private JLabel game3Winner;
    private JTabbedPane mainTabbedPane;
    private JPanel generalisedBracketView;
    private JComboBox<Integer> selectRoundGBV;
    private JComboBox<Integer> selectGameGBV;
    private JButton bracketOperationsButtonGBV;
    private JPanel generalisedTeamView;
    private JPanel generalisedObserverView;
    private JButton createTeamButtonGTV;
    private JComboBox<Integer> selectGameGTV;
    private JButton joinTeamButtonGTV;
    private JComboBox<Integer> selectGameGOV;
    private JButton selectGameButtonGOV;
    private JComboBox<String> selectObserverGOV;
    private JButton assignAsObserverButtonGOV;
    private JComboBox<String> observerListGame1;
    private JComboBox<String> observerListGame2;
    private JComboBox<String> observerListGame3;

    private String team1NameString;

    private NextScreenData nextScreenData;
//    Controllers for all Buttons (Go below)
//    Overseer Control controllers
    private EndTournController endTournController;
    private StartTournController startTournController;

    private JoinTeamController joinTeamController;

    public bracketView(NextScreenData nextScreenData, EndTournController endTournController, StartTournController startTournController,
                       JoinTeamController joinTeamController ) {
        super("Bracket View");
//        Assign all controllers for this view
        this.nextScreenData = nextScreenData;
        this.endTournController = endTournController;
        this.startTournController = startTournController;
        this.joinTeamController = joinTeamController;

//        Default Text for New Bracket View
//        Info Bar
        currentUser.setText("Logged in: ");
        bracketName.setText("Bracket Name");
        currentTournament.setText("ID: 0");
//        Bracket View
        game1Label.setText("[] 0 - 0 []");
        game2Label.setText("[BlankTeam 1] 0 - 0 [BlankTeam 2]");
        game3Label.setText("[BlankTeam 3] 0 - 0 [BlankTeam 4]");
        game2Winner.setText("Winner: ");
        game1Winner.setText("Winner: ");
        game3Winner.setText("Winner: ");
//        Team View
        team1Name.setText("BlankTeam 1");
        team1List.setListData(new String[]{});
        team2Name.setText("BlankTeam 2");
        team2List.setListData(new String[]{});
        team3Name.setText("BlankTeam 3");
        team3List.setListData(new String[]{});
        team4Name.setText("BlankTeam 4");
        team4List.setListData(new String[]{});
//        Observer Assignments (No Default Text)
//        Overseer Controls (No Default Text)

//        Action Listeners for all Buttons
//        Bracket View
        game1Button.addActionListener(this);
        game2Button.addActionListener(this);
        game3Button.addActionListener(this);
//        Team View
        joinTeam1.addActionListener(this);
        joinTeam2.addActionListener(this);
        joinTeam3.addActionListener(this);
        joinTeam4.addActionListener(this);
        createTeamBtn.addActionListener(this);
//        Observer Assignments
        assignGame1.addActionListener(this);
        assignGame2.addActionListener(this);
        assignGame3.addActionListener(this);
//        Overseer Controls
        startTournamentButton.addActionListener(this);
        declareWinnerEndTournamentButton.addActionListener(this);
        returnOptions.addActionListener(this);
        logOut.addActionListener(this);

        playerInvite.setText("Player Invite: ");
        observerInvite.setText("Observer Invite: ");

        this.mainTabbedPane.remove(generalisedBracketView);
        this.mainTabbedPane.remove(generalisedTeamView);
        this.mainTabbedPane.remove(generalisedObserverView);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
    }

//    Setter Methods to update the view, or used when creating a new instance of
//    a tournament that is currently in progress
//    Info Bar
    public void setCurrentUser(String username) {
        currentUser.setText("Logged in: " + username);
    }
    public void setBracketName(String bracketName) {
        this.bracketName.setText(bracketName);
    }
    public void setCurrentTournament(int tournamentID) {
        currentTournament.setText("ID: " + tournamentID);
    }
//    Bracket View
    public void setGame1Label(String team1 , String team2, int team1Score, int team2Score) {
        this.game1Label.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }
    public void setGame2Label(String team1 , String team2, int team1Score, int team2Score) {
        this.game2Label.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }
    public void setGame3Label(String team1 , String team2, int team1Score, int team2Score) {
        this.game3Label.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }
    public void setGame1Winner(String winner) {
        this.game1Winner.setText("Winner: " + winner);
    }
    public void setGame2Winner(String winner) {
        this.game2Winner.setText("Winner: " + winner);
    }
    public void setGame3Winner(String winner) {
        this.game3Winner.setText("Winner: " + winner);
    }
//    Team View
    public void setTeam1Name(String team1Name) {
        this.team1Name.setText(team1Name);
        this.team1NameString = team1Name;
    }
    public void setTeam1PlayerList(String[] team1List) {
        this.team1List.setListData(team1List);
    }
    public void setTeam2Name(String team2Name) {
        this.team2Name.setText(team2Name);
    }
    public void setTeam2PlayerList(String[] team2List) {
        this.team2List.setListData(team2List);
    }
    public void setTeam3Name(String team3Name) {
        this.team3Name.setText(team3Name);
    }
    public void setTeam3PlayerList(String[] team3List) {
        this.team3List.setListData(team3List);
    }
    public void setTeam4Name(String team4Name) {
        this.team4Name.setText(team4Name);
    }
    public void setTeam4PlayerList(String[] team4List) {
        this.team4List.setListData(team4List);
    }
//    Observer Assignments
    public void setObserverListGame1(ArrayList<String> observers) {
        for (String observer : observers) {
            this.observerListGame1.addItem(observer);
        }
    }
    public void setObserverListGame2(ArrayList<String> observers) {
        for (String observer : observers) {
            this.observerListGame2.addItem(observer);
        }
    }
    public void setObserverListGame3(ArrayList<String> observers) {
        for (String observer : observers) {
            this.observerListGame3.addItem(observer);
        }
    }
//    Invites
    public void setPlayerInvite(String invite) {
        this.playerInvite.setText("Player Invite: " + invite);
    }
    public void setObserverInvite(String invite) {
        this.observerInvite.setText("Observer Invite: " + invite);
    }


//    Action Listeners for all Buttons (Controllers are connected here)
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("Click " + e.getActionCommand());

//        Controller Initiation
        //        Advance Team
        // AdvanceTeamOB advanceTeamOB = new AdvanceTeamPresenter();
//        Next line is not implemented or used.
        //AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamFileWriter("brackets.txt");
        //AdvanceTeamIB advanceTeamIB = new AdvanceTeamUC(advanceTeamOB, advanceTeamGateway,
                //nextScreenData.getBrackets(), nextScreenData.getAccounts(),
                //nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
        //AdvanceTeamController advanceTeamController = new AdvanceTeamController(advanceTeamIB);
        AdvanceTeamController advanceTeamController = new AdvanceTeamController(nextScreenData.getBrackets(),
                nextScreenData.getAccounts(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());


        //       Declare Winner
//        DeclareWinnerOB declareWinnerOB = new DeclareWinnerPresenter();
//        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerFileWriter("brackets.txt");
//        DeclareWinnerIB declareWinnerIB = new DeclareWinnerUC(declareWinnerOB, declareWinnerGateway,
//                nextScreenData.getBrackets(), nextScreenData.getAccounts(),
//                nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
//        DeclareWinnerController declareWinnerController = new DeclareWinnerController(declareWinnerIB);

        DeclareWinnerController declareWinnerController = new DeclareWinnerController(nextScreenData.getBrackets(),
                nextScreenData.getAccounts(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());


        //      Change Points
//        ChangePointsOB changePointsOB = new ChangePointsPresenter();
//        ChangePointsGateway changePointsGateway = new ChangePointsFileWriter("brackets.txt");
//        ChangePointsIB changePointsIB = new ChangePointsUC(changePointsOB, changePointsGateway,
//                 nextScreenData.getAccounts(),nextScreenData.getBrackets(),
//                nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());
//        ChangePointsController changePointsController = new ChangePointsController(changePointsIB);

        ChangePointsController changePointsController = new ChangePointsController(nextScreenData.getAccounts(),
                nextScreenData.getBrackets(), nextScreenData.getCurrentBracketID(), nextScreenData.getCurrentUser());

        if (e.getSource() == game1Button) {
//            System.out.println("Game 1 Button Clicked");
//            Create new screen for game 1
//            In that screen have buttons linked to change points and advance teams
//            TODO
            DoBracketOperation doBracketOperations = new DoBracketOperation(advanceTeamController,
                    declareWinnerController, changePointsController, this);
            doBracketOperations.setGameForOperation(1);
            doBracketOperations.setGameNumLabel("Game 1");
            doBracketOperations.setTeamsLabel(game1Label.getText());
            doBracketOperations.setVisible(true);
        } else if (e.getSource() == game2Button) {
//            System.out.println("Game 2 Button Clicked");
            DoBracketOperation doBracketOperations = new DoBracketOperation(advanceTeamController,
                    declareWinnerController, changePointsController, this);
            doBracketOperations.setGameForOperation(2);
            doBracketOperations.setGameNumLabel("Game 2");
            doBracketOperations.setTeamsLabel(game2Label.getText());
            doBracketOperations.setVisible(true);

        } else if (e.getSource() == game3Button) {
//            System.out.println("Game 3 Button Clicked");
            DoBracketOperation doBracketOperations = new DoBracketOperation(advanceTeamController,
                    declareWinnerController, changePointsController, this);
            doBracketOperations.setGameForOperation(3);
            doBracketOperations.setGameNumLabel("Game 3");
            doBracketOperations.setTeamsLabel(game3Label.getText());
            doBracketOperations.setVisible(true);
        } else if (e.getSource() == joinTeam1) {
//            System.out.println("Join Team 1 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
            String team1Name = this.team1Name.getText();
            try {
                JoinTeamOD outputData = joinTeamController.joinTeam(team1Name);
                ArrayList<String> names = outputData.getMembersNames();
                setTeam1PlayerList(names.toArray(new String[0]));
            }
            catch (Exception exception) {
                System.out.println( "Error: " + exception);
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == joinTeam2) {
//            System.out.println("Join Team 2 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
            String team2Name = this.team2Name.getText();
            try {
                JoinTeamOD outputData = joinTeamController.joinTeam(team2Name);
                ArrayList<String> names = outputData.getMembersNames();
                setTeam2PlayerList(names.toArray(new String[0]));
            }
            catch (Exception exception) {
                System.out.println( "Error: " + exception);
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == joinTeam3) {
//            System.out.println("Join Team 3 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
            String team3Name = this.team3Name.getText();
            try {
                JoinTeamOD outputData = joinTeamController.joinTeam(team3Name);
                ArrayList<String> names = outputData.getMembersNames();
                setTeam3PlayerList(names.toArray(new String[0]));
            }
            catch (Exception exception) {
                System.out.println( "Error: " + exception);
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == joinTeam4) {
//            System.out.println("Join Team 4 Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
            String team4Name = this.team4Name.getText();
            try {
                JoinTeamOD outputData = joinTeamController.joinTeam(team4Name);
                ArrayList<String> names = outputData.getMembersNames();
                setTeam4PlayerList(names.toArray(new String[0]));

            }
            catch (Exception exception) {
                System.out.println( "Error: " + exception);
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == assignGame1) {
            try {
                AssignObserverOB assignObserverOB = new AssignObserverPresenter();
                AssignObserverIB assignObserverIB = new AssignObserverUC(assignObserverOB, nextScreenData.getBrackets(),
                        nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
                AssignObserverController controller = new AssignObserverController(assignObserverIB);
                controller.assignObserver((String) observerListGame1.getSelectedItem(), 1);
                //setObserverListGame1(new ArrayList<>(Collections.singletonList(output.getAssignee())));
            }
            catch(RuntimeException rex ) {
                JOptionPane.showMessageDialog(this, rex.getMessage());
            }
        } else if (e.getSource() == assignGame2) {
            try {
                AssignObserverOB assignObserverOB = new AssignObserverPresenter();
                AssignObserverIB assignObserverIB = new AssignObserverUC(assignObserverOB, nextScreenData.getBrackets(),
                        nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
                AssignObserverController controller = new AssignObserverController(assignObserverIB);

                controller.assignObserver((String) observerListGame2.getSelectedItem(), 2);
//                setObserverListGame2(new ArrayList<>(Collections.singletonList(output.getAssignee())));
            }
            catch(RuntimeException rex ) {
                JOptionPane.showMessageDialog(this, rex.getMessage());
            }
        } else if (e.getSource() == assignGame3) {
            try {
                AssignObserverOB assignObserverOB = new AssignObserverPresenter();
                AssignObserverIB assignObserverIB = new AssignObserverUC(assignObserverOB, nextScreenData.getBrackets(),
                        nextScreenData.getAccounts(), nextScreenData.getCurrentUser());
                AssignObserverController controller = new AssignObserverController(assignObserverIB);

                controller.assignObserver((String) observerListGame3.getSelectedItem(), 3);
//                setObserverListGame2(new ArrayList<>(Collections.singletonList(output.getAssignee())));
            }
            catch(RuntimeException rex ) {
                JOptionPane.showMessageDialog(this, rex.getMessage());
            }
        } else if (e.getSource() == startTournamentButton) {
//            System.out.println("Start Tournament Button Clicked");

            //            TODO
//            Calls corresponding UC through controller
            StartTournOD startData = startTournController.startTourn();
            ArrayList<String> startErrors = startData.getErrors();
            startErrors errorView = new startErrors(this.startTournController);
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
        } else if (e.getSource() == declareWinnerEndTournamentButton) {
//            System.out.println("Declare Winner End Tournament Button Clicked");
            //            TODO
//            Calls corresponding UC through controller
            // End Tournament below
            // for now I assumed I have all the infos about the inputs for ID.
//
            try {
                endTournController.endTourn();
                JOptionPane.showMessageDialog(this, "Tournament Ended");
            }
            catch (Exception exception) {
//                System.out.println("Error: " + exception);
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }
        } else if (e.getSource() == createTeamBtn){
            teamCreationOB presenter = new TeamCreationPresenter();
            teamCreationIB interactor = new teamCreationUC(presenter,nextScreenData.getCurrentUser(),
                    nextScreenData.getCurrentBracketID(),nextScreenData.getAccounts(),
                    nextScreenData.getBrackets());
            TeamCreationController controller = new TeamCreationController(interactor);
            UserInput inputScreen = new UserInput(controller, this);
            inputScreen.setVisible(true);

        } else if (e.getSource() == returnOptions){
            try {
                String username = nextScreenData.getCurrentUser();
                String password = nextScreenData.getAccounts().getUser(username).getPassword();
//                LogInOD outputData = logInController.login(username, password);

                // String currentUser = outputData.getUsername();
//                AccountRepo currentAccounts = outputData.getAccountRepo();
//                BracketRepo currentBrackets = outputData.getBracketRepo();

//                NextScreenData nextScreenData = new NextScreenData();
                nextScreenData.setCurrentUser(username);
//                nextScreenData.setAccounts(currentAccounts);
//                nextScreenData.setBrackets(currentBrackets);

//                CreateAccountOB createAccountOB = new CreateAccountPresenter();
//                CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, mainUserFactory,
//                        mainAccountRepo, mainBracketRepo);
//                CreateAccountController createAccountController = new CreateAccountController(createAccountIB);
//
//                LogInOB logInOB = new LogInPresenter();
//                LogInIB logInIB = new LogInUC(logInOB, mainAccountRepo, mainBracketRepo);
//                LogInController logInController = new LogInController(logInIB);

//                CreateBracketOB createBracketOB = new CreateBracketPresenter();
//                CreateBracketIB interactor = new CreateBracketUC(createBracketOB, username,
//                        nextScreenData.getAccounts(), nextScreenData.getBrackets());
//                CreateBracketController createBracketCon = new CreateBracketController(interactor);

                optionsScreen optionsScreen = new optionsScreen(nextScreenData);
                this.dispose();
                optionsScreen.setVisible(true);

            } catch (Exception exception) {
                JOptionPane.showMessageDialog(this, exception.getMessage());
            }

        } else if (e.getSource() == logOut) {
//            CreateAccountOB createAccountOB = new CreateAccountPresenter();
//            CreateAccountGateway createAccountGateway = new CreateAccountFileWriter("accounts.txt");
//            CreateAccountIB createAccountIB = new CreateAccountUC(createAccountOB, nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets(), createAccountGateway);
//            CreateAccountController createAccountController = new CreateAccountController(createAccountIB);
            CreateAccountController createAccountController = new CreateAccountController(nextScreenData.getAccounts(),
                    nextScreenData.getBrackets());

            //LogInOB logInOB = new LogInPresenter();
            //LogInIB logInIB = new LogInUC(logInOB, nextScreenData.getAccounts(),
//                    nextScreenData.getBrackets());
            LogInController logInController = new LogInController(nextScreenData.getAccounts(),
                    nextScreenData.getBrackets());

            homeScreen homeScreen = new homeScreen(createAccountController, logInController);

//            homeScreen homeScreen = new homeScreen(this.createAccountController, this.logInController);
            this.dispose();
            homeScreen.setVisible(true);
        }
    }
}

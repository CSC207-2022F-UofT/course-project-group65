package frameworks_and_drivers.bracket_operations;

import frameworks_and_drivers.ExtendedView;
import screens.NextScreenData;
import screens.advance_team.AdvanceTeamController;
import screens.advance_team.AdvanceTeamFailed;
//import screens.bracketView;
import screens.change_points.ChangePointsController;
import screens.change_points.ChangePointsFailed;
import screens.declare_winner.DeclareWinnerController;
import screens.declare_winner.DeclareWinnerFailed;
import use_cases.advance_team.*;
import use_cases.change_points.ChangePointsOD;
import use_cases.declare_winner.DeclareWinnerOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DoBracketOperation extends JFrame implements ActionListener {
    private JLabel title;
    private JLabel gameNumLabel;
    private JLabel teamsLabel;
    private JLabel advanceTeamLabel;
    private JLabel declareWInnerLabel;
    private JButton declareButton;
    private JLabel chngPtsLabel;
    private JButton advanceButton;
    private JTextField changePtsTF;
    private JButton changePointsButton;
    private JLabel changePtsInstruction;
    private JPanel bracketOpWindow;
    private JTextField teamPointsBox;

    private AdvanceTeamController advanceTeamController;
    private DeclareWinnerController declareWinnerController;
    private ChangePointsController changePointsController;
    public int gameID;
    private ExtendedView extendedView;

//    private bracketView viewChange;
    private NextScreenData nextScreenData;

//    public DoBracketOperation(AdvanceTeamController advanceTeamController,
//                              DeclareWinnerController declareWinnerController,
//                              ChangePointsController changePointsController, bracketView viewChange) {
//        super("Bracket Operations");
//        this.advanceTeamController = advanceTeamController;
//        this.declareWinnerController = declareWinnerController;
//        this.changePointsController = changePointsController;
//        this.viewChange = viewChange;
//
//        gameNumLabel.setText("Game Number: 0");
//        teamsLabel.setText("Teams: ");
//        advanceTeamLabel.setText("Advance Winning Team");
//        chngPtsLabel.setText("Change Points for Team");
//        declareButton.setText("Declare Winner");
//        declareWInnerLabel.setText("Declare the Winner");
//
//        advanceButton.addActionListener(this);
//        changePointsButton.addActionListener(this);
//        declareButton.addActionListener(this);
//        changePtsTF.addActionListener(this);
//
//
////        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setContentPane(bracketOpWindow);
//        this.setPreferredSize(new Dimension(650, 300));
//        this.pack();
//        this.setVisible(true);
//
//    }

    public DoBracketOperation(AdvanceTeamController advanceTeamController,
                              DeclareWinnerController declareWinnerController,
                              ChangePointsController changePointsController, ExtendedView viewChange, NextScreenData nextScreenData) {
        super("Bracket Operations");
        this.advanceTeamController = advanceTeamController;
        this.declareWinnerController = declareWinnerController;
        this.changePointsController = changePointsController;
        this.extendedView = viewChange;
        this.nextScreenData = nextScreenData;

        gameNumLabel.setText("Game Number: 0");
        teamsLabel.setText("Teams: ");
        advanceTeamLabel.setText("Advance Winning Team");
        chngPtsLabel.setText("Change Points for Team");
        declareButton.setText("Declare Winner");
        declareWInnerLabel.setText("Declare the Winner");

        advanceButton.addActionListener(this);
        changePointsButton.addActionListener(this);
        declareButton.addActionListener(this);
        changePtsTF.addActionListener(this);


//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(bracketOpWindow);
        this.setPreferredSize(new Dimension(650, 300));
        this.pack();
        this.setVisible(true);

    }


    // Getter Methods
    // I have to get the game ID somehow from next screen data or something.
    public void setGameForOperation(int gameID) {
        this.gameID = gameID;
    }

    public void setGameNumLabel(String gameID) {
        this.gameNumLabel.setText("Game Number: " + gameID);
    }

    public void setTeamsLabel(String label) {
        this.teamsLabel.setText(label);
    }
    public void changeTeamsLabel(String team1 , String team2, int team1Score, int team2Score) {
        this.teamsLabel.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }

//    public static void main(String[] args) {
//
////
////        BracketRepo repo = new BracketRepo();
////        DefaultBracket bracket = new DefaultBracket();
////        DefaultGame game = new DefaultGame();
////        DefaultGame game2 = new DefaultGame();
////        game2.setGameID(1);
////        DefaultGame game3 = new DefaultGame();
////        game3.setGameID(2);
////        bracket.setFinalGame(game);
////        game.setPrevGame1(game2);
////        game.setPrevGame2(game3);
////        repo.addBracket(bracket);
////        //game.setGameStatus(true);
////        game2.setGameStatus(true);
////        game3.setGameStatus(true);
////        game.setGameRound(2);
////        game2.setGameRound(1);
////        game3.setGameRound(1);
////
////        Team team1 = new DefaultTeam();
////        Team team2 = new DefaultTeam();
////        game2.setTeam(team1, bracket.getWinCondition());
////        game3.setTeam(team2, 0);
////        game2.setWinner(team1);
////
////
////        AccountRepo repo1 = new AccountRepo();
////        DefaultUser user2 = new DefaultUser();
////        user2.setUsername("test");
////        user2.setPassword("test2");
////        user2.setBracketRole(bracket.getTournamentID(), "Overseer");
////        repo1.addUser(user2);
////
////        AdvanceTeamID inputData = new AdvanceTeamID(1);
////
////        AdvanceTeamGateway userRepository = new AdvanceTeamFileWriter("UC Test.txt");
////        AdvanceTeamOB outputBoundary = new AdvanceTeamPresenter(); // dummy class
////        AdvanceTeamIB interactor = new AdvanceTeamUC(outputBoundary, userRepository, repo, repo1, bracket.getTournamentID(), user2.getUsername());
////        AdvanceTeamController advanceTeamController1 = new AdvanceTeamController(interactor);
////        advanceTeamController1.create(1);
////
////        System.out.println("Game 1: " + game.getGameStatus());
////        System.out.println("Game 2: " + game2.getGameStatus());
////        System.out.println("Game 3: " + game3.getGameStatus());
////        System.out.println("Final:" + game.getPrevGame1().getGameID());
////        game2.setWinner(team1);
////        System.out.println(game3.getTeams());
//
//
////        AdvanceTeamController advanceTeamController1 = new AdvanceTeamController(new AdvanceTeamIB() {
////            @Override
////            public AdvanceTeamOD advanceWinner(AdvanceTeamID advanceTeamID) {
////                return null;
////            }
////        });
////        DeclareWinnerController declareWinnerController1 = new DeclareWinnerController(new DeclareWinnerIB() {
////            @Override
////            public DeclareWinnerOD setWinner(DeclareWinnerID declareWinnerID) {
////                return null;
////            }
////        });
////
////
////        ChangePointsController changePointsController1 = new ChangePointsController(new ChangePointsIB() {
////            @Override
////            public ChangePointsOD changePoints(ChangePointsID changePointsID) {
////                return null;
////            }
////        });
//
////        DoBracketOperation doBracketOperation = new DoBracketOperation(advanceTeamController1, declareWinnerController1, changePointsController1);
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        teamsLabel.setText("TEST CHANGE");
//        if (this.gameID == 1) {
//            this.viewChange.changeGame1Label("TEST CHANGE");
//        } else if (this.gameID == 2) {
//            this.viewChange.changeGame2Label("TEST CHANGE");
//        } else if (this.gameID == 3) {
//            this.viewChange.changeGame3Label("TEST CHANGE");
//        }
        try {
            if (e.getSource() == advanceButton) {
                AdvanceTeamOD outputAdvance = advanceTeamController.create(gameID);
                nextScreenData.bundleData();
                int advGameID = outputAdvance.getAdvancedGame();
                this.extendedView.updateGameScore(advGameID, outputAdvance.getGameToTeams().get(advGameID),
                        outputAdvance.getGameToScores().get(advGameID));
            } else if (e.getSource() == declareButton) {
                DeclareWinnerOD outputWinner = declareWinnerController.create(gameID);
                System.out.println(outputWinner.getWinningTeamName());
                nextScreenData.bundleData();
                this.extendedView.updateWinner(gameID, outputWinner.getWinningTeamName());
            } else if (e.getSource() == changePointsButton){

                int points = Integer.parseInt(changePtsTF.getText().trim());
                String teamName = (String) teamPointsBox.getText().trim();
                if (teamName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select a team.");
                }
                ChangePointsOD outputData = changePointsController.create(gameID, points, teamName);
                nextScreenData.bundleData();

                changeTeamsLabel(outputData.getTeams().get(0), outputData.getTeams().get(1), outputData.getPoints().get(0), outputData.getPoints().get(1));
                //changeTeamsLabel(outputData.getTeam1Name(), outputData.getTeam2Name(), outputData.getTeam1Score(), outputData.getTeam2Score());

                this.extendedView.updateGameScore(gameID, outputData.getTeams(), outputData.getPoints());
            }
//            nextScreenData.bundleData();
//            extendedView.setTeams(nextScreenData.getTeamToPlayers());
//            extendedView.setReferees(nextScreenData.getGameToReferee(), nextScreenData.getReferees());

        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
        } catch (AdvanceTeamFailed advanceTeamFailed) {
            JOptionPane.showMessageDialog(null, advanceTeamFailed.getMessage());
        } catch (DeclareWinnerFailed declareWinnerFailed) {
            JOptionPane.showMessageDialog(null, declareWinnerFailed.getMessage());
        } catch (ChangePointsFailed changePointsFailed) {
            JOptionPane.showMessageDialog(null, changePointsFailed.getMessage());
        }
    }

}

package screens.bracketOperations;

import screens.advanceTeam.AdvanceTeamController;
import screens.advanceTeam.AdvanceTeamFailed;
import screens.bracketView;
import screens.changePoints.ChangePointsController;
import screens.changePoints.ChangePointsFailed;
import screens.declareWinner.DeclareWinnerController;
import screens.declareWinner.DeclareWinnerFailed;
import useCases.advanceTeam.*;
import useCases.changePoints.ChangePointsIB;
import useCases.changePoints.ChangePointsID;
import useCases.changePoints.ChangePointsOD;
import useCases.declareWinner.DeclareWinnerIB;
import useCases.declareWinner.DeclareWinnerID;
import useCases.declareWinner.DeclareWinnerOD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

    private bracketView viewChange;

    public DoBracketOperation(AdvanceTeamController advanceTeamController,
                              DeclareWinnerController declareWinnerController,
                              ChangePointsController changePointsController, bracketView viewChange) {
        super("Bracket Operations");
        this.advanceTeamController = advanceTeamController;
        this.declareWinnerController = declareWinnerController;
        this.changePointsController = changePointsController;
        this.viewChange = viewChange;

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
        this.gameNumLabel.setText("Game Number:" + gameID);
    }

    public void setTeamsLabel(String label) {
        this.teamsLabel.setText(label);
    }
    public void changeTeamsLabel(String team1 , String team2, int team1Score, int team2Score) {
        this.teamsLabel.setText("[" + team1 + "] " + team1Score + " - " + team2Score + " [" + team2 + "]");
    }

    public static void main(String[] args) {

//
//        BracketRepo repo = new BracketRepo();
//        DefaultBracket bracket = new DefaultBracket();
//        DefaultGame game = new DefaultGame();
//        DefaultGame game2 = new DefaultGame();
//        game2.setGameID(1);
//        DefaultGame game3 = new DefaultGame();
//        game3.setGameID(2);
//        bracket.setFinalGame(game);
//        game.setPrevGame1(game2);
//        game.setPrevGame2(game3);
//        repo.addBracket(bracket);
//        //game.setGameStatus(true);
//        game2.setGameStatus(true);
//        game3.setGameStatus(true);
//        game.setGameRound(2);
//        game2.setGameRound(1);
//        game3.setGameRound(1);
//
//        Team team1 = new DefaultTeam();
//        Team team2 = new DefaultTeam();
//        game2.setTeam(team1, bracket.getWinCondition());
//        game3.setTeam(team2, 0);
//        game2.setWinner(team1);
//
//
//        AccountRepo repo1 = new AccountRepo();
//        DefaultUser user2 = new DefaultUser();
//        user2.setUsername("test");
//        user2.setPassword("test2");
//        user2.setBracketRole(bracket.getTournamentID(), "Overseer");
//        repo1.addUser(user2);
//
//        AdvanceTeamID inputData = new AdvanceTeamID(1);
//
//        AdvanceTeamGateway userRepository = new AdvanceTeamFileWriter("UC Test.txt");
//        AdvanceTeamOB outputBoundary = new AdvanceTeamPresenter(); // dummy class
//        AdvanceTeamIB interactor = new AdvanceTeamUC(outputBoundary, userRepository, repo, repo1, bracket.getTournamentID(), user2.getUsername());
//        AdvanceTeamController advanceTeamController1 = new AdvanceTeamController(interactor);
//        advanceTeamController1.create(1);
//
//        System.out.println("Game 1: " + game.getGameStatus());
//        System.out.println("Game 2: " + game2.getGameStatus());
//        System.out.println("Game 3: " + game3.getGameStatus());
//        System.out.println("Final:" + game.getPrevGame1().getGameID());
//        game2.setWinner(team1);
//        System.out.println(game3.getTeams());


        AdvanceTeamController advanceTeamController1 = new AdvanceTeamController(new AdvanceTeamIB() {
            @Override
            public AdvanceTeamOD advanceWinner(AdvanceTeamID advanceTeamID) {
                return null;
            }
        });
        DeclareWinnerController declareWinnerController1 = new DeclareWinnerController(new DeclareWinnerIB() {
            @Override
            public DeclareWinnerOD setWinner(DeclareWinnerID declareWinnerID) {
                return null;
            }
        });


        ChangePointsController changePointsController1 = new ChangePointsController(new ChangePointsIB() {
            @Override
            public ChangePointsOD changePoints(ChangePointsID changePointsID) {
                return null;
            }
        });

//        DoBracketOperation doBracketOperation = new DoBracketOperation(advanceTeamController1, declareWinnerController1, changePointsController1);
    }

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
                advanceTeamController.create(gameID);
                this.dispose();
            } else if (e.getSource() == declareButton) {
                declareWinnerController.create(gameID);
                this.dispose();
            } else if (e.getSource() == changePointsButton){

                int points = Integer.parseInt(changePtsTF.getText().trim());
                String teamName = (String) teamPointsBox.getText().trim();
                if (teamName.equals("")) {
                    JOptionPane.showMessageDialog(null, "Please select a team.");
                }
                ChangePointsOD outputData = changePointsController.create(gameID, points, teamName);
                if (this.gameID == 1) {
                    this.changeTeamsLabel(outputData.getChangedTeamName(), outputData.getOtherTeamName(),
                            outputData.getNewPoints(), outputData.getOtherTeamPoints());
                    this.viewChange.setGame1Label(outputData.getChangedTeamName(), outputData.getOtherTeamName(),
                            outputData.getNewPoints(), outputData.getOtherTeamPoints());
                    this.dispose();
                } else if (this.gameID == 2) {
                    this.changeTeamsLabel(outputData.getChangedTeamName(), outputData.getOtherTeamName(),
                            outputData.getNewPoints(), outputData.getOtherTeamPoints());
                    this.viewChange.setGame2Label(outputData.getChangedTeamName(), outputData.getOtherTeamName(),
                            outputData.getNewPoints(), outputData.getOtherTeamPoints());
                    this.dispose();
                } else if (this.gameID == 3) {
                    this.changeTeamsLabel(outputData.getChangedTeamName(), outputData.getOtherTeamName(),
                            outputData.getNewPoints(), outputData.getOtherTeamPoints());
                    this.viewChange.setGame3Label(outputData.getChangedTeamName(), outputData.getOtherTeamName(),
                            outputData.getNewPoints(), outputData.getOtherTeamPoints());
                    this.dispose();
                }
            }


        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
            this.dispose();
        } catch (AdvanceTeamFailed advanceTeamFailed) {
            JOptionPane.showMessageDialog(null, advanceTeamFailed.getMessage());
            this.dispose();
        } catch (DeclareWinnerFailed declareWinnerFailed) {
            JOptionPane.showMessageDialog(null, declareWinnerFailed.getMessage());
            this.dispose();
        } catch (ChangePointsFailed changePointsFailed) {
            JOptionPane.showMessageDialog(null, changePointsFailed.getMessage());
            this.dispose();
        }
//
    }

}

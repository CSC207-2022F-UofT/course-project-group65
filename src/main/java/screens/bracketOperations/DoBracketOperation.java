package screens.bracketOperations;

import screens.advanceTeam.AdvanceTeamController;
import screens.advanceTeam.AdvanceTeamFailed;
import screens.changePoints.ChangePointsController;
import screens.changePoints.ChangePointsFailed;
import screens.declareWinner.DeclareWinnerController;
import screens.declareWinner.DeclareWinnerFailed;
import useCases.advanceTeam.AdvanceTeamIB;
import useCases.advanceTeam.AdvanceTeamID;
import useCases.advanceTeam.AdvanceTeamOD;
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
    private JComboBox<String> changePtsBox;
    private JLabel changePtsInstruction;
    private JPanel bracketOpWindow;

    private AdvanceTeamController advanceTeamController;
    private DeclareWinnerController declareWinnerController;
    private ChangePointsController changePointsController;
    public int gameID;

    public DoBracketOperation(AdvanceTeamController advanceTeamController,
                              DeclareWinnerController declareWinnerController,
                              ChangePointsController changePointsController){
        super("Bracket Operations");
        this.advanceTeamController = advanceTeamController;
        this.declareWinnerController = declareWinnerController;
        this.changePointsController = changePointsController;

        gameNumLabel.setText("Game Number: 0");
        teamsLabel.setText("Teams: ");
        advanceTeamLabel.setText("Advance Winning Team");
        chngPtsLabel.setText("Change Points for Team");
        declareButton.setText("Declare Winner");
        declareWInnerLabel.setText("Declare the Winner");

        advanceButton.addActionListener(this);
        changePointsButton.addActionListener(this);
        declareButton.addActionListener(this);
        changePointsButton.addActionListener(this);
        changePtsTF.addActionListener(this);
        changePtsBox.addActionListener(this);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public void setTeamsLabel(ArrayList<String> teams) {
        for (String team : teams) {
            this.teamsLabel.setText(this.teamsLabel.getText() + team + " ");
        }
    }

    public void setChangePointsBox(ArrayList<String> teams) {
        this.changePtsBox.removeAllItems();
        for (String team : teams) {
            this.changePtsBox.addItem(team);
        }
    }

    public static void main(String[] args) {
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

        DoBracketOperation doBracketOperation = new DoBracketOperation(advanceTeamController1, declareWinnerController1, changePointsController1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == advanceButton) {
                advanceTeamController.create(gameID);
            } else if (e.getSource() == declareButton) {
                declareWinnerController.create(gameID);
            } else if (e.getSource() == changePointsButton){
                int points = Integer.parseInt(changePtsTF.getText().trim());
                String teamName = (String) changePtsBox.getSelectedItem();
                if (teamName == null) {
                    JOptionPane.showMessageDialog(null, "Please select a team.");
                }
                changePointsController.create(gameID, points, teamName);
            }


        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
        } catch (AdvanceTeamFailed advanceTeamFailed) {
            JOptionPane.showMessageDialog(null, "Advance Team Failed.");
        } catch (DeclareWinnerFailed declareWinnerFailed) {
            JOptionPane.showMessageDialog(null, "Declare Winner Failed.");
        } catch (ChangePointsFailed changePointsFailed) {
            JOptionPane.showMessageDialog(null, "Change Points Failed.");
        }


//        if (e.getSource() == advanceButton) {
//            advanceTeamController.create(gameID);
//        } else if (e.getSource() == changePointsButton) {
//            int points = 0;
//            try {
//                points = Integer.parseInt(changePtsTF.getText().trim());
//            } catch (NumberFormatException ex) {
//                JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
//            }
//
//            String teamName = (String) changePtsBox.getSelectedItem();
//            if (teamName == null) {
//                JOptionPane.showMessageDialog(null, "Please select a team.");
//            }
//            changePointsController.create(gameID, points, teamName);
//        } else if (e.getSource() == declareButton) {
//            declareWinnerController.create(gameID);
//        }

    }






//    public DoBracketOperation() {
//        super("Bracket Operations");
//
//        showScreen();
//        advanceButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                advanceTeamController.create(gameID);
//            }
//        });
//        changePointsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int points = 0;
//                try {
//                    points = Integer.parseInt(changePtsTF.getText().trim());
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
//                }
//
//                String teamName = (String) changePtsBox.getSelectedItem();
//                if (teamName == null) {
//                    JOptionPane.showMessageDialog(null, "Please select a team.");
//                }
//                changePointsController.create(gameID, points, teamName);
//            }
//        });
//        declareButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                declareWinnerController.create(gameID);
//            }
//        });
//    }

    // Overloading the constructor to allow for multiple use cases to be handled

//    public DoBracketOperation(AdvanceTeamController controller){
//        super("Bracket Operations");
//        advanceTeamController = controller;
//        showScreen();
//        advanceButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                advanceTeamController.create(gameID);
//            }
//        });
//    }

//    public DoBracketOperation(DeclareWinnerController controller){
//        super("Bracket Operations");
//        declareWinnerController = controller;
//        showScreen();
//        declareButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                declareWinnerController.create(gameID);
//            }
//        });
//    }
//
//    public DoBracketOperation(ChangePointsController controller){
//        super("Bracket Operations");
//        changePointsController = controller;
//        showScreen();
//        changePointsButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int points = 0;
//                String teamName = (String) changePtsBox.getSelectedItem();
//                if (teamName == null) {
//                    JOptionPane.showMessageDialog(null, "Please select a team.");
//                }
//
//                try {
//                    points = Integer.parseInt(changePtsTF.getText().trim());
//                } catch (NumberFormatException ex) {
//                    JOptionPane.showMessageDialog(null, "Please enter a valid integer.");
//                }
//
//                changePointsController.create(gameID, points, teamName);
//            }
//        });
//    }


//    public void showScreen() {
//        gameNumLabel.setText("Game Number: 0");
//        teamsLabel.setText("Teams: ");
//        advanceTeamLabel.setText("Advance Winning Team");
//        chngPtsLabel.setText("Change Points for Team");
//        declareButton.setText("Declare Winner");
//        declareWInnerLabel.setText("Declare the Winner");
//
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setContentPane(bracketOpWindow);
//        this.setPreferredSize(new Dimension(650, 300));
//        this.pack();
//        this.setVisible(true);
//    }

}

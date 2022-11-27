package screens.joinTournament;

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

            JoinTeamOB joinTeamOB = new JoinTeamPresenter();
            JoinTeamIB joinTeamIB = new JoinTeamUC(joinTeamOB, outData.getUsername(), outData.getTournamentID(),
                    outData.getAccounts(), outData.getBrackets());
            JoinTeamController joinTeamController = new JoinTeamController(joinTeamIB);

            NextScreenData nextScreenData = new NextScreenData();
            nextScreenData.setBrackets(outData.getBrackets());
            nextScreenData.setAccounts(outData.getAccounts());
            nextScreenData.setCurrentUser(outData.getUsername());
            nextScreenData.setCurrentBracketID(outData.getTournamentID());

            bracketView view = new bracketView(nextScreenData, endTournController, startTournController, joinTeamController);
            view.setBracketName(outData.getTournamentName());
            view.setCurrentUser(outData.getUsername());
            view.setCurrentTournament(outData.getTournamentID());
            view.setPlayerInvite(outData.getInvite("Player"));
            view.setObserverInvite(outData.getInvite("Observer"));

            dispose();
            view.setVisible(true);
        }
        catch (JoinTournamentFailed ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

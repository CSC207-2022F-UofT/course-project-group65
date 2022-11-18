package screens.viewTournament;

import entities.Bracket;
import screens.NextScreenData;
import screens.bracketView;
import screens.endTourn.EndTournController;
import screens.endTourn.EndTournPresenter;
import screens.startTourn.StartTournController;
import screens.startTourn.StartTournPresenter;
import useCases.endTourn.EndTournIB;
import useCases.endTourn.EndTournOB;
import useCases.endTourn.EndTournUC;
import useCases.startTourn.StartTournIB;
import useCases.startTourn.StartTournOB;
import useCases.startTourn.StartTournUC;
import useCases.viewTournament.ViewTournamentOD;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewTournamentInfo extends JFrame implements ActionListener{
    private JPanel viewTournament;
    private JButton btSubmit;
    private JLabel lbTournamentID;
    private JComboBox cbTournamentID;
    private ViewTournamentController controller;

    public ViewTournamentInfo(ViewTournamentController controller) {
        this.controller = controller;
        setTitle("View Tournament");
        setSize(450, 300);
        setVisible(true);
        //cbTournamentID.add() adding tournaments from users current tournaments
        // may just change to a Jtextfield if too difficult
        btSubmit.addActionListener(this);
        setContentPane(viewTournament);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int tournamentID = (Integer) cbTournamentID.getSelectedItem();
        try {
            ViewTournamentOD outData = controller.viewTournament(tournamentID);

            EndTournOB endTournOB = new EndTournPresenter();
            EndTournIB endTournIB = new EndTournUC(endTournOB, outData.getUsername(), outData.getAccounts(),
                    outData.getBrackets(), outData.getTournamentID());
            EndTournController endTournController = new EndTournController(endTournIB);

            StartTournOB startTournOB = new StartTournPresenter();
            StartTournIB startTournIB = new StartTournUC(startTournOB, outData.getUsername(), outData.getAccounts(),
                    outData.getBrackets(), outData.getTournamentID());
            StartTournController startTournController = new StartTournController(startTournIB);

            NextScreenData nextScreenData = new NextScreenData();
            nextScreenData.setBrackets(outData.getBrackets());
            nextScreenData.setAccounts(outData.getAccounts());
            nextScreenData.setCurrentUser(outData.getUsername());
            nextScreenData.setCurrentBracketID(outData.getTournamentID());

            bracketView view = new bracketView(nextScreenData, endTournController, startTournController);
            Bracket bracket = outData.getBrackets().getBracket(outData.getTournamentID());
            view.setBracketName(bracket.getTournamentName());
            view.setCurrentUser(outData.getUsername());
            view.setCurrentTournament(outData.getTournamentID());
            view.setPlayerInvite(bracket.getPlayerInvite());
            view.setObserverInvite(bracket.getObserverInvite());

            dispose();
            view.setVisible(true);
        }
        catch (ViewTournamentFailed ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

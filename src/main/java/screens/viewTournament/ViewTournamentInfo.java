package screens.viewTournament;

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
        btSubmit.addActionListener(this);
        setContentPane(viewTournament);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        int tournamentID = (Integer) cbTournamentID.getSelectedItem();
        try {
            ViewTournamentOD outData = controller.viewTournament(tournamentID);
        }
        catch (Exception ex){
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}

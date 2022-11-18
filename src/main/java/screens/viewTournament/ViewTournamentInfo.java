package screens.viewTournament;

import javax.swing.*;

public class ViewTournamentInfo extends JFrame{
    private JPanel viewTournament;
    private JButton btSubmit;
    private JLabel lbTournamentID;
    private JComboBox cbTournamentID;

    public ViewTournamentInfo(){
        setContentPane(viewTournament);
        setTitle("View Tournament");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btSubmit.addActionListener(e -> {
            int TournamentID = (Integer) cbTournamentID.getSelectedItem();
        });
    }
}

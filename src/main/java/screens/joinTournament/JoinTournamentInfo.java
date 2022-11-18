package screens.joinTournament;

import javax.swing.*;

public class JoinTournamentInfo extends JFrame{
    private JPanel joinTournament;
    private JButton btSubmit;
    private JTextField tfInvite;
    private JLabel lbInvite;

    public JoinTournamentInfo(){
        setContentPane(joinTournament);
        setTitle("Join Tournament");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        btSubmit.addActionListener(e -> {
            String invite = tfInvite.getText();
        });
    }

    public static void main(String[] args) {
        JoinTournamentInfo joinTournamentInfo = new JoinTournamentInfo();
    }
}

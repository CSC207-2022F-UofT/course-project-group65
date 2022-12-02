package frameworks_and_drivers;

import javax.swing.*;

public class optionsScreen2 extends JFrame{
    private JButton joinAnExistingTeamButton;
    private JButton createANewTeamButton;
    private JButton backToPreviousScreenButton;
    private JPanel optionsScreen2;

    public optionsScreen2() {
        setContentPane(optionsScreen2);
        setTitle("Options Screen2");
        setSize(450, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

//        createANewTeamButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//
//            }
//        });
//        joinAnExistingTeamButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//
//            }
//        });
//        backToPreviousScreenButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent actionEvent) {
//
//            }
//        });

    }
    public static void main(String[] args) {
        optionsScreen2 optionsScreen2 = new optionsScreen2();
    }
}

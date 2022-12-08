package frameworks_and_drivers;

import interface_adapters.start_tournament.StartTournController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is used to display errors that occur when starting a tournament.
 */
public class StartErrors extends JFrame implements ActionListener {
    private JButton startBtn;
    private JButton cancelBtn;
    private JLabel warning1;
    private JLabel warning2;
    private JLabel warning3;

    private JPanel mainPanel;
    private final StartTournController startTournController;

    public StartErrors(StartTournController startTournController) {
        super("Start Tournament");

        this.startTournController = startTournController;

        startBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.setPreferredSize(new Dimension(800, 600));
        this.pack();
    }

    public void setWarning1(String warning) {
        warning1.setText(warning);
    }

    public void setWarning2(String warning) {
        warning2.setText(warning);
    }

    public void setWarning3(String warning) {
        warning3.setText(warning);
    }

    /**
     * This method is used to handle the actions of the buttons.
     * @param e The action event.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            startTournController.start();
            this.dispose();
        } else if (e.getSource() == cancelBtn) {
            this.dispose();
        }
    }
}

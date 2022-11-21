package screens.startTourn;

import screens.endTourn.EndTournController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startErrors extends JFrame implements ActionListener {
    private JButton startBtn;
    private JButton cancelBtn;
    private JLabel warning1;
    private JLabel warning2;
    private JLabel warning3;
    private JLabel warning4;
    private JPanel mainPanel;
    private StartTournController startTournController;

    public startErrors(StartTournController startTournController) {
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

    public void setWarning4(String warning) {
        warning4.setText(warning);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            startTournController.start();
            this.dispose();
        } else if (e.getSource() == cancelBtn) {
            this.dispose();
        }
    }
}

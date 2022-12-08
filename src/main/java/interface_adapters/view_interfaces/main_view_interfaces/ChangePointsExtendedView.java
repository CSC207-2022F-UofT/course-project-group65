package interface_adapters.view_interfaces.main_view_interfaces;

import java.util.ArrayList;

/**
 * View interface between ChangePoints and the main bracket view.
 */
public interface ChangePointsExtendedView {

    void updateGameScore(int advancedGame, ArrayList<String> teams, ArrayList<Integer> scores);

}

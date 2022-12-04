package use_cases.change_points;

import java.util.ArrayList;

/** The output data of the change points use case. */
public class ChangePointsOD {
    private final ArrayList<String> teams;
    private final ArrayList<Integer> points;
    private final int game;

    /** The constructor of the output data of the change points use case.
     * @param teams The teams that were in the game with points changed.
     * @param points The points of the teams after the change.
     */
    public ChangePointsOD(int game, ArrayList<String> teams, ArrayList<Integer> points) {
        this.points = points;
        this.teams = teams;
        this.game = game;
    }

    /** @return The teams that were in the game with points changed. */
    public ArrayList<String> getTeams() {
        return teams;
    }

    /** @return The points of the teams after the change. */
    public ArrayList<Integer> getPoints() {
        return points;
    }

    public int getGame() {
        return game;
    }
}

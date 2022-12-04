package use_cases.advance_team;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * The output data passed eventually onto the presenter
 */
public class AdvanceTeamOD {

    private final int advancedGameID;
    private final LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    private final LinkedHashMap<Integer, ArrayList<Integer>> gameToScores;

    /**
     * Creates a new AdvanceTeamOD object - the output data.
     *
     * @param advancedGame The ID of the game that was advanced
     * @param gameToTeams  The updated game to teams mapping
     * @param gameToScores The updated game to scores mapping
     */
    public AdvanceTeamOD(int advancedGame, LinkedHashMap<Integer, ArrayList<String>> gameToTeams,
                         LinkedHashMap<Integer, ArrayList<Integer>> gameToScores) {
        this.advancedGameID = advancedGame;
        this.gameToTeams = gameToTeams;
        this.gameToScores = gameToScores;
    }

    /**
     * Returns the ID of the game that was advanced.
     *
     * @return The ID of the game that was advanced
     */
    public int getAdvancedGame() {
        return this.advancedGameID;
    }

    /**
     * Returns the updated game to teams mapping.
     *
     * @return The updated game to teams mapping
     */
    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams() {
        return this.gameToTeams;
    }

    /**
     * Returns the updated game to scores mapping.
     *
     * @return The updated game to scores mapping
     */
    public LinkedHashMap<Integer, ArrayList<Integer>> getGameToScores() {
        return this.gameToScores;
    }

}

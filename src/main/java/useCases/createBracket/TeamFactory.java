package useCases.createBracket;

import entities.Team;

public interface TeamFactory {
    /*
     * This is an interface for creating a team.
     */

    Team getTeam(String teamType);

}

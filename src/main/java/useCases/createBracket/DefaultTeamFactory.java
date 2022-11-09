package useCases.createBracket;

import entities.DefaultTeam;

public class DefaultTeamFactory implements TeamFactory {
    /*
     * This is a factory for creating a team.
     */

    public DefaultTeam getTeam(String teamType) {
//        Open to extension, for example if we want to create Default Teams that have a default name.
        if (teamType.equalsIgnoreCase("Default")) {
            return new DefaultTeam();
        }
        return null;
    }
}

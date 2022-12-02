package useCases.createBracket;

import entities.DefaultTeam;

/**
 * This class is a factory for creating a default team. A default team simply has a name,
 * and a list of players on the team.
 * This class is part of the factory design pattern, and used in the builder design pattern giving
 * us a flexible way to extend the functionality of the program. For example if we wanted to add a new type
 * of "default" team that additionally kept track of stats for each player as they progress in the tournament,
 * we could simply create a new class that implements the Team interface, and add it to this factory.
 * Note: Teams are created before the games are created, as the teams are assigned to the leaves of the games tree.
 * Each team starts with a default team name, and as players join and create teams,
 * the default teams are updated and the default names are overwritten.
 * The reason we have "BlankTeams" or default teams, is so that the user can still start and simulate the tournament
 * even if it is not full.
 */
public class DefaultTeamFactory implements TeamFactory {

    /** The unique number used to create the default name of each new Team */
    private int blankTeamID = 1;

    /**
     * This method is used to create a new default team. Each team will start with a unique default name,
     * "BlankTeam #", where # is the unique blankTeamID of the team.
     * @param teamType - the type of team to create (currently, the type is always "default")
     * @return a new default team
     */
    public DefaultTeam getTeam(String teamType) {
//        Open to extension, for example if we want to create Default Teams that have a different default name.
        if (teamType.equalsIgnoreCase("Default")) {
            DefaultTeam team = new DefaultTeam();
            team.setTeamName("BlankTeam " + blankTeamID);
            blankTeamID++;
            return team;
        }
        return null;
    }
}

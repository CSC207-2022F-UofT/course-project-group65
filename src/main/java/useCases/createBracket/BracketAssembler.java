package useCases.createBracket;

import entities.Bracket;
import entities.Team;
import entities.User;

import java.util.ArrayList;

public class BracketAssembler {
    /*
     * This is a class for assembling a bracket.
     */

    private BracketBuilder builder;

    public void assembleBracket(String bracketType, User creator, String bracketName,
                      int tournamentID, int numTeams, int maxTeamSize, int winCondition) {
        if (bracketType.equals("Default")) {
            this.builder = new DefaultBracketBuilder();
        }

//        Do I need an else statement/error handler here?

        if (this.builder != null) {
            buildBracket(creator, bracketName, tournamentID, numTeams, maxTeamSize, winCondition);
        }
    }

    public void buildBracket(User creator, String bracketName, int tournamentID, int numTeams, int maxTeamSize, int winCondition) {
        this.builder.setName(bracketName);
        this.builder.setID(tournamentID);
        ArrayList<Team> new_teams = this.builder.buildTeams(numTeams);
        this.builder.buildGames(numTeams, new_teams);
        this.builder.setMaxTeamSize(maxTeamSize);
        this.builder.addOverseer(creator, tournamentID);
        this.builder.setWinCon(winCondition);
    }

    public Bracket getBracket() {
        return builder.getBracket();
    }
}

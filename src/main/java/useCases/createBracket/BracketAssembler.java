package useCases.createBracket;

import entities.Bracket;
import entities.User;

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
        this.builder.buildTeams(numTeams);
        this.builder.setMaxTeamSize(maxTeamSize);
        this.builder.buildGames(numTeams);
        this.builder.addOverseer(creator, tournamentID);
        this.builder.setWinCon(winCondition);
    }

    public Bracket getBracket() {
        return builder.getBracket();
    }
}

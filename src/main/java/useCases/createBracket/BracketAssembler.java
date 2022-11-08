package useCases.createBracket;

public class BracketAssembler {
    /*
     * This is a class for assembling a bracket.
     */

    private BracketBuilder builder;

    void setBuilder(String bracketType) {
        if (bracketType.equals("Default")) {
            builder = new DefaultBracketBuilder();
        }

//        Do I need an else statement/error handler here?

        if (builder != null) {
            buildBracket();
        }
    }

    void buildBracket() {
        builder.setName("Tournament");
        builder.setID(1);
        builder.setGames(4);
        builder.setTeams(4);
        builder.setMaxTeamSize(4);
        builder.addOverseer(null);
        builder.setWinCon(2);
    }
}

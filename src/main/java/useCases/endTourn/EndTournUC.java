package useCases.endTourn;

import entities.*;
import useCases.generalClasses.InformationRecord;

import java.util.Objects;


/**
 * A use case for ending the tournament.
 */
public class EndTournUC implements EndTournIB{
    private final EndTournOB outputBoundary;
    private final AccountRepo accounts;
    private final BracketRepo brackets;
    private final int bracketId;
    private final Bracket bracket;
    private final User user;
    private final EndTournGateway gateway;

    public EndTournUC(EndTournOB outputBoundary, String currentUser, InformationRecord informationRecord,
                      int bracketId, EndTournGateway gateway) {
        this.outputBoundary = outputBoundary;
        this.accounts = informationRecord.getAccountData();
        this.brackets = informationRecord.getBracketData();
        this.bracketId = bracketId;
        this.bracket = brackets.getBracket(bracketId);
        this.user = accounts.getUser(currentUser);
        this.gateway = gateway;

    }

    /**
     * Checks if the user is an Overseer.
     * @return true if and only if the user is an Overseer.
     */
    public boolean checkUserRole() {
        return (Objects.equals(this.user.getBracketRole(this.bracketId), "Overseer"));
    }


    /**
     * Checks if the final game has ended.
     * @return true if and only if the final game has ended.
     */
    public boolean checkGame() {
        Game finalGame = this.bracket.getFinalGame();
        return (finalGame.getNumTeams() < 2);
    }

    /**
     * Checks if there is a final winner.
     * @return true if and only if there is a final winner.
     */
    public boolean checkFinalWinner() {
        return (bracket.getFinalGame().getWinner() != null);
    }

    @Override
    public EndTournOD endTourn() {

        if (checkGame()) {
            return this.outputBoundary.presentError("This round is not the final round.");
        }

        if (!checkFinalWinner()) {
            return this.outputBoundary.presentError("No final winner has been decided yet.");
        }

        if (!checkUserRole()) {
            return this.outputBoundary.presentError("You do not have permission to end the tournament.");
        }

        this.bracket.setTournamentCondition(false);
        EndTournDSID dataStoreID = new EndTournDSID(this.brackets);

        try {
            this.gateway.save(dataStoreID);
        } catch (Exception e){
            return this.outputBoundary.presentError("Error saving to database.");
        }
        EndTournOD outputData = new EndTournOD();
        return this.outputBoundary.presentSuccess(outputData);
    }


    public AccountRepo getAccounts() {
        return accounts;
    }

    public BracketRepo getBrackets() {
        return brackets;
    }
}

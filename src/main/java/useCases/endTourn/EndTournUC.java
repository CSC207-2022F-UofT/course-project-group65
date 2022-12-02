package useCases.endTourn;

import entities.*;
import useCases.generalClasses.InformationRecord;

import java.util.Objects;


/**
 * This is the Use Case (interactor) class for the EndTourn use case. This class is responsible
 * for ending the current tournament and updating the bracket repository.
 * It connects and uses many of the classes used in this package.
 * Implements the EndTournIB to allow the controller to call the endTourn method.
 */
public class EndTournUC implements EndTournIB{
    /** The output boundary */
    private final EndTournOB outputBoundary;

    /** The bracket repository to update the current bracket */

    private final BracketRepo brackets;
    /** The bracket id to access the current bracket (tournament)*/
    private final int bracketId;
    /** The current bracket */
    private final Bracket bracket;
    /** The User who wants to start a tournament */
    private final User user;
    /** The gateway to access the database to store info */
    private final EndTournGateway gateway;

    /**
     * Creates a new EndTournUC object.
     * @param outputBoundary The output boundary
     * @param currentUser The username of the current user ending the tournament
     * @param informationRecord The information record containing the account and bracket repositories
     * @param bracketId The bracket id
     * @param gateway The gateway to access the database to store the info
     */
    public EndTournUC(EndTournOB outputBoundary, String currentUser, InformationRecord informationRecord,
                      int bracketId, EndTournGateway gateway) {
        this.outputBoundary = outputBoundary;
        AccountRepo accounts = informationRecord.getAccountData();
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

    /**
     * The main endTourn method.
     * @return the output data to be used in determining whether to present a success or error view.
     */
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
}

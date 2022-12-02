package use_cases.create_bracket;

import entities.*;
import use_cases.general_classes.InformationRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * This is the Use Case (interactor) class for the CreateBracket use case. This class is responsible
 * for creating a new bracket (based on the user input) and updating the account and bracket repositories.
 * It connects and uses many of the classes used in this package.
 * Implements the CreateBracketIB to allow the controller to call the create method.
 */
public class CreateBracketUC implements CreateBracketIB{
    /** The bracket ID that will be given to the next bracket */
    private int bracketID;
    /** The output boundary used for updating the view */
    private final CreateBracketOB outputBoundary;
    /** The user creating the bracket */
    private final String currentUser;
    /** The account repository to store the updated user info */
    private final AccountRepo accounts;
    /** The bracket repository to store the new bracket */
    private final BracketRepo brackets;
    /** The gateway to access the database to store info */
    private final CreateBracketGateway gateway;

    /**
     * Creates a new CreateBracketUC object.
     * @param createBracketOB The output boundary used for updating the view
     * @param gateway The gateway to access the database to store info
     * @param currentUser The user creating the bracket
     * @param informationRecord The information record containing the account and bracket repositories
     *                          (this class was created as a dependency inversion allowing access to the repositories)
     * Note: bracketID is either initialized as 0 if no brackets have been created, or it is set to the
     *       bracketID of the last bracket created.
     */
    public CreateBracketUC(CreateBracketOB createBracketOB, CreateBracketGateway gateway, String currentUser, InformationRecord informationRecord) {
        this.outputBoundary = createBracketOB;
        this.gateway = gateway;
        this.currentUser = currentUser;
        this.accounts = informationRecord.getAccountData();
        this.brackets = informationRecord.getBracketData();

        ArrayList<Integer> ids = new ArrayList<>(this.brackets.getBrackets().keySet());
        if (ids.size() == 0) {
            this.bracketID = 0;
        } else {
            this.bracketID = Collections.max(ids);
        }
    }

    /** Returns the bracket ID that will be given to the next bracket.
     * Each bracket receives a unique ID that is one more then the last bracket created.
     * @return The bracket ID that will be given to the next bracket
     */
    public int generateBracketID() {
        this.bracketID++;
        return this.bracketID;
    }

    /**
     * Creates a new bracket based on the user input using the assembler.
     * Updates the users account info making them the Overseer of the created tournament.
     * Helper method for the create method.
     * @param bracketType The type of bracket to be created
     * @param bracketName The name of the bracket
     * @param numTeams The number of teams in the bracket
     * @param maxTeamSize The maximum number of players per team
     * @param winCondition The number of points needed to advance a round in the bracket
     * @return The newly created bracket
     */
    public Bracket createBracket(String bracketType, String bracketName,
                                 int numTeams, int maxTeamSize, int winCondition) {
        int tournamentID = generateBracketID();
        User creator = accounts.getUser(currentUser);
        BracketAssembler assembler = new BracketAssembler();
        assembler.assembleBracket(bracketType, creator, bracketName, tournamentID,
                numTeams, maxTeamSize, winCondition);
        creator.setCurrentTournament(bracketID);
        creator.setBracketRole(bracketID, "Overseer");
        creator.addTournament(bracketID);
        return assembler.getBracket();
    }

    /**
     * Updates the bracket repositories with the new bracket.
     * Hepler method for the create method.
     * @param bracket The bracket to be added to the repository
     * @return the bracketID of the bracket that was added to the repository
     */
    public int storeBracket(BracketRepo repo, Bracket bracket) {
//        Stores the bracket in the repo and returns the bracketID
        repo.addBracket(bracket);
        return bracket.getTournamentID();
    }

    /**
     * The main create method of the CreateBracketUC class.
     * Implemented version of method in the input boundary, this is the method
     * that is called when the user clicks the create button.
     * @param createBracketID The ID of all the info the user submitted on the bracket
     *                        being created.
     * @return The output data to be used in updating the view, or an error message based on the
     *         requisite error.
     */
    public CreateBracketOD create(CreateBracketID createBracketID) {
//        Call createBracket helper method with the input data
        Bracket bracket = createBracket(
                createBracketID.getBracketType(), createBracketID.getBracketName(),
                createBracketID.getNumTeams(), createBracketID.getMaxTeamSize(),
                createBracketID.getWinCondition());

//        Store the new bracket in the bracket repository, and get the bracketID
        int bracketID = storeBracket(brackets, bracket);
//        Get all the default team names for the output data
        ArrayList<String> teams = new ArrayList<>();
        for (Team team : bracket.getTeams()) {
            teams.add(team.getTeamName());
        }
        CreateBracketOD outputData = new CreateBracketOD(currentUser, accounts, brackets,
                bracketID, teams);

//        Return the outputData or the error message, depending on the error.
        if (Objects.equals(bracket.getTournamentName(), "")){
//            Bracket failed to create, so we need to retract our bracketID (else we will be inefficiently using IDs)
            this.bracketID--;
            return this.outputBoundary.presentError("Please enter a name for your bracket.");
        } else {
            CreateBracketDSID dsid = new CreateBracketDSID(this.brackets, this.accounts);
            try {
                this.gateway.save(dsid);
            } catch (Exception e) {
                return this.outputBoundary.presentError("Error saving to database");
            }
            return this.outputBoundary.presentSuccess(outputData);
        }
    }
}

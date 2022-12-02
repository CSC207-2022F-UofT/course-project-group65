package useCases.createBracket;

import entities.*;
import useCases.generalClasses.InformationRecord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CreateBracketUC implements CreateBracketIB{
/*
    * This is a use case for creating a bracket.
 */
    private int bracketID;
    private final CreateBracketOB outputBoundary;
    private final String currentUser;
    private final AccountRepo accounts;
    private final BracketRepo brackets;
    private final CreateBracketGateway gateway;

    public CreateBracketUC(CreateBracketOB advanceTeamOB, CreateBracketGateway gateway, String currentUser, InformationRecord informationRecord) {
        this.outputBoundary = advanceTeamOB;
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

    public int generateBracketID() {
        this.bracketID++;
        return this.bracketID;
    }


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

    public int storeBracket(BracketRepo repo, Bracket bracket) {
//        Stores the bracket in the repo and returns the bracketID
        repo.addBracket(bracket);
        return bracket.getTournamentID();
    }

    public CreateBracketOD create(CreateBracketID createBracketID) {
        Bracket bracket = createBracket(
                createBracketID.getBracketType(), createBracketID.getBracketName(),
                createBracketID.getNumTeams(), createBracketID.getMaxTeamSize(),
                createBracketID.getWinCondition());

        int bracketID = storeBracket(brackets, bracket);
        ArrayList<String> teams = new ArrayList<>();
        for (Team team : bracket.getTeams()) {
            teams.add(team.getTeamName());
        }
        CreateBracketOD outputData = new CreateBracketOD(currentUser, accounts, brackets,
                bracketID, teams);

        if (Objects.equals(bracket.getTournamentName(), "")){
//            Bracket failed to create, so we need to retract our bracketID
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

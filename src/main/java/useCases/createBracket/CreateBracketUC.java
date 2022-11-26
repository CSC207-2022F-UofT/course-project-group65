package useCases.createBracket;

import entities.*;

import java.util.ArrayList;
import java.util.Objects;

public class CreateBracketUC implements CreateBracketIB{
/*
    * This is a use case for creating a bracket.
 */
    private int bracketID = 0;
    private CreateBracketOB outputBoundary;
    private String currentUser;
    private AccountRepo accounts;
    private BracketRepo brackets;

    public CreateBracketUC(CreateBracketOB advanceTeamOB, String currentUser, AccountRepo accounts, BracketRepo brackets) {
        this.outputBoundary = advanceTeamOB;
        this.currentUser = currentUser;
        this.accounts = accounts;
        this.brackets = brackets;
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
        String bracketType = createBracketID.getBracketType();
        ArrayList<String> teams = new ArrayList<>();
        for (Team team : bracket.getTeams()) {
            teams.add(team.getTeamName());
        }
        String bracketName = bracket.getTournamentName();
        String playerInvite = bracket.getPlayerInvite();
        String observerInvite = bracket.getObserverInvite();
        CreateBracketOD outputData = new CreateBracketOD(currentUser, accounts, brackets, bracketType,
                bracketID, teams, bracketName, playerInvite, observerInvite);

        if (Objects.equals(bracket.getTournamentName(), "")){
//            Bracket failed to create, so we need to retract our bracketID
            this.bracketID--;
            return this.outputBoundary.presentError("Please enter a name for your bracket.");
        } else {
            return this.outputBoundary.presentSuccess(outputData);
        }
    }


//    public static void main(String[] args) {
//        HashMap<String, Integer> passwords = new HashMap<String, Integer>();
//        passwords.put("user1", 1234);
//        ArrayList<String> usernames = new ArrayList<String>(passwords.keySet());
//        System.out.println(passwords);
//        System.out.println(passwords.keySet());
//        System.out.println(usernames);
//        CreateBracketUC uc = new CreateBracketUC();
//        AccountRepo accounts = new AccountRepo();
//        uc.createBracket("user1", accounts, "Single Elimination", "Test Bracket",
//                4, 1, 1);
//        BracketRepo repo = new BracketRepo();
//        int num = uc.storeBracket(repo, uc.assembler.getBracket());
//    }

}

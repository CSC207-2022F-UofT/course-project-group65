package useCases.createBracket;

import entities.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class CreateBracketUC {
/*
    * This is a use case for creating a bracket.
 */
    private int bracketID = 0;
    private BracketAssembler assembler;

    public int generateBracketID() {
        this.bracketID++;
        return this.bracketID;
    }

//    USER HAS A DEPENDENCY ISSUE I BELIEVE
//    public Bracket createBracket(User creator, String bracketType, String bracketName,
//                           int numTeams, int maxTeamSize, int winCondition) {
//        int tournamentID = generateBracketID();
//        this.assembler = new BracketAssembler();
//        this.assembler.assembleBracket(bracketType, creator, bracketName, tournamentID,
//                numTeams, maxTeamSize, winCondition);
//        return this.assembler.getBracket();
//    }

    public Bracket createBracket(String username, AccountRepo accounts, String bracketType, String bracketName,
                                 int numTeams, int maxTeamSize, int winCondition) {
        int tournamentID = generateBracketID();
        User creator = accounts.getUser(username);
        this.assembler = new BracketAssembler();
        this.assembler.assembleBracket(bracketType, creator, bracketName, tournamentID,
                numTeams, maxTeamSize, winCondition);
        return this.assembler.getBracket();
    }

    public int storeBracket(BracketRepo repo, Bracket bracket) {
//        Stores the bracket in the repo and returns the bracketID
        repo.addBracket(bracket);
        return bracket.getTournamentID();
    }

    public static void main(String[] args) {
        HashMap<String, Integer> passwords = new HashMap<String, Integer>();
        passwords.put("user1", 1234);
        ArrayList<String> usernames = new ArrayList<String>(passwords.keySet());
        System.out.println(passwords);
        System.out.println(passwords.keySet());
        System.out.println(usernames);
    }

}

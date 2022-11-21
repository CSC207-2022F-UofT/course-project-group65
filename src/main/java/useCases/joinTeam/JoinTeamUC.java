package useCases.joinTeam;
import java.util.ArrayList;
import java.util.List;
import entities.*;

/**
 * This is a use case for joining a team.
 */
public class JoinTeamUC implements JoinTeamIB {
    private final JoinTeamOB outputBoundary;
    private final String userName;
    private final int bracketID;
    private final AccountRepo accounts;
    private final BracketRepo brackets;

    private Team curTeam;

    public JoinTeamUC(JoinTeamOB outputBoundary, String userName, int bracketID, AccountRepo accounts,
                      BracketRepo brackets){
        this.outputBoundary = outputBoundary;
        this.userName = userName;
        this.bracketID = bracketID;
        this.accounts = accounts;
        this.brackets = brackets;
    }

    public boolean checkPlayer(){
        Bracket curBracket = brackets.getBracket(bracketID);
        User user = accounts.getUser(userName);
        return user.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }
    // Check whether the user is a player since only player can join a team.

    public Team findTeam(JoinTeamID input){
        String teamName = input.getTeamName();
        Bracket curBracket = brackets.getBracket(bracketID);
        List<Team> team_lst = curBracket.getTeams();
        for(Team team : team_lst){
            if(teamName.equals(team.getTeamName())){
                this.curTeam = team;
            }
        }
        return this.curTeam;
    }
    // It's a helper function for checkTeamExistence and checkTeamSpace since both these two functions
    // need to find the team first.

    public boolean checkTeamExistence(JoinTeamID input){
        return !findTeam(input).getTeamName().contains("BlankTeam");
    }
    //Check whether the team existence by its team name is not "BlankTeam_"

    public boolean checkTeamSpace(JoinTeamID input) {
        Team team = findTeam(input);
        if (!team.getTeamName().contains("BlankTeam") ) {
            return team.getTeamSize() > team.getTeamMembers().size();
        }
        return false;
    }
    //Check whether the team still have space for the user to join.

    public String join(JoinTeamID input){
        User user = accounts.getUser(userName);
        Team team = findTeam(input);
        team.addTeamMember(user);
        return "You have been successfully joined the team ";
    }

    @Override
    public JoinTeamOD joinTeam(JoinTeamID input){
        boolean isPlayer = checkPlayer();
        boolean teamExistence = checkTeamExistence(input);
        boolean teamSpace = checkTeamSpace(input);
        if (!isPlayer){
            return outputBoundary.FailView("Fail to join the team(Only player can join the team)");
        }
        if (!teamExistence){
            return outputBoundary.FailView("Fail to join the team(The team name does not exist)");
        }
        if(!teamSpace){
            return outputBoundary.FailView("Fail to join the team(The team is already full");
        }
// After all the checks passed, add the user into the teamMembers list and
// return success and membersNames which is for
// updating the corresponding team members list in the bracketView screen.
        User user = accounts.getUser(userName);
        String success = join(input);
        Team team = findTeam(input);
        team.addTeamMember(user);
        ArrayList<User> teamMembers = team.getTeamMembers();
        ArrayList<String> membersNames = new ArrayList<>();
        for (User member : teamMembers){
            membersNames.add(member.getUsername());
        }
        JoinTeamOD outputData = new JoinTeamOD(success, membersNames);
        return outputBoundary.SuccessView(outputData);
    }
}

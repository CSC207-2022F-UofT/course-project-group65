package useCases.joinTeam;
import java.util.List;
import entities.*;

public class joinTeamUC implements joinTeamIB {
    private final joinTeamOB outputBoundary;
    private final String userName;
    private final int bracketID;
    private final AccountRepo accounts;
    private final BracketRepo brackets;

    public joinTeamUC(joinTeamOB outputBoundary, String userName, int bracketID, AccountRepo accounts,
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

    public Team findTeam(joinTeamID input){
        String teamName = input.getTeamName();
        Bracket curBracket = brackets.getBracket(bracketID);
        List<Team> team_lst = curBracket.getTeams();
        for(Team team : team_lst){
            if(teamName.equals(team.getTeamName())){
                return team;
            }
        }
        return null;
    }

    public boolean checkTeamExistence(joinTeamID input){
        return findTeam(input) != null;
    }

    public boolean checkTeamSpace(joinTeamID input) {
        Team team = findTeam(input);
        if (team != null) {
            return team.getTeamSize() > team.getTeamMembers().size();
        }
        return false;
    }

    public String join(joinTeamID input){
        String teamName = input.getTeamName();
        User user = accounts.getUser(userName);
        Team team = findTeam(input);
        team.addTeamMember(user);
        return "You have been successfully joined the team ";

    }
    @Override
    public joinTeamOD joinTeam(joinTeamID input){
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

        User user = accounts.getUser(userName);
        String success = join(input);
        Bracket curBracket = brackets.getBracket(bracketID);
        Team team = findTeam(input);
        team.addTeamMember(user);
        List<User> teamMembers = team.getTeamMembers();
        joinTeamOD outputData = new joinTeamOD(success, userName, teamMembers, curBracket);
        return outputBoundary.SuccessView(outputData);
    }
}

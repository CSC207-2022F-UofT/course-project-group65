package useCases.joinTeam;
import java.util.List;
import entities.DefaultUser;
import entities.Team;
import entities.DefaultBracket;

public class joinTeamUC {
    public boolean checkPlayer(DefaultUser user, DefaultBracket bracket){
        return user.getBracketRole(bracket.getTournamentID()).equals("Player");
    }

    public Team findTeam(String teamName, DefaultBracket bracket){
        List<Team> team_lst = bracket.getTeams();
        for(Team team : team_lst){
            if(teamName.equals(team.getTeamName())){
                return team;
            }
        }
        return null;
    }

    public boolean checkTeamExistence(String teamName, DefaultBracket bracket){
        return findTeam(teamName, bracket) != null;
    }

    public boolean checkTeamSpace(String teamName, DefaultBracket bracket) {
        Team team = findTeam(teamName, bracket);
        if (team != null) {
            return team.getTeamSize() > team.getTeamMembers().size();
        }
        return false;
    }

    public void joinTeam(DefaultUser user, String teamName, DefaultBracket bracket){
        boolean isPlayer = checkPlayer(user, bracket);
        boolean teamExistence = checkTeamExistence(teamName, bracket);
        boolean teamSpace = checkTeamSpace(teamName, bracket);
        if (!isPlayer){
            System.out.println("Fail to join the team(Only player can join the team)");
        }
        if (!teamExistence){
            System.out.println("Fail to join the team(The team name does not exist)");
        }
        if(!teamSpace){
            System.out.println("Fail to join the team(The team is already full");
        }
        if (isPlayer && teamExistence && teamSpace){
            Team team = findTeam(teamName, bracket);
            team.addTeamMember(user);
            System.out.println("successfully joined the team < " + teamName + " >");
        }

    }
}

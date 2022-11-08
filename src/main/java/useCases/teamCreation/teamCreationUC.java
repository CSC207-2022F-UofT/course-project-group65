package useCases.teamCreation;
import java.util.List;
import entities.DefaultUser;
import entities.DefaultTeam;
import entities.Team;
import entities.DefaultBracket;



public class teamCreationUC {
    DefaultTeam newTeam = new DefaultTeam();


    public boolean checkPlayer(DefaultUser creator, DefaultBracket curBracket){
        return creator.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }

    public boolean checkTeamNameExists(String teamName, DefaultBracket curBracket){
        List<Team> teams = curBracket.getTeams();
        for(Team team : teams){
            if(teamName.equals(team.getTeamName())){
                return true;
            }
        }
        return false;
    }

    public void createTeam(String teamName, int teamSize, DefaultUser creator, DefaultBracket curBracket){
        boolean userIsPlayer = checkPlayer(creator, curBracket);
        boolean validTeamName = !checkTeamNameExists(teamName, curBracket);
        boolean spaceAvailable = checkBracketSpace(curBracket);
        if(userIsPlayer && validTeamName && spaceAvailable) {
            newTeam.setTeamSize(teamSize);
            newTeam.setTeamName(teamName);
            storePlayer(creator);
        }
    }
    public boolean checkBracketSpace(DefaultBracket curBracket){
        return curBracket.getTeams().size() <= curBracket.getTeamSize();
    }

    public void storeTeam(DefaultBracket curBracket){
        curBracket.addTeam(newTeam);
    }

    public void storePlayer(DefaultUser creator){
        newTeam.addTeamMember(creator);
    }




}

package use_cases.join_team;
import java.util.ArrayList;
import java.util.List;
import entities.*;
import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

/**
 * This is the interactor class for joinTeam.
 * This class is responsible for joining the team in the bracketbased on the user choice and
 * saving the updated bracket back to the bracket repository.
 */
public class JoinTeamUC implements JoinTeamIB {
    /** The response model for JoinTeamUC **/
    private final JoinTeamOB outputBoundary;
    /** The user's name*/
    private final String userName;
    /** The ID of the bracket that the team should be joined in **/
    private final int bracketID;
    /** The account repository */
    private final AccountRepo accounts;
    /** The bracket repository */
    private final BracketRepo brackets;
    /** The gateway for teamCreationUC to access database **/
    private final JoinTeamGateway gateway;
    /** The current Team*/
    private Team curTeam;

    /**
     * Creates a new JoinTeamUC object.
     * @param outputBoundary The output boundary used for updating the view
     * @param gateway The gateway to access the database to store info
     * @param userName The username of the user that is joining the team
     * @param informationRecord The information record containing the account and bracket repositories
     */
    public JoinTeamUC(JoinTeamOB outputBoundary, JoinTeamGateway gateway, String userName, int bracketID, InformationRecord informationRecord) {
        this.outputBoundary = outputBoundary;
        this.gateway = gateway;
        this.userName = userName;
        this.bracketID = bracketID;
        this.accounts = informationRecord.getAccountData();
        this.brackets = informationRecord.getBracketData();
    }

    /** Check whether the user is already in a team */
    public boolean checkInTeam(){
        Bracket curBracket = brackets.getBracket(bracketID);
        List<Team> team_lst = curBracket.getTeams();
        User user = accounts.getUser(userName);
        boolean condition = false;
        for(Team team : team_lst){
            if(team.getTeamMembers().contains(user)){
                condition = true;
            }
        }
        return condition;
    }

    /** Check whether the user is a player since only player can join a team. */
    public boolean checkPlayer(){
        Bracket curBracket = brackets.getBracket(bracketID);
        User user = accounts.getUser(userName);
        return user.getBracketRole(curBracket.getTournamentID()).equals("Player");
    }

    /** It's a helper function for checkTeamExistence and checkTeamSpace since both these two functions
     need to find the team first. */
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

    /** Check whether the team existence by its team name is not "BlankTeam_" */
    public boolean checkTeamExistence(JoinTeamID input){
        return !findTeam(input).getTeamName().contains("BlankTeam");
    }

    /** Check whether the team still have space for the user to join. */
    public boolean checkTeamSpace(JoinTeamID input) {
        Team team = findTeam(input);
        if (!team.getTeamName().contains("BlankTeam") ) {
            return team.getTeamSize() > team.getTeamMembers().size();
        }
        return false;
    }

    /** Return the successfully joined the team massage */
    public String join(JoinTeamID input){
        User user = accounts.getUser(userName);
        Team team = findTeam(input);
        team.addTeamMember(user);
        return "You have been successfully joined the team ";
    }

    /** After all the checks passed, add the user into the teamMembers list and
     return success and membersNames which is for
     updating the corresponding team members list in the bracketView screen. */
    @Override
    public JoinTeamOD joinTeam(JoinTeamID input){
        boolean isPlayer = checkPlayer();
        boolean teamExistence = checkTeamExistence(input);
        boolean teamSpace = checkTeamSpace(input);
        boolean inTeam = checkInTeam();
        if (!isPlayer){
            return outputBoundary.FailView("Fail to join the team (Only player can join the team)");
        }
        if (!teamExistence){
            return outputBoundary.FailView("Fail to join the team (The team name does not exist)");
        }
        if(!teamSpace){
            return outputBoundary.FailView("Fail to join the team (The team is already full");
        }
        if (inTeam){
            return outputBoundary.FailView("Fail to join the team (You are already in a team)");
        }
        String success = join(input);
        JoinTeamDSID joinTeamDSID = new JoinTeamDSID(this.brackets);
        try {
            this.gateway.save(joinTeamDSID);
        } catch (Exception e) {
            return this.outputBoundary.FailView("There was an error saving the bracket.");
        }
        Team team = findTeam(input);
        ArrayList<User> teamMembers = team.getTeamMembers();
        ArrayList<String> membersNames = new ArrayList<>();
        for (User member : teamMembers){
            membersNames.add(member.getUsername());
        }

        BundleBracketData bundleBracketData = new BundleBracketData();
        bundleBracketData.bundleBracket(this.brackets.getBracket(bracketID));

        JoinTeamOD outputData = new JoinTeamOD(success, membersNames, bundleBracketData.getTeamToPlayers());
        return outputBoundary.SuccessView(outputData);
    }
}

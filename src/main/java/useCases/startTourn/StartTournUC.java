package useCases.startTourn;

import entities.Bracket;
import entities.Game;
import entities.User;
import entities.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StartTournUC implements StartTournIB{
    public StartTournOB outputBoundary;
    public Bracket bracket;
    public User user;

    public StartTournUC(StartTournOB outputBoundary){
        this.outputBoundary = outputBoundary;
    }

    public void findBracket(StartTournID inputData) {
        this.bracket = inputData.getBracket();
    }

    public void findUser(StartTournID inputdata) {
        this.user = inputdata.getUser();
    }

    public boolean checkUserRole(StartTournID inputData) {
        return (Objects.equals(inputData.getUserRole(), "Overseer"));
    }

    public boolean checkNumTeams(StartTournID inputData) {
        List<Team> teams = inputData.getTeams();
        for (Team team : teams) {
            if (Objects.equals(team.getTeamName(), "Default Team")) {
                return false;
            }
        }
        return true;
    }


    public boolean checkTeamFull(StartTournID inputData) {
        return (inputData.getMaxNumTeams() == inputData.getTeams().size());
    }

    public  boolean checkGameObserver(StartTournID inputData) {
        return helperCheckGameObserver(inputData.getFinalGame());
    }

    // Recursive helper method for checkGameObserver.
    public boolean helperCheckGameObserver(Game game) {
        if (game.getObserver() == null) {
            return false;
        } else if (game.getPrevGame1() == null) {
            return true;
        } else {
            return (helperCheckGameObserver(game.getPrevGame1()) &&
                    helperCheckGameObserver(game.getPrevGame2()));
        }
    }

    // TODO: implement the check part after finishing front end.
    /// TODO: after returning the error message to the user, we want to know whether the user still wants to
    /// start the tournament anyway. but for now I don't know how to implement that so I'm just leaving it.
    @Override
    public StartTournOD startTourn(StartTournID inputData) {
        findBracket(inputData);
//        if (!checkUserRole(inputData)) {
//            return this.outputBoundary.presentError("You do not have permission to start the tournament.");
//        }
//
//        if (!checkNumTeams(inputData)) {
//            return this.outputBoundary.presentError("The number of teams is invalid.");
//        }
//
//        if (!checkGameObserver(inputData)) {
//              return this.outputBoundary.presentError("There is at least one game that does not have an observer assigned.");
//        }
//
//        if (!checkTeamFull(inputData)) {
//              return this.outputBoundary.presentError("The teams are not full.");
//        }

        inputData.getBracket().setTournamentCondition(true);

        StartTournOD outputData = new StartTournOD(this.bracket, this.user);
        return this.outputBoundary.presentSuccess(outputData);
    }
}

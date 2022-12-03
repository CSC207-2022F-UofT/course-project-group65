package AssignObserver;

import entities.*;
import interface_adapters.assign_observer.AssignObserverFailed;
import interface_adapters.assign_observer.AssignObserverPresenter;
import org.junit.Before;
import org.junit.Test;

import use_cases.assign_observer.*;
import use_cases.general_classes.InformationRecord;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class AssignObserverTest {
    InformationRecord info;

    @Before
    public void setup(){
        User user = new DefaultUser();
        user.setUsername("tester");
        user.addTournament(1);
        user.setCurrentTournament(1);

        User assignee = new DefaultUser();
        assignee.setUsername("assignee");
        assignee.addTournament(1);

        Team team1 = new DefaultTeam();
        team1.setTeamName("team1");
        team1.setTeamSize(2);

        Team team2 = new DefaultTeam();
        team2.setTeamName("team2");
        team2.setTeamSize(2);

        Game finalGame = new DefaultGame();
        finalGame.setGameID(1);
        finalGame.setGameRound(1);
        finalGame.setGameStatus(true);
        finalGame.setWinner(team1);
        finalGame.setTeam(team1, 1);
        finalGame.setTeam(team2, 0);

        Bracket bracket = new DefaultBracket();
        bracket.setTournamentID(1);
        bracket.setTournamentName("test");
        bracket.setPlayerInvite();
        bracket.setObserverInvite();
        bracket.setTeamSize(2);
        bracket.setWinCondition(1);
        bracket.setFinalGame(finalGame);
        bracket.addTeam(team1);
        bracket.addTeam(team2);

        AccountRepo accounts = new AccountRepo();
        BracketRepo brackets = new BracketRepo();

        accounts.addUser(assignee);
        accounts.addUser(user);
        brackets.addBracket(bracket);
        info = new InformationRecord(accounts, brackets);
    }

    @Test
    public void AssignObserverNoPermission(){


        AssignObserverGateway testGateway = new AssignObserverInMemoryBracket();

        AssignObserverOB presenter = new AssignObserverPresenter(){
            @Override
            public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
                fail("You do not have permission to preform this action.");
                return null;
            }

            @Override
            public AssignObserverOD prepareFailView(String error) {
                throw new AssignObserverFailed(error);
            }
        };
        info.getUser("tester").setBracketRole(1, "Player");

        AssignObserverIB interactor = new AssignObserverUC(presenter, testGateway, info, "tester");
        AssignObserverID inputData = new AssignObserverID("assignee", 1);

        Exception exception = assertThrows(AssignObserverFailed.class, () ->
                interactor.assignObserver(inputData));
        assertEquals("You do not have permission to preform this action.", exception.getMessage());
    }

    @Test
    public void AssignObserverNotObserver(){
        AssignObserverGateway testGateway = new AssignObserverInMemoryBracket();

        AssignObserverOB presenter = new AssignObserverPresenter(){
            @Override
            public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
                fail("Assignee is not an Observer.");
                return null;
            }

            @Override
            public AssignObserverOD prepareFailView(String error) {
                throw new AssignObserverFailed(error);
            }
        };
        info.getUser("tester").setBracketRole(1, "Overseer");
        info.getBracket(1).addReferee(info.getUser("tester"));
        info.getUser("assignee").setBracketRole(1, "Player");

        AssignObserverIB interactor = new AssignObserverUC(presenter, testGateway, info, "tester");
        AssignObserverID inputData = new AssignObserverID("assignee", 1);

        Exception exception = assertThrows(AssignObserverFailed.class, () ->
                interactor.assignObserver(inputData));
        assertEquals("Assignee is not an Observer.", exception.getMessage());
    }

    @Test
    public void AssignObserverGameDNE(){
        AssignObserverGateway testGateway = new AssignObserverInMemoryBracket();

        AssignObserverOB presenter = new AssignObserverPresenter(){
            @Override
            public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
                fail("Game ID is invalid.");
                return null;
            }

            @Override
            public AssignObserverOD prepareFailView(String error) {
                throw new AssignObserverFailed(error);
            }
        };
        info.getUser("tester").setBracketRole(1, "Overseer");
        info.getBracket(1).addReferee(info.getUser("tester"));
        info.getUser("assignee").setBracketRole(1, "Observer");
        info.getBracket(1).addReferee(info.getUser("assignee"));

        AssignObserverIB interactor = new AssignObserverUC(presenter, testGateway, info, "tester");
        AssignObserverID inputData = new AssignObserverID("assignee", 5);

        Exception exception = assertThrows(AssignObserverFailed.class, () ->
                interactor.assignObserver(inputData));
        assertEquals("Game ID is invalid.", exception.getMessage());
    }

    @Test
    public void AssignObserverPass(){


        AssignObserverGateway testGateway = new AssignObserverInMemoryBracket();

        AssignObserverOB presenter = new AssignObserverPresenter(){
            @Override
            public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
                LinkedHashMap<Integer, String> gr = new LinkedHashMap<>();
                gr.put(1, "assignee");

                assertEquals(1, outputData.getGameID());
                assertEquals("assignee", outputData.getAssignee());
                assertEquals(gr, outputData.getGameToReferee());
                return null;
            }

            @Override
            public AssignObserverOD prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        info.getUser("tester").setBracketRole(1, "Overseer");
        info.getBracket(1).addReferee(info.getUser("tester"));
        info.getUser("assignee").setBracketRole(1, "Observer");
        info.getBracket(1).addReferee(info.getUser("assignee"));

        AssignObserverIB interactor = new AssignObserverUC(presenter, testGateway, info, "tester");
        AssignObserverID inputData = new AssignObserverID("assignee", 1);

        interactor.assignObserver(inputData);
    }

    @Test
    public void AssignObserverGameHasObserver(){
        AssignObserverGateway testGateway = new AssignObserverInMemoryBracket();

        AssignObserverOB presenter = new AssignObserverPresenter(){
            @Override
            public AssignObserverOD prepareSuccessView(AssignObserverOD outputData) {
                fail("Game already has an Observer.");
                return null;
            }

            @Override
            public AssignObserverOD prepareFailView(String error) {
                throw new AssignObserverFailed(error);
            }
        };
        info.getUser("tester").setBracketRole(1, "Overseer");
        info.getBracket(1).addReferee(info.getUser("tester"));
        info.getBracket(1).getGame(1).setObserver(info.getUser("tester"));
        info.getUser("assignee").setBracketRole(1, "Observer");
        info.getBracket(1).addReferee(info.getUser("assignee"));

        AssignObserverIB interactor = new AssignObserverUC(presenter, testGateway, info, "tester");
        AssignObserverID inputData = new AssignObserverID("assignee", 1);

        Exception exception = assertThrows(AssignObserverFailed.class, () ->
                interactor.assignObserver(inputData));
        assertEquals("Game already has an Observer.", exception.getMessage());
    }
}

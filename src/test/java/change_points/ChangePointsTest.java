package change_points;

import entities.*;
import interface_adapters.change_points.ChangePointsFailed;
import interface_adapters.change_points.ChangePointsPresenter;
import org.junit.Before;
import org.junit.Test;
import use_cases.change_points.*;
import use_cases.general_classes.InformationRecord;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ChangePointsTest {

    InformationRecord info;

    @Before
    public void setup(){

        User overseer = new DefaultUser();
        overseer.setUsername("overseer");
        overseer.setPassword("password");

        User observer = new DefaultUser();
        observer.setUsername("observer");
        observer.setPassword("password");

        User player = new DefaultUser();
        player.setUsername("player");
        player.setPassword("password");

        User assignedObserver = new DefaultUser();
        assignedObserver.setUsername("assignedObserver");
        assignedObserver.setPassword("password");

        Team team1 = new DefaultTeam();
        team1.setTeamName("team1");
        team1.setTeamSize(2);

        Team team2 = new DefaultTeam();
        team2.setTeamName("team2");
        team2.setTeamSize(2);

        Team team3 = new DefaultTeam();
        team3.setTeamName("team3");
        team3.setTeamSize(2);

        Team team4 = new DefaultTeam();
        team4.setTeamName("team4");
        team4.setTeamSize(2);

        Team team5 = new DefaultTeam();
        team5.setTeamName("team5");
        team5.setTeamSize(2);

        Team team6 = new DefaultTeam();
        team6.setTeamName("team6");
        team6.setTeamSize(2);

        Game headGame = new DefaultGame();
        headGame.setGameID(1);
        headGame.setGameRound(3);
        headGame.setGameStatus(false);

        Game gameLeft = new DefaultGame();
        gameLeft.setGameID(2);
        gameLeft.setGameRound(2);
        gameLeft.setGameStatus(true);
        gameLeft.setWinner(team1);
        gameLeft.setTeam(team1, 1);
        gameLeft.setTeam(team2, 0);
        gameLeft.setNumTeams(2);
        headGame.setPrevGame1(gameLeft);

        Game gameRight = new DefaultGame();
        gameRight.setGameID(3);
        gameRight.setGameRound(2);
        gameRight.setGameStatus(false);
        gameRight.setObserver(assignedObserver);
        gameRight.setTeam(team3, 1);
        gameRight.setTeam(team4, 0);
        gameRight.setNumTeams(2);
        headGame.setPrevGame2(gameRight);

        Game gameLeftLeft = new DefaultGame();
        gameLeftLeft.setGameID(4);
        gameLeftLeft.setGameRound(1);
        gameLeftLeft.setGameStatus(false);
        gameLeftLeft.setObserver(assignedObserver);
        gameLeftLeft.setTeam(team5, 0);
        gameLeftLeft.setNumTeams(1);
        gameLeft.setPrevGame1(gameLeftLeft);



        Bracket bracket = new DefaultBracket();
        bracket.setTournamentID(1);
        bracket.setTournamentName("test");
        bracket.setTeamSize(2);
        bracket.setWinCondition(1);
        bracket.setFinalGame(headGame);
        bracket.addTeam(team1);
        bracket.addTeam(team2);
        bracket.addTeam(team3);
        bracket.addTeam(team4);
        AccountRepo accounts = new AccountRepo();
        BracketRepo brackets = new BracketRepo();
        accounts.addUser(overseer);
        accounts.addUser(observer);
        accounts.addUser(player);
        accounts.addUser(assignedObserver);
        overseer.setBracketRole(1, "Overseer");
        observer.setBracketRole(1, "Observer");
        player.setBracketRole(1, "Player");
        bracket.setWinCondition(1);
        bracket.setTournamentCondition(true);
        assignedObserver.setBracketRole(1, "Observer");
        brackets.addBracket(bracket);
        info = new InformationRecord(accounts, brackets);
    }

    @Test
    public void testTeamDNE(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "overseer");
        ChangePointsID input = new ChangePointsID(1, 1, "DNE");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("The team you are trying to change points for is not in the game.", exception.getMessage());
    }

    @Test
    public void testTeamAlreadyWon(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "overseer");
        ChangePointsID input = new ChangePointsID(2, 1, "team1");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("This game has already been won.", exception.getMessage());
    }

    @Test
    public void testUserHasNoPermission(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "player");
        ChangePointsID input = new ChangePointsID(3, 1, "team3");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("You do not have permission to change points.", exception.getMessage());
    }

    @Test
    public void testWrongObserver(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "observer");
        ChangePointsID input = new ChangePointsID(4, 1, "team5");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("You are not assigned to this game.", exception.getMessage());
    }

    @Test
    public void testGameDNE(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "overseer");
        ChangePointsID input = new ChangePointsID(5, 1, "team5");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("The game you are trying to change points in is not in the bracket.", exception.getMessage());
    }

    @Test
    public void testInvalidPoints(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "overseer");
        ChangePointsID input = new ChangePointsID(3, 1, "team3");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("The points you are trying to change to are not valid.", exception.getMessage());
    }

    @Test
    public void testNotAllGamesFull(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                fail("Unexpected success");
                return null;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                throw new ChangePointsFailed(errorMessage);
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "overseer");
        ChangePointsID input = new ChangePointsID(4, 1, "team5");
        Exception exception = assertThrows(ChangePointsFailed.class, () -> interactor.changePoints(input));
        assertEquals("Not all games in the round are full. You cannot add points yet", exception.getMessage());
    }

    @Test
    public void testSuccess(){
        ChangePointsGateway gateway = new ChangePointsDummyFileWriter();
        ChangePointsOB presenter = new ChangePointsPresenter(new ChangePointsTestView()){
            @Override
            public ChangePointsOD presentSuccess(ChangePointsOD outputData){
                return outputData;
            }

            @Override
            public ChangePointsOD presentError(String errorMessage){
                fail("Unexpected error");
                return null;
            }
        };

        ChangePointsUC interactor = new ChangePointsUC(presenter, gateway, info, 1, "overseer");
        ChangePointsID input = new ChangePointsID(3, 1, "team4");
        ChangePointsOD output = interactor.changePoints(input);
        assertEquals(output.getGame(), 3);
        assertEquals(output.getPoints(), Arrays.asList(1, 1));
        assertEquals(output.getTeams(), Arrays.asList("team3", "team4"));
    }

}

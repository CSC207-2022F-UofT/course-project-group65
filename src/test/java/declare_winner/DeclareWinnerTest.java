package declare_winner;

import entities.*;
import interface_adapters.declare_winner.DeclareWinnerFailed;
import interface_adapters.declare_winner.DeclareWinnerPresenter;
import org.junit.Before;
import org.junit.Test;
import use_cases.declare_winner.*;
import use_cases.general_classes.InformationRecord;

import static org.junit.jupiter.api.Assertions.*;

public class DeclareWinnerTest {

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
        headGame.setPrevGame1(gameLeft);

        Game gameRight = new DefaultGame();
        gameRight.setGameID(3);
        gameRight.setGameRound(2);
        gameRight.setGameStatus(false);
        gameRight.setTeam(team3, 0);
        gameRight.setTeam(team4, 0);
        headGame.setPrevGame2(gameRight);

        Game gameLeftLeft = new DefaultGame();
        gameLeftLeft.setGameID(4);
        gameLeftLeft.setGameRound(1);
        gameLeftLeft.setGameStatus(false);
        gameLeftLeft.setObserver(assignedObserver);
        gameLeftLeft.setTeam(team5, 1);
        gameLeftLeft.setTeam(team6, 0);
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
        assignedObserver.setBracketRole(1, "Observer");
        brackets.addBracket(bracket);
        info = new InformationRecord(accounts, brackets);
    }

    @Test
    public void UserHasNoPermission(){
        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerDummyFileWriter();
        DeclareWinnerOB presenter = new DeclareWinnerPresenter(new DeclareWinnerTestView()){
            @Override
            public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public DeclareWinnerOD presentError(String errorMessage) {
                throw new DeclareWinnerFailed(errorMessage);
            }
        };

        DeclareWinnerUC interactor = new DeclareWinnerUC(presenter, declareWinnerGateway, info, 1, "player");
        DeclareWinnerID input = new DeclareWinnerID(2);
        Exception exception = assertThrows(DeclareWinnerFailed.class, () -> interactor.setWinner(input));
        assertEquals("You do not have permission to declare a winner for this game.", exception.getMessage());
    }

    @Test
    public void GameAlreadyHasWinner(){
        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerDummyFileWriter();
        DeclareWinnerOB presenter = new DeclareWinnerPresenter(new DeclareWinnerTestView()){
            @Override
            public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public DeclareWinnerOD presentError(String errorMessage) {
                throw new DeclareWinnerFailed(errorMessage);
            }
        };

        DeclareWinnerUC interactor = new DeclareWinnerUC(presenter, declareWinnerGateway, info, 1, "overseer");
        DeclareWinnerID input = new DeclareWinnerID(2);
        Exception exception = assertThrows(DeclareWinnerFailed.class, () -> interactor.setWinner(input));
        assertEquals("This game has already been won.", exception.getMessage());
    }

    @Test
    public void GameNotFinished(){
        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerDummyFileWriter();
        DeclareWinnerOB presenter = new DeclareWinnerPresenter(new DeclareWinnerTestView()){
            @Override
            public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public DeclareWinnerOD presentError(String errorMessage) {
                throw new DeclareWinnerFailed(errorMessage);
            }
        };

        DeclareWinnerUC interactor = new DeclareWinnerUC(presenter, declareWinnerGateway, info, 1, "overseer");
        DeclareWinnerID input = new DeclareWinnerID(3);
        Exception exception = assertThrows(DeclareWinnerFailed.class, () -> interactor.setWinner(input));
        assertEquals("No team has won this game yet.", exception.getMessage());
    }

    @Test
    public void testWrongObserver(){
        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerDummyFileWriter();
        DeclareWinnerOB presenter = new DeclareWinnerPresenter(new DeclareWinnerTestView()){
            @Override
            public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public DeclareWinnerOD presentError(String errorMessage) {
                throw new DeclareWinnerFailed(errorMessage);
            }
        };

        DeclareWinnerUC interactor = new DeclareWinnerUC(presenter, declareWinnerGateway, info, 1, "observer");
        DeclareWinnerID input = new DeclareWinnerID(4);
        Exception exception = assertThrows(DeclareWinnerFailed.class, () -> interactor.setWinner(input));
        assertEquals("You are not assigned to this game.", exception.getMessage());
    }

    @Test
    public void testGameDNE(){
        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerDummyFileWriter();
        DeclareWinnerOB presenter = new DeclareWinnerPresenter(new DeclareWinnerTestView()){
            @Override
            public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public DeclareWinnerOD presentError(String errorMessage) {
                throw new DeclareWinnerFailed(errorMessage);
            }
        };

        DeclareWinnerUC interactor = new DeclareWinnerUC(presenter, declareWinnerGateway, info, 1, "overseer");
        DeclareWinnerID input = new DeclareWinnerID(5);
        Exception exception = assertThrows(DeclareWinnerFailed.class, () -> interactor.setWinner(input));
        assertEquals("This game does not exist.", exception.getMessage());
    }

    @Test
    public void testSuccess(){
        DeclareWinnerGateway declareWinnerGateway = new DeclareWinnerDummyFileWriter();
        DeclareWinnerOB presenter = new DeclareWinnerPresenter(new DeclareWinnerTestView()){
            @Override
            public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData) {
                return outputData;
            }

            @Override
            public DeclareWinnerOD presentError(String errorMessage) {
                fail("Unexpected error");
                return null;
            }
        };

        DeclareWinnerUC interactor = new DeclareWinnerUC(presenter, declareWinnerGateway, info, 1, "assignedObserver");
        DeclareWinnerID input = new DeclareWinnerID(4);
        DeclareWinnerOD output = interactor.setWinner(input);
        assertEquals(4, output.getGameID());
        assertEquals(output.getWinningTeamName(), "team5");
    }

}

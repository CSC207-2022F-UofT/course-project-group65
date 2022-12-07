package advance_team;

import entities.*;
import interface_adapters.advance_team.AdvanceTeamFailed;
import interface_adapters.advance_team.AdvanceTeamPresenter;
import org.junit.Before;
import org.junit.Test;
import use_cases.advance_team.*;
import use_cases.general_classes.InformationRecord;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This is the test class for advanceTeam use case
 */
public class AdvanceTeamTest {
    /**
     * The informationRecord variable used to record all the information
     */
    InformationRecord info;

    /**
     * This method is for setting up all the information needed for testing in the bracket
     */
    @Before
    public void setup() {
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

        Game headGame = new DefaultGame();
        headGame.setGameID(1);
        headGame.setGameRound(2);
        headGame.setGameStatus(false);

        Game gameLeft = new DefaultGame();
        gameLeft.setGameID(2);
        gameLeft.setGameRound(1);
        gameLeft.setGameStatus(true);
        gameLeft.setWinner(team1);
        gameLeft.setObserver(assignedObserver);
        gameLeft.setTeam(team1, 1);
        gameLeft.setTeam(team2, 0);
        headGame.setPrevGame1(gameLeft);

        Game gameRight = new DefaultGame();
        gameRight.setGameID(3);
        gameRight.setGameRound(1);
        gameRight.setGameStatus(false);
        gameRight.setTeam(team3, 0);
        gameRight.setTeam(team4, 0);
        headGame.setPrevGame2(gameRight);


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
        //System.out.println(info);
    }

    /**
     * This method tests when the game is in the final round, whether the output will be correct
     * for the user wants to advance a team
     */
    @Test
    public void testGameInFinalRound() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                throw new AdvanceTeamFailed(errorMessage);
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "overseer");
        AdvanceTeamID input = new AdvanceTeamID(1);
        Exception exception = assertThrows(AdvanceTeamFailed.class, () -> interactor.advanceWinner(input));
        assertEquals("This game is in the final round.", exception.getMessage());

    }

    /**
     * This method tests when the user has no permission to advance a team, whether the output will be correct
     * for the user wants to advance a team
     */
    @Test
    public void testUserHasNoPermission() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                throw new AdvanceTeamFailed(errorMessage);
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "player");
        AdvanceTeamID input = new AdvanceTeamID(2);

        Exception exception = assertThrows(AdvanceTeamFailed.class, () -> interactor.advanceWinner(input));
        assertEquals("You do not have permission to advance this team.", exception.getMessage());
    }

    /**
     * This method tests when the game is not finished, whether the output will be correct
     * for the user wants to advance a team
     */
    @Test
    public void testGameNotFinished() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                throw new AdvanceTeamFailed(errorMessage);
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "overseer");
        AdvanceTeamID input = new AdvanceTeamID(3);

        Exception exception = assertThrows(AdvanceTeamFailed.class, () -> interactor.advanceWinner(input));
        assertEquals("This game has not been completed.", exception.getMessage());
    }

    /**
     * This method tests when the wrong observer wants to advance a team, whether the output will be correct
     */
    @Test
    public void testWrongObserver() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                throw new AdvanceTeamFailed(errorMessage);
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "observer");
        AdvanceTeamID input = new AdvanceTeamID(2);

        Exception exception = assertThrows(AdvanceTeamFailed.class, () -> interactor.advanceWinner(input));
        assertEquals("You are not assigned to this game.", exception.getMessage());
    }

    /**
     * This method tests when the game does not exist, whether the output will be correct
     * for the user wants to advance a team
     */
    @Test
    public void testGameDNE() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                throw new AdvanceTeamFailed(errorMessage);
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "overseer");
        AdvanceTeamID input = new AdvanceTeamID(4);

        Exception exception = assertThrows(AdvanceTeamFailed.class, () -> interactor.advanceWinner(input));
        assertEquals("This game does not exist.", exception.getMessage());
    }

    /**
     * This method tests when the game is not completed, whether the output will be correct
     * for the user wants to advance a team
     */
    @Test
    public void testGameNotCompleted() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                fail("Unexpected success");
                return null;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                throw new AdvanceTeamFailed(errorMessage);
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "overseer");
        AdvanceTeamID input = new AdvanceTeamID(3);

        Exception exception = assertThrows(AdvanceTeamFailed.class, () -> interactor.advanceWinner(input));
        assertEquals("This game has not been completed.", exception.getMessage());
    }

    /**
     * This method tests if all the checker passed, whether we can advance a team successfully
     */
    @Test
    public void testSuccessfulAdvanceGame() {
        AdvanceTeamGateway advanceTeamGateway = new AdvanceTeamDummyFileWriter();
        AdvanceTeamOB presenter = new AdvanceTeamPresenter(new AdvanceTeamTestView()) {
            @Override
            public AdvanceTeamOD presentSuccess(AdvanceTeamOD advanceTeamOD) {
                return advanceTeamOD;
            }

            @Override
            public AdvanceTeamOD presentError(String errorMessage) {
                fail("Unexpected error");
                return null;
            }
        };

        AdvanceTeamUC interactor = new AdvanceTeamUC(presenter, advanceTeamGateway, info, 1, "assignedObserver");
        AdvanceTeamID input = new AdvanceTeamID(2);
        AdvanceTeamOD output = interactor.advanceWinner(input);
        assertEquals(output.getAdvancedGame(), 1);
        assertEquals(output.getGameToTeams().get(1).get(0), "team1");
        assertEquals(output.getGameToScores().get(1).get(0), 0);
    }

}

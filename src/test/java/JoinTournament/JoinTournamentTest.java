package JoinTournament;

import entities.*;
import org.junit.Before;
import org.junit.Test;
import screens.joinTournament.JoinTournamentFailed;
import screens.joinTournament.JoinTournamentPresenter;
import useCases.generalClasses.InformationRecord;
import useCases.joinTournament.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoinTournamentTest {
    InformationRecord info;
    User user;

    @Before
    public void setup(){
        user = new DefaultUser();
        user.setUsername("tester");

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

        accounts.addUser(user);
        brackets.addBracket(bracket);
        info = new InformationRecord(accounts, brackets);
    }

    @Test
    public void joinTournamentRoleDNE(){


        JoinTournamentGateway testGateway = new JoinTournInMemoryBracket();

        JoinTournamentOB presenter = new JoinTournamentPresenter(){
            @Override
            public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
                fail("Role does not exist within tournament.");
                return null;
            }

            @Override
            public JoinTournamentOD prepareFailView(String error) {
                throw new JoinTournamentFailed(error);
            }
        };

        JoinTournamentIB interactor = new JoinTournamentUC(presenter, testGateway, info, "tester");
        JoinTournamentID inputData = new JoinTournamentID("YB1test");

        Exception exception = assertThrows(JoinTournamentFailed.class, () ->
                interactor.joinBracket(inputData));
        assertEquals("Role does not exist within tournament.", exception.getMessage());
    }

    @Test
    public void joinTournamentDNE(){


        JoinTournamentGateway testGateway = new JoinTournInMemoryBracket();

        JoinTournamentOB presenter = new JoinTournamentPresenter(){
            @Override
            public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
                fail("Tournament does not exist.");
                return null;
            }

            @Override
            public JoinTournamentOD prepareFailView(String error) {
                throw new JoinTournamentFailed(error);
            }
        };

        JoinTournamentIB interactor = new JoinTournamentUC(presenter, testGateway, info, "tester");
        JoinTournamentID inputData = new JoinTournamentID("OB4test");

        Exception exception = assertThrows(JoinTournamentFailed.class, () ->
                interactor.joinBracket(inputData));
        assertEquals("Tournament does not exist.", exception.getMessage());
    }

    @Test
    public void joinTournamentPreviouslyJoined(){


        JoinTournamentGateway testGateway = new JoinTournInMemoryBracket();

        JoinTournamentOB presenter = new JoinTournamentPresenter(){
            @Override
            public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public JoinTournamentOD prepareFailView(String error) {
                throw new JoinTournamentFailed(error);
            }
        };

        user.setCurrentTournament(1);
        user.addTournament(1);
        user.setBracketRole(1, "Overseer");

        JoinTournamentIB interactor = new JoinTournamentUC(presenter, testGateway, info, "tester");
        JoinTournamentID inputData = new JoinTournamentID("OB1test");

        Exception exception = assertThrows(JoinTournamentFailed.class, () ->
                interactor.joinBracket(inputData));
        assertEquals("You have already joined this tournament.", exception.getMessage());
    }

    @Test
    public void joinTournamentPlayer(){
        JoinTournamentGateway testGateway = new JoinTournInMemoryBracket();

        JoinTournamentOB presenter = new JoinTournamentPresenter(){
            @Override
            public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
                ArrayList<String> teams = new ArrayList<>();
                teams.add("team1");
                teams.add("team2");
                LinkedHashMap<Integer, ArrayList<String>> gt = new LinkedHashMap<>();
                gt.put(1, teams);

                ArrayList<Integer> score = new ArrayList<>();
                score.add(1);
                score.add(0);
                LinkedHashMap<Integer, ArrayList<Integer>> gs = new LinkedHashMap<>();
                gs.put(1, score);

                LinkedHashMap<Integer, String> gw = new LinkedHashMap<>();
                gw.put(1, "team1");

                LinkedHashMap<String, ArrayList<String>> players = new LinkedHashMap<>();
                players.put("team1", new ArrayList<>());
                players.put("team2", new ArrayList<>());

                ArrayList<String> refs = new ArrayList<>();

                LinkedHashMap<Integer, String> gf = new LinkedHashMap<>();
                gf.put(1, null);

                assertEquals(outputData.getTournamentID(), 1);
                assertEquals(outputData.getUsername(), "tester");
                assertEquals(outputData.getGameToTeams(), gt);
                assertEquals(outputData.getGameToScores(), gs);
                assertEquals(outputData.getGameToWinner(), gw);
                assertEquals(outputData.getTeamToPlayers(), players);
                assertEquals(outputData.getReferees(), refs);
                assertEquals(outputData.getGameToReferee(), gf);
                assertEquals(outputData.getInvite("Player"), "PL1test");
                assertEquals(outputData.getInvite("Observer"), "OB1test");
                assertEquals(outputData.getTournamentName(), "test");
                return null;
            }

            @Override
            public JoinTournamentOD prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        JoinTournamentIB interactor = new JoinTournamentUC(presenter, testGateway, info, "tester");
        JoinTournamentID inputData = new JoinTournamentID("PL1test");

        interactor.joinBracket(inputData);
    }

    @Test
    public void joinTournamentObserver(){
        JoinTournamentGateway testGateway = new JoinTournInMemoryBracket();

        JoinTournamentOB presenter = new JoinTournamentPresenter(){
            @Override
            public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
                ArrayList<String> teams = new ArrayList<>();
                teams.add("team1");
                teams.add("team2");
                LinkedHashMap<Integer, ArrayList<String>> gt = new LinkedHashMap<>();
                gt.put(1, teams);

                ArrayList<Integer> score = new ArrayList<>();
                score.add(1);
                score.add(0);
                LinkedHashMap<Integer, ArrayList<Integer>> gs = new LinkedHashMap<>();
                gs.put(1, score);

                LinkedHashMap<Integer, String> gw = new LinkedHashMap<>();
                gw.put(1, "team1");

                LinkedHashMap<String, ArrayList<String>> players = new LinkedHashMap<>();
                players.put("team1", new ArrayList<>());
                players.put("team2", new ArrayList<>());

                ArrayList<String> refs = new ArrayList<>();
                refs.add("tester");

                LinkedHashMap<Integer, String> gf = new LinkedHashMap<>();
                gf.put(1, null);

                assertEquals(outputData.getTournamentID(), 1);
                assertEquals(outputData.getUsername(), "tester");
                assertEquals(outputData.getGameToTeams(), gt);
                assertEquals(outputData.getGameToScores(), gs);
                assertEquals(outputData.getGameToWinner(), gw);
                assertEquals(outputData.getTeamToPlayers(), players);
                assertEquals(outputData.getReferees(), refs);
                assertEquals(outputData.getGameToReferee(), gf);
                assertEquals(outputData.getInvite("Player"), "PL1test");
                assertEquals(outputData.getInvite("Observer"), "OB1test");
                assertEquals(outputData.getTournamentName(), "test");
                return null;
            }

            @Override
            public JoinTournamentOD prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        JoinTournamentIB interactor = new JoinTournamentUC(presenter, testGateway, info, "tester");
        JoinTournamentID inputData = new JoinTournamentID("OB1test");

        interactor.joinBracket(inputData);
    }
}

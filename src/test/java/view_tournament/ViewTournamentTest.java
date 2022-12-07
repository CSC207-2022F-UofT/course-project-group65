package view_tournament;

import entities.*;
import interface_adapters.view_tournament.ViewTournamentFailed;
import interface_adapters.view_tournament.ViewTournamentPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_cases.general_classes.InformationRecord;
import use_cases.view_tournament.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ViewTournamentTest {
    InformationRecord info;
    User user;

    @BeforeEach
    public void setup() {
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
    public void viewTournamentPreviouslyJoined() {
        ViewTournamentGateway testGateway = new ViewTournamentDummyFileWriter();

        ViewTournamentOB presenter = new ViewTournamentPresenter() {
            @Override
            public ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData) {
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
            public ViewTournamentOD prepareFailView(String error) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };

        user.setCurrentTournament(1);
        user.addTournament(1);
        user.setBracketRole(1, "Overseer");

        ViewTournamentIB interactor = new ViewTournamentUC(presenter, testGateway, info, "tester");
        ViewTournamentID inputData = new ViewTournamentID(1);

        interactor.viewBracket(inputData);
    }

    @Test
    public void viewTournamentNotPreviouslyJoined() {
        ViewTournamentGateway testGateway = new ViewTournamentDummyFileWriter();

        ViewTournamentOB presenter = new ViewTournamentPresenter() {
            @Override
            public ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData) {
                fail("Use case success is unexpected");
                return null;
            }

            @Override
            public ViewTournamentOD prepareFailView(String error) {
                throw new ViewTournamentFailed(error);
            }
        };

        ViewTournamentIB interactor = new ViewTournamentUC(presenter, testGateway, info, "tester");
        ViewTournamentID inputData = new ViewTournamentID(1);

        Exception exception = assertThrows(ViewTournamentFailed.class, () ->
                interactor.viewBracket(inputData));
        assertEquals("You have not joined this tournament yet.", exception.getMessage());
    }
}

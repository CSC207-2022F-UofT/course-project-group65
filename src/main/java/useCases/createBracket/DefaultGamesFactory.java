package useCases.createBracket;

import entities.DefaultGame;
import entities.Game;
import entities.Team;

import java.util.List;

public class DefaultGamesFactory implements GamesFactory {
    /*
     * This is a factory for creating a game.
     */

    public Game getGames(int numTeams, List<Team> teams) {
        if (numTeams == 2) {
            DefaultGame headGame = new DefaultGame();

            headGame.setGameRound(1);
            headGame.setGameID(1);

            headGame.setTeam(teams.get(0), 0);
            headGame.setTeam(teams.get(1), 0);
            headGame.setNumTeams(2);
            return headGame;

        } else if (numTeams == 4) {
            DefaultGame headGame = new DefaultGame();
            DefaultGame leftGame = new DefaultGame();
            DefaultGame rightGame = new DefaultGame();
            headGame.setPrevGame1(leftGame);
            headGame.setPrevGame2(rightGame);

            headGame.setGameRound(2);
            headGame.setGameID(1);

            leftGame.setGameRound(1);
            leftGame.setGameID(2);
            rightGame.setGameRound(1);
            rightGame.setGameID(3);

            leftGame.setTeam(teams.get(0), 0);
            leftGame.setTeam(teams.get(1), 0);
            leftGame.setNumTeams(2);

            rightGame.setTeam(teams.get(2), 0);
            rightGame.setTeam(teams.get(3), 0);
            rightGame.setNumTeams(2);
            return headGame;

        } else if (numTeams == 8) {
            DefaultGame headGame = new DefaultGame();
            DefaultGame leftGame = new DefaultGame();
            DefaultGame rightGame = new DefaultGame();
            headGame.setPrevGame1(leftGame);
            headGame.setPrevGame2(rightGame);
            DefaultGame leftLeftGame = new DefaultGame();
            DefaultGame leftRightGame = new DefaultGame();
            DefaultGame rightLeftGame = new DefaultGame();
            DefaultGame rightRightGame = new DefaultGame();
            leftGame.setPrevGame1(leftLeftGame);
            leftGame.setPrevGame2(leftRightGame);
            rightGame.setPrevGame1(rightLeftGame);
            rightGame.setPrevGame2(rightRightGame);

            headGame.setGameRound(3);
            headGame.setGameID(1);

            leftGame.setGameRound(2);
            leftGame.setGameID(2);
            rightGame.setGameRound(2);
            rightGame.setGameID(3);

            leftLeftGame.setGameRound(1);
            leftLeftGame.setGameID(4);
            leftRightGame.setGameRound(1);
            leftRightGame.setGameID(5);
            rightLeftGame.setGameRound(1);
            rightLeftGame.setGameID(6);
            rightRightGame.setGameRound(1);
            rightRightGame.setGameID(7);

            leftLeftGame.setTeam(teams.get(0), 0);
            leftLeftGame.setTeam(teams.get(1), 0);
            leftLeftGame.setNumTeams(2);
            leftRightGame.setTeam(teams.get(2), 0);
            leftRightGame.setTeam(teams.get(3), 0);
            leftRightGame.setNumTeams(2);
            rightLeftGame.setTeam(teams.get(4), 0);
            rightLeftGame.setTeam(teams.get(5), 0);
            rightLeftGame.setNumTeams(2);
            rightRightGame.setTeam(teams.get(6), 0);
            rightRightGame.setTeam(teams.get(7), 0);
            rightRightGame.setNumTeams(2);
            return headGame;

        } else if (numTeams == 16) {
            DefaultGame headGame = new DefaultGame();
            DefaultGame leftGame = new DefaultGame();
            DefaultGame rightGame = new DefaultGame();
            headGame.setPrevGame1(leftGame);
            headGame.setPrevGame2(rightGame);
            DefaultGame leftLeftGame = new DefaultGame();
            DefaultGame leftRightGame = new DefaultGame();
            DefaultGame rightLeftGame = new DefaultGame();
            DefaultGame rightRightGame = new DefaultGame();
            leftGame.setPrevGame1(leftLeftGame);
            leftGame.setPrevGame2(leftRightGame);
            rightGame.setPrevGame1(rightLeftGame);
            rightGame.setPrevGame2(rightRightGame);
            DefaultGame leftLeftLeftGame = new DefaultGame();
            DefaultGame leftLeftRightGame = new DefaultGame();
            DefaultGame leftRightLeftGame = new DefaultGame();
            DefaultGame leftRightRightGame = new DefaultGame();
            DefaultGame rightLeftLeftGame = new DefaultGame();
            DefaultGame rightLeftRightGame = new DefaultGame();
            DefaultGame rightRightLeftGame = new DefaultGame();
            DefaultGame rightRightRightGame = new DefaultGame();
            leftLeftGame.setPrevGame1(leftLeftLeftGame);
            leftLeftGame.setPrevGame2(leftLeftRightGame);
            leftRightGame.setPrevGame1(leftRightLeftGame);
            leftRightGame.setPrevGame2(leftRightRightGame);
            rightLeftGame.setPrevGame1(rightLeftLeftGame);
            rightLeftGame.setPrevGame2(rightLeftRightGame);
            rightRightGame.setPrevGame1(rightRightLeftGame);
            rightRightGame.setPrevGame2(rightRightRightGame);

            headGame.setGameRound(4);
            headGame.setGameID(1);

            leftGame.setGameRound(3);
            leftGame.setGameID(2);
            rightGame.setGameRound(3);
            rightGame.setGameID(3);

            leftLeftGame.setGameRound(2);
            leftLeftGame.setGameID(4);
            leftRightGame.setGameRound(2);
            leftRightGame.setGameID(5);
            rightLeftGame.setGameRound(2);
            rightLeftGame.setGameID(6);
            rightRightGame.setGameRound(2);
            rightRightGame.setGameID(7);

            leftLeftLeftGame.setGameRound(1);
            leftLeftLeftGame.setGameID(8);
            leftLeftRightGame.setGameRound(1);
            leftLeftRightGame.setGameID(9);
            leftRightLeftGame.setGameRound(1);
            leftRightLeftGame.setGameID(10);
            leftRightRightGame.setGameRound(1);
            leftRightRightGame.setGameID(11);
            rightLeftLeftGame.setGameRound(1);
            rightLeftLeftGame.setGameID(12);
            rightLeftRightGame.setGameRound(1);
            rightLeftRightGame.setGameID(13);
            rightRightLeftGame.setGameRound(1);
            rightRightLeftGame.setGameID(14);
            rightRightRightGame.setGameRound(1);
            rightRightRightGame.setGameID(15);

            leftLeftLeftGame.setTeam(teams.get(0), 0);
            leftLeftLeftGame.setTeam(teams.get(1), 0);
            leftLeftLeftGame.setNumTeams(2);
            leftLeftRightGame.setTeam(teams.get(2), 0);
            leftLeftRightGame.setTeam(teams.get(3), 0);
            leftLeftRightGame.setNumTeams(2);
            leftRightLeftGame.setTeam(teams.get(4), 0);
            leftRightLeftGame.setTeam(teams.get(5), 0);
            leftRightLeftGame.setNumTeams(2);
            leftRightRightGame.setTeam(teams.get(6), 0);
            leftRightRightGame.setTeam(teams.get(7), 0);
            leftRightRightGame.setNumTeams(2);
            rightLeftLeftGame.setTeam(teams.get(8), 0);
            rightLeftLeftGame.setTeam(teams.get(9), 0);
            rightLeftLeftGame.setNumTeams(2);
            rightLeftRightGame.setTeam(teams.get(10), 0);
            rightLeftRightGame.setTeam(teams.get(11), 0);
            rightLeftRightGame.setNumTeams(2);
            rightRightLeftGame.setTeam(teams.get(12), 0);
            rightRightLeftGame.setTeam(teams.get(13), 0);
            rightRightLeftGame.setNumTeams(2);
            rightRightRightGame.setTeam(teams.get(14), 0);
            rightRightRightGame.setTeam(teams.get(15), 0);
            rightRightRightGame.setNumTeams(2);
            return headGame;
        }
        return null;
    }
}


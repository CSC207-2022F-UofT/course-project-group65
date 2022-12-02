package useCases.createBracket;

import entities.DefaultGame;
import entities.Game;
import entities.Team;

import java.util.*;

public class DefaultGamesFactory implements GamesFactory {
    /*
     * This is a factory for creating a game.
     */

    public void insertValue(Game root, int id) {
        Queue<Game> queue = new LinkedList<>();
        queue.add(root);
        root.setGameID(1);
        DefaultGame newGame = new DefaultGame();

        while (!queue.isEmpty()) {
            root = queue.peek();
            queue.remove();

            if (root.getPrevGame1() == null) {
                root.setPrevGame1(newGame);
                newGame.setGameID(id+1);

                break;
            } else
                queue.add(root.getPrevGame1());

            if (root.getPrevGame2() == null) {
                root.setPrevGame2(newGame);
                newGame.setGameID(id+1);
                break;
            } else
                queue.add(root.getPrevGame2());
        }
    }

    public void setRounds(Game root, int rounds){
        if (root.getPrevGame1() == null && root.getPrevGame2() == null)
            root.setGameRound(rounds);
        else {
            root.setGameRound(rounds);
            setRounds(root.getPrevGame1(), rounds - 1);
            setRounds(root.getPrevGame2(), rounds - 1);
        }
    }

    public void setTeams(Game root, List<Team> teams){
        if (root.getPrevGame1() == null && root.getPrevGame2() == null) {
            root.setTeam(teams.get(1), 0);
            root.setTeam(teams.get(0), 0);
            root.setNumTeams(2);
            teams.remove(0);
            teams.remove(0);
        }
        else {
            setTeams(root.getPrevGame1(), teams);
            setTeams(root.getPrevGame2(), teams);
        }
    }

    public DefaultGame getGames(int numTeams, List<Team> teams)
    {
        DefaultGame root = new DefaultGame();
        int rounds = (int) (Math.log(numTeams) / Math.log(2));
        int numGames = (int) (Math.pow(2, rounds) - 1);
        for (int i = 1; i < numGames; i++) {
            insertValue(root, i);
        }
        setRounds(root, rounds);
        List<Team> clonedTeams = new ArrayList<>(teams);
        setTeams(root, clonedTeams);
        return root;
    }
}


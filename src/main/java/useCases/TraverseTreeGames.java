package useCases;


// Make this into the strategy pattern

public class TraverseTreeGames {
    public Game head;

    public TraverseTreeGames(Game head, int roundNum) {
        this.head = head;
        this.roundNum = roundNum;
    }

    public List<Game> traverseTreeGames(Game game, int roundNum) {
        List<Game> games = new ArrayList<>();
        if (root == null) {
            return games;
        }
        if (roundNum == 1) {
            games.add(game);
            return games;
        } else if (roundNum > 1) {
            games.addAll(traverseTreeGames(game.getPrevGame1(), roundNum - 1));
            games.addAll(traverseTreeGames(game.getPrevGame2(), roundNum - 1));
        }
        return games;
    }
}

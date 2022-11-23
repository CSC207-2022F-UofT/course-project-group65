package entities;

import java.io.Serializable;

public class DefaultBracket extends Bracket implements Serializable {
    public DefaultBracket() {
        super();
    }

    @Override
    public int getNumRounds() {
        return (int) (Math.log(teams.size())/Math.log(2));
    }
}


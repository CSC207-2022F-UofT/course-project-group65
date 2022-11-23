package useCases.advanceTeam;

import entities.Bracket;

public interface AdvanceTeamIB<T extends Bracket> {
    AdvanceTeamOD advanceWinner(AdvanceTeamID advanceTeamID);
}

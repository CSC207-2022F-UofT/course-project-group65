package entities;

import java.util.ArrayList;
/**
 * This interface represents a team in a Game.
 */
public interface Team {
    /** The getter and setter methods */
    String getTeamName();

    void setTeamName(String teamName);

    int getTeamSize();

    void setTeamSize(int teamSize);

    ArrayList<User> getTeamMembers();

    /** The abstract method for add a team member to team's member list */
    void addTeamMember(User teamMember);
}

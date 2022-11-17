package entities;

import java.util.ArrayList;
public interface Team {
    /*
     * This interface represents a team in a Game.
     */
    String getTeamName();

    void setTeamName(String teamName);

    int getTeamSize();

    void setTeamSize(int teamSize);

    ArrayList<User> getTeamMembers();

    void addTeamMember(User teamMember);
}

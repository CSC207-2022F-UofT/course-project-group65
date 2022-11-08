package entities;

import java.util.List;
public interface Team {
    /*
     * This interface represents a team in a Game.
     */
    String getTeamName();

    void setTeamName(String teamName);

    int getTeamSize();

    void setTeamSize(int teamSize);

    List<User> getTeamMembers();

    void addTeamMember(User teamMember);
}

package join_team;

import entities.*;
import interface_adapters.data_interface_adapters.join_team_data.JoinTeamFileWriter;
import interface_adapters.join_team.JoinTeamFailed;
import interface_adapters.join_team.JoinTeamPresenter;
import org.junit.Test;
import use_cases.general_classes.InformationRecord;
import use_cases.join_team.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

/**
 * This tests if a player can join an existed team with the input data teamName
 */
public class JoinTeamTest {

    @Test
    public void testJoinATeam() {
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);
        DefaultUser joiner = new DefaultUser();
        joiner.setUsername("t2");
        newAR.addUser(joiner);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("team1");
        newTeam.setTeamSize(2);

        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        joiner.setBracketRole(1, "Player");
        newTeam.addTeamMember(creator);

        JoinTeamOB outputBoundary = new JoinTeamPresenter(){
            @Override
            public JoinTeamOD SuccessView(JoinTeamOD outputData) {
                ArrayList<String> expected = new ArrayList<>();
                expected.add("t1");
                expected.add("t2");
                LinkedHashMap<String, ArrayList<String>> gr = new LinkedHashMap<>();
                gr.put("team1", expected );

                assertEquals(expected,outputData.getMembersNames());
                assertEquals(gr, outputData.getTeamToPlayers());
                return null;
            }

            @Override
            public JoinTeamOD FailView(String errorMessage) {
                fail("Use case failure is unexpected.");
                return null;
            }
        };
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        JoinTeamUC joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "t2", 1, newInfoRec);
        JoinTeamID inputData = new JoinTeamID("team1");

        joinTeamUC.joinTeam(inputData);
    }

    /**
     * This tests the team exists check in the use case
     */
    @Test
    public void testTeamExistence() {
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);
        DefaultUser joiner = new DefaultUser();
        joiner.setUsername("t2");
        newAR.addUser(joiner);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam trueTeam = new DefaultTeam();
        trueTeam.setTeamName("team1");
        trueTeam.setTeamSize(2);
        newBracket.addTeam(trueTeam);
        DefaultTeam falseTeam = new DefaultTeam();
        falseTeam.setTeamName("BlankTeam2");
        falseTeam.setTeamSize(2);
        newBracket.addTeam(falseTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        joiner.setBracketRole(1, "Player");
        trueTeam.addTeamMember(creator);

        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        InformationRecord newInfoRec = new InformationRecord(newAR, newBR);
        JoinTeamUC joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "t2", 1, newInfoRec);
        JoinTeamID inputData1 = new JoinTeamID("team1");
        JoinTeamID inputData2 = new JoinTeamID("BlankTeam2");


        boolean existence1 = joinTeamUC.checkTeamExistence(inputData1);
        boolean existence2 = joinTeamUC.checkTeamExistence(inputData2);

        assertTrue(existence1);
        assertFalse(existence2);
    }

    /**
     * This tests the player is already in a team, and he still wants to join a team
     */
    @Test
    public void testAlreadyInTeam() {
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("team1");
        newTeam.setTeamSize(2);

        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        newTeam.addTeamMember(creator);

        InformationRecord info = new InformationRecord(newAR, newBR);

        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        JoinTeamIB joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "t1", 1, info);
        JoinTeamID inputData = new JoinTeamID("team1");

        Exception exception = assertThrows(JoinTeamFailed.class, () ->
                joinTeamUC.joinTeam(inputData));
        assertEquals("Fail to join the team (You are already in a team)", exception.getMessage());
    }

    /**
     * This tests the user who is not a player wants to join a team
     */
    @Test
    public void joinNotAPlayer() {
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);
        DefaultUser joiner = new DefaultUser();
        joiner.setUsername("t2");
        newAR.addUser(joiner);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("team1");
        newTeam.setTeamSize(2);

        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        joiner.setBracketRole(1, "Observer");
        newTeam.addTeamMember(creator);

        InformationRecord info = new InformationRecord(newAR, newBR);

        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        JoinTeamIB joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "t2", 1, info);
        JoinTeamID inputData = new JoinTeamID("team1");

        Exception exception = assertThrows(JoinTeamFailed.class, () ->
                joinTeamUC.joinTeam(inputData));
        assertEquals("Fail to join the team (Only player can join the team)", exception.getMessage());
    }

    /**
     * This tests the user wants to join a team which is already full
     */
    @Test
    public void joinFullTeam() {
        JoinTeamGateway gateway = new JoinTeamFileWriter("tests.txt");

        AccountRepo newAR = new AccountRepo();
        DefaultUser creator = new DefaultUser();
        creator.setUsername("t1");
        newAR.addUser(creator);
        DefaultUser joiner = new DefaultUser();
        joiner.setUsername("t2");
        newAR.addUser(joiner);

        BracketRepo newBR = new BracketRepo();
        DefaultBracket newBracket = new DefaultBracket();
        newBracket.setTournamentID(1);
        DefaultTeam newTeam = new DefaultTeam();
        newTeam.setTeamName("team1");
        newTeam.setTeamSize(1);

        newBracket.addTeam(newTeam);
        newBR.addBracket(newBracket);

        creator.setBracketRole(1, "Player");
        joiner.setBracketRole(1, "Player");
        newTeam.addTeamMember(creator);

        InformationRecord info = new InformationRecord(newAR, newBR);

        JoinTeamOB outputBoundary = new JoinTeamPresenter();
        JoinTeamIB joinTeamUC = new JoinTeamUC(outputBoundary, gateway, "t2", 1, info);
        JoinTeamID inputData = new JoinTeamID("team1");

        Exception exception = assertThrows(JoinTeamFailed.class, () ->
                joinTeamUC.joinTeam(inputData));
        assertEquals("Fail to join the team (The team is already full)", exception.getMessage());
    }
}


package interface_adapters;

import use_cases.general_classes.InformationRecord;
import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * This class serves as a general UI controller in a sense. It is used to store the data that is needed to be passed to
 * the next screen and occasionally used to pass data from the presenter to the UI controller. Part of a singleton
 * design pattern.
 */
public class NextScreenData {
    private String currentUser;
    private int currentBracketID;
    private BundleBracketData bundleBracketData;
    private final InformationRecord informationRecord;

    public NextScreenData(InformationRecord informationRecord) {
        this.currentUser = "";
        this.currentBracketID = 0;
        this.bundleBracketData = new BundleBracketData();
        this.informationRecord = informationRecord;

    }

    // Setters and getters for a number of different pieces of information for the UI to function.
    public InformationRecord getInformationRecord() {
        return informationRecord;
    }

    public void bundleData(){
        this.bundleBracketData = new BundleBracketData();
        this.bundleBracketData.bundleBracket(this.informationRecord.getBracket(this.currentBracketID));
    }

    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams() {
        return this.bundleBracketData.getGameToTeams();
    }

    public LinkedHashMap<Integer, ArrayList<Integer>> getGameToScores() {
        return this.bundleBracketData.getGameToScores();
    }

    public LinkedHashMap<Integer, String> getGameToWinner() {
        return this.bundleBracketData.getGameToWinner();
    }

    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers() {
        return this.bundleBracketData.getTeamToPlayers();
    }

    public ArrayList<String> getReferees() {
        return this.bundleBracketData.getReferees();
    }

    public LinkedHashMap<Integer, String> getGameToReferee() {
        return this.bundleBracketData.getGameToReferee();
    }

    public LinkedHashMap<String, String> getRoleToInvite() {
        return this.bundleBracketData.getRoleToInvite();
    }

    public String getTournamentName() {
        return this.bundleBracketData.getTournamentName();
    }

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    public String getCurrentUser() {
        return this.currentUser;
    }

    public ArrayList<Integer> getUsersTournaments(String username) {
        return this.informationRecord.getUserTournaments(username);
    }

    public int getMaxTeamSize(int bracketID) {
        return this.informationRecord.getMaxTeamSize(bracketID);
    }

    public void setCurrentBracketID(int bracketID) {
        this.currentBracketID = bracketID;
    }

    public int getCurrentBracketID() {
        return this.currentBracketID;
    }

}

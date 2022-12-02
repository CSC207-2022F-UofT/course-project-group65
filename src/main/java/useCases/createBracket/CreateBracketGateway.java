package useCases.createBracket;

/** An interface for the gateway of the create bracket use case.
 * This interface is used for dependency inversion, allowing the use case to be independent of the database.
 */
public interface CreateBracketGateway {
    /** Saves the data to the database
     * @param dsid - the data source interface data
     */
    void save(CreateBracketDSID dsid) throws Exception;

}

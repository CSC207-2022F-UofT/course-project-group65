package interface_adapters.view_interfaces.main_view_interfaces;

/**
 * The view interface between declare winner use case and the main view.
 */
public interface DeclareWinnerView {

    void updateWinner(int gameID, String winner);

}

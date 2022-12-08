package interface_adapters.view_interfaces.main_view_interfaces;

import java.util.LinkedHashMap;

/**
 * View interface for the assign observer and extended view.
 */
public interface AssignObserverView {

    void updateObserverAssignments(LinkedHashMap<Integer, String> gameToReferee);
}

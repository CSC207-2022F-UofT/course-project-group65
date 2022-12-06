package use_cases.assign_observer;

/**
 * The input boundary for assign observer.
 */
public interface AssignObserverIB {
    /**
     * Defines the method to be called to activate use case.
     *
     * @param input The input data for the use case.
     * @return The output data for the use case.
     */
    AssignObserverOD assignObserver(AssignObserverID input);
}

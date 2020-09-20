package fa.dfa;

import java.util.HashSet;

import fa.State;

/**
 * Deterministic finite automata object. Includes getters and seters for start and end state
 * charactistics. Has set of possible transitions from the state.
 * @author Luke Ptomey
 * @author Kyle Epperson
 */

public class DFAState extends State {
    
    private HashSet<String> statesItCanGoTo;
    private boolean isEndState, isStartState;
    
    /**
     * DFA State Constructor
     * @param name is name of state 
     */
    public DFAState(String name) {
        this.name = name;
        statesItCanGoTo = new HashSet<String>();
        isEndState = isStartState = false;
    }

    /**
     * Check whether state can transition to given state
     * @param toState is destination state
     * @return true or false based on whether state can transition to given state
     */
    public boolean checkIfCanGoTo(State toState){
        return statesItCanGoTo.contains(toState);

        //more implementation??
    }

    /**
     * Appends to list of possible states this state can transition to.
     *  (Includes symbol needed to reach destination state)
     * @param toState is destination state
     * @param symbol is symbol needed to reach destination state
     */
    public void addToCanGoToList(String toState, char symbol) {
        String stateAndSymbol = toState + " " + symbol;
        statesItCanGoTo.add(stateAndSymbol);
    }

    /**
     * Setter for whether state is start state
     */
    public void setStateState() {
        isStartState = true;
    }

    /**
     * Gets whether state is start state
     * @return true if start state and false if not
     */
    public boolean getStateState() {
        return isStartState;
    }

    /**
     * Setter for whether state is end state
     */
    public void setEndState() {
        isEndState = true;
    }

    /**
     * Gets whether state is end state
     * @return true if end state and false if not
     */
    public boolean getEndState() {
        return isEndState;
    }


}

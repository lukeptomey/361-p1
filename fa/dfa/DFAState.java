package fa.dfa;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import fa.State;

/**
 * Deterministic finite automata object. Includes getters and seters for start and end state
 * charactistics. Has set of possible transitions from the state.
 * @author Luke Ptomey
 * @author Kyle Epperson
 */

public class DFAState extends State {
    
    private HashMap<Character, String> transitionStates;
    private boolean isEndState, isStartState;
    
    /**
     * DFA State Constructor
     * @param name is name of state 
     */
    public DFAState(String name) {
        this.name = name;
        transitionStates = new HashMap<Character, String>();
        isEndState = isStartState = false;
    }

    /**
     * Check whether state can transition to given state
     * @param toState is destination state
     * @return true or false based on whether state can transition to given state
     */
    public boolean checkIfValidTransition(Character keySymbol){
        return transitionStates.containsKey((Character)keySymbol);
    }

    /**
     * Appends to list of possible states this state can transition to.
     *  (Includes symbol needed to reach destination state)
     * @param toState is destination state
     * @param symbol is symbol needed to reach destination state
     */
    public void addTransitionToState(String toState, char onSymb) {
        transitionStates.put(Character.valueOf(onSymb), toState);
    }

    /**
     * Setter for whether state is start state
     */
    public void setStartState(boolean value) {
        isStartState = value;
    }

    /**
     * Gets whether state is start state
     * @return true if start state and false if not
     */
    public boolean getStartState() {
        return isStartState;
    }

    /**
     * Setter for whether state is end state
     */
    public void setEndState(boolean value) {
        isEndState = value;
    }

    /**
     * Gets whether state is end state
     * @return true if end state and false if not
     */
    public boolean getEndState() {
        return isEndState;
    }

    /**
     * Gets LinkedList of transition states that this node can go to.
     * @return LinkedList of Map Entries of node transitions.
     */
    public LinkedList<Map.Entry<Character,String>> getTransitionStates() {
        LinkedList retval = new LinkedList<Map.Entry<Character,String>>();
        for(Map.Entry<Character, String> entry : transitionStates.entrySet()){
            retval.add(entry);
        }

        return retval;
    }


}

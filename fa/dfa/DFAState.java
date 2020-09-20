package fa.dfa;

import java.util.HashSet;

import fa.State;

public class DFAState extends State {
    
    private HashSet<String> statesItCanGoTo;
    private boolean isEndState, isStartState;
    
    public DFAState(String name) {
        this.name = name;
        statesItCanGoTo = new HashSet<String>();
        isEndState = isStartState = false;
    }

    public boolean checkIfCanGoTo(State toState){
        return statesItCanGoTo.contains(toState);
    }

    public void addToCanGoToList(String toState, char symbol) {
        String stateAndSymbol = toState + " " + symbol;
        statesItCanGoTo.add(stateAndSymbol);
    }

    public String toString(){
        return statesItCanGoTo.toString();
    }

    public void setStartState() {
        isStartState = true;
    }

    public boolean getStartState() {
        return isStartState;
    }

    public void setEndState() {
        isEndState = true;
    }

    public boolean getEndState() {
        return isEndState;
    }


}

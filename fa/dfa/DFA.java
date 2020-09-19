package fa.dfa;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import fa.State;

public class DFA implements DFAInterface {

    private LinkedHashSet<DFAState> Q;
    // private Map T;

    public DFA() {
        Q = new LinkedHashSet<DFAState>();
        // T = new LinkedHashMap<>();
    }


    @Override
    public void addStartState(String name) {
        DFAState state = new DFAState(name);
        state.setStateState();
        if(!(Q.add(state))){
            // Iterate
        }
    }

    @Override
    public void addState(String name) {
        DFAState state = new DFAState(name);
        Q.add(state);
    }

    @Override
    public void addFinalState(String name) {
        DFAState state = new DFAState(name);
        state.setEndState();
        Q.add(state);

    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {
       // Find fromState in Q.
       // Add method in Q to add transition onSymb and toState.

       Iterator<DFAState> it = Q.iterator();

       while(it.hasNext()){
           DFAState temp = it.next();
           //System.out.println(temp.getName()+ " " + fromState);
           if(temp.getName().equals(fromState)){
               //System.out.println("Adding " + toState);
                temp.addToCanGoToList(toState, onSymb);
                break;
           }
       }



    }

    @Override
    public Set<? extends State> getStates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public State getStartState() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Character> getABC() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DFA complement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean accepts(String s) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public State getToState(DFAState from, char onSymb) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString() {
    /*  // DEBUG: Prints HashSet transitions from States
        Iterator<DFAState> it = Q.iterator();
       while(it.hasNext()){
            DFAState temp = it.next();
            System.out.println(temp.getName() + " -> " + temp.toString());
       }
       System.out.println("Done");
       */
        return Q.toString();
    }


    
}

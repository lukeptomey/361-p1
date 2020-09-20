package fa.dfa;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;
import fa.State;

/**
 * Deterministic finite automata object. Includes add and get methods for states and 
 * transitions. Additionally, it can create the complement of a given DFA and checks
 * if a given string is accepted.
 * @author Luke Ptomey
 * @author Kyle Epperson
 */



public class DFA implements DFAInterface {

    private LinkedHashSet<DFAState> Q;
    // private Map T;
    private HashSet<Character> alphabet;


    /**
     * DFA constructor
     */
    public DFA() {
        Q = new LinkedHashSet<DFAState>();
        // T = new LinkedHashMap<>();
        alphabet = new HashSet<Character>();
    }


    @Override
    public void addStartState(String name) {
        DFAState state = new DFAState(name);
        state.setStartState();
        if(!(Q.add(state))){
            Iterator<DFAState> it = Q.iterator();
            while(it.hasNext()) {
                state = it.next();
                if(state.getName().equals(name)) {
                    state.setStartState();
                }
            }
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

        alphabet.add(onSymb);

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
        Set states = new Set;

        Iterator<DFAState> it = Q.iterator();

        while(it.hasNext()){
            DFASTATE insert =it.next();
            states.insert(temp);
        }
        return states;
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
        return alphabet;
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
        /*
            // DEBUG: Prints HashSet transitions from States
        Iterator<DFAState> it = Q.iterator();
        while(it.hasNext()){
            DFAState temp = it.next();
            System.out.println(temp.getName() + " -> " + temp.toString());
        }
        System.out.println("Done");
        */

        StringBuilder qOutput = new StringBuilder();        // All states
        StringBuilder q0Output = new StringBuilder();       // Start States
        StringBuilder sigmaOutput = new StringBuilder();    // Language
        StringBuilder fOutput = new StringBuilder();        // Final States
        StringBuilder deltaOutput = new StringBuilder();    // Transitions

        // Setup
        qOutput.append("Q = { ");
        fOutput.append("F = { ");
        q0Output.append("q0 = { ");
        sigmaOutput.append("Sigma = { ");

        // Loop through Q getting relevant info about each node
        Iterator<DFAState> it = Q.iterator();
        while(it.hasNext()){
            DFAState current = it.next();
            qOutput.append(current.getName() + " ");

            if(current.getEndState()) {
                fOutput.append(current.getName() + " ");
            }

            if(current.getStartState()) {
                q0Output.append(current.getName() + " ");
            }
        }

        // Loop though alphabet getting characters
        Iterator<Character> abcIt = alphabet.iterator();
        while(abcIt.hasNext()) {
            Character chr = abcIt.next();
            sigmaOutput.append(chr.toString() + " ");
        }

        // Formatting
        qOutput.append("}\n");
        fOutput.append("}\n");
        q0Output.append("}\n");
        sigmaOutput.append("}\n");

        // Builds final output string
        StringBuilder finalOutput = new StringBuilder();
        finalOutput.append(qOutput);
        finalOutput.append(sigmaOutput);
        finalOutput.append(q0Output);
        finalOutput.append(fOutput);

        return finalOutput.toString();
    }


    
}

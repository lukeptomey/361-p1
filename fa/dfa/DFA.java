package fa.dfa;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Iterator;
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
    private HashSet<Character> alphabet;


    /**
     * DFA constructor
     */
    public DFA() {
        Q = new LinkedHashSet<DFAState>();
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

        // Add to alphabet if new symbol.
        alphabet.add(onSymb);

        Iterator<DFAState> it = Q.iterator();

        while(it.hasNext()){
           DFAState temp = it.next();
            if(temp.getName().equals(fromState)){
                temp.addTransitionToState(toState, onSymb);
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

        StringBuilder qOutput = new StringBuilder();        // All states
        StringBuilder q0Output = new StringBuilder();       // Start States
        StringBuilder sigmaOutput = new StringBuilder();    // Language
        StringBuilder fOutput = new StringBuilder();        // Final States
        StringBuilder deltaOutput = new StringBuilder();    // Transitions
        ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
        int row = 0;
        int col = 1;
        int count = 1;

        // Setup
        qOutput.append("Q = { ");
        fOutput.append("F = { ");
        q0Output.append("q0 = ");
        sigmaOutput.append("Sigma = { ");
        deltaOutput.append("delta = \n\t\t");

        // Loop though alphabet getting characters
        Iterator<Character> abcIt = alphabet.iterator();
        a.add(new ArrayList<String>());
        a.get(row).add("");   // Empty space
        while(abcIt.hasNext()) {
            Character chr = abcIt.next();
            sigmaOutput.append(chr.toString() + " ");
            deltaOutput.append(chr.toString() + "\t");
            a.get(row).add(chr.toString());
            count+=1;
        }
        row += 1;

        // Loop through Q getting relevant info about each node
        Iterator<DFAState> it = Q.iterator();
        while(it.hasNext()){
            
            DFAState current = it.next();
            qOutput.append(current.getName() + " ");

            a.add(new ArrayList<String>(count));
            a.get(row).add(current.getName());

            if(current.getEndState()) {
                fOutput.append(current.getName() + " ");
            }

            if(current.getStartState()) {
                q0Output.append(current.getName() + " ");
            }
            
            // Creates 2D delta array
            LinkedList<Map.Entry<Character, String>> transitions = current.getTransitionStates();
           // System.out.println(transitions);
            for (Map.Entry<Character, String> transition : transitions) {
                while(!(a.get(0).get(col).equals(transition.getKey().toString()))) {
                    col +=1;
                }
                a.get(row).add(col, (String)transition.getValue());
                col = 1;
            }
            row += 1;
        }

        // Creates delta output with 2D array
        System.out.println(a);
        for(int i = 1; i < row; i++) {
            deltaOutput.append("\n\t");
            for(int m = 0; m < count; m++) {
                deltaOutput.append(a.get(i).get(m) + "\t");
            }
        }
        

        // Formatting
        qOutput.append("}\n");
        fOutput.append("}\n");
        q0Output.append("\n");
        sigmaOutput.append("}\n");
        deltaOutput.append("\n");

        // Combines for final output string
        StringBuilder finalOutput = new StringBuilder();
        finalOutput.append(qOutput);
        finalOutput.append(sigmaOutput);
        finalOutput.append(deltaOutput);
        finalOutput.append(q0Output);
        finalOutput.append(fOutput);


        return finalOutput.toString();
    }


    
}

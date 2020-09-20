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
    private LinkedHashSet<String> origTrans;


    /**
     * DFA constructor
     */
    public DFA() {
        Q = new LinkedHashSet<DFAState>();
        alphabet = new HashSet<Character>();
        origTrans = new LinkedHashSet<String>();
    }


    @Override
    public void addStartState(String name) {
        DFAState state = new DFAState(name);
        state.setStartState(true);

        // Goes into if state already exists in Q.
        if(!(Q.add(state))){
            Iterator<DFAState> it = Q.iterator();
            while(it.hasNext()) {
                state = it.next();
                if(state.getName().equals(name)) {
                    state.setStartState(true);
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
        state.setEndState(true);
        Q.add(state);

    }

    @Override
    public void addTransition(String fromState, char onSymb, String toState) {

        // Add to alphabet if new symbol. Add transition to list.
        alphabet.add(onSymb);
        origTrans.add(fromState + onSymb + toState);

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
        LinkedHashSet<DFAState> states = new LinkedHashSet<DFAState>();

        Iterator<DFAState> it = Q.iterator();

        while(it.hasNext()){
            DFAState insert =it.next();
            states.add(insert);
        }
        return states;
    }

    @Override
    public Set<? extends State> getFinalStates() {
        LinkedHashSet<DFAState> finalStates = new LinkedHashSet<DFAState>();

        Iterator<DFAState> it = Q.iterator();

        while(it.hasNext()){
            DFAState temp = it.next();

            if(temp.getEndState() == true){
                finalStates.add(temp);
            }
        }
        return finalStates;
    }

    @Override
    public State getStartState() {

        DFAState startState;
        Iterator<DFAState> it = Q.iterator();

        while(it.hasNext()){
            DFAState temp = it.next();

            if(temp.getStartState() == true){
               startState=temp;
               return startState;
            }
        }
        return null;
    }

    @Override
    public Set<Character> getABC() {
        return alphabet;
    }

    @Override
    public DFA complement() {
        DFA complementDFA = new DFA();
        complementDFA.createCompClone(Q, origTrans);

        return complementDFA;
    }

    @Override
    public boolean accepts(String s) {

        State start = getStartState();
        DFAState current = new DFAState("");

        Iterator<DFAState> it = Q.iterator();
        while(it.hasNext()) {
            current = it.next();
            if(current.getName().equals(start.getName())){
                break;
            }
        }

        //compare with transition map entries
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);

            //empty string exception
            if(Character.valueOf(c).equals('e') && current.getEndState()) {
                return true;
            }
            current = (DFAState)getToState(current, c);
            if(current == null){    // null if invalid transition
                return false;
            }
        }

        if(current.getEndState()) {
            return true;
        }

        return false;

    }

    @Override
    public State getToState(DFAState from, char onSymb) {

        LinkedList<Map.Entry<Character,String>> transitions = from.getTransitionStates();

        for(Map.Entry<Character,String> transition : transitions){
            if(transition.getKey().equals((Character)onSymb)) {
                Iterator<DFAState> it = Q.iterator();
                while(it.hasNext()) {
                    DFAState current = it.next();
                    if(current.getName().equals(transition.getValue())) {
                        return current;
                    }
                }
            }
        }
        
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

    /*
     * Called by DFA. Sets up a new DFA clone of its self and applies complement to 
     * start and final states.
     * @param cQ Original Q
     * @param newT original transition values
     */
    private void createCompClone(LinkedHashSet<DFAState> cQ, LinkedHashSet<String> newT) {

        // Create copy of states in Q
        Iterator<DFAState> it3 = cQ.iterator();
        while(it3.hasNext()) {
            DFAState current = it3.next();
            if(current.getEndState()){
                addFinalState(current.getName());
            } else
            if(current.getStartState()) {
                addStartState(current.getName());
            } else
            addState(current.getName());
        }

        // Add transition copy
        Iterator<String> it2 = newT.iterator();
        while(it2.hasNext()) {
            String current = it2.next();
            addTransition(((Character)current.charAt(0)).toString(), current.charAt(1), ((Character)current.charAt(2)).toString());
        }

        // Swap final states of complement
        Set cStates = getStates();
        Iterator<DFAState> it = cStates.iterator();
        while(it.hasNext()) {
            DFAState current = it.next();
       
            current.setEndState(!(current.getEndState()));
        }

    }
    
}

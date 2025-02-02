package fa.dfa;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * January 19, 2017
 * The class reads the input file and instantiates a DFA from it.
 * Next it reads a string from the same file and prints "yes" if the string 
 * is in the DFA's language or "not if the string is not in the DFA's language.
 * @author elenasherman
 *
 */
public class DFADriver {

	/**
	 * @param args - a file name containing a DFA encoding and a set of strings.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//The file name is passed as an argument
		String fileName = args[0];
		File file = new File(fileName);
		//System.out.println(file.exists());
		if(file.exists()){
			//create a DFA instance -- you need to write DFA.java that
			//implements DFAInterface.java
			DFA dfa = new DFA();
			Scanner scan = new Scanner(file);
			//the first line is the set of final states
			//get the string of the final states and split it on a space
			String finalStates = scan.nextLine().trim();
			//using tokenizer to split the string
			StringTokenizer tk = new StringTokenizer(finalStates, " ");
			while(tk.hasMoreTokens()){
				dfa.addFinalState(tk.nextToken());
			}
			
			//the second line is the start state
			String startStateName = scan.nextLine().trim();
			dfa.addStartState(startStateName);

			//get the string of other states and split in on space too
			String otherStates = scan.nextLine().trim();
			tk = new StringTokenizer(otherStates, " ");
			while(tk.hasMoreTokens()){
				dfa.addState(tk.nextToken());
			}

			//read in the transactions
			String trans = scan.nextLine();
			tk = new StringTokenizer(trans, " ");
			while(tk.hasMoreTokens()){
				char[] tran = tk.nextToken().toCharArray();
				dfa.addTransition(String.valueOf(tran[0]), tran[1], String.valueOf(tran[2]));
			}

			//print out dfa in the specified format.
			System.out.println(dfa.toString());
			//get the compliment
			DFA dfaC = dfa.complement();
			boolean correctC = true;
			//now process the input strings
			while(scan.hasNext()){
					String input = scan.nextLine();
					boolean accepts = dfa.accepts(input);
					System.out.println(accepts?"yes":"no");
                    boolean acceptsC = dfaC.accepts(input);
					if(accepts == acceptsC) {
						correctC = false;
					}
			}
			System.out.println("\nIs dfaC correct? : " + (correctC?"yes":"no"));
			scan.close();
		} else {
			System.err.println(file + " does not exists - please check the file path");
		}
	}

}

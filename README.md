# Project 1: (Deterministic Finite Automata)

* Author: Luke Ptomey and Kyle Epperson
* Class: CS361 Section 1
* Semester: Fall 2020

## Overview

This Java application models a deterministic finite automaton using DFA states.

## Compiling and Using

To compile fa.dfa.DFADriver from the top directory of these files:
```
$ javac fa/dfa/DFADriver.java

```

To run fa.dfa.DFADriver (with inputfile):
```
$ java fa.dfa.DFADriver ./tests/p1tc1.txt
```

No user input besides input file in run command.

## Discussion

When first working on the project, we initially had problems creating DFA.java because
we did not determine what each state knew about itself along with possible transitions.
To fix this issue, we used a HashMap to store the transition states. We also stored
whether a state was final or a start states using boolean values.

Once we finished DFAState, we decided to use a LinkedHashSet to store Q and transitions.
Next, we used a HashSet to store the alphabet. We decided these were the best data structures to use
after reviewing the Java API. It was fairly easy to implement the add 
and get methods but we had trouble with the complement method the most.

While working on the complement method, we both had the misconception that the start state was
changed. We spent a long while trying to debug, but we had the concept wrong initially. One other
misconception we had was that we did not realize non-final states had to become final states. We fixed
this issue by iterating through all of the states and flipping the isEndState boolean in DFAState.

Overall, the project was not challenging as anticipated. However, it would have been easier if we
reviewed the concepts taught in class beforehand. This was a good review for complements. We are glad that 
we ran into these problems now before heading into p2. 

## Extra Credit

No extra credit was attempted.

## Sources used

- Java Documentation was researched for reference.
[Java Platform, Standard Edition 8 API Specification](https://docs.oracle.com/javase/8/docs/api/)
 

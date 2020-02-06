# Finite-Automaton-Builder
This project is able to build an automaton with N states and M symbols. Then it takes a series of symbols and checks if the built automaton accepts the series or rejects it.

This is a small class roject to build and test any automaton. 

# Specific Instrucitons
Emulate a general finite automaton. The input to this method should include:

  -The number N of states q0, ..., qN − 1; we assume that q0 is the start state;

  -The number M of symbols s0, ... sM − 1;

  -An integer array state[n][m] that describes to what state the finite automaton moves if it was in the state qn and sees the symbol sm; 

  -A boolean array final[n] that tells us, for each state, whether this state is final or not.

This program needs to keep track of a current state. Initially, this location is 0.
Your program should emulate the work of the finite automaton step-by-step.

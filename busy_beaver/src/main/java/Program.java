import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Program {

    private ArrayList<State> states;

    public Program(ArrayList<State> states) {
        this.setStates(states);
    }

    public Program(State[] states) {
        this.setStates(new ArrayList<State>(Arrays.asList(states)));
    }


    /*
    function run(states){
        var tape = [], tape_pos = 0, current_state = 0, snapshots = []
        do {
            var symbol_under_tape = tape[tape_pos]==1?1:0
            var current_tuple = states[current_state][symbol_under_tape]

            var state_snapshot = JSON.stringify(
                [current_tuple, tape, tape_pos, current_state]
            )
            if(snapshots.indexOf(state_snapshot) != -1)
                return [count_ones(tape), 'repeating']
            else snapshots.push(state_snapshot)

            tape[tape_pos] = current_tuple[0]
            tape_pos += tape_pos_increment[ current_tuple[1] ]
            current_state = current_tuple[2]

            //console.log(tape)
        } while ( current_tuple[2] != h )
        return [count_ones(tape), 'terminated']
    }
    */
    public int run() {
        Program program = this;
        ArrayList<State> states = program.getStates();

        Tape tape = new Tape();
        int currentState = 0;
        HashSet<Snapshot> snapshots = new HashSet<Snapshot>();
        int stepCounter = 0;
        while (currentState >= 0) {

            boolean symbolUnderTape = tape.getSymbolUnderTape();
            Tuple currentTuple = states.get(currentState).getTuples()[symbolUnderTape ? 1 : 0];
            Snapshot snapshot = new Snapshot(currentTuple, tape, states.get(currentState));

            if (true)
                if (snapshots.contains(snapshot))
                    return -1;
                else
                    snapshots.add(snapshot);

            tape.set(tape.getTapePosition(), currentTuple.writeOne);
            try {
                tape.incrementTapePosition(currentTuple.moveRight ? 1 : -1);
            } catch (IndexOutOfBoundsException e) {
                return -2;
            }

            currentState = currentTuple.nextStateIndex;

            stepCounter++;

            // safety measure (disabled)
            if (false && stepCounter == 100000) // could be larger
                return -3; // it should not be called
        }
        return tape.countOnes();
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public void setStates(ArrayList<State> states) {
        this.states = states;
    }
}

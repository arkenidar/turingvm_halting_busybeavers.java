import java.util.HashSet;

public class Main {

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
    private static int run(State[] states){

        Tape tape = new Tape();
        int currentState = 0;
        HashSet<Snapshot> snapshots = new HashSet<>();

        while (currentState >= 0) {

            boolean symbolUnderTape = tape.getSymbolUnderTape();
            Tuple currentTuple = states[currentState].tuples[symbolUnderTape?1:0];
            Snapshot snapshot = new Snapshot(currentTuple, tape, states[currentState]);

            /*
            // debug
            snapshots.add(snapshot);
            assert snapshots.contains(snapshot);
            Snapshot snapshot1 = new Snapshot(currentTuple, tape, states[currentState]);
            assert snapshot1.equals(snapshot);
            assert snapshots.contains(snapshot1);
            */

            if(snapshots.contains(snapshot))
                return -1;
            else
                snapshots.add(snapshot);

            tape.set(tape.tapePosition, currentTuple.writeOne);
            tape.tapePosition += currentTuple.moveRight ? 1 : -1;
            currentState = currentTuple.nextStateIndex;
        }
        return tape.countOnes();
    }

    public static void main(String[] args) {
        /*
        var _3_states_6_ones = [
            [[1, r, B], [1, l, C]],
            [[1, l, A], [1, r, B]],
            [[1, l, B], [1, r, h]],
        ]
        */
        State[] _3_states_6_ones = {
                new State(new Tuple(true, true, 1), new Tuple(true, false, 2)),
                new State(new Tuple(true, false, 0), new Tuple(true, true, 1)),
                new State(new Tuple(true, false, 1), new Tuple(true, true, -1))
        };
        System.out.println("_3_states_6_ones: "+run(_3_states_6_ones));

        /*
        var infinitely_looping_program = [
            [[1, r, B], [0, r, B]],
            [[1, l, A], [1, l, A]],
        ]
        */
        State[] infinitely_looping_program = {
                new State(new Tuple(true, true, 1), new Tuple(false, true, 1)),
                new State(new Tuple(true, false, 0), new Tuple(true, false, 0))
        };
        System.out.println("infinitely_looping_program: "+run(infinitely_looping_program));

        TupleGenerator tupleGenerator = new TupleGenerator(3);
        for (Tuple tuple : tupleGenerator) {
            System.out.println(tuple);
        }

        StateGenerator stateGenerator = new StateGenerator(3);
        for (State state : stateGenerator) {
            System.out.println(state);
        }

    }
}

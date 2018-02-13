import java.util.HashSet;

public class Main {

    public static void main(String[] args) {
        test4();
    }

    public static void test0() {

        // initializations
        HashSet<Snapshot> snapshots = new HashSet<Snapshot>();
        Tuple tuple = new Tuple(false, false, 0);
        State currentState = new State(tuple, tuple);
        Tuple currentTuple = tuple;
        Tape tape = new Tape();
        Snapshot snapshot = new Snapshot(currentTuple, tape, currentState);

        // debug checks
        snapshots.add(snapshot);
        assert snapshots.contains(snapshot);

        Snapshot snapshot1 = new Snapshot(currentTuple, tape, currentState);
        assert snapshot.equals(snapshot1);

        int h1, h2;
        h1 = snapshot.hashCode();
        h2 = snapshot1.hashCode();
        assert h1 == h2;

        assert snapshots.contains(snapshot1);
    }

    public static void test1() {

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
        Program program_3_states_6_ones = new Program(_3_states_6_ones);
        System.out.println("_3_states_6_ones: " + program_3_states_6_ones.run());

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
        System.out.println("infinitely_looping_program: " + (new Program(infinitely_looping_program)).run());
    }

    public static void test2() {

        TupleGenerator tupleGenerator = new TupleGenerator(3);
        for (Tuple tuple : tupleGenerator) {
            System.out.println(tuple);
        }

        StateGenerator stateGenerator = new StateGenerator(3);
        for (State state : stateGenerator) {
            System.out.println(state);
        }
    }

    public static void test3() {

        final int statesCount = 1;
        System.out.printf("Program generation has begun, please wait...\n");
        ProgramGenerator programGenerator = new ProgramGenerator(statesCount);

        int programsCounter = 0;
        final int idealProgramsCount = (int) Math.pow(Math.pow(4 * (statesCount + 1), 2), statesCount);

        final boolean checkForRepetitions = true;
        HashSet<Program> previousPrograms = new HashSet<Program>();

        for (Program program : programGenerator) {

            if (checkForRepetitions) {
                if (previousPrograms.contains(program)) {
                    System.out.println("already there");
                    break;
                } else {
                    previousPrograms.add(program);
                }
            }

            programsCounter++;

            System.out.printf("Programs counter: %d/%d; ", programsCounter, idealProgramsCount);
            System.out.printf("Result: %d; Program: %s;\n", program.run(), program);

        }

        System.out.printf("Terminated: %d/%d\n", programsCounter, idealProgramsCount);

    }

    // busy beaver test
    public static void test4() {

        // 2-state -> 4
        // 3-state -> 6
        final int statesCount = 3;
        System.out.printf("Program generation has begun, please wait...\n");
        ProgramGenerator programGenerator = new ProgramGenerator(statesCount);

        int programsCounter = 0;
        final int idealProgramsCount = (int) Math.pow(Math.pow(4 * (statesCount + 1), 2), statesCount);

        final boolean checkForRepetitions = false;
        HashSet<Program> previousPrograms = new HashSet<Program>();

        int max = -1;

        for (Program program : programGenerator) {

            if (checkForRepetitions) {
                if (previousPrograms.contains(program)) {
                    System.out.println("already there");
                    break;
                } else {
                    previousPrograms.add(program);
                }
            }

            programsCounter++;

            System.out.printf("Programs counter: %d/%d;\n", programsCounter, idealProgramsCount);

            int result = program.run();
            //System.out.printf("Result: %d; Program: %s;\n", result, program);
            if (result > max) max = result;

        }

        System.out.printf("Terminated: %d/%d\n", programsCounter, idealProgramsCount);
        System.out.printf("Max: %d\n", max);
    }
}


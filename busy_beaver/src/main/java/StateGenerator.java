public class StateGenerator extends Generator<State> {
    @Override
    protected void run() throws InterruptedException {
        for (Tuple tupleForZero : new TupleGenerator(statesCount))
            for (Tuple tupleForOne : new TupleGenerator(statesCount))
                yield(new State(tupleForZero, tupleForOne));
    }

    private int statesCount;

    public StateGenerator(int statesCount) {
        this.statesCount = statesCount;
    }
}

public class State {

    private final Tuple[] tuples;

    public State(Tuple tupleForZero, Tuple tupleForOne) {
        tuples = new Tuple[2];
        getTuples()[0] = tupleForZero;
        getTuples()[1] = tupleForOne;
    }

    public State(State other) {
        tuples = new Tuple[2];
        getTuples()[0] = new Tuple(other.getTuples()[0]);
        getTuples()[1] = new Tuple(other.getTuples()[1]);
    }

    @Override
    public boolean equals(Object obj) {
        State another = (State) obj;
        boolean c1 = getTuples()[0] == another.getTuples()[0];
        boolean c2 = getTuples()[1] == another.getTuples()[1];
        return c1 && c2;
    }

    @Override
    public String toString() {
        return "[" + getTuples()[0] + "," + getTuples()[1] + "]";
    }

    public Tuple[] getTuples() {
        return tuples;
    }
}

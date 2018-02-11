public class Snapshot {

    private final Tuple currentTuple;
    private final Tape currentTape;
    private final State currentState;

    public Snapshot(Tuple currentTuple, Tape currentTape, State currentState) {
        // copy on snapshot
        this.currentTuple = new Tuple(currentTuple);
        this.currentTape = new Tape(currentTape.getIndexOfOnes(), currentTape.getTapePosition());
        this.currentState = currentState;
    }

    @Override
    public boolean equals(Object obj) {
        Snapshot another = (Snapshot) obj;
        boolean c1, c2, c3;
        c1 = currentTuple.equals(another.currentTuple);
        c2 = currentTape.equals(another.currentTape);
        c3 = currentState == another.currentState;
        return c1 && c2 && c3;
    }

    @Override
    public int hashCode() {
        int h1, h2, h3;
        h1 = currentTuple.hashCode();
        h2 = currentTape.hashCode();
        h3 = currentState.hashCode();
        return h1 + h2 + h3;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", currentTuple, currentTape, currentState);
    }
}

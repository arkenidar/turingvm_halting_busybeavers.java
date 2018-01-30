public class Snapshot {

    private final Tuple currentTuple;
    private final TapeSnapshot currentTape;
    private final State currentState;

    public Snapshot(Tuple currentTuple, Tape currentTape, State currentState){
        // copy on snapshot
        this.currentTuple = new Tuple(currentTuple);
        this.currentTape = new TapeSnapshot(currentTape.indexOfOnes, currentTape.tapePosition);
        this.currentState = currentState;
    }

    @Override
    public boolean equals(Object obj){
        Snapshot another = (Snapshot) obj;
        boolean c1, c2, c3;
        c1 = currentTuple.equals(another.currentTuple);
        c2 = currentTape.equals(another.currentTape);
        c3 = currentState == another.currentState;
        return c1 && c2 && c3;
    }
}

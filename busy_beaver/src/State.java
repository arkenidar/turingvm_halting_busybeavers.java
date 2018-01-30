public class State {

    public final Tuple[] tuples;

    public State(Tuple tupleForZero, Tuple tupleForOne){
        tuples = new Tuple[2];
        tuples[0] = tupleForZero;
        tuples[1] = tupleForOne;
    }

    @Override
    public boolean equals(Object obj){
        State another = (State) obj;
        boolean c1 = tuples[0] == another.tuples[0];
        boolean c2 = tuples[1] == another.tuples[1];
        return c1 && c2;
    }

    @Override
    public String toString(){
        return "["+tuples[0]+","+tuples[1]+"]";
    }
}

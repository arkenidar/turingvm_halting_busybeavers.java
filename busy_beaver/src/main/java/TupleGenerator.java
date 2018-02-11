/*
function* generate_tuples(states_count){
    for(var value_to_write of [0, 1]){
        for(var tape_move_direction of [r, l]){
            for(var next_state = 0; next_state < states_count; next_state++){
                yield [value_to_write, tape_move_direction, next_state]
            }
            yield [value_to_write, tape_move_direction, h]
        }
    }
}
*/
public class TupleGenerator extends Generator<Tuple> {

    private int statesCount;

    public TupleGenerator(int statesCount) {
        this.statesCount = statesCount;
    }

    @Override
    protected void run() throws InterruptedException {
        for (boolean valueToWrite : new BooleanGenerator()) {
            for (boolean moveRigth : new BooleanGenerator()) {
                for (int nextState = 0; nextState < statesCount; nextState++) {
                    yield(new Tuple(valueToWrite, moveRigth, nextState));
                }
                yield(new Tuple(valueToWrite, moveRigth, -1));
            }
        }
    }

    private class BooleanGenerator extends Generator<Boolean> {
        @Override
        protected void run() throws InterruptedException {
            yield(false);
            yield(true);
        }
    }
}

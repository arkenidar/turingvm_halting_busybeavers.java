import java.util.ArrayList;

/*
function* nested_generate_states(states_count, depth, array = []){
    if(depth >= 1){
        for(var value of generate_states(states_count)){
            var new_array = array.slice()
            new_array.push(value.slice())
            yield* nested_generate_states(states_count, depth-1, new_array )
        }
    } else if(depth == 0)
        yield array.slice()
}

function* generate_programs(states_count){
    yield* nested_generate_states(states_count, states_count)
}
*/
public class ProgramGenerator extends Generator<Program> {
    @Override
    protected void run() throws InterruptedException {
        if (depth >= 1) {
            for (State state : new StateGenerator(statesCount)) {
                ArrayList<State> newArray = new ArrayList<State>(array);
                newArray.add(new State(state));
                ProgramGenerator pg = new ProgramGenerator(statesCount, depth - 1, newArray);
                for (Program program : pg)
                    yield(program);
            }
        } else if (depth == 0) {
            yield(new Program(new ArrayList<State>(array)));
        }
    }

    private int statesCount, depth;
    private ArrayList<State> array;

    public ProgramGenerator(int statesCount, int depth, ArrayList<State> array) {
        this.statesCount = statesCount;
        this.depth = depth;
        this.array = array;
    }

    public ProgramGenerator(int statesCount) {
        this.statesCount = statesCount;
        this.depth = statesCount;
        this.array = new ArrayList<State>();
    }
}

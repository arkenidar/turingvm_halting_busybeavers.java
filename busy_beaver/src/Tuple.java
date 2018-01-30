public class Tuple {

    public final boolean writeOne;
    public final boolean moveRight;
    public final int nextStateIndex;

    public Tuple(boolean writeOne, boolean moveRight, int nextStateIndex){
        this.writeOne = writeOne;
        this.moveRight = moveRight;
        this.nextStateIndex = nextStateIndex;
    }

    public Tuple(Tuple another){
        this.writeOne = another.writeOne;
        this.moveRight = another.moveRight;
        this.nextStateIndex = another.nextStateIndex;
    }

    @Override
    public boolean equals(Object obj) {
        Tuple another = (Tuple) obj;
        return writeOne == another.writeOne &&
                moveRight == another.moveRight &&
                nextStateIndex == another.nextStateIndex;
    }

    @Override
    public String toString(){
        return "("+(writeOne?1:0)+","+(moveRight?"R":"L")+","+nextStateIndex+")";
    }
}

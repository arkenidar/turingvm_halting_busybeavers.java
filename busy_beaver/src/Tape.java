import java.util.HashSet;

public class Tape {

    public final HashSet<Long> indexOfOnes;
    public long tapePosition;

    public Tape(HashSet<Long> indexOfOnes, Long tapePosition){
        this.indexOfOnes = indexOfOnes;
        this.tapePosition = tapePosition;
    }

    Tape() {
        indexOfOnes = new HashSet<Long>();
        tapePosition = 0;
    }

    public Tape(Tape another){
        indexOfOnes = another.indexOfOnes;
        tapePosition = another.tapePosition;
    }

    public void set(Long index, boolean value){
        if(value)
            indexOfOnes.add(index);
        else
            indexOfOnes.remove(index);
    }

    public boolean get(Long index){
        return indexOfOnes.contains(index);
    }

    public boolean getSymbolUnderTape() {
        return get(tapePosition);
    }

    public int countOnes(){
        return indexOfOnes.size();
    }

    @Override
    public boolean equals(Object obj) {
        Tape another = (Tape) obj;
        return indexOfOnes.equals(another.indexOfOnes) &&
                tapePosition == another.tapePosition;
    }
}

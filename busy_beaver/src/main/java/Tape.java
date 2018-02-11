import java.util.Arrays;
import java.util.HashSet;

public class Tape {

    public HashSet<Long> getIndexOfOnes() {
        return indexOfOnes;
    }

    private final HashSet<Long> indexOfOnes;
    private long tapePosition;
    private final long upperBound = 100;
    private final long lowerBound = -upperBound;

    public Tape(HashSet<Long> indexOfOnes, Long tapePosition) {
        this.indexOfOnes = indexOfOnes;
        this.tapePosition = tapePosition;
    }

    Tape() {
        indexOfOnes = new HashSet<Long>();
        tapePosition = 0;
    }

    public Tape(Tape another) {
        indexOfOnes = another.indexOfOnes;
        tapePosition = another.tapePosition;
    }

    public long getTapePosition() {
        return tapePosition;
    }

    public void incrementTapePosition(long delta) throws IndexOutOfBoundsException {
        tapePosition += delta;
        if (tapePosition < lowerBound || tapePosition > upperBound) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void set(Long index, boolean value) {
        if (value)
            indexOfOnes.add(index);
        else
            indexOfOnes.remove(index);
    }

    public boolean get(Long index) {
        return indexOfOnes.contains(index);
    }

    public boolean getSymbolUnderTape() {
        return get(tapePosition);
    }

    public int countOnes() {
        return indexOfOnes.size();
    }

    @Override
    public boolean equals(Object obj) {
        Tape another = (Tape) obj;
        return indexOfOnes.equals(another.indexOfOnes) &&
                tapePosition == another.tapePosition;
    }

    @Override
    public String toString() {
        return String.format("(%d, %s)", tapePosition, Arrays.toString(indexOfOnes.toArray()));
    }

    @Override
    public int hashCode() {
        return (int) tapePosition + indexOfOnes.hashCode();
    }
}

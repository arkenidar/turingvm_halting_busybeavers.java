import java.util.Arrays;
import java.util.HashSet;

public class TapeSnapshot {
    private final Object[] indexOfOnes;
    private final long tapePosition;
    TapeSnapshot(HashSet<Long> indexOfOnes, Long tapePosition){
        this.indexOfOnes = indexOfOnes.toArray();
        this.tapePosition = tapePosition;
    }

    @Override
    public boolean equals(Object obj) {
        TapeSnapshot another = (TapeSnapshot) obj;
        boolean c1 = Arrays.equals(indexOfOnes, another.indexOfOnes);
        boolean c2 = tapePosition == another.tapePosition;
        return c1 && c2;
    }
}

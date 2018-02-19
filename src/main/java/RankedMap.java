import java.util.HashMap;
import java.lang.*;

class RankedMap extends HashMap<Double, String> {
    private final int maxSize;

    RankedMap(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public String put(Double rank, String word) {
        if (this.size() < maxSize)
            return super.put(rank, word);
        else {
            final Double[] smallestRank = {Double.MAX_VALUE};

            this.keySet().forEach((Double key) -> {
                if (key.compareTo(smallestRank[0]) < 0)
                    smallestRank[0] = key;
            });

            if(rank.compareTo(smallestRank[0]) > 0) {
                super.remove(smallestRank[0]);
                return super.put(rank, word);
            }
            return "";
        }
    }

    @Override
    public String toString() {
        return String.join(" ", this.values());
    }
}

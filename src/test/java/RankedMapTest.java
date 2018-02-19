import org.junit.Test;

import static org.junit.Assert.*;

public class RankedMapTest {

    @Test
    public void testNormalPut() {
        RankedMap rankedMap = new RankedMap(5);
        rankedMap.put(1.0, "hello");
        assertEquals(rankedMap.get(1.0), "hello");
    }

    @Test
    public void testMaxSize() {
        RankedMap rankedMap = new RankedMap(5);
        rankedMap.put(1.0, "hello");
        rankedMap.put(2.0, "hello");
        rankedMap.put(3.0, "hello");
        rankedMap.put(4.0, "hello");
        rankedMap.put(5.0, "hello");
        rankedMap.put(6.0, "hello");
        rankedMap.put(7.0, "hello");
        rankedMap.put(8.0, "hello");
        assertEquals(rankedMap.size(), 5);
    }

    @Test
    public void testSmallPut() {
        RankedMap rankedMap = new RankedMap(5);
        rankedMap.put(1.0, "hello");
        rankedMap.put(2.0, "hello");
        rankedMap.put(3.0, "hello");
        rankedMap.put(4.0, "hello");
        rankedMap.put(5.0, "hello");
        rankedMap.put(0.5, "hello");
        assertEquals(rankedMap.get(0.5), null);
    }

    @Test
    public void testLargePut() {
        RankedMap rankedMap = new RankedMap(5);
        rankedMap.put(1.0, "hello");
        rankedMap.put(2.0, "hello");
        rankedMap.put(3.0, "hello");
        rankedMap.put(4.0, "hello");
        rankedMap.put(5.0, "hello");
        rankedMap.put(6.0, "hello");
        assertEquals(rankedMap.get(1.0), null);
        assertEquals(rankedMap.get(6.0), "hello");
    }

    @Test
    public void testMediumPut() {
        RankedMap rankedMap = new RankedMap(5);
        rankedMap.put(1.0, "hello");
        rankedMap.put(2.0, "hello");
        rankedMap.put(3.0, "hello");
        rankedMap.put(4.0, "hello");
        rankedMap.put(5.0, "hello");
        rankedMap.put(3.5, "hello");
        assertEquals(rankedMap.get(1.0), null);
        assertEquals(rankedMap.get(3.5), "hello");
    }

    @Test
    public void testToString() {
        RankedMap rankedMap = new RankedMap(5);
        rankedMap.put(1.0, "hello");
        rankedMap.put(2.0, "hello");
        rankedMap.put(3.0, "hello");
        rankedMap.put(4.0, "hello");
        rankedMap.put(5.0, "hello");
        assertEquals(rankedMap.toString(), "hello hello hello hello hello");
    }

}
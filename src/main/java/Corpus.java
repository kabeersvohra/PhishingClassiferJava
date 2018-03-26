import java.util.HashMap;

public class Corpus extends HashMap<String, Integer> {

    @Override
    public Integer put(String key, Integer value) {
        return super.put(key.toLowerCase(), value);
    }

    @Override
    public Integer get(Object key) {
        Integer result =  super.get(key.toString().toLowerCase());
        if (result == null) return 0;
        return result;
    }
}
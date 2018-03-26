import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void queryGoogleTest() throws IOException {
        List<String> result = Util.queryGoogle("gmail");
        assertEquals(result.get(0), "https://www.google.com/gmail/");
        result = Util.queryGoogle("facebook");
        assertEquals(result.get(0), "https://en-gb.facebook.com/");
    }

    @Test
    public void idfWithCommonWord() throws IOException {
        WordDictionary dictionary = Util.loadLocalDictionary();
        System.out.println(Util.idf(dictionary, "and"));
    }

}
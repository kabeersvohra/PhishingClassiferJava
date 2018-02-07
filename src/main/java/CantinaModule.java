import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CantinaModule implements Module {

    public Classification classify(String url, BufferedImage img, File http) throws IOException {
        Document doc = Jsoup.parse(http, "UTF-8", url);
        List<String> words = Arrays.asList(doc.text().split(" ")); //all words inside document
        Map<Double, String> tfWords = new RankedMap(5);
        words.forEach((word) -> tfWords.put(Util.tf(words, word), word));
        return null;
    }


}

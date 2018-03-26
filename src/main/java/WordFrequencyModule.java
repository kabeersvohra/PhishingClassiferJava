import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

public class WordFrequencyModule implements Module {

    public Classification classify(String url, BufferedImage img, File http) throws IOException {
        Document doc = Jsoup.parse(http, "UTF-8", url);
        List<String> words = Arrays.asList(doc.text().split(" ")); //all words inside document
        RankedMap tfWords = new RankedMap(5);
        WordDictionary dictionary = Util.loadLocalDictionary();
        words.forEach((word) -> {
            tfWords.put(Util.tf(words, word) * Util.idf(dictionary, word), word);
        });
        List<String> googleResults = Util.queryGoogle(tfWords.toString());
        final Boolean[] match = {false};
        googleResults.forEach((String urlResult) -> {
            try {
                URI currentURI = new URI(url);
                URI resultURI = new URI(urlResult);
                String currentHost = currentURI.getHost();
                String resultHost = resultURI.getHost();
                currentHost = currentHost.startsWith("www.") ? currentHost.substring(4) : currentHost;
                resultHost = resultHost.startsWith("www.") ? resultHost.substring(4) : resultHost;
                if (currentHost.equals(resultHost)) match[0] = true;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        return new Classification(match[0]);
    }

}

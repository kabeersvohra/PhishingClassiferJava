import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import static org.apache.commons.io.FileUtils.listFiles;

public class CantinaModule implements Module {

    public Classification classify(String url, BufferedImage img, File http) throws IOException {
        Document doc = Jsoup.parse(http, "UTF-8", url);
        List<String> words = Arrays.asList(doc.text().split(" ")); //all words inside document
        RankedMap tfWords = new RankedMap(5);
        words.forEach((word) -> tfWords.put(Util.tf(words, word), word));
        List<String> googleResults = Util.queryGoogle(tfWords.toString());
        googleResults.forEach((String urlResult) -> {
            try {
                URI currentURI = new URI(url);
                URI resultURI = new URI(urlResult);
                String currentHost = currentURI.getHost();
                String resultHost = resultURI.getHost();
                currentHost = currentHost.startsWith("www.") ? currentHost.substring(4) : currentHost;
                resultHost = resultHost.startsWith("www.") ? resultHost.substring(4) : resultHost;
//                if (currentHost.equals(resultHost)) return null;
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
        return null;
    }


}

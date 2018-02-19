import org.apache.commons.io.filefilter.TrueFileFilter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.io.FileUtils.listFiles;

public class Util {

    private static final String DICTIONARY_LOCATION = "data/dictionary.xml";

    static double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    static double idf(List<List<String>> docs, String term) {
        double n = 0;
        for (List<String> doc : docs) {
            for (String word : doc) {
                if (term.equalsIgnoreCase(word)) {
                    n++;
                    break;
                }
            }
        }
        return Math.log(docs.size() / n);
    }

    public static double tfIdf(List<String> doc, List<List<String>> docs, String term) {
        return tf(doc, term) * idf(docs, term);
    }

    static List<String> queryGoogle(String query) throws IOException {
        final String GOOGLE_SEARCH_URL = "https://www.google.co.uk/search";
        final String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                "(KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";

        String searchURL = GOOGLE_SEARCH_URL + "?q="+query;
        Document doc = Jsoup.connect(searchURL).userAgent(userAgent).get();

        Elements results = doc.select("h3.r > a");

        List<String> urlResults = new ArrayList<>();

        for (Element result : results) {
            urlResults.add(result.attr("href"));
        }

        return urlResults;
    }
    
    static void loadDictionaryLocally(String location) throws IOException, SAXException, ParserConfigurationException {
        PrintWriter destination = new PrintWriter(DICTIONARY_LOCATION, "utf-8");
        File source = new File(location);
        for (File fXmlFile : listFiles(source, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
            if (!fXmlFile.getName().endsWith((".xml"))) {
                break;
            }
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);

            NodeList nodes = doc.getElementsByTagName("w");
            for (int i = 0; i < nodes.getLength(); i++) {
                destination.println(nodes.item(i).getTextContent());
            }
        }
        destination.close();
    }

}

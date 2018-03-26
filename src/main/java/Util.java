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
import java.io.*;
import java.util.*;

import static org.apache.commons.io.FileUtils.listFiles;

public class Util {

    private static final String DICTIONARY_LOCATION = "data/dictionary.txt";

    static double tf(List<String> doc, String term) {
        double result = 0;
        for (String word : doc) {
            if (term.equalsIgnoreCase(word))
                result++;
        }
        return result / doc.size();
    }

    static double idf(WordDictionary dictionary, String term) {
        Corpus corpus = dictionary.getCorpus();
        int n = corpus.get(term) + 1;
        return Math.log(dictionary.getNumberDocs() / n);
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

    static WordDictionary loadLocalDictionary() throws IOException {
        BufferedReader dictionary = new BufferedReader(new FileReader(DICTIONARY_LOCATION));
        double numberDocs = Double.parseDouble(dictionary.readLine().substring(1));
        String data;
        Corpus corpus = new Corpus();
        while((data = dictionary.readLine()) != null) {
           List<String> entry = Arrays.asList(data.split(" "));
           corpus.put(entry.get(0), Integer.parseInt(entry.get(1)));
        }
        return new WordDictionary(numberDocs, corpus);
    }
    
    static void populateLocalDictionary(String location) throws IOException, SAXException, ParserConfigurationException {
        PrintWriter destination = new PrintWriter(DICTIONARY_LOCATION);
        File source = new File(location);
        HashMap<String, Integer> result = new HashMap<>();
        HashSet<String> intermediary = new HashSet<>();
        int numberDocs = 0;
        for (File fXmlFile : listFiles(source, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE)) {
            if (!fXmlFile.getName().endsWith((".xml"))) {
                break;
            }
            numberDocs++;
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            org.w3c.dom.Document doc = dBuilder.parse(fXmlFile);
            NodeList nodes = doc.getElementsByTagName("w");
            for (int i = 0; i < nodes.getLength(); i++) {
                intermediary.add(nodes.item(i).getTextContent().replaceAll("\\s+",""));
            }

            intermediary.forEach(word -> {
                if (!result.containsKey(word))
                    result.put(word, 1);
                else
                    result.put(word, result.get(word) + 1);
            });
        }
        destination.println(numberDocs);
        result.forEach((word,occurrences) -> {
           destination.println(word + " " + occurrences);
        });
        destination.close();
    }

}

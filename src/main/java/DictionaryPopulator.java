import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class DictionaryPopulator {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        Util.populateLocalDictionary("C:\\Users\\Kabeer\\Downloads\\2554\\Texts");
    }

}

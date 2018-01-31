import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PhishingClassifier {

    public static void main(String[] args) throws IOException {
        String url = args[0];
        BufferedImage img = ImageIO.read(new File(args[1]));
        File http = new File(args[2]);
        Classification classification = classify(url, img, http);
    }

    private static Classification classify(String url, BufferedImage img, File http) {
        Module module = null;
        return module.classify(url, img, http);
    }

}

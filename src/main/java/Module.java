import java.awt.image.BufferedImage;
import java.io.File;

public interface Module {
    Classification classify(String url, BufferedImage img, File http);
}

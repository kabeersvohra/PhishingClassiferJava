import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public interface Module {
    Classification classify(String url, BufferedImage img, File http) throws IOException;
}

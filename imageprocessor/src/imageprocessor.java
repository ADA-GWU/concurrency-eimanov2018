import java.awt.*;
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class imageprocessor {
    private int width;
    private int height;
    BufferedImage inputImage;

//    Constructor of the imageprocessor
public imageprocessor(BufferedImage inputImage) {
    this.inputImage=inputImage;
    this.width=inputImage.getWidth();
    this.height=inputImage.getHeight();
}
    public boolean compute(int left, int top, int right, int bottom) {
         int r = 0;
         int g = 0;
         int b = 0;
         int num = 0;
        for( int i = left; i < right; i++) {
            for( int j = top; j < bottom; j++) {
                if (i < 0 || i >= width || j < 0 || j >= height)
                    continue;
                final Color c = new Color(inputImage.getRGB(i,j));
                r += c.getRed();
                g += c.getGreen();
                b += c.getBlue();
                num++;
            }
        }
        final Color nc = new Color (r/num, g/num, b/num);

        for( int i = left; i <right; i++) {
            for( int j = top; j < bottom ; j++) {
                if (i < 0 || i >= width || j < 0 || j >= height)
                    continue;
                this.inputImage.setRGB(i, j, nc.getRGB());
            }
        }
        return true;
    }
//    Save the image in the environment path
    public void writeImage(final String filename ) throws IOException {
        ImageIO.write(this.inputImage, "jpeg", new File(filename));
    }
}
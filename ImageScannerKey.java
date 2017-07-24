import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageScannerKey {

    public static void main(String[] args) {
        try {
            String workingDir = System.getProperty("user.dir");
            System.out.println("Current working directory : " + workingDir);
            String workingDir1 = workingDir  + "/src/";
            BufferedImage image = ImageIO.read(new File( workingDir1 + "Capture1.png"));
            ///// Above Image selection
            ///Mouse Click
            boolean isOnScreen = isOnScreen(image);

            BufferedImage image1 = ImageIO.read(new File(workingDir1 + "Capture.png"));
            boolean isOnScreen2 = isOnScreen(image1);

            System.out.print(isOnScreen);
            System.out.print(isOnScreen2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Search Image and Optionally do action accordingly
     * @param bi image
     * @return successfully found image
     */
    private static boolean isOnScreen(BufferedImage bi) {
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {

                boolean invalid = false;
                int k = x, l = y;
                for (int a = 0; a < bi.getWidth(); a++) {
                    l = y;
                    for (int b = 0; b < bi.getHeight(); b++) {
                        if (bi.getRGB(a, b) != image.getRGB(k, l)) {
                            invalid = true;
                            break;
                        } else {
                            l++;
                        }
                    }
                    if (invalid) {
                        break;
                    } else {
                        k++;
                    }

                }

                if (!invalid) {

                    /// Mouse
                    System.out.println(k + " " + l);
                    try {
                        Robot move = new Robot();
                        move.mouseMove(k, l);
                        move.mousePress(InputEvent.BUTTON1_MASK);
                        move.mouseRelease(InputEvent.BUTTON1_MASK);
                    } catch (AWTException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        return false; //If no image is found

    }

}
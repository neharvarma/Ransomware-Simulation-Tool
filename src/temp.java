import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class temp {

    public static void hideTextInImage(File outputImageFile, String textToHide) {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File originalImageFile = fileChooser.getSelectedFile();
                BufferedImage originalImage = ImageIO.read(originalImageFile);
                BufferedImage modifiedImage = hideText(originalImage, textToHide);
                ImageIO.write(modifiedImage, "png", outputImageFile);
                JOptionPane.showMessageDialog(null, "Text hidden in image successfully.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to hide text in image.");
        }
    }

    private static BufferedImage hideText(BufferedImage originalImage, String textToHide) {
        BufferedImage modifiedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        modifiedImage.createGraphics().drawImage(originalImage, 0, 0, null);
        int width = modifiedImage.getWidth();
        int height = modifiedImage.getHeight();
        int charIndex = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width && charIndex < textToHide.length(); x++) {
                int pixel = modifiedImage.getRGB(x, y);
                int blue = pixel & 0xff;
                blue &= 0xfe; // Clear the least significant bit
                int nextBit = (textToHide.charAt(charIndex) >> 7 - x % 8) & 1;
                blue |= nextBit;
                modifiedImage.setRGB(x, y, (pixel & 0xffff00ff) | (blue << 8));
                if (x % 8 == 7) {
                    charIndex++;
                }
            }
        }
        return modifiedImage;
    }

    public static void main(String[] args) {
        File outputImageFile = new File("modified_image.png");
        String textToHide = "This is a secret message.";
        hideTextInImage(outputImageFile, textToHide);
    }
}

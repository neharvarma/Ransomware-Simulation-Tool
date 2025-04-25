import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class temp1 {

    public static String extractTextFromImage() {
        try {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);
            if (result == JFileChooser.APPROVE_OPTION) {
                File imageFile = fileChooser.getSelectedFile();
                BufferedImage image = ImageIO.read(imageFile);
                StringBuilder extractedText = new StringBuilder();
                int width = image.getWidth();
                int height = image.getHeight();
                int charIndex = 0;
                char nextChar = 0;
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        int pixel = image.getRGB(x, y);
                        int blue = (pixel >> 8) & 1;
                        nextChar |= blue << (7 - charIndex % 8);
                        if (charIndex % 8 == 7) {
                            if (nextChar == '\0') {
                                return extractedText.toString();
                            }
                            extractedText.append(nextChar);
                            nextChar = 0;
                        }
                        charIndex++;
                    }
                }
                return extractedText.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String binaryToString(String binary) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < binary.length(); i += 8) {
            String byteString = binary.substring(i, Math.min(i + 8, binary.length()));
            int asciiValue = Integer.parseInt(byteString, 2);
            stringBuilder.append((char) asciiValue);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String extractedText = extractTextFromImage();
        if (extractedText != null && !extractedText.isEmpty()) {
            System.out.println("Extracted text: " + extractedText);
        } else {
            System.out.println("No hidden text found in the image.");
        }
    }
}

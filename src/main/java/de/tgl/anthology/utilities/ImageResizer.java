package de.tgl.anthology.utilities;

import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageResizer {
    private static final Logger LOGGER = Logger.getLogger(ImageResizer.class.getName());

    public static boolean listFilesForFolder(String readInPath, String writeOutPath, int height, int width) {
        File folder = new File(readInPath);
        boolean isSuccessful = true;
        for (final File fileEntry : Objects.requireNonNull(folder.listFiles())) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(readInPath, writeOutPath, height, width);
            } else {
                try {
                    resize(readInPath, fileEntry.getName(), height, width, writeOutPath);
                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "IMG " + fileEntry.getName() + " did not convert successfully.");
                    LOGGER.log(Level.WARNING, e.getMessage());
                    isSuccessful = false;
                }
            }
        }
        return isSuccessful;
    }


    private static void resize(String readPath, String name, int height, int width, String writePath) throws IOException {
        String outputFormat = "jpg";
        if(name.contains(outputFormat)) {
            File icon = new File(readPath + name);
            BufferedImage originalImage = ImageIO.read(icon);
            originalImage = Scalr.resize(originalImage, Scalr.Method.ULTRA_QUALITY, Scalr.Mode.FIT_TO_WIDTH, width, height);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(originalImage, outputFormat, byteArrayOutputStream);
            byteArrayOutputStream.flush();
            FileOutputStream fos = new FileOutputStream(writePath + name + "_backup." + outputFormat);
            fos.write(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.close();
        }
    }
}

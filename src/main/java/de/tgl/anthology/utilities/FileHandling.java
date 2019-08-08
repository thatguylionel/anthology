package de.tgl.anthology.utilities;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileHandling {
    /**
     * Read contents of file and return as String
     * TODO: Add more comprehensive comments
     *
     * @param path
     * @param encoding
     * @return
     * @throws IOException
     */
    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Generates the directory
     * TODO: Add more comprehensive comments
     *
     * @param directory Name of directory
     */
    static void generateDirectory(String directory) {
        File file = new File(directory);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }
    }
}

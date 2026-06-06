package com.tools.imageconverter.impl;

import com.tools.imageconverter.core.ImageConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Base abstract class for converters that rely on standard ImageIO.
 * Implements the Template Method pattern for file validation and image reading.
 */
public abstract class BaseImageIOConverter implements ImageConverter {

    @Override
    public void convert(File input, File output) throws IOException {
        if (input == null || !input.exists()) {
            throw new IllegalArgumentException("Input file does not exist: " + (input != null ? input.getPath() : "null"));
        }
        if (output == null) {
            throw new IllegalArgumentException("Output file cannot be null");
        }

        BufferedImage image = ImageIO.read(input);
        if (image == null) {
            throw new IOException("Could not read image from " + input.getName() + " (Unsupported format or corrupted file)");
        }

        writeImage(image, output);
    }

    /**
     * Writes the BufferedImage to the output file using the specific implementation details.
     *
     * @param image  The loaded BufferedImage
     * @param output The output file destination
     * @throws IOException If writing fails
     */
    protected abstract void writeImage(BufferedImage image, File output) throws IOException;
}

package com.tools.imageconverter.core;

import java.io.File;
import java.io.IOException;

public interface ImageConverter {
    /**
     * Checks if this converter supports conversion between the given formats.
     *
     * @param sourceFormat Source format
     * @param targetFormat Target format
     * @return true if supported, false otherwise
     */
    boolean supports(ImageFormat sourceFormat, ImageFormat targetFormat);

    /**
     * Performs the image conversion.
     *
     * @param input Input image file
     * @param output Output image file
     * @throws IOException If an I/O error occurs
     */
    void convert(File input, File output) throws IOException;
}

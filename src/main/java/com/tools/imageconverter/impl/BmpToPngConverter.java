package com.tools.imageconverter.impl;

import com.tools.imageconverter.core.ImageConverter;
import com.tools.imageconverter.core.ImageFormat;

import java.io.File;
import java.io.IOException;

public class BmpToPngConverter implements ImageConverter {

    @Override
    public boolean supports(ImageFormat sourceFormat, ImageFormat targetFormat) {
        return sourceFormat == ImageFormat.BMP && targetFormat == ImageFormat.PNG;
    }

    @Override
    public void convert(File input, File output) throws IOException {
        System.out.println("Converting " + input.getName() + " to " + output.getName());
        
        // TODO: Implement custom BMP to PNG conversion logic reading the file bytes
        // This would involve parsing the BMP header, extracting pixels,
        // applying DEFLATE/ZLIB compression and writing the PNG format structure.
        
        System.out.println("Note: Internal logic pending implementation.");
    }
}

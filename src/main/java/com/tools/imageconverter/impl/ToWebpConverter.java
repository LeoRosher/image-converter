package com.tools.imageconverter.impl;

import com.tools.imageconverter.core.ImageFormat;
import com.tools.imageconverter.core.LossyImageConverter;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

public class ToWebpConverter extends BaseImageIOConverter implements LossyImageConverter {

    private float quality = 0.8f; // Default 80%

    @Override
    public void setQuality(float quality) {
        if (quality < 0.0f || quality > 1.0f) {
            throw new IllegalArgumentException("Quality must be between 0.0 and 1.0");
        }
        this.quality = quality;
    }

    @Override
    public float getQuality() {
        return quality;
    }

    @Override
    public boolean supports(ImageFormat sourceFormat, ImageFormat targetFormat) {
        return (sourceFormat == ImageFormat.PNG || 
                sourceFormat == ImageFormat.JPEG || 
                sourceFormat == ImageFormat.BMP) && 
               targetFormat == ImageFormat.WEBP;
    }

    @Override
    protected void writeImage(BufferedImage image, File output) throws IOException {
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByMIMEType("image/webp");
        if (!writers.hasNext()) {
            throw new IOException("No WebP ImageWriter found. Ensure org.sejda.imageio:webp-imageio is in the classpath.");
        }
        
        ImageWriter writer = writers.next();
        
        try (ImageOutputStream ios = ImageIO.createImageOutputStream(output)) {
            writer.setOutput(ios);
            
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            if (writeParam.canWriteCompressed()) {
                writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
                
                // Sejda WebP plugin requires a compression type to be set before quality
                String[] types = writeParam.getCompressionTypes();
                if (types != null && types.length > 0) {
                    // Try to find "Lossy" type, otherwise use the first one
                    String targetType = types[0];
                    for (String type : types) {
                        if (type.equalsIgnoreCase("Lossy")) {
                            targetType = type;
                            break;
                        }
                    }
                    writeParam.setCompressionType(targetType);
                }
                
                writeParam.setCompressionQuality(quality);
            }
            
            writer.write(null, new IIOImage(image, null, null), writeParam);
        } finally {
            writer.dispose();
        }
    }
}

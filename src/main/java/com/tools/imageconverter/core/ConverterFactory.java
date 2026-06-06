package com.tools.imageconverter.core;

import com.tools.imageconverter.impl.BmpToPngConverter;
import com.tools.imageconverter.impl.ToWebpConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to obtain the appropriate ImageConverter instance.
 */
public class ConverterFactory {

    private final List<ImageConverter> converters;

    public ConverterFactory() {
        this.converters = new ArrayList<>();
        // Register available converters
        this.converters.add(new BmpToPngConverter());
        this.converters.add(new ToWebpConverter());
    }

    /**
     * Finds and returns a suitable converter for the given source and target formats.
     *
     * @param sourceFormat The source image format
     * @param targetFormat The target image format
     * @return An ImageConverter that supports the conversion, or null if none is found.
     */
    public ImageConverter getConverter(ImageFormat sourceFormat, ImageFormat targetFormat) {
        for (ImageConverter converter : converters) {
            if (converter.supports(sourceFormat, targetFormat)) {
                return converter; // In a more complex scenario, we could return new instances here
            }
        }
        return null;
    }
}

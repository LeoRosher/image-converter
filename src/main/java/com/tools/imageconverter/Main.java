package com.tools.imageconverter;

import com.tools.imageconverter.core.ImageConverter;
import com.tools.imageconverter.core.ImageFormat;
import com.tools.imageconverter.impl.BmpToPngConverter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        System.out.println("Starting Image Converter...");

        // Example of using the structure
        File inputFile = new File("example.bmp");
        File outputFile = new File("output.png");

        ImageConverter converter = new BmpToPngConverter();

        if (converter.supports(ImageFormat.BMP, ImageFormat.PNG)) {
            System.out.println("Conversion supported. Processing...");
            try {
                converter.convert(inputFile, outputFile);
                System.out.println("Conversion successful.");
            } catch (Exception e) {
                System.err.println("Error during conversion: " + e.getMessage());
            }
        } else {
            System.out.println("Conversion not supported by this converter.");
        }
    }
}

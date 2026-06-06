package com.tools.imageconverter;

import com.tools.imageconverter.core.ConverterFactory;
import com.tools.imageconverter.core.ImageConverter;
import com.tools.imageconverter.core.ImageFormat;
import com.tools.imageconverter.core.LossyImageConverter;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java -jar image-converter.jar <input-file-path> [quality: 0.0 - 1.0]");
            System.exit(1);
        }

        String inputFilePath = args[0];
        File inputFile = new File(inputFilePath);

        if (!inputFile.exists() || !inputFile.isFile()) {
            System.err.println("Error: The input file does not exist or is not a valid file: " + inputFilePath);
            System.exit(1);
        }

        // Extract extension and base name
        String fileName = inputFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            System.err.println("Error: The file does not have an extension to determine its format.");
            System.exit(1);
        }

        String extension = fileName.substring(dotIndex + 1);
        String baseName = fileName.substring(0, dotIndex);

        // Define output file (same directory, same name, .webp extension)
        File parentDir = inputFile.getParentFile();
        File outputFile = new File(parentDir, baseName + ".webp");

        ImageFormat sourceFormat = ImageFormat.fromExtension(extension);
        ImageFormat targetFormat = ImageFormat.WEBP;

        if (sourceFormat == ImageFormat.UNKNOWN) {
            System.err.println("Error: Unknown or unsupported source format: " + extension);
            System.exit(1);
        }

        ConverterFactory factory = new ConverterFactory();
        ImageConverter converter = factory.getConverter(sourceFormat, targetFormat);

        if (converter != null) {
            System.out.println("Starting conversion...");
            System.out.println("Input: " + inputFile.getAbsolutePath());
            System.out.println("Output: " + outputFile.getAbsolutePath());

            // Set quality if the converter supports lossy compression
            if (converter instanceof LossyImageConverter) {
                LossyImageConverter lossyConverter = (LossyImageConverter) converter;
                float quality = 0.80f; // Default 80%
                
                if (args.length >= 2) {
                    try {
                        quality = Float.parseFloat(args[1]);
                        if (quality < 0.0f || quality > 1.0f) {
                            System.err.println("Warning: Quality must be between 0.0 and 1.0. Using default: 0.8");
                            quality = 0.80f;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Invalid quality parameter. Using default: 0.8");
                    }
                }
                
                lossyConverter.setQuality(quality);
                System.out.println("Compression quality: " + (quality * 100) + "%");
            }
            
            try {
                long startTime = System.currentTimeMillis();
                converter.convert(inputFile, outputFile);
                long endTime = System.currentTimeMillis();
                System.out.println("Conversion successful in " + (endTime - startTime) + "ms.");
            } catch (Exception e) {
                System.err.println("Error during conversion: " + e.getMessage());
                e.printStackTrace();
                System.exit(1);
            }
        } else {
            System.err.println("Conversion from " + sourceFormat + " to " + targetFormat + " is not supported.");
            System.exit(1);
        }
    }
}

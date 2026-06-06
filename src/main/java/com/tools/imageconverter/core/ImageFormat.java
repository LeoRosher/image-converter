package com.tools.imageconverter.core;

public enum ImageFormat {
    BMP,
    PNG,
    JPEG,
    GIF,
    TIFF,
    WEBP,
    UNKNOWN;

    public static ImageFormat fromExtension(String extension) {
        if (extension == null) return UNKNOWN;
        switch (extension.toLowerCase()) {
            case "bmp": return BMP;
            case "png": return PNG;
            case "jpg":
            case "jpeg": return JPEG;
            case "gif": return GIF;
            case "tiff":
            case "tif": return TIFF;
            case "webp": return WEBP;
            default: return UNKNOWN;
        }
    }
}

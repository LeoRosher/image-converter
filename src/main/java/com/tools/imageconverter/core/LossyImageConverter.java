package com.tools.imageconverter.core;

/**
 * Extension of ImageConverter that supports lossy compression and quality settings.
 */
public interface LossyImageConverter extends ImageConverter {
    
    /**
     * Sets the compression quality for the conversion.
     * 
     * @param quality A float value between 0.0f (lowest quality/highest compression) 
     *                and 1.0f (highest quality/lowest compression).
     */
    void setQuality(float quality);
    
    /**
     * Gets the current compression quality.
     * 
     * @return The current quality value.
     */
    float getQuality();
}

package android.graphics;

/**
 * Stub for android.graphics.ColorSpace (API 26+),
 * with nested Rgb so tests compile under compileSdkVersion=25.
 */
public class ColorSpace {
    // no-op stub

    /** Stub for ColorSpace.Named enum you already have */
    public enum Named {
        SRGB, DISPLAY_P3, ADOBE_RGB, BT2020
    }

    /**
     * Stub of the nested Rgb class.
     * The real ColorSpace.Rgb extends ColorSpace and defines gamuts.
     */
    public static final class Rgb extends ColorSpace {
        public Rgb() {
            super();
        }
        // If your code calls any methods on Rgb (e.g. getTransferParameters),
        // stub them here. Otherwise, this empty stub will suffice.
    }
}

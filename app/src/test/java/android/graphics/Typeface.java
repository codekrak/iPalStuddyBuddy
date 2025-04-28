package android.graphics;

/**
 * Stub for android.graphics.Typeface (so Typeface.Builder can compile),
 * and its nested Builder class (API 26+).
 */
public class Typeface {
    /** Empty constructor so tests compile, real engine not needed. */
    public Typeface() { }

    /**
     * Stub of the nested Builder. Real signature takes a File or AssetManager.
     * Add any methods your code actually calls.
     */
    public static final class Builder {
        public Builder(String familyName) {
            // no-op
        }
        public Typeface build() {
            return new Typeface();
        }
        // stub any other methods your code uses, e.g.:
        // public Builder setWeight(int weight) { return this; }
    }
}

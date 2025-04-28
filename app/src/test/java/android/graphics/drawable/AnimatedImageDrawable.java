package android.graphics.drawable;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;

/**
 * Stub for android.graphics.drawable.AnimatedImageDrawable (API 28+)
 * so tests compile under compileSdkVersion=25.
 */
public class AnimatedImageDrawable extends Drawable {
    @Override
    public void draw(Canvas canvas) {
        // no-op
    }

    @Override
    public void setAlpha(int alpha) {
        // no-op
    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {
        // no-op
    }

    @Override
    public int getOpacity() {
        return PixelFormat.TRANSLUCENT;
    }
}

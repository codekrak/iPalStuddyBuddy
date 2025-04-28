package android.speech;

import android.content.Context;
import android.content.Intent;

/**
 * Test‐only stub of SpeechRecognizer.
 */
public class SpeechRecognizer {
    public static final String RESULTS_RECOGNITION = "results";
    public static final int ERROR_AUDIO   = 1;
    public static final int ERROR_NETWORK = 2;

    private RecognitionListener listener;
    private Intent lastIntent;
    private boolean listening;
    private boolean destroyed;

    // this replaces the real Android factory
    public static SpeechRecognizer createSpeechRecognizer(Context ctx) {
        return new SpeechRecognizer();
    }

    public void setRecognitionListener(RecognitionListener l) {
        listener = l;
    }

    public void startListening(Intent intent) {
        lastIntent = intent;
        listening = true;
    }

    public void stopListening() {
        listening = false;
    }

    public void destroy() {
        destroyed = true;
    }

    // ─── Test helpers ──────────────────────────────────

    public RecognitionListener getRecognitionListener() {
        return listener;
    }

    public Intent getLastRecognizerIntent() {
        return lastIntent;
    }

    public boolean isListening() {
        return listening;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}

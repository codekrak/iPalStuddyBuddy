package org.robolectric.shadows;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import org.robolectric.annotation.Implementation;
import org.robolectric.annotation.Implements;
import org.robolectric.util.ReflectionHelpers;

@Implements(SpeechRecognizer.class)
public class ShadowSpeechRecognizer {
    // Track the state in the shadow
    private RecognitionListener listener;
    private Intent lastIntent;
    private boolean isListening;
    private boolean isDestroyed;

    @Implementation
    public static SpeechRecognizer createSpeechRecognizer(Context context) {
        return ReflectionHelpers.callConstructor(
                SpeechRecognizer.class,
                new ReflectionHelpers.ClassParameter[0]
        );
    }

    @Implementation
    public void setRecognitionListener(RecognitionListener listener) {
        this.listener = listener;
    }

    @Implementation
    public void startListening(Intent intent) {
        lastIntent = intent;
        isListening = true;
    }

    @Implementation
    public void stopListening() {
        isListening = false;
    }

    @Implementation
    public void destroy() {
        isDestroyed = true;
    }

    // Test access methods
    public RecognitionListener getRecognitionListener() {
        return listener;
    }

    public Intent getLastRecognizerIntent() {
        return lastIntent;
    }

    public boolean isListening() {
        return isListening;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }
}
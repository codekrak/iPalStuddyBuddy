package com.example.studdybuddy;

/**
 * Interface for receiving speech recognition results or errors.
 * Used by SpeechRecognizerManager to communicate with the main activity.
 */
public interface SpeechListener {

    /**
     * Called when speech is successfully recognized.
     * @param result The recognized spoken text.
     */
    void onSpeechResult(String result);

    /**
     * Called when a speech recognition error occurs.
     * @param error The error message or code.
     */
    void onSpeechError(String error);
}

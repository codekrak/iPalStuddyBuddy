package com.example.studdybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Manages speech recognition functionality for the Study Buddy app.
 */
public class SpeechRecognizerManager {

    private SpeechRecognizer speechRecognizer;
    private Intent recognizerIntent;

    // Listener to handle callbacks
    private SpeechListener listener;

    public SpeechRecognizerManager(Context context, SpeechListener listener) {
        this.listener = listener;
        final SpeechListener finalListener = listener; // ✅ Needed for inner class

        // Create speech recognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);

        // Set recognition listener
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override public void onReadyForSpeech(Bundle params) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                if (finalListener != null) {
                    finalListener.onSpeechError("Speech recognition error: " + error);
                }
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    finalListener.onSpeechResult(matches.get(0));
                } else {
                    finalListener.onSpeechError("No recognizable speech detected.");
                }
            }

            @Override public void onPartialResults(Bundle partialResults) {}
            @Override public void onEvent(int eventType, Bundle params) {}
        });

        // Set up the recognition intent
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
    }

    public void startListening() {
        if (speechRecognizer != null) {
            speechRecognizer.startListening(recognizerIntent);
        }
    }

    public void stopListening() {
        if (speechRecognizer != null) {
            speechRecognizer.stopListening();
        }
    }

    public void destroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
            speechRecognizer = null;
        }
    }

    /* package-private */ RecognitionListener getRecognitionListenerForTesting() {
        return new RecognitionListener() {
            @Override public void onReadyForSpeech(Bundle params) {}
            @Override public void onBeginningOfSpeech() {}
            @Override public void onRmsChanged(float rmsdB) {}
            @Override public void onBufferReceived(byte[] buffer) {}
            @Override public void onEndOfSpeech() {}

            @Override
            public void onError(int error) {
                if (listener != null) {
                    listener.onSpeechError("Speech recognition error: " + error);
                }
            }

            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    listener.onSpeechResult(matches.get(0));
                } else {
                    listener.onSpeechError("No recognizable speech detected.");
                }
            }

            @Override public void onPartialResults(Bundle partialResults) {}
            @Override public void onEvent(int eventType, Bundle params) {}
        };
    }
}
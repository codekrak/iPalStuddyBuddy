package com.example.studdybuddy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class SpeechRecognizerManagerTest {

    @Mock private SpeechListener mockListener;
    @Mock private SpeechRecognizer mockRecognizer;

    private SpeechRecognizerManager manager;
    private Intent recognizerIntent;
    private RecognitionListener recognitionListener;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        // 1) Create real manager
        Context ctx = RuntimeEnvironment.application;
        manager = new SpeechRecognizerManager(ctx, mockListener);

        // 2) Inject mock SpeechRecognizer
        Field srField = SpeechRecognizerManager.class.getDeclaredField("speechRecognizer");
        srField.setAccessible(true);
        srField.set(manager, mockRecognizer);

        // 3) Get recognizer intent
        Field intentField = SpeechRecognizerManager.class.getDeclaredField("recognizerIntent");
        intentField.setAccessible(true);
        recognizerIntent = (Intent) intentField.get(manager);

        // 4) Hook to recognition listener
        recognitionListener = manager.getRecognitionListenerForTesting();
        assertNotNull("Listener must not be null", recognitionListener);
    }

    @Test
    public void startListening_callsStartListeningWithCorrectIntent() {
        manager.startListening();
        verify(mockRecognizer).startListening(recognizerIntent);
    }

    @Test
    public void stopListening_callsStopListening() {
        manager.stopListening();
        verify(mockRecognizer).stopListening();
    }

    @Test
    public void destroy_callsDestroy() {
        manager.destroy();
        verify(mockRecognizer).destroy();
    }

    @Test
    public void recognitionListener_onResults_forwardsResult() {
        Bundle results = new Bundle();
        ArrayList<String> matches = new ArrayList<>();
        matches.add("test result"); // ✅ Make sure it's NOT EMPTY
        results.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, matches);

        recognitionListener.onResults(results);

        verify(mockListener).onSpeechResult("test result"); // ✅ Correct verify
    }

    @Test
    public void recognitionListener_onError_forwardsError() {
        recognitionListener.onError(SpeechRecognizer.ERROR_AUDIO);
        verify(mockListener).onSpeechError(contains("Speech recognition error"));
    }

    @Test
    public void intent_isConfiguredCorrectly() {
        assertEquals(RecognizerIntent.ACTION_RECOGNIZE_SPEECH,
                recognizerIntent.getAction());

        assertEquals(RecognizerIntent.LANGUAGE_MODEL_FREE_FORM,
                recognizerIntent.getStringExtra(
                        RecognizerIntent.EXTRA_LANGUAGE_MODEL));

        Object langExtra = recognizerIntent.getSerializableExtra(
                RecognizerIntent.EXTRA_LANGUAGE);
        assertTrue("Expected a Locale", langExtra instanceof Locale);
        assertEquals(Locale.getDefault(), langExtra);

        assertEquals(1,
                recognizerIntent.getIntExtra(
                        RecognizerIntent.EXTRA_MAX_RESULTS, -1));
    }
}

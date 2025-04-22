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
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpeechRecognizerManagerTest {

    @Mock private Context mockContext;
    @Mock private SpeechListener mockListener;
    @Mock private SpeechRecognizer mockSpeechRecognizer;
    @Captor private ArgumentCaptor<Intent> intentCaptor;
    @Captor private ArgumentCaptor<RecognitionListener> listenerCaptor;

    private SpeechRecognizerManager manager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        when(SpeechRecognizer.createSpeechRecognizer(mockContext)).thenReturn(mockSpeechRecognizer);
        manager = new SpeechRecognizerManager(mockContext, mockListener);

        // Capture the listener during setup
        verify(mockSpeechRecognizer).setRecognitionListener(listenerCaptor.capture());
    }

    @Test
    public void testStartListening() {
        manager.startListening();
        verify(mockSpeechRecognizer).startListening(any(Intent.class));
    }

    @Test
    public void testStopListening() {
        manager.stopListening();
        verify(mockSpeechRecognizer).stopListening();
    }

    @Test
    public void testDestroy() {
        manager.destroy();
        verify(mockSpeechRecognizer).destroy();
    }

    @Test
    public void testRecognizerIntentSetup() {
        manager.startListening();
        verify(mockSpeechRecognizer).startListening(intentCaptor.capture());

        Intent capturedIntent = intentCaptor.getValue();
        assertEquals(RecognizerIntent.ACTION_RECOGNIZE_SPEECH, capturedIntent.getAction());
        assertEquals(RecognizerIntent.LANGUAGE_MODEL_FREE_FORM,
                capturedIntent.getStringExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL));
        assertEquals(Locale.getDefault().toString(),
                capturedIntent.getStringExtra(RecognizerIntent.EXTRA_LANGUAGE));
        assertEquals(1,
                capturedIntent.getIntExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 0));
    }

    @Test
    public void testOnResultsCallback() {
        RecognitionListener listener = listenerCaptor.getValue();
        Bundle results = new Bundle();
        ArrayList<String> matches = new ArrayList<>();
        matches.add("test speech");
        results.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, matches);

        listener.onResults(results);
        verify(mockListener).onSpeechResult("test speech");
    }

    @Test
    public void testErrorCallback() {
        RecognitionListener listener = listenerCaptor.getValue();
        listener.onError(SpeechRecognizer.ERROR_AUDIO);
        verify(mockListener).onSpeechError(contains("Speech recognition error"));
    }
}
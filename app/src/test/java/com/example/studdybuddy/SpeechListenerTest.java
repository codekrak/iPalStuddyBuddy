package com.example.studdybuddy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SpeechListenerTest {

    @Mock private SpeechListener mockListener;

    @Test
    public void onSpeechResult_callsListener() {
        String testResult = "test result";
        mockListener.onSpeechResult(testResult);
        verify(mockListener).onSpeechResult(testResult);
    }

    @Test
    public void onSpeechError_callsListener() {
        String testError = "test error";
        mockListener.onSpeechError(testError);
        verify(mockListener).onSpeechError(testError);
    }
}
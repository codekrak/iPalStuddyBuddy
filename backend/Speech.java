import android.content.Context;
import android.util.Log;

public class SpeechManagerIntegration {

    private SpeechManager mSpeechManager;
    private Context context;
    private static final String TAG = "SpeechManagerIntegration";

    public SpeechManagerIntegration(Context context) {
        this.context = context;
        initializeSpeechManager();
    }

    private void initializeSpeechManager() {
        mSpeechManager = (SpeechManager) context.getSystemService(SpeechService.SERVICE_NAME);
        if (mSpeechManager == null) {
            mSpeechManager = new SpeechManager(context, status -> {
                if (status) {
                    Log.d(TAG, "SpeechManager init success!");
                    if (mSpeechManager.getTtsEnable()) {
                        Log.d(TAG, "TTS is enabled.");
                    } else {
                        Log.d(TAG, "TTS is disabled.");
                    }
                } else {
                    Log.e(TAG, "SpeechManager init failed.");
                }
            }, "com.avatar.dialog");
        } else {
            Log.d(TAG, "SpeechManager obtained from system service.");
        }
    }

    public void setTtsListener(TtsListener listener) {
        if (mSpeechManager != null) {
            mSpeechManager.setTtsListener(listener);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
        }
    }

    public int startSpeaking(String text) {
        if (mSpeechManager != null && mSpeechManager.getTtsEnable()) {
            return mSpeechManager.startSpeaking(text);
        } else {
            Log.e(TAG, "SpeechManager is not initialized or TTS is disabled.");
            return -1; // Or some other error code
        }
    }

    public int startSpeaking(String text, boolean isRealtime, boolean isShowSubtitle) {
        if (mSpeechManager != null && mSpeechManager.getTtsEnable()) {
            return mSpeechManager.startSpeaking(text, isRealtime, isShowSubtitle);
        } else {
            Log.e(TAG, "SpeechManager is not initialized or TTS is disabled.");
            return -1; // Or some other error code
        }
    }

    public int forceStartSpeaking(String text) {
        if (mSpeechManager != null) {
            return mSpeechManager.forceStartSpeaking(text);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return -1; // Or some other error code
        }
    }

    public int forceStartSpeaking(String text, boolean isRealtime, boolean isShowSubtitle) {
        if (mSpeechManager != null) {
            return mSpeechManager.forceStartSpeaking(text, isRealtime, isShowSubtitle);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return -1; // Or some other error code
        }
    }

    public boolean stopSpeaking(int requestId) {
        if (mSpeechManager != null) {
            return mSpeechManager.stopSpeaking(requestId);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public boolean isSpeaking() {
        if (mSpeechManager != null) {
            return mSpeechManager.isSpeaking();
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public boolean isSpeaking(int requestId) {
        if (mSpeechManager != null) {
            return mSpeechManager.isSpeaking(requestId);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public boolean setTtsEnable(boolean enable) {
        if (mSpeechManager != null) {
            return mSpeechManager.setTtsEnable(enable);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public boolean getTtsEnable() {
        if (mSpeechManager != null) {
            return mSpeechManager.getTtsEnable();
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public int getTtsSpeed() {
        if (mSpeechManager != null) {
            return mSpeechManager.getTtsSpeed();
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return -1;
        }
    }

    public int setTtsSpeed(int speed) {
        if (mSpeechManager != null) {
            return mSpeechManager.setTtsSpeed(speed);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return -1;
        }
    }

    public int setTtsSpeed(int speed, boolean persist) {
        if (mSpeechManager != null) {
            return mSpeechManager.setTtsSpeed(speed, persist);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return -1;
        }
    }

    public boolean isEstablished() {
        if (mSpeechManager != null) {
            return mSpeechManager.isEstablished();
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public void shutdown() {
        if (mSpeechManager != null) {
            mSpeechManager.shutdown();
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
        }
    }

    public boolean setChatEnable(boolean enable) {
        if (mSpeechManager != null) {
            return mSpeechManager.setChatEnable(enable);
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }

    public boolean getChatEnable() {
        if (mSpeechManager != null) {
            return mSpeechManager.getChatEnable();
        } else {
            Log.e(TAG, "SpeechManager is not initialized.");
            return false;
        }
    }
}
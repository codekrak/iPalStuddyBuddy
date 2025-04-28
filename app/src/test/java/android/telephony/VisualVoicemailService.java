package android.telephony;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Stub for android.telephony.VisualVoicemailService (API 26+),
 * with nested VisualVoicemailTask so tests compile under API25.
 */
public abstract class VisualVoicemailService extends Service {
    public VisualVoicemailService() { super(); }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Stub of the nested VisualVoicemailTask class.
     * Real signature is something like VisualVoicemailService.VisualVoicemailTask.
     */
    public static class VisualVoicemailTask {
        // no-op stub
        public VisualVoicemailTask() { }
        // stub any methods your code might call:
        // e.g. public void run() { }
    }
}

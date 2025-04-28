package android.telecom;

import android.os.Bundle;

/**
 * Stub for android.telecom.Call so we can define the nested RttCall.
 */
public class Call {
    // no-op stub of the outer Call class

    /**
     * Stub of the nested RttCall class (API 29+),
     * so tests compile under compileSdkVersion=25.
     */
    public static final class RttCall extends Call {
        public RttCall(Bundle inCallServiceExtras) {
            super();
            // no-op
        }
        // stub any methods your code invokes, e.g.:
        // public void setRemoteCall(RttCall remote) { }
    }
}

package com.example.studdybuddy;

import org.robolectric.annotation.Implements;

// Override java.lang.System.exit(int) during tests so it never actually kills the
// test JVM or interferes with Robolectric’s lifecycle.
@Implements(className = "java.lang.System")
public class ShadowSystem {
    public static void exit(int status) {
        // no-op
    }
}

package android.telephony;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Stub for android.telephony.VisualVoicemailSms (API 26+),
 * so tests compile under compileSdkVersion=25.
 */
public final class VisualVoicemailSms implements Parcelable {
    // No-op stub

    public VisualVoicemailSms() { }

    // Parcelable boilerplate:
    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) { }

    public static final Creator<VisualVoicemailSms> CREATOR =
            new Creator<VisualVoicemailSms>() {
                @Override
                public VisualVoicemailSms createFromParcel(Parcel in) {
                    return new VisualVoicemailSms();
                }
                @Override
                public VisualVoicemailSms[] newArray(int size) {
                    return new VisualVoicemailSms[size];
                }
            };
}

package eliran.recepie.com.contentproviderhw;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by eliran on 3/28/2017.
 */

public class SmsObj implements Parcelable {

    String ContactName;
    String BodySms;

    public SmsObj(String contactName, String bodySms) {
        ContactName = contactName;
        BodySms = bodySms;
    }

    protected SmsObj(Parcel in) {
        ContactName = in.readString();
        BodySms = in.readString();
    }

    public static final Creator<SmsObj> CREATOR = new Creator<SmsObj>() {
        @Override
        public SmsObj createFromParcel(Parcel in) {
            return new SmsObj(in);
        }

        @Override
        public SmsObj[] newArray(int size) {
            return new SmsObj[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(ContactName);
        dest.writeString(BodySms);
    }
}

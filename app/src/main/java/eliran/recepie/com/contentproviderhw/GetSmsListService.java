package eliran.recepie.com.contentproviderhw;

import android.app.IntentService;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Parcelable;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;


public class GetSmsListService extends IntentService {
ArrayList<SmsObj> AllSms;
    public GetSmsListService() {
        super("GetSmsListService");
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onHandleIntent(Intent intent) {
      Cursor cursor=getContentResolver().query(Telephony.Sms.CONTENT_URI,null,null,null,null);
        AllSms=new ArrayList<SmsObj>();
        while(cursor.moveToNext())
        {
            String columnName= Telephony.Sms.ADDRESS;
            String BodyColumnName=Telephony.Sms.BODY;
            int columnPosition= cursor.getColumnIndex(columnName);
            int BodycolumnPosition= cursor.getColumnIndex(BodyColumnName);
            String personName=cursor.getString(columnPosition);
            String Body=cursor.getString(BodycolumnPosition);

            AllSms.add(new SmsObj(personName,Body));
        }
        Intent intent1=new Intent("com.recepiesearch.eliran.DONE!");
        intent1.putParcelableArrayListExtra("AllSms",AllSms);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent1);
    }

}

package eliran.recepie.com.contentproviderhw;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SmsListFrag smsListFrag=new SmsListFrag();
        getFragmentManager().beginTransaction().addToBackStack("SmsListFrag").replace(R.id.activity_main,smsListFrag).commit();
    }
}

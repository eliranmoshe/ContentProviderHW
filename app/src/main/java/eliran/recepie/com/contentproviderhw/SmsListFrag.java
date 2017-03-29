package eliran.recepie.com.contentproviderhw;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 */
public class SmsListFrag extends Fragment {

RecyclerView recyclerView;
    RvAdapter adapter;
    ArrayList<SmsObj> AllSms;
    public SmsListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view=inflater.inflate(R.layout.fragment_sms_list, container, false);



        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        IntentFilter intentFilter=new IntentFilter("com.recepiesearch.eliran.DONE!");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new SmsListener(),intentFilter);
        recyclerView= (RecyclerView) view.findViewById(R.id.SmsListRV);
        final Intent intent=new Intent(getActivity(),GetSmsListService.class);
        getActivity().startService(intent);
        Button ExportBtn= (Button) view.findViewById(R.id.ExportBtn);
        ExportBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> StringArray = new ArrayList<String>();
                Gson gson = new Gson();

                for (int i = 0; i < AllSms.size(); i++) {
                    StringArray.add(gson.toJson(AllSms.get(i)));
                }

                Log.d("dvfds", "fsdfsd");

                StringBuffer result = new StringBuffer();

                for (int i = 0; i < StringArray.size(); i++) {
                    result.append(StringArray.get(i));
                }

                String mynewstring = result.toString();
             //   writeToFile(mynewstring, getActivity());
                File filelocation = new File(Environment.getExternalStorageDirectory().getPath(), "config.txt");


                try {

                    // creates the file
                    filelocation.createNewFile();

                    // creates a FileWriter Object
                    FileWriter writer = new FileWriter(filelocation);

                    // Writes the content to the file
                    writer.write( mynewstring );
                    writer.flush();
                    writer.close();
                } catch (IOException ee)
                {

                }



                Uri path = Uri.fromFile(filelocation);


                Intent intent1=new Intent(Intent.ACTION_SEND);
                intent1.setType("message/rfc822");
                intent1.putExtra(Intent.EXTRA_STREAM,path);
                getActivity().startActivity(Intent.createChooser(intent1, "E-mail"));
            }
        });






        return view;
    }




    public class SmsListener extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            AllSms=intent.getParcelableArrayListExtra("AllSms");
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            adapter=new RvAdapter(AllSms,context);
            recyclerView.setAdapter(adapter);
        }
    }

}

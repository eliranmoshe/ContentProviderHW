package eliran.recepie.com.contentproviderhw;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;
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
        View view=inflater.inflate(R.layout.fragment_sms_list, container, false);
        IntentFilter intentFilter=new IntentFilter("com.recepiesearch.eliran.DONE!");
        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(new SmsListener(),intentFilter);
        recyclerView= (RecyclerView) view.findViewById(R.id.SmsListRV);
        Intent intent=new Intent(getActivity(),GetSmsListService.class);
        getActivity().startService(intent);
        getActivity().findViewById(R.id.ExportBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO shared oll AllSms to text file
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

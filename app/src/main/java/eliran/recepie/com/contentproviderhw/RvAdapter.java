package eliran.recepie.com.contentproviderhw;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.Telephony;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eliran on 3/28/2017.
 */

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.RvHolder> {

    ArrayList<SmsObj> AllSms;
    Context context;
    public RvAdapter(ArrayList<SmsObj> AllSms,Context context) {
        this.context=context;
        this.AllSms = AllSms;
    }

    @Override
    public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.sms_item,parent,false);
        RvHolder rvHolder=new RvHolder(itemview);
        return rvHolder;
    }

    @Override
    public void onBindViewHolder(RvHolder holder, int position) {


        SmsObj smsObj=AllSms.get(position);
        holder.BindData(smsObj);
    }

    @Override
    public int getItemCount() {

        return AllSms.size();
    }






class RvHolder extends RecyclerView.ViewHolder{
TextView ContactTv;
    TextView BodyTv;
    public RvHolder(final View itemView) {
        super(itemView);

        ContactTv= (TextView) itemView.findViewById(R.id.ConteactTv);
        BodyTv= (TextView) itemView.findViewById(R.id.BodySmsTV);
    }

    public void BindData(final SmsObj smsObj)
    {

        ContactTv.setText(smsObj.ContactName);
        BodyTv.setText(smsObj.BodySms);
        ContactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //go to message app with the current phone number
                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                smsIntent.setType("vnd.android-dir/mms-sms");
                smsIntent.putExtra("address",smsObj.ContactName);
                //smsIntent.putExtra("sms_body","Body of Message");
                context.startActivity(smsIntent);
            }
        });

    }
}
}


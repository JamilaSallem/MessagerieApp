package com.example.jamila.messagerieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class AdapterClass extends ArrayAdapter<Message> {
    Context ctx;

    public AdapterClass( Context context, List<Message> objects) {
        super(context, R.layout.model, objects);
        this.ctx=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView=LayoutInflater.from(ctx).inflate(R.layout.model,parent,false);

        TextView sender=convertView.findViewById(R.id.Sender);
        TextView msg=convertView.findViewById(R.id.Message);
        TextView time=convertView.findViewById(R.id.Time);

        Message message=getItem(position);
        sender.setText(message.getSender());
        msg.setText(""+message.getMsg());
        time.setText(""+message.getTime());

        return convertView;
    }
}

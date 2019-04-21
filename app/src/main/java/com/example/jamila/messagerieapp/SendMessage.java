package com.example.jamila.messagerieapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class SendMessage extends AppCompatActivity {
    EditText time,sender,msg;
    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);

        time = (EditText) findViewById(R.id.time);
        sender=findViewById(R.id.sender);
        msg=findViewById(R.id.message);

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(SendMessage.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute ) {
                        time.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, false);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });

        send=findViewById(R.id.send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String exped=sender.getText().toString();
                String mesg= msg.getText().toString();
                String temp=time.getText().toString();
                Message message=new Message(exped,mesg,temp);
                DBmessage db=new DBmessage(SendMessage.this);
                db.addMessage(message);
                Toast.makeText(SendMessage.this, "Message Sent", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(SendMessage.this,activity_list.class);
                startActivity(intent);
            }
        });

    }
}

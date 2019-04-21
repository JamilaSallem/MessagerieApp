package com.example.jamila.messagerieapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateMessage extends AppCompatActivity {

    EditText sender,msg,time;
    Button update;
    int id;
    DBmessage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_message);

        sender=findViewById(R.id.addSender);
        msg=findViewById(R.id.addMessage);
        time=findViewById(R.id.addTime);
        update=findViewById(R.id.updateBtn);

        Intent intent=getIntent();
        id=intent.getIntExtra("id",0);

        db=new DBmessage(this);
        final Message message=db.getMessageById(id);

        sender.setText(message.getSender());
        msg.setText(""+message.getMsg());
        time.setText(message.getTime());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String exped=sender.getText().toString();
                String mesg=msg.getText().toString();
                String temp=time.getText().toString();

                Message message=new Message(exped,mesg,temp);
                db.updateMessage(message);
                Toast.makeText(UpdateMessage.this, "Message Updated", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(UpdateMessage.this,activity_list.class);
                startActivity(intent1);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==id)
        {
            showPopUp();

        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopUp() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle("Press yes to confirm").setMessage("Do you really want ro remove this contact?").setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                db.deleteMessage(id);
                Toast.makeText(UpdateMessage.this, "Message Deleted", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateMessage.this,activity_list.class);
                startActivity(intent);

            }
        });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}

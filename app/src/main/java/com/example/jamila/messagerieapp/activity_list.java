package com.example.jamila.messagerieapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class activity_list extends AppCompatActivity {
    ListView list;
    DBmessage db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list=findViewById(R.id.liste);
        db=new DBmessage(this);
        ArrayList<Message> messageArrayList=db.getAllMessages();
        messageArrayList.add(new Message("Jamila","bonjour","12:13"));
        final AdapterClass adapter=new AdapterClass(this,messageArrayList);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {

                Message message=(Message) parent.getItemAtPosition(position);
                int id=message.getId();

                Intent intent=new Intent(activity_list.this,UpdateMessage.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.add)
        {
            Intent intent=new Intent(activity_list.this,SendMessage.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }
}

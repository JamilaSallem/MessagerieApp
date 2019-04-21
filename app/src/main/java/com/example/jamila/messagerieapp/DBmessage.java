package com.example.jamila.messagerieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBmessage extends SQLiteOpenHelper {
    private static final String DB_NAME="dbmessage";
    private static final int VERSION=1;
    private static final String TB_NAME="message";

    public DBmessage (Context context){
        super(context,DB_NAME, null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create="CREATE TABLE "+ TB_NAME+" (sender VARCHAR2(20), message VARCHAR2(200), time VARCHAR2(10),id INTEGER PRIMARY KEY)";
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String delete="DROP TABLE IF EXISTS "+TB_NAME;
        db.execSQL(delete);
        onCreate(db);

    }

    public void addMessage(Message message)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("sender",message.getSender());
        values.put("message",message.getMsg());
        values.put("time",message.getTime());
        db.insert(TB_NAME,null,values);
    }

    public void deleteMessage(int id)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.delete(TB_NAME,"id=?",new String[]{String.valueOf(id)});
    }

    public Message updateMessage(Message message)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("sender",message.getSender());
        values.put("message",message.getMsg());
        values.put("time",message.getTime());
        db.update(TB_NAME,values,"id=?",new String[]{String.valueOf(message.getId())});

        return message;
    }

    public ArrayList<Message> getAllMessages()
    {
        SQLiteDatabase db=getReadableDatabase();
        String query="SELECT * FROM "+TB_NAME;
        Cursor cursor=db.rawQuery(query,null);
        ArrayList<Message> array=new ArrayList<Message>();
        if (cursor.moveToFirst())
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                String sender=cursor.getString(cursor.getColumnIndex("sender"));
                String msg=cursor.getString(cursor.getColumnIndex("message"));
                String time=cursor.getString(cursor.getColumnIndex("time"));

                Message message=new Message(sender,msg,time,id);
                array.add(message);
            }while (cursor.moveToNext());
        }
        return array;
    }

    public Message getMessageById(int id)
    {
        SQLiteDatabase db=getReadableDatabase();
        String query=" SELECT * FROM "+TB_NAME+" WHERE id="+id;
        Cursor cursor=db.rawQuery(query,null);
        Message message=null;
        if (cursor.moveToFirst())
        {
            int id_m=cursor.getInt(cursor.getColumnIndex("id"));
            String sender_m=cursor.getString(cursor.getColumnIndex("sender"));
            String msg_m=cursor.getString(cursor.getColumnIndex("message"));
            String time_m=cursor.getString(cursor.getColumnIndex("time"));

            message=new Message(sender_m,msg_m,time_m,id_m);
        }
        return message;
    }
}




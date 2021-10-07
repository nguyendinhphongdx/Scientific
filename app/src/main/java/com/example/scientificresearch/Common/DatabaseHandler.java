package com.example.scientificresearch.Common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.scientificresearch.Model.Memory;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "schoolManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "memories";
    private static final String KEY_ID = "id";
    private static final String KEY = "key";
    private static final String VALUES = "value";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_memories_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT) ", TABLE_NAME, KEY_ID, KEY, VALUES);
        sqLiteDatabase.execSQL(create_memories_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_memories_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        sqLiteDatabase.execSQL(drop_memories_table);
        onCreate(sqLiteDatabase);
    }
    public void addMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(!existsMemory(memory.getKey())){
            ContentValues values = new ContentValues();
            values.put(KEY, memory.getKey());
            values.put(VALUES, memory.getValue());
            db.insert(TABLE_NAME, null, values);
        }else{
            Log.d("MEMORIES", "addMemory: "+memory.getKey()+ " is exists");
        }
        db.close();
    }
    public Memory getMemory(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY + " = ?", new String[] { String.valueOf(key) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Memory memory = new Memory();
        if(cursor.getCount()==0){
            memory.setKey("");
            memory.setValue("");
        }else{
            memory.setKey(cursor.getString(1));
            memory.setValue(cursor.getString(2));
        }
        return memory;
    }
    public List<Memory> getAllMemory() {
        List<Memory>  memories = new ArrayList<>();
        String query = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            Memory memory = new Memory(cursor.getString(1), cursor.getString(2));
            memories.add(memory);
            cursor.moveToNext();
        }
        return memories;
    }
    public void updateMemory(Memory memory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY, memory.getKey());
        values.put(VALUES, memory.getValue());
        db.update(TABLE_NAME, values, KEY + " = ?", new String[] { String.valueOf(memory.getKey()) });
        db.close();
    }
    public void deleteMemory(String key) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, KEY + " = ?", new String[] { String.valueOf(key) });
        db.close();
    }
    public boolean existsMemory(String key) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY + " = ?", new String[] { String.valueOf(key) },null, null, null);
        if(cursor != null)
            return true;
        return false;
    }
}

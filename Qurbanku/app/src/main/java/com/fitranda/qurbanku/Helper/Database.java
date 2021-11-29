package com.fitranda.qurbanku.Helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbqurban";
    private static final int VERSION = 1;

    SQLiteDatabase db;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        db = this.getWritableDatabase();
    }

    public boolean runSQL(String sql){
        try {
            db.execSQL(sql);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Cursor select(String sql){
        try {
            return db.rawQuery(sql,null);
        }catch (Exception e){
            return null;
        }
    }

    public void buatTabel(){
        String tblbarang = "CREATE TABLE \"tblkeranjang\" (\n" +
                "\t\"idbarang\"\tINTEGER,\n" +
                "\t\"hewan\"\tTEXT,\n" +
                "\t\"harga\"\tINTEGER,\n" +
                "\t\"idpenjual\"\tINTEGER,\n" +
                "\t\"toko\"\tTEXT,\n" +
                "\t\"alamat\"\tTEXT,\n" +
                "\t\"gambar\"\tTEXT,\n" +
                "\t\"idhewan\"\tTEXT,\n" +
                "\tPRIMARY KEY(\"idbarang\" AUTOINCREMENT)\n" +
                ");";
        String drop = "DROP TABLE tblkeranjang";

        runSQL(tblbarang);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

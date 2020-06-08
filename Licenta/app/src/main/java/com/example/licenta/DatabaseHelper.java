package com.example.licenta;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.security.PublicKey;
import java.sql.Blob;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String database_name = "Money.db";
    public static final String tablename = "MONEY";
    public static final String col1 = "ID";
    public static final String col0="CATEGORY";
    public static final String col2 = "TYPE";
    public static final String col3 = "DESCRIBE";
    public static final String col4 = "PRICE";
    public static final String col5 = "DATE";

    public static final String tablename1="IMAGINI";
    public static final String id1="ID_IMG";
    public static final String nume="NUME";
     public static final String img="IMG";

    public DatabaseHelper(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + tablename + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,CATEGORY TEXT,TYPE TEXT,DESCRIBE TEXT,PRICE Text,DATE Text)");
        db.execSQL("create table " + tablename1 + "(NUME TEXT,IMG INT PRIMARY KEY)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tablename);
        db.execSQL("DROP TABLE IF EXISTS " + tablename1);
        onCreate(db);
    }

    //img


    public boolean insertImg( String name, int image) throws SQLiteException {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new  ContentValues();
        cv.put(nume,    name);
        cv.put(img,   image);
        long result=database.insert( tablename1, null, cv );
        if(result==-1)
            return false;
        else return true;
    }

    public ArrayList<Expense> getAllImag(){
        ArrayList<Expense> e=new ArrayList<Expense>();
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("select * from "+ tablename1,null);
        while (rez.moveToNext()){
            String nume=rez.getString(0);
            int img=rez.getInt(1);
            Expense expense=new Expense(nume,img);
            e.add(expense);
        }
        return e;
    }

    //IMG


    public boolean insertData(String category,String type, String describe, String price, String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col0,category);
        contentValues.put(col2, type);
        contentValues.put(col3, describe);
        contentValues.put(col4, price);
        contentValues.put(col5, date);
        long result = db.insert(tablename, null, contentValues);
        if (result == -1)
            return false;
        else return true;
    }


    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rez = db.rawQuery("select * from " + tablename, null);
        return rez;
    }

    public boolean updateData(String descriere, String price, String data, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col3, descriere);
        contentValues.put(col4, price);
        contentValues.put(col5, data);
        String[] vector = new String[]{id};
        long result = db.update("MONEY", contentValues, "ID = ?", vector);
        if (result == -1)
            return false;
        else return true;
    }


    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("MONEY", "ID = ?", new String[]{id});
    }


    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rez = db.rawQuery("select id,category,type, describe,price,date from " + tablename, null);
        return rez;
    }

    public Cursor getTransaction_dupaDescriere(String descriere) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor rez = db.rawQuery("SELECT * FROM " + tablename + " where type = ?", new String[]{descriere});
        return rez;
    }

    public Cursor getTransaction_dupaCategorie(String c,String luna){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("SELECT *FROM "+ tablename+ " where category =? and substr(date,4,2)=? ",new String[]{c,luna});
        return rez;
    }

    public Cursor getTransaction_dupaCategorie_luna_an(String c,String luna,String an){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("SELECT *FROM "+ tablename+ " where category =? and substr(date,4,2)=? and substr(date,7,4)=?",new String[]{c,luna,an});
        return rez;
    }

 /*  public Cursor getTransaction_dupaData(String luna)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("SELECT * FROM   EXPENSE  WHERE substr(date,4,2)=?",new String[]{luna} );
        return rez;
    }*/

    public Cursor getId(String type)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String query="SELECT "+col1 + "FROM" +tablename +" WHERE "+col2 + " = '" + type+"'";
        Cursor data=db.rawQuery(query,null);
        return data;
    }


    public float sumaExpense_Income(String category,String luna){

        float total = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(" + col4+ ")FROM " + tablename +" WHERE category = ? and substr(date,4,2) = ?",new String[]{category,luna});
        if(c.moveToFirst()){
            total = c.getFloat(0);
        }
        return total;
    }



    public Cursor getPrice_Type(String category,String luna){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT PRICE,TYPE FROM " + tablename +" WHERE category = ? and substr(date,4,2) = ?",new String[]{category,luna});
        return c;
    }

    public Cursor getPrice_TypeLUNA_AN(String category,String luna,String an){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT PRICE,TYPE FROM " + tablename +" WHERE category = ? and substr(date,4,2) = ? and substr(date,7,4) = ? ",new String[]{category,luna,an});
        return c;
    }

    public Float  getPret_dupaZiLuna(String zi,String luna,String categ,String an){
        float total=0;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("SELECT SUM(" +col4+ ") FROM "+ tablename + " WHERE substr(date,1,2)= ? and substr(date,4,2) = ? and category=? and substr(date,7,4) = ?",new String[]{zi,luna,categ,an});
        if(c.moveToFirst()){
            total = c.getFloat(0);
        }
        return total;
    }

    public float  sumaExpenseIncome_dupaAn(String categ,String luna,String an){
        float total = 0;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT SUM(" + col4+ ")FROM " + tablename +" WHERE category = ? and substr(date,7,4) = ?and substr(date,4,2) = ?",new String[]{categ,an,luna});
        if(c.moveToFirst()){
            total = c.getFloat(0);
        }
        return total;
    }


    public Cursor getData(){

        SQLiteDatabase db=this.getWritableDatabase();
        Cursor c=db.rawQuery("select date from "+ tablename ,null);
        return c;
    }

    public Cursor get_dupaluna(String l)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("select id,category,type, describe,price,date from " +tablename + " where substr(date,4,2)=? ",new String[]{l});
        return rez;
    }
    public Cursor get_dupaluna_an(String l,String an)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor rez=db.rawQuery("select id,category,type, describe,price,date from " +tablename + " where substr(date,4,2)=? and substr(date,7,4) = ?",new String[]{l,an});
        return rez;
    }

  }

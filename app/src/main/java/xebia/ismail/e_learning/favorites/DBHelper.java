package xebia.ismail.e_learning.favorites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import java.util.ArrayList;
import java.util.List;
/**
class designed to work with the database
**/


public class DBHelper extends SQLiteOpenHelper {
    private static String DB_NAME = "contact_db";
    public static  String TB_NAME = "contact_info";
    private static int DB_VERSION = 1;

    public static String ID  = "id";
    public static String NAME  = "name";
    private static String EMAIL  = "email";
    private static String IMAGE  = "image";
    private static String DATE  = "date";
    private static String TIME  = "time";
    public static String PRICE = "price";

    private static String QUERY = "create table " + TB_NAME + " ( " +
            ID+" int auto_increment, " +
            NAME+" text," +
            EMAIL+" text," +
            IMAGE+" text," +
            TIME+" text," +
            DATE+" text," +
            PRICE+" text"+
            ")";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE "+TB_NAME+" IF EXISTS");
        sqLiteDatabase.execSQL(QUERY);
    }

    public void addContact(String name,String email, String image, String time, String date, String price){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,name);
        cv.put(EMAIL,email);
        cv.put(IMAGE,image);
        cv.put(TIME,time);
        cv.put(DATE,date);
        cv.put(PRICE,price);
        db.insert(TB_NAME,null,cv);
    }
    public List<ContactModel> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from "+TB_NAME,null);

        String name="";
        String email="";
        String image="";
        String time="";
        String date="";
        String price="";
        int id=0;

        ArrayList<ContactModel> arrayList = new ArrayList<>();

        if(c.moveToFirst()){
            do{
                id =c.getInt(0);
                name = c.getString(1);
                email = c.getString(2);
                image = c.getString(3);
                time = c.getString(4);
                date = c.getString(5);
                price=c.getString(6);
                ContactModel contactModel = new ContactModel(id,name,email,image,time,date,price);
                arrayList.add(contactModel);
            }while (c.moveToNext());

        }
        return arrayList;
    }
}

package com.example.thucu.sqldemo;

import android.database.Cursor;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity {
    Database database;
    TextView txtMain;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMain = (TextView) findViewById(R.id.txtMain);
        // Create Database
        database = new Database(this, "noteDB.sqlite", null, 1);
//        dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        // Create Table
        database.queryWriteData("CREATE TABLE IF NOT EXISTS NoteDetail (ID INTEGER PRIMARY KEY AUTOINCREMENT, noteTitle VARCHAR(200))");


        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
        String strDate1 = "2017-08-01 11:00:00";
        String strdate2 = "2017-08-04 11:00:00";
        try {
            Date date1 = dateFormat1.parse(strDate1);
            Date date2 = dateFormat1.parse(strdate2);
            long diff = date2.getDate() - date1.getDate();
            long diff2 = date2.getDate() + 1;
            Log.d("Date","Count: " + diff);
            Toast.makeText(this, "Count: " + diff, Toast.LENGTH_SHORT).show();
            txtMain.setText("Count: " + diff + " - Date: " + diff2);
            txtMain.setVisibility(View.VISIBLE);

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        String myDate = dateFormat.format(new Date());
//        Toast.makeText(this, "date: " + myDate, Toast.LENGTH_SHORT).show();
        configDB();
        insertMenu("Tuoi Tre","","","","",1,"");
        insertNewsItem("Bai bao 1","","https://baodientu.demo/baibao1","https://baodientu.demo/baibao1.jpg",0,"","","","","","","","","");
        // Insert Data
//        database.queryWriteData("INSERT INTO NoteDetail (id, noteTitle) VALUES (null, 'Note Title 1');");
//        Cursor count = database.queryCountTable("NoteDetail");
//        while (count.moveToNext()){
//            String cnt = count.getString(0);
//            Toast.makeText(this, "cnt: " + cnt, Toast.LENGTH_SHORT).show();
//        }

        Cursor cursor2 = database.queryReadData("SELECT COUNT(*) FROM NewsInfo WHERE CAST ((JULIANDAY(CURRENT_DATE) - JULIANDAY(DateCreate)) AS INTEGER) > 3");
        while (cursor2.moveToNext()){
            String cnt = cursor2.getString(0);
            Toast.makeText(this, "cnt 2: " + cnt, Toast.LENGTH_SHORT).show();
        }
        // Select Data
        Cursor selectNote = database.queryReadData("SELECT * FROM NoteDetail");
        while (selectNote.moveToNext()){
            // Lấy data từ cột noteTitle, số thứ tự cột noteTitle là 1
            String noteTitle = selectNote.getString(1);
//            txtMain.setText(noteTitle);
//            Toast.makeText(this, "Title: " + noteTitle, Toast.LENGTH_SHORT).show();
        }

    }
    private void configDB(){
        // Create database
//        database = new Database(this, "feedDB", null, 1);
//        database.queryDropTable("Newspaper");
        // Create table Newspaper
        database.queryWriteData("CREATE TABLE IF NOT EXISTS Newspaper (id INTEGER PRIMARY KEY AUTOINCREMENT, newspaperName VARCHAR(200), newspapgerDescr VARCHAR(200), newspaperURL VARCHAR(200), newspaperKeyword VARCHAR(200), newspaperLogoURL VARCHAR(200), newspaperLogoLocal BLOB)");

        // Create table NewsCategoryMaster
        database.queryWriteData("CREATE TABLE IF NOT EXISTS NewsCategoryMaster (id INTEGER PRIMARY KEY AUTOINCREMENT, categorymasterName VARCHAR(200), categorymasterDescr VARCHAR(200), categorymasterKeyword VARCHAR(200))");

        // Create table NewsCategorySub
        database.queryWriteData("CREATE TABLE IF NOT EXISTS NewsCategorySub (id INTEGER PRIMARY KEY AUTOINCREMENT, categorysubName VARCHAR(200), categorySubDescr VARCHAR(200), categorySubKeyword VARCHAR(200), categorymasterKeyword VARCHAR(200))");

        // Create table NewsCategoryDetail
        database.queryWriteData("CREATE TABLE IF NOT EXISTS NewsCategoryDetail (id INTEGER PRIMARY KEY AUTOINCREMENT, newspaperName VARCHAR(200), newspaperKeyword VARCHAR(200), categorymasterName VARCHAR(200), categorymasterKeyword VARCHAR(200), categorysubName VARCHAR(200), categorySubKeyword VARCHAR(200), categoryURL VARCHAR(200), DateCreate DATETIME, DateModify DATETIME, Status VARCHAR(200))");

        // Create table NewsInfo
        database.queryWriteData("CREATE TABLE IF NOT EXISTS NewsInfo (id INTEGER PRIMARY KEY AUTOINCREMENT, newsTitle VARCHAR(200), newsDescr VARCHAR(200), newsURL VARCHAR(200), newsImageURL VARCHAR(200), newsImageLocal BLOB, newspaperName VARCHAR(200), newspaperKeyword VARCHAR(200), categorymasterName VARCHAR(200), categorymasterKeyword VARCHAR(200), categorysubName VARCHAR(200), categorySubKeyword VARCHAR(200), DateCreate DATETIME, DateModify DATETIME, Status VARCHAR(200))");

        // Create table userConfig
        database.queryWriteData("CREATE TABLE IF NOT EXISTS userConfig (id INTEGER PRIMARY KEY AUTOINCREMENT, username VARCHAR(200), lastSelected VARCHAR(200))");

    }
    private void insertMenu(String newspaperName, String newspapgerDescr,String newspaperURL, String newspaperKeyword, String newspaperLogoURL, int newspaperLogoLocal, String status){
//        newspaperName = "";
//        newspapgerDescr = "";
//        newspaperURL = "";
//        newspaperKeyword = "";
//        newspaperLogoURL = "";
//        newspaperLogoLocal = 0;
        status = "OPEN";
        String sqlQuery = "INSERT INTO Newspaper (id, newspaperName, newspapgerDescr, newspaperURL, newspaperKeyword, newspaperLogoURL, newspaperLogoLocal, Status ) " +
                "VALUES (null, '" +newspaperName+"', '" +newspapgerDescr+"', '" +newspaperURL+"', '" +newspaperKeyword+"', '"+newspaperLogoURL+"', '" +newspaperLogoLocal+"', '" +status+"' )";
//        database.queryWriteData(sqlQuery);
        Log.d("Data", sqlQuery);

    }
    private void insertNewsItem(String newsTitle , String newsDescr , String newsURL , String newsImageURL , int newsImageLocal , String newspaperName , String newspaperKeyword , String categorymasterName , String categorymasterKeyword , String categorysubName , String categorySubKeyword , String DateCreate , String DateModify , String Status){
//        newsTitle  = "";
//        newsDescr  = "";
//        newsURL  = "";
//        newsImageURL  = "";
//        newsImageLocal  = 0;
//        newspaperName  = "";
//        newspaperKeyword  = "";
//        categorymasterName  = "";
//        categorymasterKeyword  = "";
//        categorysubName  = "";
//        categorySubKeyword  = "";
        String currentDate = dateFormat.format(new Date());
        DateCreate  = currentDate;
        DateModify  = currentDate;
        Status = "Open";

        String sqlQuery = "INSERT INTO NewsInfo(id, newsTitle, newsDescr, newsURL, newsImageURL, newsImageLocal, newspaperName, newspaperKeyword, categorymasterName, categorymasterKeyword, categorysubName, categorySubKeyword, DateCreate, DateModify, Status) " +
                          "VALUES (null, '"+newsTitle+"', '"+newsDescr+"', '"+newsURL+"', '"+newsImageURL+"', "+newsImageLocal+" , '"+newspaperName+"', '"+newspaperKeyword+"', '"+categorymasterName+"', '"+categorymasterKeyword+"', '"+categorysubName+"', '"+categorySubKeyword+"', '"+DateCreate+"', '"+DateModify+"', '"+Status+"')";
        database.queryWriteData(sqlQuery);
        Log.d("Data", sqlQuery);

    }




}

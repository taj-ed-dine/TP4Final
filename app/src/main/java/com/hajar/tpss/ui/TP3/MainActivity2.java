package com.hajar.tpss.ui.TP3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hajar.tpss.R;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase db;
    ArrayAdapter<Contact> adapter;
    ArrayList<Contact> contacts = new ArrayList();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        db=DataBaseHelper.getInstance(getApplicationContext()).getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from contacts",null);
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String Prenom=cursor.getString(1);
            String Name=cursor.getString(2);
            String Phone=cursor.getString(3);
            String Email=cursor.getString(4);
            Contact contact=new Contact(id,Prenom,Name,Phone,Email);
            contacts.add(contact);
        }
        listView=findViewById(R.id.listView);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,contacts);//✔ يتم عرضها في ListView باستخدام ArrayAdapter.
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((prent ,view,position,id)->{
            Contact contact=contacts.get(position);
            Intent intent =new Intent(this,MainActivity3.class);
            intent.putExtra("contact",contact);
            startActivity(intent);
        });


    }
}

package com.hajar.tpss.ui.TP3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.hajar.tpss.R;

public class MainActivity3 extends AppCompatActivity {
    Contact contact;
    TextView PrenomText,NameText,PhoneText,EmailText;

    SQLiteDatabase db;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db=DataBaseHelper.getInstance(getApplicationContext()).getWritableDatabase();

        PrenomText=findViewById(R.id.PrenomText);
        NameText=findViewById(R.id.NameText);
        PhoneText=findViewById(R.id.PhoneText);
        EmailText=findViewById(R.id.EmailText);
        contact=(Contact) getIntent().getSerializableExtra("contact");
        PrenomText.setText(contact.getPrenom());
        NameText.setText(contact.getName());
        PhoneText.setText(contact.getPhone());
        EmailText.setText(contact.getEmail());


    }

    public void delete(View view) {
        db.execSQL("delete from contacts where id=?",new Integer[]{contact.getId()});
        Intent intent =new Intent(this,MainActivity2.class);
        startActivity(intent);
        finish();
    }

    public void Update(View view) {
        String Prenom=PrenomText.getText().toString();
        String Name=NameText.getText().toString();
        String Phone=PhoneText.getText().toString();
        String Email=EmailText.getText().toString();
        db.execSQL("update contacts set Prenom=?,Name=?,Phone=?,Email=? where id=?",
                new String[]{
                        Prenom,
                        Name,
                        Phone,
                        Email,
                        String.valueOf(contact.getId())
                }
        );
        Intent intent =new Intent(this,MainActivity2.class);
        startActivity(intent);
        finish();

    }
}
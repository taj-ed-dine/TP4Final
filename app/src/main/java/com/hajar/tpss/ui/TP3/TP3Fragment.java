package com.hajar.tpss.ui.TP3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.hajar.tpss.R;

public class TP3Fragment extends Fragment {

    private EditText Prenom, name, Phone, Email;
    private Button btnAjouter, btnAfficher;

    public static TP3Fragment newInstance() {
        return new TP3Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_p3, container, false);

        Prenom = view.findViewById(R.id.Prenom);
        name = view.findViewById(R.id.name);
        Phone = view.findViewById(R.id.Phone);
        Email = view.findViewById(R.id.Email);
        btnAjouter = view.findViewById(R.id.button);
        btnAfficher = view.findViewById(R.id.button2);

        btnAjouter.setOnClickListener(v -> Ajouter());


        btnAfficher.setOnClickListener(v -> Afficher());

        return view;
    }

    public void Ajouter() {
        String PrenomValue = Prenom.getText().toString().trim();
        String nameValue = name.getText().toString().trim();
        String PhoneValue = Phone.getText().toString().trim();
        String EmailValue = Email.getText().toString().trim();

        if (PrenomValue.isEmpty() || nameValue.isEmpty() || PhoneValue.isEmpty() || EmailValue.isEmpty()) {
            Toast.makeText(requireContext(), "Tous les champs sont obligatoires", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = DataBaseHelper.getInstance(requireContext()).getWritableDatabase();
        db.execSQL("INSERT INTO contacts (Prenom, name, Phone, Email) VALUES (?, ?, ?, ?)",
                new String[]{PrenomValue, nameValue, PhoneValue, EmailValue});

        Toast.makeText(requireContext(), "Contact ajouté", Toast.LENGTH_SHORT).show();

        Prenom.setText("");
        name.setText("");
        Phone.setText("");
        Email.setText("");
    }

    public void Afficher() {
        Intent intent = new Intent(requireActivity(), MainActivity2.class);
        startActivity(intent);
    }
}

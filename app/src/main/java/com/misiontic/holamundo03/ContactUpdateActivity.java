package com.misiontic.holamundo03;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.misiontic.holamundo03.db.MySQLiteHelper;

public class ContactUpdateActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etLastname;
    private EditText etAddress;
    private EditText etPhone;
    private EditText etBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_update);

        int personId = getIntent().getIntExtra("person", 0);

        etName = findViewById(R.id.etUpdatePersonName);
        etLastname = findViewById(R.id.etUpdateLastname);
        etAddress = findViewById(R.id.etUpdateAddress);
        etPhone =findViewById(R.id.etUpdateTelephone);
        etBirthday = findViewById(R.id.etUpdateBirthday);

        cargarContacto(personId);
    }

    public void cargarContacto(int personId) {
        MySQLiteHelper conexion_bd = new MySQLiteHelper(this);
        String sentence = "SELECT * FROM personas WHERE _id = ?";
        String[] params = new String[]{String.valueOf(personId)};
        Cursor resultado = conexion_bd.getData(sentence, params);
        resultado.moveToFirst();

        etName.setText(resultado.getString(1));
        etLastname.setText(resultado.getString(2));
        etAddress.setText(resultado.getString(3));
        etPhone.setText(resultado.getString(4));
        etBirthday.setText(resultado.getString(5));

    }

}
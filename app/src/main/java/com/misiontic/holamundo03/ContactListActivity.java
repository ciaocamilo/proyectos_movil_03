package com.misiontic.holamundo03;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.misiontic.holamundo03.db.MySQLiteHelper;
import com.misiontic.holamundo03.listview.ContactListViewAdapter;

import java.util.ArrayList;

public class ContactListActivity extends AppCompatActivity {

    private ArrayList<String> contactList;
    private static ListView listView;
    private static ContactListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        contactList = new ArrayList<>();
        listView = findViewById(R.id.contactList);

        showContacts();
    }

    public void showContacts() {
        int id;
        String nombre;
        String apellidos;

        MySQLiteHelper conexion_bd = new MySQLiteHelper(this);
        String sentence = "SELECT * FROM personas";

        Cursor resultados = conexion_bd.getData(sentence, null);

        try {

            resultados.moveToFirst();
            do {
                id  = resultados.getInt(0);
                int indice = resultados.getColumnIndex("nombres");
                nombre = resultados.getString(indice);
                apellidos = resultados.getString(2);
                // Toast.makeText(this, id + "-" + nombre + " " + apellidos, Toast.LENGTH_LONG).show();

                // Lista
                contactList.add(id + "-" + nombre + " " + apellidos);
                adapter = new ContactListViewAdapter(this, contactList);
                listView.setAdapter(adapter);
                //

            } while (resultados.moveToNext());

        } catch (Exception e) {
            Toast.makeText(this, "Error al realizar la consulta", Toast.LENGTH_SHORT).show();
        } finally {
            resultados.close();
        }

    }

}
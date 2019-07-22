package com.example.lista_notasactvity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

private NotasAdapter adapter;
    private ArrayList<Nota> notas;
    private ListView lista_notas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notas = new ArrayList<>();
        notas.add(new Nota("hola","que tal"));
        notas.add(new Nota("123","456"));

        adapter =new NotasAdapter();

        lista_notas = (ListView) findViewById(R.id.lista_notas);
        lista_notas.setAdapter(adapter);
    }

    private class NotasAdapter extends ArrayAdapter<Nota>{
         NotasAdapter() {
            super(MainActivity.this, R.layout.item_lista_notas, notas);
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View result = convertView;
            if (result == null){
                LayoutInflater inflater = getLayoutInflater();
                result = inflater.inflate(R.layout.item_lista_notas, parent, false);
            }

            Nota nota = getItem(position);
            TextView titulo = (TextView) result.findViewById(R.id.titulo);
            titulo.setText(nota.getTitulo());
            TextView resumen = (TextView) result.findViewById(R.id.resumen);
            resumen.setText(nota.getTexto());
            return result;
        }
    }

}

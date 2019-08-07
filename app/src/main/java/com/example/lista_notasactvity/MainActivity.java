package com.example.lista_notasactvity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity<intent> extends AppCompatActivity {

    public static final int NUEVA_NOTA = 0;
    public static final int EDITA_NOTA = 1;
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

        lista_notas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                onEditaNota(pos);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case NUEVA_NOTA:
                break;

            case EDITA_NOTA:
                break;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onEditaNota(int pos) {
        Nota nota = notas.get(pos);
        Intent intent = new Intent(this, EditaNotaActivity.class);
        intent.putExtra("titulo",nota.getTitulo());
        intent.putExtra("texto",nota.getTexto());
        startActivityForResult(intent, EDITA_NOTA);
    }

    public void onNuevaNota(View view) {
        Intent intent = new  Intent(this, EditaNotaActivity.class );
        startActivityForResult(intent, NUEVA_NOTA);
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

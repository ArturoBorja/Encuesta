package com.example.encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView listaPregunta;
    List<Pregunta> p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPregunta = findViewById(R.id.List_pregunta);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.23.8.192:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios servicios =retrofit.create(Servicios.class);
        Call<List<Pregunta>> preguntas=servicios.obtenerPreguntas();
        preguntas.enqueue(new Callback<List<Pregunta>>() {
            @Override
            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
                ArrayAdapter<Pregunta> adapter = new ArrayAdapter<>(MainActivity.this,
                        android.R.layout.simple_list_item_1,response.body());
                p = response.body();
                listaPregunta.setAdapter(adapter);
                Log.e("R2","R2");
            }

            @Override
            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
                Log.e("arturo",t.toString());
            }
        });

        listaPregunta.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,ListarRespuestas.class);
                intent.putExtra("id_pregunta",p.get(i).getId());
                startActivity(intent);
            }
        });
    }
}

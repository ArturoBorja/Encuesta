package com.example.encuesta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class ListarRespuestas extends AppCompatActivity {
    ListView listar_respuestas;
    List<Respuestas> r;
    ArrayAdapter<Respuestas> adapter;
    Intent intpregunta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_respuestas);
        listar_respuestas = findViewById(R.id.listar_respuestas);
        intpregunta = getIntent();

        CargarVista();

        listar_respuestas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Retrofit retrofit1 = new Retrofit.Builder()
                        .baseUrl("http://172.23.8.192:8000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Servicios servicios = retrofit1.create(Servicios.class);
                Call<Respuestas> respuestasCall = servicios.votarRespuesta(r.get(i).getId());
                respuestasCall.enqueue(new Callback<Respuestas>() {
                    @Override
                    public void onResponse(Call<Respuestas> call, Response<Respuestas> response) {
                        CargarVista();
                    }

                    @Override
                    public void onFailure(Call<Respuestas> call, Throwable t) {

                    }
                });

            }
        });
    }
    public void CargarVista(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://172.23.8.192:8000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Servicios serv = retrofit.create(Servicios.class);
        Call<List<Respuestas>> resp =serv.obtenerRespuestas(intpregunta.getIntExtra("id_pregunta",1));
        resp.enqueue(new Callback<List<Respuestas>>() {
            @Override
            public void onResponse(Call<List<Respuestas>> call, Response<List<Respuestas>> response) {
                adapter = new ArrayAdapter<>(ListarRespuestas.this,
                        android.R.layout.simple_list_item_1,response.body());
                r=response.body();
                listar_respuestas.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Respuestas>> call, Throwable t) {

            }
        });
    }
    public void ConsultaBasica(){

    }
}

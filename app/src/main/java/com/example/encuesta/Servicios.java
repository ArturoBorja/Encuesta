package com.example.encuesta;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface Servicios {
    @GET("pregunta/")
    Call<List<Pregunta>> obtenerPreguntas();
    @GET("respuesta/{id}/")
    Call<List<Respuestas>> obtenerRespuestas(@Path("id")int id_pregunta);
    @GET("respuesta_voto/{id}/")
    Call<Respuestas> votarRespuesta(@Path("id")int id_respuesta);
}
